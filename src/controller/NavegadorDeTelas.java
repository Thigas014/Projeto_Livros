package src.controller;

import java.util.List;

import javax.swing.*;
import src.view.*;

import src.model.LivroModel;



public class NavegadorDeTelas {
    private static JFrame frame;

    // Inicializa o JFrame principal
    private static void inicializarFrame() {
        if (frame == null) {
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 500);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
        }
    }

    // Exibe a tela de login
    public static void mostrarTelaLogin() {
        LoginController controller = new LoginController();
        TelaLogin telaLogin = new TelaLogin(controller);
        inicializarFrame();
        frame.getContentPane().removeAll();
        telaLogin.mostrarTela(frame);
        frame.setVisible(true);
    }

    // Exibe a tela de cadastro
    public static void mostrarTelaCadastro() {
        CadastroController controller = new CadastroController();
        TelaCadastro telaCadastro = new TelaCadastro(controller);
        inicializarFrame();
        frame.getContentPane().removeAll();
        telaCadastro.mostrarTela(frame);
        frame.setVisible(true);
    }

    // Exibe a tela de menu
    public static void mostrarTelaMenu() {
        inicializarFrame();
        frame.getContentPane().removeAll();
        MenuController controller = new MenuController();
        TelaMenu telaMenu = new TelaMenu(controller);
        telaMenu.mostrarTela(frame);
        frame.setVisible(true);
    }

    
    // Exibe a tela de visualizar livros
    public static void mostrarTelaVisualizarLivros() {
        inicializarFrame();
        frame.getContentPane().removeAll();
        // Obtenha a lista de livros do MenuController
        MenuController menuController = new MenuController();
        List<LivroModel> listaDeLivros = menuController.getLivros(); 
        VerLivrosController controller = new VerLivrosController(listaDeLivros);
        VerLivros telaVisualizarLivros = new VerLivros(controller);
        telaVisualizarLivros.mostrarTela(frame);
        frame.setVisible(true);
    }
    
    // Exibe a tela de visualizar histórico
    public static void mostrarTelaVerHistorico() {
        inicializarFrame();
        frame.getContentPane().removeAll();
        MenuController menuController = new MenuController(); 
        VerHistoricoController controller = new VerHistoricoController(menuController.getHistorico(), menuController); // Passa o menuController
        TelaVerHistorico verHistoricoView = new TelaVerHistorico(controller);
        verHistoricoView.mostrarTela(frame);
        frame.setVisible(true);
    }

    // Exibe a tela de remover livro
    public static void mostrarTelaRemoverLivro() {
        inicializarFrame();
        frame.getContentPane().removeAll();
        MenuController menuController = new MenuController();
        List<LivroModel> listaDeLivros = menuController.getLivros();
        RemoverLivroController controller = new RemoverLivroController(listaDeLivros);
        TelaRemoverLivro removerLivroView = new TelaRemoverLivro(controller);
        removerLivroView.mostrarTela(frame);
        frame.setVisible(true);
    }

    //  Exibe a tela de adicionar livro
    public static void mostrarTelaAdicionarLivro() {
        inicializarFrame();
        frame.getContentPane().removeAll();
        AdicionarLivroController controller = new AdicionarLivroController();
        TelaAdicionarLivro telaAdicionarLivro = new TelaAdicionarLivro(controller);
        telaAdicionarLivro.mostrarTela(frame);
        frame.setVisible(true);

    }

    // Exibe a tela de escolher livro
   public static void mostrarTelaEscolherLivro() {
        inicializarFrame();
        frame.getContentPane().removeAll();
        MenuController menuController = new MenuController();
        EscolherLivroController controller = new EscolherLivroController(menuController);
        TelaEscolherLivro escolherLivroView = new TelaEscolherLivro(controller);
        escolherLivroView.mostrarTela(frame);
        frame.setVisible(true);
    }


    // Encerra a aplicação
    public static void sairDaAplicacao (JFrame currentFrame) {
        int confirm = JOptionPane.showConfirmDialog(currentFrame, "Tem certeza de que deseja sair?", "Confirmar Saída", 
        JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            MenuController.salvarLivros();
           System.exit(0); 
}   }}
