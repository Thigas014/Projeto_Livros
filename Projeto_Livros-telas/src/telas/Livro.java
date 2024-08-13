package src.telas;
import java.io.Serializable;   
import javax.swing.*;

public class Livro implements Serializable {
    private String titulo;
    private String autor;
    private String genero;
    private String url;
    private Icon imagem;

    
    public Livro(String titulo, String autor, String genero, String url) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.url = url;
    }

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