package src.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class RemoverLivroTela {

    private static List<Livro> livros;
    private static List<Livro> livrosFiltrados;
    private static JPanel livrosPanel;
    private static GridBagConstraints gbc;
    private static JFrame removerLivroFrame; // fazendo isso para de dar erro #$##@

    public static void mostrarTelaRemoverLivro(List<Livro> livrosDisponiveis) {
        livros = livrosDisponiveis;
        livrosFiltrados = new ArrayList<>(livros);

        JFrame removerLivroFrame = new JFrame("Remover Livro");
        removerLivroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removerLivroFrame.setSize(800, 500);
        removerLivroFrame.setResizable(false);
        removerLivroFrame.setLocationRelativeTo(null);

        JPanel removerLivroPanel = new JPanel(new BorderLayout());

        // Campo de pesquisa
        JPanel pesquisaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel pesquisaLabel = new JLabel("Pesquisar:");
        JTextField pesquisaField = new JTextField(20);
        JButton pesquisarButton = new JButton("Pesquisar");
        pesquisaPanel.setBackground(Color.GRAY);

        pesquisaPanel.add(pesquisaLabel);
        pesquisaPanel.add(pesquisaField);
        pesquisaPanel.add(pesquisarButton);

        removerLivroPanel.add(pesquisaPanel, BorderLayout.NORTH);

        // Painel de livros
        livrosPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(livrosPanel);
        scrollPane.getViewport().setBackground(Color.WHITE);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        removerLivroPanel.add(scrollPane, BorderLayout.CENTER);

        // Botão de voltar e limpar
        JButton voltarButton = new JButton("Voltar");
        JButton limparPesquisaButton = new JButton("Limpar Pesquisa");

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerLivroFrame.dispose();
                MenuScreen.mostrarTelaMenu();
            }
        });

        limparPesquisaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisaField.setText("");
                livrosFiltrados.clear();
                livrosFiltrados.addAll(livros);
                atualizarListaDeLivros(livrosPanel, gbc);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.add(limparPesquisaButton);
        buttonPanel.add(voltarButton);

        removerLivroPanel.add(buttonPanel, BorderLayout.SOUTH);

        removerLivroFrame.add(removerLivroPanel);
        removerLivroFrame.setVisible(true);

        atualizarListaDeLivros(livrosPanel, gbc);

        // Ação do botão de pesquisa
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
    }

    private static void atualizarListaDeLivros(JPanel livrosPanel, GridBagConstraints gbc) {
        livrosPanel.removeAll();

        int y = 0;
        for (Livro livro : livrosFiltrados) {
            JPanel livroPanel = new JPanel(new GridBagLayout());
            livroPanel.setBackground(Color.WHITE);
            livroPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel imagemLabel = new JLabel();
            if (livro.getImagem() != null) {
                imagemLabel.setIcon((Icon) livro.getImagem());
            }
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridheight = 3;
            gbc.fill = GridBagConstraints.BOTH;
            livroPanel.add(imagemLabel, gbc);

            JLabel tituloLabel = new JLabel("Título: " + livro.getTitulo());
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            livroPanel.add(tituloLabel, gbc);

            JLabel autorLabel = new JLabel("Autor: " + livro.getAutor());
            gbc.gridy = 1;
            livroPanel.add(autorLabel, gbc);

            JLabel generoLabel = new JLabel("Gênero: " + livro.getGenero());
            gbc.gridy = 2;
            livroPanel.add(generoLabel, gbc);

            JButton removerButton = new JButton("Remover");
            gbc.gridy = 3;
            gbc.gridx = 1;
            livroPanel.add(removerButton, gbc);

            removerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int resposta = JOptionPane.showConfirmDialog(removerLivroFrame, 
                        "Tem certeza que deseja remover o livro?", "Confirmação", JOptionPane.YES_NO_OPTION);

                    if (resposta == JOptionPane.YES_OPTION) {
                        livros.remove(livro);
                        livrosFiltrados.remove(livro); // Remover também da lista filtrada
                        MenuScreen.salvarBancoDeDadosLivros();
                        JOptionPane.showMessageDialog(removerLivroFrame, "Livro removido com sucesso!");
                        atualizarListaDeLivros(livrosPanel, gbc); // Atualiza a lista de livros na tela
                    }
                }
            });

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
