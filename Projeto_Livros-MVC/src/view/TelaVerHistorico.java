package src.view;

import src.controller.MenuController;
import src.controller.NavegadorDeTelas;
import src.model.LivroModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TelaVerHistorico {
    private JPanel livrosPanel;
    private MenuController menuController;

    public TelaVerHistorico(MenuController menuController) {
        this.menuController = menuController;
    }

    public void mostrarTela(JFrame frame) {
        frame.setTitle("Histórico de Leitura");

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Painel de livros com rolagem
        livrosPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(livrosPanel);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        scrollPane.getViewport().setBackground(Color.WHITE);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = criarPainelDeBotoes();
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        // Adiciona o painel principal ao frame
        frame.add(mainPanel);
        frame.setVisible(true);

        // Atualiza a lista de livros no painel de histórico
        atualizarListaDeLivros(menuController.getHistorico());
    }

    private JPanel criarPainelDeBotoes() {
        // Criação dos botões
        JButton limparHistoricoButton = new JButton("Limpar Histórico");
        limparHistoricoButton.addActionListener(e -> limparHistorico());

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> NavegadorDeTelas.mostrarTelaMenu());

        // Painel de botões à direita
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(limparHistoricoButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(voltarButton);
        buttonPanel.add(Box.createVerticalGlue());

        return buttonPanel;
    }

    private void limparHistorico() {
        List<LivroModel> historico = menuController.getHistorico();
        int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja limpar o histórico?", 
                "Limpar Histórico", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_NO_OPTION) {
            if (historico.isEmpty()) {
                JOptionPane.showMessageDialog(null, "O histórico já está limpo", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                menuController.limparHistoricoUsuarioLogado();  // Limpa o histórico do usuário logado
                JOptionPane.showMessageDialog(null, "Histórico limpo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizarListaDeLivros(menuController.getHistorico());
            }
        }
    }

    private void atualizarListaDeLivros(List<LivroModel> historico) {
        livrosPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;
        for (LivroModel livro : historico) {
            JPanel livroPanel = criarPainelLivro(livro);
            gbc.gridx = 0;
            gbc.gridy = y++;
            livrosPanel.add(livroPanel, gbc);
        }

        livrosPanel.revalidate();
        livrosPanel.repaint();
    }

    private JPanel criarPainelLivro(LivroModel livro) {
        JPanel livroPanel = new JPanel(new GridBagLayout());
        livroPanel.setBackground(Color.WHITE);
        livroPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        JLabel imagemLabel = new JLabel();
        if (livro.getImagem() != null) {
            imagemLabel.setIcon((Icon) livro.getImagem());
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        livroPanel.add(imagemLabel, gbc);

        JLabel tituloLabel = new JLabel("Título: " + livro.getTitulo());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        livroPanel.add(tituloLabel, gbc);

        JLabel autorLabel = new JLabel("Autor(es): " + livro.getAutor());
        gbc.gridy = 1;
        livroPanel.add(autorLabel, gbc);

        JLabel generoLabel = new JLabel("Gênero(s): " + livro.getGenero());
        gbc.gridy = 2;
        livroPanel.add(generoLabel, gbc);

         // Botão de remover livro do histórico
        JButton removerButton = new JButton("Remover do Histórico");
        removerButton.addActionListener(e -> {
        int resposta = JOptionPane.showConfirmDialog(removerButton, "Tem certeza que quer limpar do histórico esse livro: " + livro.getTitulo() + "?", "Limpar", JOptionPane.YES_NO_OPTION);
        
        if(resposta == JOptionPane.YES_OPTION){
            menuController.limparLivroEspecifico(livro);
            atualizarListaDeLivros(menuController.getHistorico());
        }
    });
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        livroPanel.add(removerButton, gbc);

        return livroPanel;
    }
}
