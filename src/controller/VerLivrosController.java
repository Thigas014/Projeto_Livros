package src.controller;

import src.model.LivroModel;

import java.util.List;

public class VerLivrosController {
    private List<LivroModel> livros;
    private List<LivroModel> livrosFiltrados;

    public VerLivrosController(List<LivroModel> livros) {
        this.livros = livros;
        this.livrosFiltrados = livros; // Inicializa com todos os livros disponíveis
    }

    // Método para filtrar os livros com base em um termo de pesquisa
    public void filtrarLivros(String termo) {
        livrosFiltrados = livros.stream()
            .filter(livro -> normalizarTexto(livro.getTitulo()).contains(termo)
                    || normalizarTexto(livro.getAutor()).contains(termo)
                    || normalizarTexto(livro.getGenero()).contains(termo))
            .toList();
    }


    // Retorna os livros filtrados
    public List<LivroModel> getLivrosFiltrados() {
        return livrosFiltrados;
    }

    // Método para limpar o filtro e mostrar todos os livros
    public void limparFiltro() {
        livrosFiltrados = livros;
    }

    // Método para normalizar texto (remover acentos e converter para minúsculas)
    private String normalizarTexto(String texto) {
        return java.text.Normalizer.normalize(texto, java.text.Normalizer.Form.NFD)
            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
            .toLowerCase();
    }
    
    
}
