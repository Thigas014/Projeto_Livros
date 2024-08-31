package src.model;

import java.io.Serializable;
import javax.swing.Icon;

public class LivroModel implements Serializable {
    private static final long serialVersionUID = 1;

    private String titulo;
    private String autor;
    private String genero;
    private String url;
    private Icon imagem;

    public LivroModel(String titulo, String autor, String genero, String url, Icon imagem) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.url = url;
        this.imagem = imagem;
    }
    

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Icon getImagem() {
        return imagem;
    }

    public void setImagem(Icon imagem) {
        this.imagem = imagem;
    }
}
