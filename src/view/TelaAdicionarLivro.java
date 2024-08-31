package src.view;

import src.controller.NavegadorDeTelas;
import src.controller.AdicionarLivroController;
import src.placeholder.PlaceholderTextField;

import javax.swing.*;
import java.awt.*;

public class TelaAdicionarLivro {
    private AdicionarLivroController controller;
    private PlaceholderTextField tituloField;
    private PlaceholderTextField autorField;
    private PlaceholderTextField generoField;
    private PlaceholderTextField urlField;
    private JLabel imagemSelecionadaLabel;

    public TelaAdicionarLivro(AdicionarLivroController controller) {
        this.controller = controller;
    }

    public void mostrarTela(JFrame frame) {
        frame.setTitle("Adicionar Livro");
        frame.setSize(800, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().removeAll(); // Limpa o conteúdo anterior

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel adicionarLivroPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        adicionarLivroPanel.setBackground(Color.GRAY);

        JLabel tituloLabel = new JLabel("Título:");
        // Usando PlaceholderTextField com placeholder
        tituloField = new PlaceholderTextField("Digite o título do livro", 30);
        gbc.gridx = 0;
        gbc.gridy = 0;
        adicionarLivroPanel.add(tituloLabel, gbc);
        gbc.gridx = 1;
        adicionarLivroPanel.add(tituloField, gbc);

        JLabel autorLabel = new JLabel("Autor(es):");
        // Usando PlaceholderTextField com placeholder
        autorField = new PlaceholderTextField("Digite o(s) autor(es) separados por ;", 30);
        gbc.gridx = 0;
        gbc.gridy = 1;
        adicionarLivroPanel.add(autorLabel, gbc);
        gbc.gridx = 1;
        adicionarLivroPanel.add(autorField, gbc);

        JLabel generoLabel = new JLabel("Gênero(s):");
        // Usando PlaceholderTextField com placeholder
        generoField = new PlaceholderTextField("Digite o(s) gênero(s) separados por ;", 30);
        gbc.gridx = 0;
        gbc.gridy = 2;
        adicionarLivroPanel.add(generoLabel, gbc);
        gbc.gridx = 1;
        adicionarLivroPanel.add(generoField, gbc);

        JLabel urlLabel = new JLabel("URL:");
        // Usando PlaceholderTextField com placeholder
        urlField = new PlaceholderTextField("Digite a URL da imagem", 30);
        gbc.gridx = 0;
        gbc.gridy = 3;
        adicionarLivroPanel.add(urlLabel, gbc);
        gbc.gridx = 1;
        adicionarLivroPanel.add(urlField, gbc);

        JLabel imagemLabel = new JLabel("Imagem:");
        JButton selecionarImagemButton = new JButton("Selecionar Imagem");
        imagemSelecionadaLabel = new JLabel();
        imagemSelecionadaLabel.setPreferredSize(new Dimension(100, 150));
        gbc.gridx = 0;
        gbc.gridy = 4;
        adicionarLivroPanel.add(imagemLabel, gbc);
        gbc.gridx = 1;
        adicionarLivroPanel.add(selecionarImagemButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        adicionarLivroPanel.add(imagemSelecionadaLabel, gbc);

        JButton adicionarButton = new JButton("Adicionar");
        gbc.gridx = 0;
        gbc.gridy = 6;

        adicionarLivroPanel.add(adicionarButton, gbc);

        JButton voltarButton = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 7;
        
        adicionarLivroPanel.add(voltarButton, gbc);

        mainPanel.add(adicionarLivroPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Ações dos botões
        selecionarImagemButton.addActionListener(e -> controller.selecionarImagem(imagemSelecionadaLabel));

        adicionarButton.addActionListener(e -> {
            String titulo = tituloField.getText();
            String autor = autorField.getText();
            String genero = generoField.getText();
            String url = urlField.getText();
            Icon imagem = imagemSelecionadaLabel.getIcon();
            controller.adicionarLivro(titulo, autor, genero, url, imagem);
        });

        voltarButton.addActionListener(e -> NavegadorDeTelas.mostrarTelaMenu());
    }
}
