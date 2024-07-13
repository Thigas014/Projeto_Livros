package src.dados;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import src.classes.HistoricoLivros;
import src.classes.Livro;

public class FileManager {
    private static final String LIVROS_FILE = "src/dados/livros.ser";
    private static final String HISTORICO_FILE = "src/dados/historico.ser";

    public static void salvarLivros(List<Livro> livros) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LIVROS_FILE))) {
            oos.writeObject(livros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Livro> carregarLivros() {
        List<Livro> livros = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(LIVROS_FILE))) {
            livros = (List<Livro>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public static void salvarHistorico(HistoricoLivros historicoLivros) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HISTORICO_FILE))) {
            oos.writeObject(historicoLivros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HistoricoLivros carregarHistorico() {
        HistoricoLivros historicoLivros = new HistoricoLivros();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HISTORICO_FILE))) {
            historicoLivros = (HistoricoLivros) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return historicoLivros;
    }
}
