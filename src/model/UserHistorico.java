package src.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserHistorico implements Serializable {

    private static final long serialVersionUID = 1699728832798367797L;  // Adicionando o serialVersionUID

    private String nomeUsuario;
    private List<LivroModel> livros;

    // Construtor que usa o nome do usuário como identificador
    public UserHistorico(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.livros = new ArrayList<>();
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public List<LivroModel> getLivrosLidos() {
        return livros;
    }

    public void setLivros(List<LivroModel> livros) {
        this.livros = livros;
    }

    // Método para adicionar um livro ao histórico
    public void adicionarLivro(LivroModel livro) {
        if (livro != null && !livros.contains(livro)) {
            livros.add(livro);
        }
    }

    // Método para limpar o histórico de livros
    public void limparHistorico() {
        livros.clear();
    }
}
