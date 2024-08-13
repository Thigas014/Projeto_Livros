package src.telas;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private static final String USERDATABASE = "Projeto_Livros-telas/src/dados/userDatabase.ser";
    private static Map<String, String> bancoDeDadosUsuarios = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static void loadUserDatabase() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERDATABASE))) {
            bancoDeDadosUsuarios = (HashMap<String, String>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de banco de dados de usuários não encontrado. Um novo arquivo será criado.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveUserDatabase() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERDATABASE))) {
            oos.writeObject(bancoDeDadosUsuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean verifyCredentials(String usuario, String senha) {
        return senha.equals(bancoDeDadosUsuarios.get(usuario));
    }

    public static boolean userExists(String usuario) {
        return bancoDeDadosUsuarios.containsKey(usuario);
    }

    public static void addUser(String usuario, String senha) {
        bancoDeDadosUsuarios.put(usuario, senha);
    }
}
