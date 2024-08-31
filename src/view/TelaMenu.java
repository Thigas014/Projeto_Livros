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

        JButton visualizarLivrosButton = new JButton("Ver Livros");
        JButton adicionarLivroButton = new JButton("Adicionar Livro");
        JButton escolherLivroButton = new JButton("Escolher Livro");
        JButton visualizarHistoricoButton = new JButton("Ver Histórico");
        JButton removerLivroButton = new JButton("Remover Livro");
        JButton sairButton = new JButton("Sair");

        Dimension buttonSize = new Dimension(200, 30);
        visualizarLivrosButton.setPreferredSize(buttonSize);
        adicionarLivroButton.setPreferredSize(buttonSize);
        escolherLivroButton.setPreferredSize(buttonSize);
        visualizarHistoricoButton.setPreferredSize(buttonSize);
        removerLivroButton.setPreferredSize(buttonSize);
        sairButton.setPreferredSize(buttonSize);

        menuPanel.add(visualizarLivrosButton);
        menuPanel.add(adicionarLivroButton);
        menuPanel.add(escolherLivroButton);
        menuPanel.add(visualizarHistoricoButton);
        menuPanel.add(removerLivroButton);
        menuPanel.add(sairButton);

        frame.add(menuPanel);

        
        // Listeners delegando ações ao MenuController ou deixo passso direto pro navegador
        //visualizarLivrosButton.addActionListener(e -> controller.acaoVisualizarLivros());
        visualizarLivrosButton.addActionListener(e -> NavegadorDeTelas.mostrarTelaVisualizarLivros());
        visualizarHistoricoButton.addActionListener(e -> NavegadorDeTelas.mostrarTelaVerHistorico());
        removerLivroButton.addActionListener(e -> NavegadorDeTelas.mostrarTelaRemoverLivro());
        adicionarLivroButton.addActionListener(e -> NavegadorDeTelas.mostrarTelaAdicionarLivro());
        escolherLivroButton.addActionListener(e -> NavegadorDeTelas.mostrarTelaEscolherLivro());
        
        /* 
        adicionarLivroButton.addActionListener(e -> controller.acaoAdicionarLivro());

        escolherLivroButton.addActionListener(e -> controller.acaoEscolherLivro());

        visualizarHistoricoButton.addActionListener(e -> controller.acaoVisualizarHistorico());

        removerLivroButton.addActionListener(e -> controller.acaoRemoverLivro());

        */
        sairButton.addActionListener(e -> {
            NavegadorDeTelas.sairDaAplicacao(frame);
        });
        
    }
}
