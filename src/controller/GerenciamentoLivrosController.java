package src.controller;

import src.model.LivroModel;
import src.util.FiltrarLivros;

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

    public GerenciamentoLivrosController() {
        carregarDados();
    }

    private void carregarDados() {
        livrosFiltrados = new ArrayList<>(MenuController.getLivros());
    }

    public List<LivroModel> getLivrosFiltrados() {
        return livrosFiltrados;
    }

    public void filtrarLivros(String termoDePesquisa) {
        livrosFiltrados = FiltrarLivros.filtrarLivros(MenuController.getLivros(), termoDePesquisa);
    }

    public void limparFiltro() {
        FiltrarLivros.limparFiltro(livrosFiltrados, MenuController.getLivros());
    }

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
                JOptionPane.showMessageDialog(imagemSelecionadaLabel.getParent(), "Erro ao carregar a imagem.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma imagem foi selecionada.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    

    public void adicionarLivro(String titulo, String autor, String genero, String url, Icon imagem) {
        if (titulo.trim().isEmpty() && autor.trim().isEmpty() && genero.trim().isEmpty() && url.trim().isEmpty() && imagem == null) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos e uma imagem deve ser colocada.", "Campos vazios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (titulo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo não pode estar vazio.", "Campo de Título é obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (autor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo não pode estar vazio.", "Campo de Autor é obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (genero.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo não pode estar vazio.", "Campo de Gênero(s) é obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (url.trim().isEmpty()) {
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
        MenuController.adicionarLivro(novoLivro);
        JOptionPane.showMessageDialog(null, "Livro adicionado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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
