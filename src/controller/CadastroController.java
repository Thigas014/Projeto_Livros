package src.controller;

import src.dados.BancoDeDados;
import src.model.UsuarioModel;

public class CadastroController {
    private BancoDeDados bancoDeDados;

    public CadastroController() {
        this.bancoDeDados = new BancoDeDados();
    }

    public boolean cadastrarUsuario(String usuario, String senha) {
        if (usuario.isEmpty() || senha.isEmpty()) {
            return false; // Indica falha no cadastro
        }

        if (bancoDeDados.getBancoDeDadosUsuarios().containsKey(usuario)) {
            return false; // Indica que o usuário já existe
        }

        bancoDeDados.salvarUsuario(new UsuarioModel(usuario, senha));
        return true; // Cadastro bem-sucedido
    }
}
