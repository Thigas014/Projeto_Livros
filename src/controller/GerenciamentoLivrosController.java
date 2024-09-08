package src.controller;

import src.model.LivroModel;
import src.util.FiltrarLivros;
import src.util.PlaceholderTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class GerenciamentoLivrosController {
    private List<LivroModel> livrosFiltrados;
    private MenuController menuController;

    public GerenciamentoLivrosController(MenuController menuController) {
        this.menuController = menuController;
        carregarDados();
    }

    public MenuController getMenuController() {
        return menuController;
    }

    // Carrega a lista de livros globais (disponível para todos os usuários)
    private void carregarDados() {
        livrosFiltrados = new ArrayList<>(menuController.getLivros());
    }

    // Retorna os livros filtrados (após pesquisa ou carregamento inicial)
    public List<LivroModel> getLivrosFiltrados() {
        return livrosFiltrados;
    }

    // Filtra a lista de livros com base em um termo de pesquisa
    public void filtrarLivros(String termoDePesquisa) {
        livrosFiltrados = FiltrarLivros.filtrarLivros(menuController.getLivros(), termoDePesquisa);
    }

    // Limpa o filtro e restaura a lista de livros global
    public void limparFiltro() {
        FiltrarLivros.limparFiltro(livrosFiltrados, menuController.getLivros());
    }

    // Seleciona uma imagem do arquivo e exibe no JLabel
    public void selecionarImagem(JLabel imagemSelecionadaLabel) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);

                if (bufferedImage == null) {
                    throw new IOException("Não foi possível ler a imagem.");
                }

                Image scaledImage = bufferedImage.getScaledInstance(
                        imagemSelecionadaLabel.getWidth(),
                        imagemSelecionadaLabel.getHeight(),
                        Image.SCALE_SMOOTH);

                imagemSelecionadaLabel.setIcon(new ImageIcon(scaledImage));
                imagemSelecionadaLabel.setText(null);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(imagemSelecionadaLabel.getParent(), "Erro ao carregar a imagem.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma imagem foi selecionada.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // Adiciona um novo livro à lista global de livros
    public void adicionarLivro(String titulo, String autor, String genero, String url, Icon imagem, PlaceholderTextField tituloField, PlaceholderTextField autorField, PlaceholderTextField generoField, PlaceholderTextField urlField) {
        if ((titulo.trim().isEmpty() || titulo.equals(tituloField.getPlaceholder())) &&
        (autor.trim().isEmpty() || autor.equals(autorField.getPlaceholder())) &&
        (genero.trim().isEmpty() || genero.equals(generoField.getPlaceholder())) &&
        (url.trim().isEmpty() || url.equals(urlField.getPlaceholder())) &&
        imagem == null) {
        
        JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos e uma imagem deve ser colocada.", "Campos vazios", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (titulo.trim().isEmpty() || titulo.equals(tituloField.getPlaceholder())) {
        JOptionPane.showMessageDialog(null, "O campo não pode estar vazio.", "Campo de Título é obrigatório", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (autor.trim().isEmpty() || autor.equals(autorField.getPlaceholder())) {
        JOptionPane.showMessageDialog(null, "O campo não pode estar vazio.", "Campo de Autor é obrigatório", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (genero.trim().isEmpty() || genero.equals(generoField.getPlaceholder())) {
        JOptionPane.showMessageDialog(null, "O campo não pode estar vazio.", "Campo de Gênero(s) é obrigatório", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (url.trim().isEmpty() || url.equals(urlField.getPlaceholder())) {
        JOptionPane.showMessageDialog(null, "O campo não pode estar vazio.", "Campo de URL é obrigatório", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (!validarURL(url)) {
        JOptionPane.showMessageDialog(null, "Verifique e tente novamente.", "URL inválida", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (imagem == null) {
        JOptionPane.showMessageDialog(null, "Você deve selecionar uma imagem.", "Imagem obrigatória", JOptionPane.WARNING_MESSAGE);
        return;
    }

        LivroModel novoLivro = new LivroModel(titulo, autor, genero, url, imagem);
        menuController.adicionarLivro(novoLivro);  // Adiciona à lista global de livros
        JOptionPane.showMessageDialog(null, "Livro adicionado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        NavegadorDeTelas.mostrarTelaMenu();
    }

    // Abre uma URL no navegador
    public void abrirUrl(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao abrir a URL.");
        }
    }

    // Valida uma URL antes de adicionar o livro
    @SuppressWarnings("deprecation")
    private boolean validarURL(String url) {
        try {
            java.net.URL obj = new java.net.URL(url);
            obj.openConnection().connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
