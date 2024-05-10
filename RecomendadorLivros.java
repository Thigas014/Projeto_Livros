import java.util.ArrayList;
import java.util.List;

public class RecomendadorLivros {
    public List<Livro> recomendarLivros(Usuario usuario, List<Livro> todosLivros) {
        List<Livro> recomendacoes = new ArrayList<>();
        List<Livro> historicoLivrosUsuario = usuario.getHistoricoLivros();

        for (Livro livro : todosLivros) {
            boolean jaLeu = false;
            for (Livro livroHistorico : historicoLivrosUsuario) {
                if (livro.getTitulo().equals(livroHistorico.getTitulo())) {
                    jaLeu = true;
                    break;
                }
            }
            if (!jaLeu && livro.getGenero().equals(getGeneroMaisLido(usuario))) {
                recomendacoes.add(livro);
            }
        }

        return recomendacoes;
    }

    private String getGeneroMaisLido(Usuario usuario) {
        List<Livro> historicoLivros = usuario.getHistoricoLivros();
        if (historicoLivros.isEmpty()) {
            return ""; // Se o histórico estiver vazio, não há gênero mais lido
        }

        // Contagem dos gêneros
        int maxCount = 0;
        String generoMaisLido = "";
        for (Livro livro : historicoLivros) {
            String genero = livro.getGenero();
            int count = 0;
            for (Livro livroHistorico : historicoLivros) {
                if (livroHistorico.getGenero().equals(genero)) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                generoMaisLido = genero;
            }
        }

        return generoMaisLido;
    }
}


