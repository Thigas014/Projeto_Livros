package src.controller;

import src.model.LivroModel;
import src.util.FiltrarLivros;

import java.util.List;

public class VerLivrosController {
    private List<LivroModel> livros;
    private List<LivroModel> livrosFiltrados;

    public VerLivrosController(List<LivroModel> livros) {
        this.livros = livros;
        this.livrosFiltrados = livros;
    }

    public void filtrarLivros(String termo) {
        livrosFiltrados = FiltrarLivros.filtrarLivros(livros, termo);
    }

    public List<LivroModel> getLivrosFiltrados() {
        return livrosFiltrados;
    }

    public void limparFiltro() {
        FiltrarLivros.limparFiltro(livrosFiltrados, livros);
    }
}
