/* 
package src.controller;

import src.model.LivroModel;
import src.util.FiltrarLivros;
import src.view.TelaEscolherLivro;

import javax.swing.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class EscolherLivroController {
    private List<LivroModel> livros;
    private List<LivroModel> livrosFiltrados;
    private MenuController menuController;
    private TelaEscolherLivro view;

    public EscolherLivroController(MenuController menuController) {
        this.menuController = menuController;
        this.livros = menuController.getLivros();
        this.livrosFiltrados = new ArrayList<>(livros);
        //this.view = new TelaEscolherLivro(this);
    }

    public void mostrarTela(JFrame frame) {
        view.mostrarTela(frame);
    }

    public List<LivroModel> getLivrosFiltrados() {
        return livrosFiltrados;
    }

    public void filtrarLivros(String termoDePesquisa) {
        livrosFiltrados = FiltrarLivros.filtrarLivros(livros, termoDePesquisa);
    }

    public void limparFiltro() {
        FiltrarLivros.limparFiltro(livrosFiltrados, livros);
    }

    public void voltarParaMenu() {
        NavegadorDeTelas.mostrarTelaMenu();
    }

    public void abrirUrl(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao abrir a URL.");
        }
    }

    public void adicionarAoHistorico(LivroModel livro) {
        menuController.adicionarAoHistorico(livro);
    }

}
*/