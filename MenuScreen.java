import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Declaração da classe MenuScreen
public class MenuScreen {

    // Constantes que armazenam os nomes dos arquivos para o banco de dados e histórico
    private static final String BOOK_FILE_NAME = "bookDatabase.ser";
    private static final String HISTORY_FILE_NAME = "historyDatabase.ser";

    // Listas estáticas para armazenar livros e o histórico de livros
    static List<Livro> livros = new ArrayList<>();
    static List<Livro> historico = new ArrayList<>();

    // Método para mostrar a tela do menu
    public static void mostrarTelaMenu() {
        // Carrega os dados dos livros e do histórico
        carregarBancoDeDadosLivros();
        carregarHistoricoLivros();

        // Cria a janela principal do menu
        JFrame menuFrame = new JFrame("Menu");
        menuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        menuFrame.setSize(800, 500); // Definindo o tamanho da janela para 800x500 pixels
        menuFrame.setResizable(false);
        

        // Adiciona um listener para salvar o banco de dados ao fechar o programa
        menuFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salvarBancoDeDadosLivros();
                System.exit(0);
            }
        });

        // Cria um painel para o menu e define o layout
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 1));
        menuPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Adiciona uma borda vazia para espaçamento
        menuPanel.setBackground(Color.GRAY);
        // Cria botões para diferentes funcionalidades
        JButton visualizarLivrosButton = new JButton("Ver Livros");
        JButton adicionarLivroButton = new JButton("Adicionar Livro");
        JButton escolherLivroButton = new JButton("Escolher Livro");
        JButton visualizarHistoricoButton = new JButton("Ver Histórico");
        JButton removerLivroButton = new JButton("Remover Livro");
        JButton sairButton = new JButton("Sair");

        // Define o tamanho preferido para os botões
        Dimension buttonSize = new Dimension(200, 30);
        visualizarLivrosButton.setPreferredSize(buttonSize);
        adicionarLivroButton.setPreferredSize(buttonSize);
        escolherLivroButton.setPreferredSize(buttonSize);
        visualizarHistoricoButton.setPreferredSize(buttonSize);
        removerLivroButton.setPreferredSize(buttonSize);
        sairButton.setPreferredSize(buttonSize);

        // Adiciona os botões ao painel do menu
        menuPanel.add(visualizarLivrosButton);
        menuPanel.add(adicionarLivroButton);
        menuPanel.add(escolherLivroButton);
        menuPanel.add(visualizarHistoricoButton);
        menuPanel.add(removerLivroButton);
        menuPanel.add(sairButton);

        // Adiciona o painel do menu à janela principal
        menuFrame.add(menuPanel);

        // Adiciona listeners aos botões para definir ações ao serem clicados
        visualizarLivrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();  // Fecha a janela atual
                VerLivrosTela.mostrarTelaVerLivros(livros);  // Mostra a tela de visualização de livros
            }
        });

        adicionarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                AdicionarLivroTela.adicionarLivro();  // Mostra a tela para adicionar um livro
            }
        });

        escolherLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                EscolherLivroTela.mostrarTelaEscolherLivro(livros, historico);  // Mostra a tela para escolher um livro
            }
        });

        visualizarHistoricoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                VerHistoricoTela.mostrarHistorico();  // Mostra a tela do histórico
            }
        });

        removerLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                RemoverLivroTela.mostrarTelaRemoverLivro(livros);  // Mostra a tela para remover um livro
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaSair.mostrarTelaSair(menuFrame);  // Mostra a tela de confirmação de saída
            }
        });

        // Torna a janela visível
        menuFrame.setVisible(true);
    }

    // Método para carregar o banco de dados de livros
    @SuppressWarnings("unchecked")
    public static void carregarBancoDeDadosLivros() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOK_FILE_NAME))) {
            livros = (ArrayList<Livro>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de banco de dados de livros não encontrado. Um novo arquivo será criado.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para salvar o banco de dados de livros
    public static void salvarBancoDeDadosLivros() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOK_FILE_NAME))) {
            oos.writeObject(livros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar o histórico de livros
    @SuppressWarnings("unchecked")
    public static void carregarHistoricoLivros() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HISTORY_FILE_NAME))) {
            historico = (ArrayList<Livro>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de banco de dados de histórico não encontrado. Um novo arquivo será criado.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para salvar o histórico de livros
    public static void salvarHistoricoLivros() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HISTORY_FILE_NAME))) {
            oos.writeObject(historico);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}












