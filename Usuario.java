import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private List<Livro> historicoLivros;

    public Usuario(String nome) {
        this.nome = nome;
        this.historicoLivros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarLivroAoHistorico(Livro livro) {
        historicoLivros.add(livro);
    }

    public List<Livro> getHistoricoLivros() {
        return new ArrayList<>(historicoLivros);
    }
}
