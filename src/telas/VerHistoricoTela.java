package src.telas;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerHistoricoTela {

    public static void mostrarHistorico() {
        JFrame historicoFrame = new JFrame("Histórico de Livros");
        historicoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        historicoFrame.setSize(800, 500); 
        historicoFrame.setResizable(false); 

        JPanel historicoPanel = new JPanel(new BorderLayout()); 
        

        JButton limparHistoricoButton = new JButton("Limpar Histórico");
        limparHistoricoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuScreen.historico.clear(); 
                MenuScreen.salvarHistoricoLivros(); 
                JOptionPane.showMessageDialog(historicoFrame, "Histórico limpo com sucesso!");
                historicoFrame.dispose(); 
                MenuScreen.mostrarTelaMenu(); 
            }
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historicoFrame.dispose(); 
                MenuScreen.mostrarTelaMenu(); 
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); 
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.add(Box.createVerticalGlue()); // Adiciona um espaço vertical flexível
        buttonPanel.add(limparHistoricoButton); 
        buttonPanel.add(Box.createVerticalStrut(10)); 
        buttonPanel.add(voltarButton); 
        buttonPanel.add(Box.createVerticalGlue()); 

        JPanel livrosPanel = new JPanel(new GridBagLayout()); 
        JScrollPane scrollPane = new JScrollPane(livrosPanel); 
        scrollPane.getViewport().setBackground(Color.WHITE); 

        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        int y = 0; // Contador para a posição vertical dos componentes no livrosPanel
        for (Livro livro : MenuScreen.historico) { 
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

            gbc.gridx = 0;
            gbc.gridy = y++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            livrosPanel.add(livroPanel, gbc); 
        }

        historicoPanel.add(scrollPane, BorderLayout.CENTER); 
        historicoPanel.add(buttonPanel, BorderLayout.EAST); 

        historicoFrame.add(historicoPanel); 
        historicoFrame.setVisible(true); 
    }
}





