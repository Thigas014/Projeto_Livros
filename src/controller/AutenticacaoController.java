package src.controller;

import src.dados.BancoDeDados;
import src.model.UsuarioModel;

public class AutenticacaoController {
    private BancoDeDados bancoDeDados;

    public AutenticacaoController() {
        this.bancoDeDados = new BancoDeDados();
    }

    public boolean verificarCredenciais(String usuario, String senha) {
        String senhaArmazenada = bancoDeDados.getBancoDeDadosUsuarios().get(usuario);
        return senhaArmazenada != null && senhaArmazenada.equals(senha);
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
