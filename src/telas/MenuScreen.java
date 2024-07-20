package src.telas;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MenuScreen {
    // Constantes que armazenam os nomes dos arquivos para o banco de dados e histórico
    private static final String BOOKBATABESE = "src/dados/bookDatabase.ser";
    private static final String HISTORYDATABESE = "src/dados/historyDatabase.ser";

    static List<Livro> livros = new ArrayList<>();
    static List<Livro> historico = new ArrayList<>();

    public static void mostrarTelaMenu() {
        carregarBancoDeDadosLivros();
        carregarHistoricoLivros();

        JFrame menuFrame = new JFrame("Menu");
        menuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        menuFrame.setSize(800, 500); 
        menuFrame.setResizable(false);
        
        menuFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salvarBancoDeDadosLivros();
                System.exit(0);
            }
        });

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 1));
        menuPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        menuPanel.setBackground(Color.GRAY);

        JButton visualizarLivrosButton = new JButton("Ver Livros");
        JButton adicionarLivroButton = new JButton("Adicionar Livro");
        JButton escolherLivroButton = new JButton("Escolher Livro");
        JButton visualizarHistoricoButton = new JButton("Ver Histórico");
        JButton removerLivroButton = new JButton("Remover Livro");
        JButton sairButton = new JButton("Sair");

        Dimension buttonSize = new Dimension(200, 30);
        visualizarLivrosButton.setPreferredSize(buttonSize);
        adicionarLivroButton.setPreferredSize(buttonSize);
        escolherLivroButton.setPreferredSize(buttonSize);
        visualizarHistoricoButton.setPreferredSize(buttonSize);
        removerLivroButton.setPreferredSize(buttonSize);
        sairButton.setPreferredSize(buttonSize);

        menuPanel.add(visualizarLivrosButton);
        menuPanel.add(adicionarLivroButton);
        menuPanel.add(escolherLivroButton);
        menuPanel.add(visualizarHistoricoButton);
        menuPanel.add(removerLivroButton);
        menuPanel.add(sairButton);

        menuFrame.add(menuPanel);

        visualizarLivrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose(); 
                VerLivrosTela.mostrarTelaVerLivros(livros); 
            }
        });

        adicionarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                AdicionarLivroTela.adicionarLivro(); 
            }
        });

        escolherLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                EscolherLivroTela.mostrarTelaEscolherLivro(livros, historico); 
            }
        });

        visualizarHistoricoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                VerHistoricoTela.mostrarHistorico();  
            }
        });

        removerLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                RemoverLivroTela.mostrarTelaRemoverLivro(livros); 
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaSair.mostrarTelaSair(menuFrame); 
            }
        });

        menuFrame.setVisible(true);
    }


    @SuppressWarnings("unchecked")
    public static void carregarBancoDeDadosLivros() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKBATABESE))) {
            livros = (ArrayList<Livro>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de banco de dados de livros não encontrado. Um novo arquivo será criado.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void salvarBancoDeDadosLivros() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKBATABESE))) {
            oos.writeObject(livros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void carregarHistoricoLivros() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HISTORYDATABESE))) {
            historico = (ArrayList<Livro>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de banco de dados de histórico não encontrado. Um novo arquivo será criado.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void salvarHistoricoLivros() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HISTORYDATABESE))) {
            oos.writeObject(historico);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}












