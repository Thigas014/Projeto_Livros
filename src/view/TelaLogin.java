package src.view;

import javax.swing.*;

import src.controller.AutenticacaoController;
import src.controller.NavegadorDeTelas;
import src.model.UsuarioModel;
import src.util.*;

import java.awt.*;

public class TelaLogin {
    private AutenticacaoController controller;

    // O controlador é agora injetado no construtor
    public TelaLogin(AutenticacaoController controller) {
        this.controller = controller;
    }
    
    public void mostrarTela(JFrame frame) {
        frame.setTitle("Tela de Login");
        
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        frame.add(painelPrincipal);

        JPanel painelCentro = new JPanel(new GridBagLayout());
        painelPrincipal.add(painelCentro, BorderLayout.CENTER);
        painelCentro.setBackground(Color.gray);

        adicionarComponentes(painelCentro, frame);
    }

    private void adicionarComponentes(JPanel painel, JFrame frame) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel rotuloUsuario = new JLabel("Usuário:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(rotuloUsuario, gbc);
        
        PlaceholderTextField textoUsuario = new PlaceholderTextField("Digite seu nome de usuário", 20);
        gbc.gridx = 1;
        painel.add(textoUsuario, gbc);

        JLabel rotuloSenha = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(rotuloSenha, gbc);
        
        PlaceholderPasswordField textoSenha = new PlaceholderPasswordField("Digite sua senha", 20);
        gbc.gridx = 1;
        painel.add(textoSenha, gbc);

        JButton botaoLogin = new JButton("Entrar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        painel.add(botaoLogin, gbc);

        JButton botaoCadastro = new JButton("Cadastro");
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        painel.add(botaoCadastro, gbc);

        botaoLogin.addActionListener(e -> {
            String usuario = textoUsuario.getText().trim();
            String senha = new String(textoSenha.getPassword()).trim();
        
            // Tratando o placeholder também
            if ((usuario.isEmpty() || usuario.equals(textoUsuario.getPlaceholder())) && (senha.isEmpty() || senha.equals(textoSenha.getPlaceholder()))) {
                JOptionPane.showMessageDialog(painel, "Campo de Usuário e Senha vazios", "Erro de Login!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (senha.isEmpty() || senha.equals(textoSenha.getPlaceholder())) {
                JOptionPane.showMessageDialog(painel, "Campo de Senha vazio", "Erro de Login!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (usuario.isEmpty() || usuario.equals(textoUsuario.getPlaceholder())) {
                JOptionPane.showMessageDialog(painel, "Campo de Usuário vazio", "Erro de Login!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // Verificar credenciais e obter o objeto UsuarioModel
            UsuarioModel usuarioLogado = controller.verificarCredenciais(usuario, senha);
        
            if (usuarioLogado != null) {
                JOptionPane.showMessageDialog(painel, "Bem-vindo: " + usuario + "!", "Login bem-sucedido!", JOptionPane.INFORMATION_MESSAGE);
                
                // Define o usuário logado no NavegadorDeTelas
                NavegadorDeTelas.setUsuarioAtual(usuarioLogado);
                
                frame.dispose();
                NavegadorDeTelas.mostrarTelaMenu(); // Navegação centralizada
            } else {
                JOptionPane.showMessageDialog(painel, "Credenciais inválidas", "Erro de Login!", JOptionPane.ERROR_MESSAGE);
            }
        });
        


        botaoCadastro.addActionListener(e -> {
            frame.dispose();
            NavegadorDeTelas.mostrarTelaCadastro(); // Navegação centralizada
        });
    }
}