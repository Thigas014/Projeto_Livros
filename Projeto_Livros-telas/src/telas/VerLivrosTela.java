package src.telas;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class VerLivrosTela {

    private static List<Livro> livros; 
    private static List<Livro> livrosFiltrados; 

    
    public static void mostrarTelaVerLivros(List<Livro> livrosDisponiveis) {
        livros = livrosDisponiveis; 
        livrosFiltrados = new ArrayList<>(livros); // Inicializa a lista de livros filtrados com uma cópia dos livros disponíveis

        JFrame verLivrosFrame = new JFrame("Livros Disponíveis");
        verLivrosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        verLivrosFrame.setSize(800, 500); 
        verLivrosFrame.setResizable(false);
        verLivrosFrame.setLocationRelativeTo(null); 

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
        

        JButton voltarButton = new JButton("Voltar");
        JButton limparFiltroButton = new JButton("Limpar Filtro");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(limparFiltroButton);
        buttonPanel.add(voltarButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH); 
        buttonPanel.setBackground(Color.GRAY); 

        verLivrosFrame.add(mainPanel); 
        verLivrosFrame.setVisible(true); 

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

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verLivrosFrame.dispose(); 
                MenuScreen.mostrarTelaMenu(); 
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
        }

        livrosPanel.revalidate(); 
        livrosPanel.repaint(); 
    }

    // Método para normalizar o texto removendo acentos e convertendo para minúsculas
    private static String normalizarTexto(String texto) {
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        textoNormalizado = textoNormalizado.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return textoNormalizado.toLowerCase();
    }
}
