package src.classes;

import java.util.List;
import java.util.Scanner;

import src.dados.FileManager;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private String nomeUsuario;
    private List<Livro> livros;
    private HistoricoLivros historicoLivros;
    private Usuario usuario;
    private RecomendadorLivros recomendador;

    public Menu(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.livros = FileManager.carregarLivros();
        this.historicoLivros = FileManager.carregarHistorico();
        this.usuario = historicoLivros.getUsuarioPorNome(nomeUsuario);
        if (this.usuario == null) {
            this.usuario = new Usuario(nomeUsuario);
            historicoLivros.adicionarUsuario(this.usuario);
            FileManager.salvarHistorico(this.historicoLivros);
        }
        this.recomendador = new RecomendadorLivros();

        if (this.livros.isEmpty()) {
            adicionarLivrosExemplo(this.livros);
            FileManager.salvarLivros(this.livros);
        }
    }

    public void iniciar() {
        int opcao;
        do {
            System.out.println("\n=== Menu de Livros ===");
            System.out.println("1. Ver livros disponíveis");
            System.out.println("2. Escolher livro");
            System.out.println("3. Ver histórico de leitura");
            System.out.println("4. Ver recomendações");
            System.out.println("5. Apagar Livro");
            System.out.println("6. Adicionar Livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    mostrarLivrosDisponiveis();
                    break;
                case 2:
                    escolherLivro();
                    break;
                case 3:
                    verHistorico();
                    break;
                case 4:
                    verRecomendacoes();
                    break;
                case 5:
                    apagarLivro();
                    break;
                case 6:
                    adicionarLivro();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }

    private void mostrarLivrosDisponiveis() {
        System.out.println("\n=== Livros Disponíveis ===");
        for (int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i);
            System.out.println((i + 1) + ". " + livro.getTitulo() + " - " + livro.getAutor() + " (" + livro.getGenero() + ")");
        }
    }

    private void escolherLivro() {
        mostrarLivrosDisponiveis();
        System.out.print("Escolha o número do livro que deseja pegar: ");
        int escolha = scanner.nextInt();
        if (escolha > 0 && escolha <= livros.size()) {
            Livro livroEscolhido = livros.get(escolha - 1);
            System.out.println("Você escolheu o livro: " + livroEscolhido.getTitulo());
            usuario.adicionarLivroAoHistorico(livroEscolhido);
            FileManager.salvarHistorico(historicoLivros);
            System.out.println("Livro adicionado ao seu histórico.");

            // Redirecionar para o URL associado ao livro
            System.out.println("Acesse o livro em: " + livroEscolhido.getUrl());
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    private void verHistorico() {
        List<Livro> historico = usuario.getHistoricoLivros();
        if (historico.isEmpty()) {
            System.out.println("Seu histórico está vazio.");
        } else {
            System.out.println("\n=== Seu Histórico ===");
            for (Livro livro : historico) {
                System.out.println(livro.getTitulo() + " - " + livro.getAutor() + " (" + livro.getGenero() + ")");
            }
        }
    }

    private void verRecomendacoes() {
        List<Livro> recomendacoes = recomendador.recomendarLivros(usuario, livros);
        if (recomendacoes.isEmpty()) {
            System.out.println("Não há recomendações no momento.");
        } else {
            System.out.println("\n=== Recomendações ===");
            for (Livro livro : recomendacoes) {
                System.out.println(livro.getTitulo() + " - " + livro.getAutor() + " (" + livro.getGenero() + ")");
            }
        }
    }

    private void apagarLivro() {
        mostrarLivrosDisponiveis();
        System.out.print("Escolha o número do livro que deseja apagar: ");
        int escolha = scanner.nextInt();
        if (escolha > 0 && escolha <= livros.size()) {
            Livro livroRemovido = livros.remove(escolha - 1);
            System.out.println("Livro removido: " + livroRemovido.getTitulo());
            FileManager.salvarLivros(livros);
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    private void adicionarLivro() {
        scanner.nextLine();
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Digite o gênero do livro: ");
        String genero = scanner.nextLine();
        System.out.print("Digite o URL do livro: ");
        String url = scanner.nextLine();

        Livro novoLivro = new Livro(titulo, autor, genero, url);
        livros.add(novoLivro);
        System.out.println("Livro adicionado com sucesso!");
        FileManager.salvarLivros(livros);
    }

    private void adicionarLivrosExemplo(List<Livro> livros) {
        livros.add(new Livro("O Guia do Mochileiro das Galáxias VOL 1", "Douglas Adams", "Ficção Científica Cômica", "https://caldeiradigital.wordpress.com/wp-content/uploads/2010/08/o-guia-do-mochileiro-das-galaxias.pdf"));
        livros.add(new Livro("O Guia do Mochileiro das Galáxias VOL 2", "Douglas Adams", "Ficção Científica Cômica", "https://caldeiradigital.wordpress.com/wp-content/uploads/2010/08/2-o-restaurante-no-fim-do-universo.pdf"));
        livros.add(new Livro("O Senhor dos Anéis VOL 1", "J.R.R. Tolkien", "Fantasia", "http://educa.alfenas.mg.gov.br/content/pdf/biblioteca/senhor-dos-aneis-a-sociedade-do-anel.pdf"));
        livros.add(new Livro("O Senhor dos Anéis VOL 2", "J.R.R. Tolkien", "Fantasia", "http://educa.alfenas.mg.gov.br/content/pdf/biblioteca/senhor-dos-aneis-as-duas-torres.pdf"));
    }
    
}
