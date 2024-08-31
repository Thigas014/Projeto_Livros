package src.controller;

import src.dados.BancoDeDadosLivros;
import src.model.LivroModel;

import java.util.List;

public class MenuController {
    private static BancoDeDadosLivros bancoDeDadosLivros;
    private static List<LivroModel> livros;
    private static List<LivroModel> historico;

    public MenuController() {
        MenuController.bancoDeDadosLivros = new BancoDeDadosLivros();
        carregarDados();  // Carregar dados ao iniciar o controller
    }

    private void carregarDados() {
        MenuController.livros = bancoDeDadosLivros.carregarLivros();
        MenuController.historico = bancoDeDadosLivros.carregarHistorico();
    }

    public List<LivroModel> getLivros() {
        return livros;
    }

    public List<LivroModel> getHistorico() {
        return historico;
    }

    public static void salvarLivros() {
        bancoDeDadosLivros.salvarLivros(livros);
    }

    public static void salvarHistorico() {
        bancoDeDadosLivros.salvarHistorico(historico);
    }

    public void adicionarAoHistorico(LivroModel livro) {
        historico.add(livro);
        salvarHistorico(); 
    }

    public static void adicionarLivro(LivroModel livro) {
        livros.add(livro);
        salvarLivros();
    }

    public void removerLivro(LivroModel livro) {
        livros.remove(livro);
        salvarLivros();  
    }
}



    
    // Ações de navegação ver amanha ou quarta com o professor
    /* 
    public void acaoVisualizarLivros() {
        NavegadorDeTelas.mostrarTelaVisualizarLivros(); 
    }
    
    public void acaoAdicionarLivro() {
        NavegadorDeTelas.mostrarTelaAdicionarLivro(); 
    }

    public void acaoEscolherLivro() {
        NavegadorDeTelas.mostrarTelaEscolherLivro(); 
    }

    public void acaoVisualizarHistorico() {
        NavegadorDeTelas.mostrarTelaVisualizarHistorico(); 
    }

    public void acaoRemoverLivro() {
        NavegadorDeTelas.mostrarTelaRemoverLivro(); 
    }
        
    */

