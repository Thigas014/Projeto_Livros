package src.controller;

import bd.BancoDeDados;
import src.model.UsuarioModel;

public class AutenticacaoController {
    private BancoDeDados bancoDeDados;
    private UsuarioModel usuarioLogado;

    public AutenticacaoController() {
        this.bancoDeDados = new BancoDeDados();
    }

    // Verifica credenciais e retorna o objeto UsuarioModel se as credenciais forem válidas
    public UsuarioModel verificarCredenciais(String usuario, String senha) {
        UsuarioModel usuarioExistente = bancoDeDados.getUsuario(usuario);
        if (usuarioExistente != null && usuarioExistente.getSenha().equals(senha)) {
            usuarioLogado = usuarioExistente; // Armazena o usuário logado
            return usuarioExistente; // Retorna o objeto UsuarioModel
        }
        return null; // Retorna null se o login falhar
    }

    public boolean cadastrarUsuario(String usuario, String senha) {
        if (usuario.isEmpty() || senha.isEmpty()) {
            return false; // Indica falha no cadastro
        }

        if (bancoDeDados.getUsuario(usuario) != null) {
            return false; // Indica que o usuário já existe
        }

        UsuarioModel novoUsuario = new UsuarioModel(usuario, senha);
        bancoDeDados.salvarUsuario(novoUsuario);
        return true; // Cadastro bem-sucedido
    }

    public UsuarioModel getUsuarioLogado() {
        return usuarioLogado;
    }
}
