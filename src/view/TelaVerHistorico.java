package src.view;

import src.controller.NavegadorDeTelas;
import src.controller.VerHistoricoController;
import src.model.LivroModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TelaVerHistorico {
    private VerHistoricoController controller;
    private JPanel livrosPanel;

    public TelaVerHistorico(VerHistoricoController controller) {
        this.controller = controller;
    }

    public void mostrarTela(JFrame frame) {
        frame.setTitle("Histórico de Leitura");
        frame.setSize(800, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().removeAll(); // Limpa o conteúdo anterior

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Painel de botões
        JButton limparHistoricoButton = new JButton("Limpar Histórico");
        limparHistoricoButton.addActionListener(e -> {
            List<LivroModel> historico = controller.getHistorico();
            int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza de que limpar o historico?", "Limpar Historico", 
            JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_NO_OPTION){
                if (historico.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Historico já esta limpo", "Aviso", JOptionPane.WARNING_MESSAGE);

                }else{
                    JOptionPane.showMessageDialog(null, "Histórico limpo!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    controller.limparHistorico();
                    NavegadorDeTelas.mostrarTelaMenu();
                }
            }
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> NavegadorDeTelas.mostrarTelaMenu());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(limparHistoricoButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(voltarButton);
        buttonPanel.add(Box.createVerticalGlue());

        // Painel de livros
        livrosPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(livrosPanel);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        scrollPane.getViewport().setBackground(Color.WHITE);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        frame.add(mainPanel);
        frame.setVisible(true);

        atualizarListaDeLivros(controller.getHistorico());
    }

    private void atualizarListaDeLivros(List<LivroModel> historico) {
        livrosPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;
        for (LivroModel livro : historico) {
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

            JLabel autorLabel = new JLabel("Autor(es): " + livro.getAutor());
            gbc.gridy = 1;
            livroPanel.add(autorLabel, gbc);

            JLabel generoLabel = new JLabel("Gênero(s): " + livro.getGenero());
            gbc.gridy = 2;
            livroPanel.add(generoLabel, gbc);

            gbc.gridx = 0;
            gbc.gridy = y++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            livrosPanel.add(livroPanel, gbc);
        }

        livrosPanel.revalidate();
        livrosPanel.repaint();
    }
}
