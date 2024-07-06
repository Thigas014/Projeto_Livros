import java.text.Normalizer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class EscolherLivroTela {

    private static List<Livro> livros;
    private static List<Livro> livrosFiltrados;
    private static List<Livro> historicoLivros;

    public static void mostrarTelaEscolherLivro(List<Livro> livrosDisponiveis, List<Livro> historicoAtual) {
        livros = livrosDisponiveis;
        livrosFiltrados = new ArrayList<>(livros);
        historicoLivros = historicoAtual;

        JFrame escolherLivroFrame = new JFrame("Escolher Livro");
        escolherLivroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        escolherLivroFrame.setSize(800, 500);
        escolherLivroFrame.setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel pesquisaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel pesquisaLabel = new JLabel("Pesquisar:");
        JTextField pesquisaField = new JTextField(20);
        JButton pesquisarButton = new JButton("Pesquisar");
        pesquisaPanel.setBackground(Color.GRAY);

        pesquisaPanel.add(pesquisaLabel);
        pesquisaPanel.add(pesquisaField);
        pesquisaPanel.add(pesquisarButton);

        JPanel livrosPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(livrosPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        mainPanel.add(pesquisaPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton limparFiltroButton = new JButton("Limpar Filtro"); 
        JButton voltarButton = new JButton("Voltar");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(limparFiltroButton); 
        buttonPanel.add(voltarButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setBackground(Color.GRAY);

        escolherLivroFrame.add(mainPanel);
        escolherLivroFrame.setVisible(true);

        atualizarListaDeLivros(livrosPanel, gbc);

        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String termoDePesquisa = normalizarTexto(pesquisaField.getText());
                livrosFiltrados.clear();
                for (Livro livro : livros) {
                    if (normalizarTexto(livro.getTitulo()).contains(termoDePesquisa) ||
                            normalizarTexto(livro.getAutor()).contains(termoDePesquisa) ||
                            normalizarTexto(livro.getGenero()).contains(termoDePesquisa)) {
                        livrosFiltrados.add(livro);
                    }
                }
                atualizarListaDeLivros(livrosPanel, gbc);
            }
        });

    
        limparFiltroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                livrosFiltrados.clear(); 
                livrosFiltrados.addAll(livros); 
                pesquisaField.setText(""); 
                atualizarListaDeLivros(livrosPanel, gbc); 
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                escolherLivroFrame.dispose();
                MenuScreen.mostrarTelaMenu();
            }
        });
    }

    private static void atualizarListaDeLivros(JPanel livrosPanel, GridBagConstraints gbc) {
        livrosPanel.removeAll();

        int y = 0;
        for (Livro livro : livrosFiltrados) {
            JPanel livroPanel = new JPanel(new GridBagLayout());
            GridBagConstraints livroGbc = new GridBagConstraints();
            livroGbc.insets = new Insets(5, 5, 5, 5);
            livroPanel.setBackground(Color.white);
            livroPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel imagemLabel = new JLabel();
            if (livro.getImagem() != null) {
                imagemLabel.setIcon((Icon) livro.getImagem());
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

            gbc.gridx = 0;
            gbc.gridy = y++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            livrosPanel.add(livroPanel, gbc);

            livroPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    abrirUrl(livro.getUrl());
                    adicionarAoHistorico(livro);
                    MenuScreen.salvarHistoricoLivros();
                    JOptionPane.showMessageDialog(null, "Livro adicionado ao histórico: " + livro.getTitulo());
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    livroPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    livroPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
        }

        livrosPanel.revalidate();
        livrosPanel.repaint();
    }

    private static void abrirUrl(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao abrir a URL.");
        }
    }

    private static void adicionarAoHistorico(Livro livro) {
        historicoLivros.add(livro);
    }

    private static String normalizarTexto(String texto) {
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        textoNormalizado = textoNormalizado.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return textoNormalizado.toLowerCase();
    }
}



