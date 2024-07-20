package src.telas;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TelaLogin {

    private static final String USERDATABASE = "src/dados/userDatabase.ser";
    private static Map<String, String> bancoDeDadosUsuarios = new HashMap<>();

    // Método principal para carregar dados e mostrar a tela de login
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            carregarBancoDeDadosUsuarios(); // Carrega os dados dos usuários do arquivo
            mostrarTelaLogin(); // Exibe a tela de login
        });
    }

    // Método para exibir a tela de login
    public static void mostrarTelaLogin() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500); // Definindo o tamanho da janela para 800x500 pixels

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        frame.add(painelPrincipal);
        

        JPanel painelCentro = new JPanel(new GridBagLayout());
        painelPrincipal.add(painelCentro, BorderLayout.CENTER);
        painelCentro.setBackground(Color.gray);

        adicionarComponentes(painelCentro, frame); // Adiciona componentes (labels, campos de texto, botões) ao painel

        // Centraliza a janela na tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        frame.setVisible(true);
        frame.setResizable(false); // Impede redimensionamento da janela
    }

    // Método para adicionar componentes à tela de login
    private static void adicionarComponentes(JPanel painel, JFrame frame) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes

        // Rótulo e campo de texto para o usuário
        JLabel rotuloUsuario = new JLabel("Usuário:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(rotuloUsuario, gbc);
        JTextField textoUsuario = new JTextField(20);
        gbc.gridx = 1;
        painel.add(textoUsuario, gbc);

        // Rótulo e campo de senha
        JLabel rotuloSenha = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(rotuloSenha, gbc);
        JPasswordField textoSenha = new JPasswordField(20);
        gbc.gridx = 1;
        painel.add(textoSenha, gbc);

        // Botão para realizar o login
        JButton botaoLogin = new JButton("Entrar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span duas colunas
        gbc.anchor = GridBagConstraints.CENTER; // Alinha ao centro
        painel.add(botaoLogin, gbc);

        // Botão para abrir a tela de cadastro
        JButton botaoCadastro = new JButton("Cadastro");
        gbc.gridy = 3;
        gbc.gridwidth = 1; // Resetando o gridwidth
        painel.add(botaoCadastro, gbc);

        // Ação do botão de login
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textoUsuario.getText();
                String senha = new String(textoSenha.getPassword());
                // Verifica se as credenciais são válidas
                if (verificarCredenciais(usuario, senha)) {
                    JOptionPane.showMessageDialog(painel, "Login bem-sucedido!");
                    frame.dispose(); // Fecha a tela de login
                    MenuScreen.mostrarTelaMenu(); // Exibe o menu principal
                } else {
                    JOptionPane.showMessageDialog(painel, "Credenciais inválidas.");
                }
            }
        });

        // Ação do botão de cadastro
        botaoCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDialogoCadastro(frame); // Abre o diálogo para cadastro de novo usuário
            }
        });
    }

    // Método para verificar se as credenciais são válidas
    private static boolean verificarCredenciais(String usuario, String senha) {
        return senha.equals(bancoDeDadosUsuarios.get(usuario));
    }

    // Método para mostrar o diálogo de cadastro de novo usuário
    private static void mostrarDialogoCadastro(JFrame parentFrame) {
        JDialog dialogoCadastro = new JDialog(parentFrame, "Cadastro", true); // Janela de diálogo modal
        dialogoCadastro.setSize(800, 500); // Tamanho do diálogo para 800x500 pixels
        dialogoCadastro.setLocationRelativeTo(parentFrame); // Centraliza o diálogo em relação ao JFrame pai

        JPanel painelCadastro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes
        painelCadastro.setBackground(Color.gray);

        // Rótulo e campo de texto para o novo usuário
        JLabel rotuloNovoUsuario = new JLabel("Novo Usuário:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCadastro.add(rotuloNovoUsuario, gbc);
        JTextField textoNovoUsuario = new JTextField(20);
        gbc.gridx = 1;
        painelCadastro.add(textoNovoUsuario, gbc);

        // Rótulo e campo de senha para o novo usuário
        JLabel rotuloNovaSenha = new JLabel("Nova Senha:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCadastro.add(rotuloNovaSenha, gbc);
        JPasswordField textoNovaSenha = new JPasswordField(20);
        gbc.gridx = 1;
        painelCadastro.add(textoNovaSenha, gbc);

        // Botão para cadastrar novo usuário
        JButton botaoCadastro = new JButton("Cadastrar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span duas colunas
        gbc.anchor = GridBagConstraints.CENTER; // Alinha ao centro
        painelCadastro.add(botaoCadastro, gbc);

        // Botão para voltar ao login
        JButton botaoVoltar = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span duas colunas
        gbc.anchor = GridBagConstraints.CENTER; // Alinha ao centro
        painelCadastro.add(botaoVoltar, gbc);

        // Ação do botão de cadastrar novo usuário
        botaoCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String novoUsuario = textoNovoUsuario.getText();
                String novaSenha = new String(textoNovaSenha.getPassword());
                // Verifica se os campos não estão vazios e se o usuário já não existe
                if (!novoUsuario.isEmpty() && !novaSenha.isEmpty() && !bancoDeDadosUsuarios.containsKey(novoUsuario)) {
                    bancoDeDadosUsuarios.put(novoUsuario, novaSenha); // Adiciona novo usuário ao banco de dados
                    salvarBancoDeDadosUsuarios(); // Salva o banco de dados atualizado
                    JOptionPane.showMessageDialog(dialogoCadastro, "Cadastro bem-sucedido!");
                    dialogoCadastro.dispose(); // Fecha o diálogo de cadastro
                } else {
                    JOptionPane.showMessageDialog(dialogoCadastro, "Nome de usuário já existente ou campos vazios.");
                }
            }
        });

        // Ação do botão de voltar
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogoCadastro.dispose(); // Fecha o diálogo de cadastro
            }
        });

        dialogoCadastro.add(painelCadastro); // Adiciona o painel de cadastro ao diálogo
        dialogoCadastro.setVisible(true); // Torna o diálogo visível
    }

    // Método para carregar o banco de dados de usuários do arquivo serializado
    @SuppressWarnings("unchecked")
    private static void carregarBancoDeDadosUsuarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERDATABASE))) {
            bancoDeDadosUsuarios = (HashMap<String, String>) ois.readObject(); // Lê o objeto do arquivo
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de banco de dados de usuários não encontrado. Um novo arquivo será criado.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para salvar o banco de dados de usuários no arquivo serializado
    private static void salvarBancoDeDadosUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERDATABASE))) {
            oos.writeObject(bancoDeDadosUsuarios); // Escreve o objeto no arquivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
