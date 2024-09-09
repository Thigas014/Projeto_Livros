package src.dados;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import src.model.UsuarioModel;

public class BancoDeDados {
    private static final String USERDATABASE = "src/dados/userDatabase.txt";
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

    public void salvarBancoDeDadosUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERDATABASE))) {
            oos.writeObject(bancoDeDadosUsuarios);
            System.out.println("Banco de dados de usuários salvo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
