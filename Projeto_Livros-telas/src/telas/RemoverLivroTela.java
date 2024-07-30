package src.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RemoverLivroTela {

    public static void mostrarTelaRemoverLivro(List<Livro> livros) {
        JFrame removerLivroFrame = new JFrame("Remover Livro");
        removerLivroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removerLivroFrame.setSize(800, 500);
        removerLivroFrame.setResizable(false);
        removerLivroFrame.setLocationRelativeTo(null);

        JPanel removerLivroPanel = new JPanel(new BorderLayout());

        JPanel livrosPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(livrosPanel);
        scrollPane.getViewport().setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;
        for (Livro livro : livros) {
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
                    livros.remove(livro);
                    MenuScreen.salvarBancoDeDadosLivros();
                    JOptionPane.showMessageDialog(removerLivroFrame, "Livro removido com sucesso!");
                    removerLivroFrame.dispose();
                    MenuScreen.mostrarTelaMenu();
                }
            });

            gbc.gridx = 0;
            gbc.gridy = y++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            livrosPanel.add(livroPanel, gbc);
        }

        removerLivroPanel.add(scrollPane, BorderLayout.CENTER);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerLivroFrame.dispose();
                MenuScreen.mostrarTelaMenu();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.add(voltarButton);

        removerLivroPanel.add(buttonPanel, BorderLayout.SOUTH);

        removerLivroFrame.add(removerLivroPanel);
        removerLivroFrame.setVisible(true);
    }
}
