/* 
package src.controller;

import src.model.LivroModel;
import src.util.FiltrarLivros;

import java.util.ArrayList;
import java.util.List;

public class RemoverLivroController {
    private List<LivroModel> livros;
    private List<LivroModel> livrosFiltrados;

    public RemoverLivroController(List<LivroModel> livros) {
        this.livros = livros;
        this.livrosFiltrados = new ArrayList<>(livros);
    }

    public List<LivroModel> getLivrosFiltrados() {
        return livrosFiltrados;
    }

    public void filtrarLivros(String termo) {
        livrosFiltrados = FiltrarLivros.filtrarLivros(livros, termo);
    }

    public void limparFiltro() {
        FiltrarLivros.limparFiltro(livrosFiltrados, livros);
    }

    public void removerLivro(LivroModel livro) {
        livros.remove(livro);
        limparFiltro(); // Atualiza a lista filtrada 
        MenuController.salvarLivros(); // Salva a lista de livros 
    }
}
*/