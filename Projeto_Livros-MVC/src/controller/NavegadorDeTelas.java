package src.controller;

import javax.swing.*;
import src.model.UsuarioModel;
import src.view.*;

public class NavegadorDeTelas {
    private static JFrame frame;
    private static UsuarioModel usuarioAtual; // Armazena o usuário logado

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

    // Armazena o usuário logado
    public static void setUsuarioAtual(UsuarioModel usuario) {
        usuarioAtual = usuario;
    }
    

    public static UsuarioModel getUsuarioAtual() {
        return usuarioAtual;
    }

    // Exibe a tela de login
    public static void mostrarTelaLogin() {
        AutenticacaoController controller = new AutenticacaoController();
        TelaLogin telaLogin = new TelaLogin(controller);
        inicializarFrame();
        frame.getContentPane().removeAll();
        telaLogin.mostrarTela(frame);
        frame.setVisible(true);
    }

    // Exibe a tela de cadastro
    public static void mostrarTelaCadastro() {
        AutenticacaoController controller = new AutenticacaoController();
        TelaCadastro telaCadastro = new TelaCadastro(controller);
        inicializarFrame();
        frame.getContentPane().removeAll();
        telaCadastro.mostrarTela(frame);
        frame.setVisible(true);
    }

    // Exibe a tela de menu com o usuário logado
    public static void mostrarTelaMenu() {
        if (usuarioAtual != null) {
            inicializarFrame();
            frame.getContentPane().removeAll();
            MenuController controller = new MenuController(usuarioAtual);
            TelaMenu telaMenu = new TelaMenu(controller);
            telaMenu.mostrarTela(frame);
            frame.setVisible(true);
        } else {
            mostrarTelaLogin();
        }
    }

    // Exibe a tela de visualizar livros
    public static void mostrarTelaVisualizarLivros() {
        inicializarFrame();
        frame.getContentPane().removeAll();
        MenuController menuController = new MenuController(usuarioAtual); // Passar o usuário atual ou controller
        GerenciamentoLivrosController controller = new GerenciamentoLivrosController(menuController);
        VerLivros telaVisualizarLivros = new VerLivros(controller);
        telaVisualizarLivros.mostrarTela(frame);
        frame.setVisible(true);
    }

    // Exibe a tela de visualizar histórico do usuário logado
    public static void mostrarTelaVerHistorico() {
        inicializarFrame();
        frame.getContentPane().removeAll();
        if (usuarioAtual != null) {
            MenuController menuController = new MenuController(usuarioAtual); // Passa o usuário atual para o controller
           // GerenciamentoLivrosController controller = new GerenciamentoLivrosController(menuController); // Passa o MenuController
            TelaVerHistorico verHistoricoView = new TelaVerHistorico(menuController); // Corrigido o construtor
            verHistoricoView.mostrarTela(frame); // Exibe a tela de histórico
            frame.setVisible(true);
        } else {
            mostrarTelaLogin(); // Redireciona para o login se o usuário não estiver logado
        }
    }
    


    // Exibe a tela de remover livro
    public static void mostrarTelaRemoverLivro() {
        inicializarFrame();
        frame.getContentPane().removeAll();
        MenuController menuController = new MenuController(usuarioAtual);
        GerenciamentoLivrosController controller = new GerenciamentoLivrosController(menuController);
        TelaRemoverLivro removerLivroView = new TelaRemoverLivro(controller);
        removerLivroView.mostrarTela(frame);
        frame.setVisible(true);
    }

    // Exibe a tela de adicionar livro
    public static void mostrarTelaAdicionarLivro() {
        inicializarFrame();
        frame.getContentPane().removeAll();
        MenuController menuController = new MenuController(usuarioAtual);
        GerenciamentoLivrosController controller = new GerenciamentoLivrosController(menuController);
        TelaAdicionarLivro telaAdicionarLivro = new TelaAdicionarLivro(controller);
        telaAdicionarLivro.mostrarTela(frame);
        frame.setVisible(true);
    }

// Exibe a tela de escolher livro
public static void mostrarTelaEscolherLivro(UsuarioModel usuarioAtual) {
    inicializarFrame(); // Inicializa o frame se necessário
    frame.getContentPane().removeAll(); // Limpa o conteúdo anterior do frame
    
    // Cria o MenuController com o usuário atual
    MenuController menuController = new MenuController(usuarioAtual);

    // Cria o GerenciamentoLivrosController com o MenuController
    GerenciamentoLivrosController controller = new GerenciamentoLivrosController(menuController);

    // Cria a tela TelaEscolherLivro com o GerenciamentoLivrosController
    TelaEscolherLivro escolherLivroView = new TelaEscolherLivro(controller);

    // Exibe a tela
    escolherLivroView.mostrarTela(frame); // Mostra a tela no frame
    frame.setVisible(true); // Torna o frame visível
}

    

    // Encerra a aplicação
    public static void sairDaAplicacao(JFrame currentFrame, MenuController menuController) {
        int confirm = JOptionPane.showConfirmDialog(currentFrame, "Tem certeza de que deseja sair?", "Confirmar Saída", 
        JOptionPane.YES_NO_OPTION);
    
        if (confirm == JOptionPane.YES_OPTION) {
             menuController.salvarEstadoAplicacao(); //Salva os livros usando a instância de MenuController
            System.exit(0);
        }
    }

    
}
