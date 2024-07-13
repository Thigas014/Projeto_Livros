package src.dados;
import java.io.Serializable;

public class DadosLogin implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nomeUsuario;
    private String senha;

    public DadosLogin(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }
}