package src.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro {

    public static void mostrarTelaCadastro() {
        JFrame frame = new JFrame("Cadastro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        JPanel painelCadastro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        painelCadastro.setBackground(Color.gray);

        JLabel rotuloNovoUsuario = new JLabel("Novo Usuário:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCadastro.add(rotuloNovoUsuario, gbc);
        JTextField textoNovoUsuario = new JTextField(20);
        gbc.gridx = 1;
        painelCadastro.add(textoNovoUsuario, gbc);

        JLabel rotuloNovaSenha = new JLabel("Nova Senha:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCadastro.add(rotuloNovaSenha, gbc);
        JPasswordField textoNovaSenha = new JPasswordField(20);
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

        botaoCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String novoUsuario = textoNovoUsuario.getText();
                String novaSenha = new String(textoNovaSenha.getPassword());

                if (!novoUsuario.isEmpty() && !novaSenha.isEmpty() && !TelaLogin.bancoDeDadosUsuarios.containsKey(novoUsuario)) {
                    TelaLogin.bancoDeDadosUsuarios.put(novoUsuario, novaSenha);
                    TelaLogin.salvarBancoDeDadosUsuarios();
                    JOptionPane.showMessageDialog(frame, "Cadastro bem-sucedido!");
                    frame.dispose();
                    TelaLogin.mostrarTelaLogin();
                } else {
                    JOptionPane.showMessageDialog(frame, "Nome de usuário já existente ou campos vazios.");
                }
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                TelaLogin.mostrarTelaLogin();
            }
        });

        frame.add(painelCadastro);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
