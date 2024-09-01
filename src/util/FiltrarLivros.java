package src.util;

import src.model.LivroModel;

import java.text.Normalizer;
import java.util.List;
import java.util.stream.Collectors;

public class FiltrarLivros {

    public static List<LivroModel> filtrarLivros(List<LivroModel> livros, String termo) {
        String termoNormalizado = normalizarTexto(termo);
        return livros.stream()
            .filter(livro -> normalizarTexto(livro.getTitulo()).contains(termoNormalizado)
                          || normalizarTexto(livro.getAutor()).contains(termoNormalizado)
                          || normalizarTexto(livro.getGenero()).contains(termoNormalizado))
            .collect(Collectors.toList());
    }

    public static void limparFiltro(List<LivroModel> livrosFiltrados, List<LivroModel> livrosOriginais) {
        livrosFiltrados.clear();
        livrosFiltrados.addAll(livrosOriginais);
    }

    private static String normalizarTexto(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
            .toLowerCase();
    }
}
