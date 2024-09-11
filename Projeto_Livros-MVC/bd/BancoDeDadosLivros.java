package bd;

import src.model.LivroModel;
import src.model.UserHistorico;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoDeDadosLivros {
    // Caminhos atualizados para o diretório "dados" no mesmo local onde o JAR será executado
    private static final String BOOKDATABASE = "dados/bookDatabase.txt";
    private static final String USERHISTORYDATABASE = "dados/userHistoryDatabase.txt";

    // Carrega a lista global de livros
    public List<LivroModel> carregarLivros() {
        return carregarDados(BOOKDATABASE);
    }

    // Carrega o histórico de um usuário específico baseado no nome
    public UserHistorico carregarHistoricoUsuario(String nomeUsuario) {
        Map<String, UserHistorico> historicos = carregarHistoricos();
        return historicos.getOrDefault(nomeUsuario, new UserHistorico(nomeUsuario));
    }

    // Salva a lista global de livros
    public void salvarLivros(List<LivroModel> livros) {
        salvarDados(BOOKDATABASE, livros);
    }

    // Atualiza o histórico de um usuário específico no banco de dados
    public void atualizarHistoricoUsuario(UserHistorico userHistorico) {
        Map<String, UserHistorico> historicos = carregarHistoricos();
        historicos.put(userHistorico.getNomeUsuario(), userHistorico);  // Associa o histórico ao nome do usuário
        salvarDados(USERHISTORYDATABASE, historicos);  // Salva todos os históricos atualizados
    }

    // Carrega todos os históricos de usuários do banco de dados
    private Map<String, UserHistorico> carregarHistoricos() {
        return carregarDados(USERHISTORYDATABASE);
    }

    // Método genérico para carregar dados de um arquivo
    @SuppressWarnings("unchecked")
    private <T> T carregarDados(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (T) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de banco de dados não encontrado. Um novo arquivo será criado.");
            if (filePath.equals(BOOKDATABASE)) {
                return (T) new ArrayList<LivroModel>();  // Retorna lista vazia de livros
            } else {
                return (T) new HashMap<String, UserHistorico>();  // Retorna mapa vazio de históricos
            }
        } catch (EOFException e) {
            System.out.println("Arquivo de banco de dados está vazio.");
            if (filePath.equals(BOOKDATABASE)) {
                return (T) new ArrayList<LivroModel>();  // Retorna lista vazia de livros
            } else {
                return (T) new HashMap<String, UserHistorico>();  // Retorna mapa vazio de históricos
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método genérico para salvar dados em um arquivo
    private void salvarDados(String filePath, Object dados) {
        File diretorio = new File("dados");
        if (!diretorio.exists()) {
            diretorio.mkdirs();  // Cria o diretório se não existir
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(dados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
