package src.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserDatabase.loadUserDatabase();
            mostrarTelaLogin();
        });
    }

    public static void mostrarTelaLogin() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        frame.add(painelPrincipal);

        JPanel painelCentro = new JPanel(new GridBagLayout());
        painelPrincipal.add(painelCentro, BorderLayout.CENTER);
        painelCentro.setBackground(Color.gray);

        adicionarComponentes(painelCentro, frame);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private static void adicionarComponentes(JPanel painel, JFrame frame) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel rotuloUsuario = new JLabel("Usuário:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(rotuloUsuario, gbc);
        JTextField textoUsuario = new JTextField(20);
        gbc.gridx = 1;
        painel.add(textoUsuario, gbc);

        JLabel rotuloSenha = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(rotuloSenha, gbc);
        JPasswordField textoSenha = new JPasswordField(20);
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

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textoUsuario.getText();
                String senha = new String(textoSenha.getPassword());
                if (UserDatabase.verifyCredentials(usuario, senha)) {
                    JOptionPane.showMessageDialog(painel, "Login bem-sucedido!");
                    frame.dispose();
                    MenuScreen.mostrarTelaMenu();
                } else {
                    JOptionPane.showMessageDialog(painel, "Credenciais inválidas.");
                }
            }
        });

        botaoCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastro.mostrarDialogoCadastro(frame);
            }
        });
    }
}
