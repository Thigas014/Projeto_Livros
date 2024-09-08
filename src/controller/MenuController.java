package src.controller;

import src.dados.BancoDeDadosLivros;
import src.model.LivroModel;
import src.model.UserHistorico;
import src.model.UsuarioModel;

import java.util.ArrayList;
import java.util.List;

public class MenuController {
    private BancoDeDadosLivros bancoDeDadosLivros;
    private UsuarioModel usuarioLogado;  // O usuário que está logado
    private UserHistorico historicoUsuario;
    private List<LivroModel> livrosGlobais;  // Lista global de livros disponíveis para todos os usuários

    public MenuController(UsuarioModel usuarioLogado) {
        this.bancoDeDadosLivros = new BancoDeDadosLivros();
        this.usuarioLogado = usuarioLogado;
        carregarLivrosGlobais();  // Carregar lista global de livros
        carregarHistoricoUsuario();  // Carregar histórico do usuário logado
    }

    // Carrega a lista global de livros
    private void carregarLivrosGlobais() {
        this.livrosGlobais = bancoDeDadosLivros.carregarLivros();  // Carrega os livros do banco de dados
    }

    // Carrega o histórico de leitura do usuário logado
    private void carregarHistoricoUsuario() {
        this.historicoUsuario = bancoDeDadosLivros.carregarHistoricoUsuario(usuarioLogado.getNomeUsuario());
        if (this.historicoUsuario == null) {
            this.historicoUsuario = new UserHistorico(usuarioLogado.getNomeUsuario());  // Cria novo histórico se não existir
        }
    }

    // Adiciona um livro ao histórico do usuário logado
    public void adicionarLivroAoHistorico(LivroModel livro) {
        if (historicoUsuario != null) {
            historicoUsuario.adicionarLivro(livro); 
            bancoDeDadosLivros.atualizarHistoricoUsuario(historicoUsuario);  // Atualiza o histórico no banco de dados
        } else {
            System.err.println("Erro: Nenhum histórico de usuário encontrado.");
        }
    }

    // Retorna a lista de livros lidos do usuário logado
    public List<LivroModel> getHistorico() {
        return historicoUsuario != null ? historicoUsuario.getLivrosLidos() : new ArrayList<>();
    }

    // Limpa o histórico do usuário logado
    public void limparHistoricoUsuarioLogado() {
        if (historicoUsuario != null) {
            historicoUsuario.limparHistorico();
            bancoDeDadosLivros.atualizarHistoricoUsuario(historicoUsuario);  // Atualiza o banco de dados com o histórico limpo
        }
    }

    // Adiciona um livro à lista global de livros
    public void adicionarLivro(LivroModel livro) {
        livrosGlobais.add(livro);
        bancoDeDadosLivros.salvarLivros(livrosGlobais);  // Atualiza a lista global no banco de dados
    }

    // Retorna a lista global de livros
    public List<LivroModel> getLivros() {
        return livrosGlobais;
    }
    
    // Remove um livro da lista global
    public void removerLivro(LivroModel livro) {
        livrosGlobais.remove(livro);
        bancoDeDadosLivros.salvarLivros(livrosGlobais);  // Atualiza a lista global no banco de dados
    }

    // Método para salvar o estado da aplicação antes de sair
    public void salvarEstadoAplicacao() {
        // Salva a lista global de livros
        bancoDeDadosLivros.salvarLivros(livrosGlobais);

        // Atualiza o histórico do usuário logado no banco de dados
        bancoDeDadosLivros.atualizarHistoricoUsuario(historicoUsuario);
    }
}
