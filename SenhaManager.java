import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SenhaManager {
    private static final String ARQUIVO_DADOS_LOGIN = "dados_login.ser";
    private static final Map<String, String> usuariosSenhas = new HashMap<>();

    static {
        carregarDadosLogin();
    }

    private static void carregarDadosLogin() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DADOS_LOGIN))) {
            usuariosSenhas.clear();
            DadosLogin dadosLogin;
            while ((dadosLogin = (DadosLogin) ois.readObject()) != null) {
                usuariosSenhas.put(dadosLogin.getNomeUsuario(), dadosLogin.getSenha());
            }
        } catch (EOFException ignored) {
            // Ignorar, indica o final do arquivo
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar os dados de login: " + e.getMessage());
        }
    }

    public static boolean verificarCredenciais(String nomeUsuario, String senha) {
        String senhaArmazenada = usuariosSenhas.get(nomeUsuario);
        return senhaArmazenada != null && senhaArmazenada.equals(senha);
    }

    public static boolean adicionarUsuario(String nomeUsuario, String senha) {
        if (usuariosSenhas.containsKey(nomeUsuario)) {
            return false; // Já existe um usuário com esse nome
        } else {
            usuariosSenhas.put(nomeUsuario, senha);
            salvarDadosLogin();
            return true;
        }
    }

    private static void salvarDadosLogin() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DADOS_LOGIN))) {
            for (Map.Entry<String, String> entry : usuariosSenhas.entrySet()) {
                oos.writeObject(new DadosLogin(entry.getKey(), entry.getValue()));
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados de login: " + e.getMessage());
        }
    }
}
