import javax.swing.*;          
import java.io.Serializable;   

// Declara a classe Livro que implementa a interface Serializable
public class Livro implements Serializable {
    // Declaração de atributos privados da classe Livro
    private String titulo;
    private String autor;
    private String genero;
    private String url;
    private ImageIcon imagem;

    // Construtor da classe Livro, que inicializa os atributos titulo, autor, genero e url
    public Livro(String titulo, String autor, String genero, String url) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.url = url;
    }

    // Métodos getter para obter os valores dos atributos privados
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

    public ImageIcon getImagem() {
        return imagem;
    }

    // Método setter para definir o valor do atributo imagem
    public void setImagem(ImageIcon imagem) {
        this.imagem = imagem;
    }
}
