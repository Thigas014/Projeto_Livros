package src.telas;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

public class RemoverLivroTela {

    private static List<Livro> livros;

    public static void mostrarTelaRemoverLivro(List<Livro> livrosParaRemover) {
        livros = livrosParaRemover;

        JFrame removerLivroFrame = new JFrame("Remover Livro");
        removerLivroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removerLivroFrame.setSize(800, 500);
        removerLivroFrame.setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(Color.GRAY);
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Pesquisar");
        JButton limparFiltroButton = new JButton("Limpar Filtro"); 
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(limparFiltroButton); 
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        JPanel livrosPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(livrosPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton voltarButton = new JButton("Voltar");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(voltarButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setBackground(Color.GRAY); 

        removerLivroFrame.add(mainPanel);
        removerLivroFrame.setVisible(true);

        
        atualizarListaDeLivros(livrosPanel, gbc);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoPesquisa = searchField.getText().toLowerCase().trim();
                List<Livro> livrosFiltrados = new ArrayList<>();
                for (Livro livro : livros) {
                    String titulo = livro.getTitulo().toLowerCase();
                    String autor = livro.getAutor().toLowerCase();
                    String genero = livro.getGenero().toLowerCase();

                    
                    if (titulo.contains(textoPesquisa) || autor.contains(textoPesquisa) || genero.contains(textoPesquisa)) {
                        livrosFiltrados.add(livro);
                    }
                }
                
                atualizarListaDeLivros(livrosPanel, gbc, livrosFiltrados);
            }
        });

        
        limparFiltroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchField.setText(""); 
                atualizarListaDeLivros(livrosPanel, gbc); 
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerLivroFrame.dispose();
                MenuScreen.mostrarTelaMenu();
            }
        });
    }

    private static void atualizarListaDeLivros(JPanel livrosPanel, GridBagConstraints gbc) {
        atualizarListaDeLivros(livrosPanel, gbc, livros);
    }

    private static void atualizarListaDeLivros(JPanel livrosPanel, GridBagConstraints gbc, List<Livro> livros) {
        livrosPanel.removeAll();

        int y = 0;
        for (Livro livro : livros) {
            JPanel livroPanel = new JPanel(new GridBagLayout());
            GridBagConstraints livroGbc = new GridBagConstraints();
            livroGbc.insets = new Insets(5, 5, 5, 5);
            livroPanel.setBackground(Color.white);
            livroPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel imagemLabel = new JLabel();
            if (livro.getImagem() != null) {
                imagemLabel.setIcon(livro.getImagem());
            }
            livroGbc.gridx = 0;
            livroGbc.gridy = 0;
            livroGbc.gridheight = 3;
            livroGbc.fill = GridBagConstraints.BOTH;
            livroPanel.add(imagemLabel, livroGbc);

            JLabel tituloLabel = new JLabel("Título: " + livro.getTitulo());
            livroGbc.gridx = 1;
            livroGbc.gridy = 0;
            livroGbc.gridheight = 1;
            livroPanel.add(tituloLabel, livroGbc);

            JLabel autorLabel = new JLabel("Autor: " + livro.getAutor());
            livroGbc.gridy = 1;
            livroPanel.add(autorLabel, livroGbc);

            JLabel generoLabel = new JLabel("Gênero: " + livro.getGenero());
            livroGbc.gridy = 2;
            livroPanel.add(generoLabel, livroGbc);

            JButton removerButton = new JButton("Remover");
            livroGbc.gridx = 2;
            livroGbc.gridy = 0;
            livroGbc.gridheight = 3;
            livroGbc.fill = GridBagConstraints.BOTH;
            livroPanel.add(removerButton, livroGbc);

            gbc.gridx = 0;
            gbc.gridy = y++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            livrosPanel.add(livroPanel, gbc);

            removerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int opcao = JOptionPane.showConfirmDialog(null,
                            "Tem certeza que deseja remover o livro: " + livro.getTitulo() + "?", "Confirmar remoção",
                            JOptionPane.YES_NO_OPTION);
                    if (opcao == JOptionPane.YES_OPTION) {
                        livros.remove(livro);
                        MenuScreen.salvarBancoDeDadosLivros();
                        JOptionPane.showMessageDialog(null, "Livro removido com sucesso!");
                        atualizarListaDeLivros(livrosPanel, gbc, livros); 
                    }
                }
            });
        }

        livrosPanel.revalidate();
        livrosPanel.repaint();
    }
}




