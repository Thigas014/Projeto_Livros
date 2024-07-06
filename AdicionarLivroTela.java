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
        // Cria a janela para adicionar livro
        JFrame adicionarLivroFrame = new JFrame("Adicionar Livro");
        adicionarLivroFrame.setSize(800, 500); // Define o tamanho da janela para 800x500 pixels
        adicionarLivroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define a operação de fechar a janela
        adicionarLivroFrame.setResizable(false); // Impede o redimensionamento da janela

        // Cria o painel principal com layout GridBagLayout
        JPanel adicionarLivroPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); // Cria um objeto para configurar os componentes dentro do GridBagLayout
        gbc.insets = new Insets(5, 5, 5, 5); // Define as margens internas (espaço entre os componentes)
        gbc.fill = GridBagConstraints.HORIZONTAL; // Define como os componentes devem preencher o espaço disponível
        adicionarLivroPanel.setBackground(Color.GRAY); // Define a cor de fundo do painel

        // Adiciona o campo para o título do livro
        JLabel tituloLabel = new JLabel("Título:");
        JTextField tituloField = new JTextField(30);
        gbc.gridx = 0; // Posição da coluna no grid
        gbc.gridy = 0; // Posição da linha no grid
        adicionarLivroPanel.add(tituloLabel, gbc); // Adiciona o rótulo do título ao painel
        gbc.gridx = 1; // Avança para a próxima coluna
        adicionarLivroPanel.add(tituloField, gbc); // Adiciona o campo de texto do título ao painel

        // Adiciona o campo para o autor do livro
        JLabel autorLabel = new JLabel("Autor:");
        JTextField autorField = new JTextField(30);
        gbc.gridx = 0; // Volta para a primeira coluna
        gbc.gridy = 1; // Avança para a próxima linha
        adicionarLivroPanel.add(autorLabel, gbc); // Adiciona o rótulo do autor ao painel
        gbc.gridx = 1; // Avança para a próxima coluna
        adicionarLivroPanel.add(autorField, gbc); // Adiciona o campo de texto do autor ao painel

        // Adiciona o campo para o gênero do livro
        JLabel generoLabel = new JLabel("Gênero:");
        JTextField generoField = new JTextField(30);
        gbc.gridx = 0; // Volta para a primeira coluna
        gbc.gridy = 2; // Avança para a próxima linha
        adicionarLivroPanel.add(generoLabel, gbc); // Adiciona o rótulo do gênero ao painel
        gbc.gridx = 1; // Avança para a próxima coluna
        adicionarLivroPanel.add(generoField, gbc); // Adiciona o campo de texto do gênero ao painel

        // Adiciona o campo para a URL do livro
        JLabel urlLabel = new JLabel("URL:");
        JTextField urlField = new JTextField(30);
        gbc.gridx = 0; // Volta para a primeira coluna
        gbc.gridy = 3; // Avança para a próxima linha
        adicionarLivroPanel.add(urlLabel, gbc); // Adiciona o rótulo da URL ao painel
        gbc.gridx = 1; // Avança para a próxima coluna
        adicionarLivroPanel.add(urlField, gbc); // Adiciona o campo de texto da URL ao painel

        // Adiciona o rótulo e o botão para selecionar a imagem do livro
        JLabel imagemLabel = new JLabel("Imagem:");
        JButton selecionarImagemButton = new JButton("Selecionar Imagem");
        JLabel imagemSelecionadaLabel = new JLabel();
        imagemSelecionadaLabel.setPreferredSize(new Dimension(100, 150)); // Define o tamanho preferido do rótulo de imagem
        gbc.gridx = 0; // Volta para a primeira coluna
        gbc.gridy = 4; // Avança para a próxima linha
        adicionarLivroPanel.add(imagemLabel, gbc); // Adiciona o rótulo de imagem ao painel
        gbc.gridx = 1; // Avança para a próxima coluna
        adicionarLivroPanel.add(selecionarImagemButton, gbc); // Adiciona o botão de seleção de imagem ao painel

        // Adiciona o rótulo para mostrar a imagem selecionada
        gbc.gridx = 0; // Volta para a primeira coluna
        gbc.gridy = 5; // Avança para a próxima linha
        gbc.gridwidth = 2; // Define que o componente ocupa 2 colunas no grid
        gbc.fill = GridBagConstraints.CENTER; // Define como o componente deve preencher o espaço
        adicionarLivroPanel.add(imagemSelecionadaLabel, gbc); // Adiciona o rótulo de imagem selecionada ao painel
        gbc.gridwidth = 1; // Reseta para o valor padrão
        gbc.fill = GridBagConstraints.HORIZONTAL; // Reseta para o valor padrão

        // Adiciona o botão para adicionar o livro
        JButton adicionarButton = new JButton("Adicionar");
        gbc.gridx = 0; // Volta para a primeira coluna
        gbc.gridy = 6; // Avança para a próxima linha
        adicionarLivroPanel.add(adicionarButton, gbc); // Adiciona o botão de adicionar ao painel

        // Adiciona o botão para voltar ao menu
        JButton voltarButton = new JButton("Voltar");
        gbc.gridx = 1; // Avança para a próxima coluna
        adicionarLivroPanel.add(voltarButton, gbc); // Adiciona o botão de voltar ao painel

        // Adiciona o painel principal à janela e torna a janela visível
        adicionarLivroFrame.add(adicionarLivroPanel); // Adiciona o painel ao JFrame
        adicionarLivroFrame.setVisible(true); // Torna a janela visível

        // Adiciona ação ao botão de selecionar imagem
        selecionarImagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria um seletor de arquivos
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Se um arquivo for selecionado, tenta carregar a imagem
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedImage bufferedImage = ImageIO.read(selectedFile);
                        // Redimensiona a imagem para caber no rótulo
                        Image scaledImage = bufferedImage.getScaledInstance(
                                imagemSelecionadaLabel.getWidth(),
                                imagemSelecionadaLabel.getHeight(),
                                Image.SCALE_SMOOTH);
                        imagemSelecionadaLabel.setIcon(new ImageIcon(scaledImage));
                        imagemSelecionadaLabel.setText(null); // Remove qualquer texto do rótulo
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(adicionarLivroPanel, "Erro ao carregar a imagem.");
                    }
                }
            }
        });

        // Adiciona ação ao botão de adicionar livro
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém os valores dos campos de texto e da imagem
                String titulo = tituloField.getText();
                String autor = autorField.getText();
                String genero = generoField.getText();
                String url = urlField.getText();
                Icon imagem = imagemSelecionadaLabel.getIcon();

                // Verifica se todos os campos estão preenchidos
                if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty() || url.isEmpty() || imagem == null) {
                    JOptionPane.showMessageDialog(adicionarLivroPanel, "Preencha todos os campos e selecione uma imagem.");
                } else {
                    // Cria um novo livro com os valores dos campos
                    Livro novoLivro = new Livro(titulo, autor, genero, url);
                    novoLivro.setImagem((ImageIcon) imagem); // Configura a imagem do livro
                    MenuScreen.livros.add(novoLivro); // Adiciona o livro à lista de livros
                    MenuScreen.salvarBancoDeDadosLivros(); // Salva no banco de dados
                    JOptionPane.showMessageDialog(adicionarLivroPanel, "Livro adicionado com sucesso!");
                    adicionarLivroFrame.dispose(); // Fecha a janela de adicionar livro
                    MenuScreen.mostrarTelaMenu(); // Volta para a tela do menu
                }
            }
        });

        // Adiciona ação ao botão de voltar
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarLivroFrame.dispose(); // Fecha a janela de adicionar livro
                MenuScreen.mostrarTelaMenu(); // Volta para a tela do menu
            }
        });
    }
}
