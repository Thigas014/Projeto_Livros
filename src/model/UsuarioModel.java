package src.model;

import java.io.Serializable;

public class UsuarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nomeUsuario;
    private String senha;

    public UsuarioModel(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    // Métodos getters
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }
}
