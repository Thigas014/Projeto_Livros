package src.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TelaLogin {

    private static final String USERDATABASE = "Projeto_Livros-telas/src/dados/userDatabase.ser";
    static Map<String, String> bancoDeDadosUsuarios = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            carregarBancoDeDadosUsuarios();
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
                if (verificarCredenciais(usuario, senha)) {
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
                frame.dispose();
                TelaCadastro.mostrarTelaCadastro();
            }
        });
    }

    private static boolean verificarCredenciais(String usuario, String senha) {
        return senha.equals(bancoDeDadosUsuarios.get(usuario));
    }

    @SuppressWarnings("unchecked")
    private static void carregarBancoDeDadosUsuarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERDATABASE))) {
            bancoDeDadosUsuarios = (HashMap<String, String>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de banco de dados de usuários não encontrado. Um novo arquivo será criado.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void salvarBancoDeDadosUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERDATABASE))) {
            oos.writeObject(bancoDeDadosUsuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
