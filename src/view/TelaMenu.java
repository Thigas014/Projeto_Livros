package src.view;

import src.controller.MenuController;
import src.controller.NavegadorDeTelas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaMenu {
    public TelaMenu(MenuController controller) {
    }

    public void mostrarTela(JFrame frame) {
        frame.setTitle("Menu");

        JPanel menuPanel = new JPanel(new GridLayout(0, 1));
        menuPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        menuPanel.setBackground(Color.GRAY);

        // Criação dos botões
        JButton visualizarLivrosButton = new JButton("Ver Livros");
        JButton adicionarLivroButton = new JButton("Adicionar Livro");
        JButton escolherLivroButton = new JButton("Escolher Livro");
        JButton visualizarHistoricoButton = new JButton("Ver Histórico");
        JButton removerLivroButton = new JButton("Remover Livro");
        JButton sairButton = new JButton("Sair");

        // Configuração das cores e fonte dos botões
        Color buttonBackgroundColor = Color.BLACK; // Fundo preto
        Color buttonTextColor = Color.WHITE; // Texto branco
        Font buttonFont = new Font("Arial", Font.BOLD, 14); // Texto em negrito

        visualizarLivrosButton.setBackground(buttonBackgroundColor);
        visualizarLivrosButton.setForeground(buttonTextColor);
        visualizarLivrosButton.setFont(buttonFont);

        adicionarLivroButton.setBackground(buttonBackgroundColor);
        adicionarLivroButton.setForeground(buttonTextColor);
        adicionarLivroButton.setFont(buttonFont);

        escolherLivroButton.setBackground(buttonBackgroundColor);
        escolherLivroButton.setForeground(buttonTextColor);
        escolherLivroButton.setFont(buttonFont);

        visualizarHistoricoButton.setBackground(buttonBackgroundColor);
        visualizarHistoricoButton.setForeground(buttonTextColor);
        visualizarHistoricoButton.setFont(buttonFont);

        removerLivroButton.setBackground(buttonBackgroundColor);
        removerLivroButton.setForeground(buttonTextColor);
        removerLivroButton.setFont(buttonFont);

        sairButton.setBackground(buttonBackgroundColor);
        sairButton.setForeground(buttonTextColor);
        sairButton.setFont(buttonFont);

        // Definição do tamanho dos botões
        Dimension buttonSize = new Dimension(200, 30);
        visualizarLivrosButton.setPreferredSize(buttonSize);
        adicionarLivroButton.setPreferredSize(buttonSize);
        escolherLivroButton.setPreferredSize(buttonSize);
        visualizarHistoricoButton.setPreferredSize(buttonSize);
        removerLivroButton.setPreferredSize(buttonSize);
        sairButton.setPreferredSize(buttonSize);

        // Adicionando os botões ao painel
        menuPanel.add(visualizarLivrosButton);
        menuPanel.add(adicionarLivroButton);
        menuPanel.add(escolherLivroButton);
        menuPanel.add(visualizarHistoricoButton);
        menuPanel.add(removerLivroButton);
        menuPanel.add(sairButton);

        // Adicionando o painel ao frame
        frame.add(menuPanel);

        // Ações dos botões
        visualizarLivrosButton.addActionListener(e -> NavegadorDeTelas.mostrarTelaVisualizarLivros());
        adicionarLivroButton.addActionListener(e -> NavegadorDeTelas.mostrarTelaAdicionarLivro());
        escolherLivroButton.addActionListener(e -> NavegadorDeTelas.mostrarTelaEscolherLivro());
        visualizarHistoricoButton.addActionListener(e -> NavegadorDeTelas.mostrarTelaVerHistorico());
        removerLivroButton.addActionListener(e -> NavegadorDeTelas.mostrarTelaRemoverLivro());

        sairButton.addActionListener(e -> NavegadorDeTelas.sairDaAplicacao(frame));
    }
}
