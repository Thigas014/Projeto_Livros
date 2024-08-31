package src.dados;

import src.model.LivroModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BancoDeDadosLivros {
    private static final String BOOKDATABASE = "src/dados/bookDatabase.ser";
    private static final String HISTORYDATABASE = "src/dados/historyDatabase.ser";

    /*  ver se fica aq 
    private MenuController controller;
    List<LivroModel> livros = controller.carregarLivros();
    List<LivroModel> historico = controller.carregarHistorico();
    */

    public List<LivroModel> carregarLivros() {
        return carregarDados(BOOKDATABASE);
    }

    public List<LivroModel> carregarHistorico() {
        return carregarDados(HISTORYDATABASE);
    }

    public void salvarLivros(List<LivroModel> livros) {
        salvarDados(BOOKDATABASE, livros);
    }

    public void salvarHistorico(List<LivroModel> historico) {
        salvarDados(HISTORYDATABASE, historico);
    }

    @SuppressWarnings("unchecked")
    private List<LivroModel> carregarDados(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (ArrayList<LivroModel>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de banco de dados de livros não encontrado. Um novo arquivo será criado.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void salvarDados(String filePath, List<LivroModel> dados) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(dados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
