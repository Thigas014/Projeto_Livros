package src.controller;

import src.model.LivroModel;
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
        livrosFiltrados.clear();
        for (LivroModel livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(termo.toLowerCase()) ||
                livro.getAutor().toLowerCase().contains(termo.toLowerCase()) ||
                livro.getGenero().toLowerCase().contains(termo.toLowerCase())) {
                livrosFiltrados.add(livro);
            }
        }
    }

    public void limparFiltro() {
        livrosFiltrados.clear();
        livrosFiltrados.addAll(livros);
    }

    public void removerLivro(LivroModel livro) {
        livros.remove(livro);
        limparFiltro(); // Atualiza a lista filtrada após remoção
        MenuController.salvarLivros(); // Salva a lista de livros atualizada
    }
}
