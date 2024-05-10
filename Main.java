import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean loginSucesso = false;
        String nomeUsuario = null;

        // Loop principal para o menu de acesso
        do {
            System.out.println("\n=== Menu de Acesso ===");
            System.out.println("1. Login");
            System.out.println("2. Cadastro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    nomeUsuario = fazerLogin();
                    loginSucesso = (nomeUsuario != null);
                    break;
                case 2:
                    fazerCadastro();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (!loginSucesso);

        System.out.println("Login bem-sucedido para: " + nomeUsuario);

        // Carregar livros disponíveis
        List<Livro> livros = FileManager.carregarLivros();
        // Adicionar livros de exemplo se a lista estiver vazia
        if (livros.isEmpty()) {
            adicionarLivrosExemplo(livros);
            FileManager.salvarLivros(livros);
        }
    
        // Carregar histórico de leitura do usuário
        HistoricoLivros historicoLivros = FileManager.carregarHistorico();
        Usuario usuario = historicoLivros.getUsuarioPorNome(nomeUsuario);
        // Criar um novo usuário se não existir no histórico
        if (usuario == null) {
            usuario = new Usuario(nomeUsuario);
            historicoLivros.adicionarUsuario(usuario);
            FileManager.salvarHistorico(historicoLivros);
        }

        // Criar o recomendador de livros
        RecomendadorLivros recomendador = new RecomendadorLivros();

        int opcao;
        // Loop principal para o menu de livros
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
                    mostrarLivrosDisponiveis(livros);
                    break;
                case 2:
                    escolherLivro(livros, historicoLivros, usuario);
                    break;
                case 3:
                    verHistorico(usuario);
                    break;
                case 4:
                    verRecomendacoes(recomendador, historicoLivros, livros, usuario);
                    break;
                case 5:
                    apagarLivro(livros);
                    break;
                case 6:
                    adicionarLivro(livros);
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

    // Método para adicionar um novo livro à lista de livros
    private static void adicionarLivro(List<Livro> livros) {
        scanner.nextLine(); // Limpar o buffer do scanner
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

    // Método para adicionar livros de exemplo à lista de livros
    private static void adicionarLivrosExemplo(List<Livro> livros) {
        livros.add(new Livro("O Guia do Mochileiro das Galáxias VOL 1", "Douglas Adams", "Ficção Científica Cômica",
                "https://exemplo.com/livro1"));
        livros.add(new Livro("O Guia do Mochileiro das Galáxias VOL 2", "Douglas Adams", "Ficção Científica Cômica",
                "https://exemplo.com/livro2"));
        livros.add(new Livro("O Senhor dos Anéis VOL 1", "J.R.R. Tolkien", "Fantasia",
                "https://exemplo.com/livro3"));
        livros.add(new Livro("O Senhor dos Anéis VOL 2", "J.R.R. Tolkien", "Fantasia",
                "https://exemplo.com/livro4"));
    }

    // Método para mostrar os livros disponíveis
    private static void mostrarLivrosDisponiveis(List<Livro> livros) {
        System.out.println("\n=== Livros Disponíveis ===");
        for (int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i);
            System.out.println(
                    (i + 1) + ". " + livro.getTitulo() + " - " + livro.getAutor() + " (" + livro.getGenero() + ")");
        }
    }

    // Método para apagar um livro da lista de livros
    private static void apagarLivro(List<Livro> livros) {
        mostrarLivrosDisponiveis(livros);
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

    // Método para escolher um livro para ler
    private static void escolherLivro(List<Livro> livros, HistoricoLivros historicoLivros, Usuario usuario) {
        mostrarLivrosDisponiveis(livros);
        System.out.print("Escolha o número do livro que deseja pegar emprestado: ");
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

    // Método para exibir o histórico de leitura do usuário
    private static void verHistorico(Usuario usuario) {
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

    // Método para ver recomendações de livros para o usuário
    private static void verRecomendacoes(RecomendadorLivros recomendador, HistoricoLivros historicoLivros,
            List<Livro> livros, Usuario usuario) {
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

    // Método para fazer login
    private static String fazerLogin() {
        System.out.print("Digite seu nome de usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        if (SenhaManager.verificarCredenciais(nome, senha)) {
            return nome;
        } else {
            System.out.println("Credenciais inválidas.");
            return null;
        }
    }

    // Método para fazer cadastro
    private static void fazerCadastro() {
        System.out.print("Digite um nome de usuário para cadastro: ");
        String novoNome = scanner.nextLine();
        System.out.print("Digite uma senha: ");
        String novaSenha = scanner.nextLine();

        if (SenhaManager.adicionarUsuario(novoNome, novaSenha)) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Erro: Não foi possível cadastrar o usuário. Nome de usuário ou senha já em uso.");
        }
    }
}

