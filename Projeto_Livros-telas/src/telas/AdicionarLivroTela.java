package src.telas;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AdicionarLivroTela {

    public static void adicionarLivro() {
        JFrame adicionarLivroFrame = new JFrame("Adicionar Livro");
        adicionarLivroFrame.setSize(800, 500); 
        adicionarLivroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adicionarLivroFrame.setResizable(false); 
        adicionarLivroFrame.setLocationRelativeTo(null);

        // Cria o painel principal com layout GridBagLayout
        JPanel adicionarLivroPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); // Cria um objeto para configurar os componentes dentro do GridBagLayout
        gbc.insets = new Insets(5, 5, 5, 5); // Define as margens internas (espaço entre os componentes)
        gbc.fill = GridBagConstraints.HORIZONTAL; // Define como os componentes devem preencher o espaço disponível
        adicionarLivroPanel.setBackground(Color.GRAY); 

        
        JLabel tituloLabel = new JLabel("Título:");
        JTextField tituloField = new JTextField(30);
        gbc.gridx = 0; // Posição da coluna no grid
        gbc.gridy = 0; // Posição da linha no grid
        adicionarLivroPanel.add(tituloLabel, gbc); 
        gbc.gridx = 1; // coluna
        adicionarLivroPanel.add(tituloField, gbc); // Adiciona o campo de texto ao painel

        
        JLabel autorLabel = new JLabel("Autor:");
        JTextField autorField = new JTextField(30);
        gbc.gridx = 0;
        gbc.gridy = 1;
        adicionarLivroPanel.add(autorLabel, gbc); 
        gbc.gridx = 1;
        adicionarLivroPanel.add(autorField, gbc); 

        
        JLabel generoLabel = new JLabel("Gênero:");
        JTextField generoField = new JTextField(30);
        gbc.gridx = 0;
        gbc.gridy = 2; 
        adicionarLivroPanel.add(generoLabel, gbc); 
        gbc.gridx = 1; 
        adicionarLivroPanel.add(generoField, gbc); 

        
        JLabel urlLabel = new JLabel("URL:");
        JTextField urlField = new JTextField(30);
        gbc.gridx = 0;
        gbc.gridy = 3; 
        adicionarLivroPanel.add(urlLabel, gbc); 
        gbc.gridx = 1; 
        adicionarLivroPanel.add(urlField, gbc); 

        
        JLabel imagemLabel = new JLabel("Imagem:");
        JButton selecionarImagemButton = new JButton("Selecionar Imagem");
        JLabel imagemSelecionadaLabel = new JLabel();
        imagemSelecionadaLabel.setPreferredSize(new Dimension(100, 150)); //tamanho img
        gbc.gridx = 0; 
        gbc.gridy = 4; 
        adicionarLivroPanel.add(imagemLabel, gbc); 
        gbc.gridx = 1; 
        adicionarLivroPanel.add(selecionarImagemButton, gbc); 

        gbc.gridx = 0; 
        gbc.gridy = 5; 
        gbc.gridwidth = 2; // Define que o componente ocupa 2 colunas no grid
        gbc.fill = GridBagConstraints.CENTER; // Define como o componente deve preencher o espaço
        adicionarLivroPanel.add(imagemSelecionadaLabel, gbc); 
        gbc.gridwidth = 1; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        
        JButton adicionarButton = new JButton("Adicionar");
        gbc.gridx = 0; 
        gbc.gridy = 6; 
        adicionarLivroPanel.add(adicionarButton, gbc); 

        
        JButton voltarButton = new JButton("Voltar");
        gbc.gridx = 1; 
        adicionarLivroPanel.add(voltarButton, gbc); 

        
        adicionarLivroFrame.add(adicionarLivroPanel);
        adicionarLivroFrame.setVisible(true); 

        
        selecionarImagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria um seletor de arquivos
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedImage bufferedImage = ImageIO.read(selectedFile);
                        
                        Image scaledImage = bufferedImage.getScaledInstance(
                                imagemSelecionadaLabel.getWidth(),
                                imagemSelecionadaLabel.getHeight(),
                                Image.SCALE_SMOOTH);
                        imagemSelecionadaLabel.setIcon(new ImageIcon(scaledImage));
                        imagemSelecionadaLabel.setText(null); 
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(adicionarLivroPanel, "Erro ao carregar a imagem.");
                    }
                }
            }
        });


        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String titulo = tituloField.getText();
                String autor = autorField.getText();
                String genero = generoField.getText();
                String url = urlField.getText();
                Icon imagem = imagemSelecionadaLabel.getIcon();

                
                if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty() || url.isEmpty() || imagem == null) {
                    JOptionPane.showMessageDialog(adicionarLivroPanel, "Preencha todos os campos e selecione uma imagem.");
                } else {
                    
                    Livro novoLivro = new Livro(titulo, autor, genero, url);
                    novoLivro.setImagem((ImageIcon) imagem); 
                    MenuScreen.livros.add(novoLivro); 
                    MenuScreen.salvarBancoDeDadosLivros(); 
                    JOptionPane.showMessageDialog(adicionarLivroPanel, "Livro adicionado com sucesso!");
                    adicionarLivroFrame.dispose(); 
                    MenuScreen.mostrarTelaMenu(); 
                }
            }
        });

        
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarLivroFrame.dispose(); 
                MenuScreen.mostrarTelaMenu(); 
            }
        });
    }
}
