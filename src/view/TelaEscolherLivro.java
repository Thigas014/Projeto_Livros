package src.view;

import src.controller.GerenciamentoLivrosController;
import src.controller.MenuController;
import src.controller.NavegadorDeTelas;
import src.model.LivroModel;
import src.util.CampoPesquisa;
import src.util.PlaceholderTextField;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaEscolherLivro extends CampoPesquisa{
    private GerenciamentoLivrosController controller;
    private JPanel livrosPanel;
    private PlaceholderTextField pesquisaField;
    private JButton pesquisarButton;
    private JButton limparFiltroButton;
    private JButton voltarButton;

    public TelaEscolherLivro(GerenciamentoLivrosController controller) {
        this.controller = controller;
    }

    public void mostrarTela(JFrame frame) {
        frame.setTitle("Escolher Livro");
      
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Painel de pesquisa
        JPanel pesquisaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel pesquisaLabel = new JLabel("Pesquisar:");
        pesquisaField = new PlaceholderTextField("Pesquise por Autor, Genêro ou Título",20);
        pesquisarButton = new JButton("Pesquisar");
        pesquisaPanel.setBackground(Color.GRAY);
        pesquisaPanel.add(pesquisaLabel);
        pesquisaPanel.add(pesquisaField);
        pesquisaPanel.add(pesquisarButton);

        // Painel de livros
        livrosPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(livrosPanel);
        mainPanel.add(pesquisaPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        limparFiltroButton = new JButton("Limpar Filtro");
        voltarButton = new JButton("Voltar");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(limparFiltroButton);
        buttonPanel.add(voltarButton);
        buttonPanel.setBackground(Color.GRAY);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);

        atualizarListaDeLivros(controller.getLivrosFiltrados());

        // Ações dos botões
        pesquisarButton.addActionListener(e -> {
            if (isCampoDePesquisaVazio(pesquisaField)) {
                mostrarAvisoCampoVazio();
            } else {
                controller.filtrarLivros(pesquisaField.getText().trim());
                atualizarListaDeLivros(controller.getLivrosFiltrados());
            }
        });

        limparFiltroButton.addActionListener(e -> {
            if (isCampoDePesquisaVazio(pesquisaField)) {
                mostrarAvisoCampoJaVazio();
            } else {
                pesquisaField.setText("");
                controller.limparFiltro();
                atualizarListaDeLivros(controller.getLivrosFiltrados());
            }

        });

        voltarButton.addActionListener(e -> NavegadorDeTelas.mostrarTelaMenu());
    }

    private void atualizarListaDeLivros(List<LivroModel> livrosFiltrados) {
        livrosPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;
        for (LivroModel livro : livrosFiltrados) {
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

            JLabel autorLabel = new JLabel("Autor(es): " + livro.getAutor());
            livroGbc.gridy = 1;
            livroPanel.add(autorLabel, livroGbc);

            JLabel generoLabel = new JLabel("Gênero(s): " + livro.getGenero());
            livroGbc.gridy = 2;
            livroPanel.add(generoLabel, livroGbc);

            gbc.gridx = 0;
            gbc.gridy = y++;
            livrosPanel.add(livroPanel, gbc);

            livroPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja escolher esse livro: " + 
                    livro.getTitulo()  + "?", "Escolher Livro", JOptionPane.YES_NO_OPTION);

                    if(confirmacao == JOptionPane.YES_NO_OPTION){
                        controller.abrirUrl(livro.getUrl());
                        MenuController.adicionarAoHistorico(livro);
                        JOptionPane.showMessageDialog(null, "Livro adicionado ao histórico: " + livro.getTitulo(), "Adicionado ao histórico", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    livroPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    livroPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
        }

        livrosPanel.revalidate();
        livrosPanel.repaint();
    }
}
