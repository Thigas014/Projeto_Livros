package src.view;

import javax.swing.*;

import src.controller.AutenticacaoController;
import src.controller.NavegadorDeTelas;
import src.util.*;

import java.awt.*;

public class TelaCadastro {
    private AutenticacaoController controller;

    public TelaCadastro(AutenticacaoController controller) {
        this.controller = controller;
    }

    public void mostrarTela(JFrame frame) {
        frame.setTitle("Cadastro");
        
        JPanel painelCadastro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        painelCadastro.setBackground(Color.gray);

        JLabel rotuloNovoUsuario = new JLabel("Novo Usuário:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCadastro.add(rotuloNovoUsuario, gbc);
        PlaceholderTextField textoNovoUsuario = new PlaceholderTextField("Digite o nome de usuário",20);
        gbc.gridx = 1;
        painelCadastro.add(textoNovoUsuario, gbc);

        JLabel rotuloNovaSenha = new JLabel("Nova Senha:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCadastro.add(rotuloNovaSenha, gbc);
        PlaceholderPasswordField textoNovaSenha = new PlaceholderPasswordField("Digite uma senha",20);
        gbc.gridx = 1;
        painelCadastro.add(textoNovaSenha, gbc);

        JButton botaoCadastro = new JButton("Cadastrar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        painelCadastro.add(botaoCadastro, gbc);

        JButton botaoVoltar = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        painelCadastro.add(botaoVoltar, gbc);

        botaoCadastro.addActionListener(e -> {
            String novoUsuario = textoNovoUsuario.getText().trim();
            String novaSenha = new String(textoNovaSenha.getPassword()).trim();

            // Verifica se ambos os campos estão vazios tratando o placeholder tbm
            if ((novoUsuario.isEmpty() || novoUsuario.equals(textoNovoUsuario.getPlaceholder())) &&
                    (novaSenha.isEmpty() || novaSenha.equals(textoNovaSenha.getPlaceholder()))) {
                JOptionPane.showMessageDialog(painelCadastro, "Campo de Usuário e Senha vazios", "Erro de Cadastro!", JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (novoUsuario.isEmpty() || novoUsuario.equals(textoNovoUsuario.getPlaceholder())) {
                JOptionPane.showMessageDialog(painelCadastro, "Campo de Usuário vazio", "Erro de Cadastro!", JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (novaSenha.isEmpty() || novaSenha.equals(textoNovaSenha.getPlaceholder())) {
                JOptionPane.showMessageDialog(painelCadastro, "Campo de Senha vazio", "Erro de Cadastro!", JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (controller.cadastrarUsuario(novoUsuario, novaSenha)) {
                JOptionPane.showMessageDialog(painelCadastro, "Cadastro bem-sucedido!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                NavegadorDeTelas.mostrarTelaLogin(); // Navegação centralizada
            } else {
                JOptionPane.showMessageDialog(painelCadastro, "O usuário já existe.", "Erro de Cadastro!", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoVoltar.addActionListener(e -> {
            frame.dispose();
            NavegadorDeTelas.mostrarTelaLogin();
        });

        frame.add(painelCadastro);
    }
}