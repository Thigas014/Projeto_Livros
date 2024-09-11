package bd;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import src.model.UsuarioModel;

public class BancoDeDados {
    // Caminho atualizado para o diretório de execução, usando um caminho relativo
    private static final String USERDATABASE = "dados/userDatabase.txt";
    private Map<String, UsuarioModel> bancoDeDadosUsuarios;

    public BancoDeDados() {
        bancoDeDadosUsuarios = carregarBancoDeDadosUsuarios();
    }

    // Carrega o banco de dados de usuários a partir do arquivo serializado
    @SuppressWarnings("unchecked")
    private Map<String, UsuarioModel> carregarBancoDeDadosUsuarios() {
        File arquivo = new File(USERDATABASE);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de banco de dados de usuários não encontrado. Um novo arquivo será criado.");
            return new HashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (HashMap<String, UsuarioModel>) ois.readObject();
        } catch (EOFException e) {
            System.out.println("Arquivo de banco de dados de usuários está vazio.");
            return new HashMap<>();  // Se vazio, retorna um novo mapa
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();  // Se ocorrer um erro, retorna um mapa vazio
        }
    }

    // Salva o banco de dados de usuários em arquivo
    public void salvarBancoDeDadosUsuarios() {
        File diretorio = new File("dados");
        if (!diretorio.exists()) {
            diretorio.mkdirs();  // Cria o diretório se não existir
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERDATABASE))) {
            oos.writeObject(bancoDeDadosUsuarios);
            System.out.println("Banco de dados de usuários salvo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Salva um único usuário no banco de dados e atualiza o arquivo
    public void salvarUsuario(UsuarioModel usuarioModel) {
        bancoDeDadosUsuarios.put(usuarioModel.getNomeUsuario(), usuarioModel);
        salvarBancoDeDadosUsuarios();
    }

    // Retorna o banco de dados de usuários
    public Map<String, UsuarioModel> getBancoDeDadosUsuarios() {
        return bancoDeDadosUsuarios;
    }

    // Método para obter um usuário pelo nome de usuário
    public UsuarioModel getUsuario(String nomeUsuario) {
        return bancoDeDadosUsuarios.get(nomeUsuario);
    }
}
