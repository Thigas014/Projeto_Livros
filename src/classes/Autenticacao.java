package src.classes;

import java.util.Scanner;

import src.dados.SenhaManager;

public class Autenticacao {
    private static final Scanner scanner = new Scanner(System.in);

    public String autenticarUsuario() {
        boolean loginSucesso = false;
        String nomeUsuario = null;

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
                    return null;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (!loginSucesso);

        System.out.println("Login bem-sucedido para: " + nomeUsuario);
        return nomeUsuario;
    }

    private String fazerLogin() {
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

    private void fazerCadastro() {
        System.out.print("Digite um nome de usuário para cadastro: ");
        String novoNome = scanner.nextLine();
        System.out.print("Digite uma senha: ");
        String novaSenha = scanner.nextLine();

        if (SenhaManager.adicionarUsuario(novoNome, novaSenha)) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Erro: Não foi possível cadastrar o usuário. Nome de usuário já em uso.");
        }
    }
}
