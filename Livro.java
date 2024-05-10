import java.io.Serializable;

public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;
    private String titulo;
    private String autor;
    private String genero;
    private String url;

    public Livro(String titulo, String autor, String genero, String url) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public String getUrl() {
        return url;
    }
}
