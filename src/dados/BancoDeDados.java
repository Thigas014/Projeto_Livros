package src.dados;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import src.model.UsuarioModel;

public class BancoDeDados {
    private static final String USERDATABASE = "src/dados/userDatabase.ser";
    private Map<String, String> bancoDeDadosUsuarios;

    public BancoDeDados() {
        bancoDeDadosUsuarios = carregarBancoDeDadosUsuarios();
    }

    // Carrega o banco de dados de usuários a partir do arquivo serializado
    @SuppressWarnings("unchecked")
    private Map<String, String> carregarBancoDeDadosUsuarios() {
        File arquivo = new File(USERDATABASE);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de banco de dados de usuários não encontrado. Um novo arquivo será criado.");
            criarArquivoSeNaoExistir(arquivo);
            return new HashMap<>();  // Retorna um HashMap vazio se o arquivo não existir
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (HashMap<String, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();  // Retorna um HashMap vazio se ocorrer algum erro na leitura
        }
    }

    // Salva o banco de dados de usuários no arquivo serializado
    public void salvarBancoDeDadosUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERDATABASE))) {
            oos.writeObject(bancoDeDadosUsuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para salvar um novo usuário no banco de dados
    public void salvarUsuario(UsuarioModel usuarioModel) {
        bancoDeDadosUsuarios.put(usuarioModel.getUsuario(), usuarioModel.getSenha());
        salvarBancoDeDadosUsuarios(); // Salva o banco de dados após adicionar o novo usuário
    }

    // Cria o arquivo se ele não existir
    private void criarArquivoSeNaoExistir(File arquivo) {
        try {
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs(); // Cria diretórios, se necessário
            }
            if (arquivo.createNewFile()) {
                System.out.println("Novo arquivo de banco de dados criado: " + USERDATABASE);
            }
        } catch (IOException e) {
            System.err.println("Erro ao tentar criar o arquivo de banco de dados: " + e.getMessage());
        }
    }

    // Retorna o banco de dados de usuários
    public Map<String, String> getBancoDeDadosUsuarios() {
        return bancoDeDadosUsuarios;
    }
}
