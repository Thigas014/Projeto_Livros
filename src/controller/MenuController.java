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
        carregarDados(); 
    }

    private void carregarDados() {
        MenuController.livros = bancoDeDadosLivros.carregarLivros();
        MenuController.historico = bancoDeDadosLivros.carregarHistorico();
    }

    public static List<LivroModel> getLivros() {
        return livros;
    }

    public static List<LivroModel> getHistorico() {
        return historico;
    }

    public static void salvarLivros() {
        bancoDeDadosLivros.salvarLivros(livros);
    }

    public static void salvarHistorico() {
        bancoDeDadosLivros.salvarHistorico(historico);
    }

    public static void adicionarAoHistorico(LivroModel livro) {
        historico.add(livro);
        salvarHistorico(); 
    }

    public static void adicionarLivro(LivroModel livro) {
        livros.add(livro);
        salvarLivros();
    }

    public static void removerLivro(LivroModel livro) {
        livros.remove(livro);
        salvarLivros();  
    }

    public static void limparHistorico() {
        historico.clear();
        salvarHistorico();
    }
}
