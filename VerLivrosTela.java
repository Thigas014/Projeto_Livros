import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class VerLivrosTela {

    private static List<Livro> livros; // Lista de livros
    private static List<Livro> livrosFiltrados; // Lista de livros filtrados

    // Método para mostrar a tela de visualização de livros disponíveis
    public static void mostrarTelaVerLivros(List<Livro> livrosDisponiveis) {
        livros = livrosDisponiveis; // Inicializa a lista de livros com os livros disponíveis
        livrosFiltrados = new ArrayList<>(livros); // Inicializa a lista de livros filtrados com uma cópia dos livros disponíveis

        JFrame verLivrosFrame = new JFrame("Livros Disponíveis");
        verLivrosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define a ação de fechamento do frame
        verLivrosFrame.setSize(800, 500); // Define o tamanho do frame para 800x500 pixels
        verLivrosFrame.setResizable(false); // Impede o redimensionamento do frame

        JPanel mainPanel = new JPanel(new BorderLayout()); // Painel principal usando BorderLayout
        mainPanel.setBackground(Color.LIGHT_GRAY); // Define a cor de fundo do painel principal mas n vai

        // Painel para pesquisa de livros
        JPanel pesquisaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel pesquisaLabel = new JLabel("Pesquisar:");
        JTextField pesquisaField = new JTextField(20);
        JButton pesquisarButton = new JButton("Pesquisar");
        pesquisaPanel.setBackground(Color.GRAY); // Define a cor de fundo do painel de pesquisa

        pesquisaPanel.add(pesquisaLabel);
        pesquisaPanel.add(pesquisaField);
        pesquisaPanel.add(pesquisarButton);

        // Painel para exibir a lista de livros
        JPanel livrosPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(livrosPanel); // JScrollPane para permitir rolagem

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        mainPanel.add(pesquisaPanel, BorderLayout.NORTH); // Adiciona o painel de pesquisa na parte superior
        mainPanel.add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane no centro do painel principal
        

        JButton voltarButton = new JButton("Voltar");
        JButton limparFiltroButton = new JButton("Limpar Filtro");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(limparFiltroButton);
        buttonPanel.add(voltarButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH); // Adiciona o painel de botões na parte inferior
        buttonPanel.setBackground(Color.GRAY); // Define a cor de fundo do painel de baixo

        verLivrosFrame.add(mainPanel); // Adiciona o painel principal ao JFrame
        verLivrosFrame.setVisible(true); // Torna o JFrame visível

        atualizarListaDeLivros(livrosPanel, gbc); // Atualiza a lista de livros exibida inicialmente

        // ActionListener para o botão de pesquisa
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String termoDePesquisa = normalizarTexto(pesquisaField.getText()); // Normaliza o texto de pesquisa
                livrosFiltrados.clear(); // Limpa a lista de livros filtrados
                for (Livro livro : livros) {
                    // Verifica se o título, autor ou gênero do livro contém o termo de pesquisa normalizado
                    if (normalizarTexto(livro.getTitulo()).contains(termoDePesquisa) ||
                        normalizarTexto(livro.getAutor()).contains(termoDePesquisa) ||
                        normalizarTexto(livro.getGenero()).contains(termoDePesquisa)) {
                        livrosFiltrados.add(livro); // Adiciona o livro à lista de livros filtrados
                    }
                }
                atualizarListaDeLivros(livrosPanel, gbc); // Atualiza a lista de livros exibida com os livros filtrados
            }
        });

        // ActionListener para o botão de voltar
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verLivrosFrame.dispose(); // Fecha o JFrame de visualização de livros
                MenuScreen.mostrarTelaMenu(); // Volta para o menu principal
            }
        });

        // ActionListener para o botão de limpar filtro
        limparFiltroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                livrosFiltrados.clear(); // Limpa a lista de livros filtrados
                livrosFiltrados.addAll(livros); // Restaura os livros filtrados para a lista completa
                pesquisaField.setText(""); // Limpa o campo de pesquisa
                atualizarListaDeLivros(livrosPanel, gbc); // Atualiza a lista de livros exibida
            }
        });
    }

    // Método para atualizar a lista de livros exibidos no painel
    private static void atualizarListaDeLivros(JPanel livrosPanel, GridBagConstraints gbc) {
        livrosPanel.removeAll(); // Remove todos os componentes do painel de livros

        int y = 0; // Contador para a posição vertical dos componentes
        for (Livro livro : livrosFiltrados) { // Itera sobre os livros filtrados
            JPanel livroPanel = new JPanel(new GridBagLayout()); // Painel para exibir informações do livro
            GridBagConstraints livroGbc = new GridBagConstraints();
            livroGbc.insets = new Insets(5, 5, 5, 5);
            livroPanel.setBackground(Color.white);
            livroPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel imagemLabel = new JLabel();
            if (livro.getImagem() != null) {
                imagemLabel.setIcon((Icon) livro.getImagem()); // Define o ícone do livro, se disponível
            }
            livroGbc.gridx = 0;
            livroGbc.gridy = 0;
            livroGbc.gridheight = 3;
            livroGbc.fill = GridBagConstraints.BOTH;
            livroPanel.add(imagemLabel, livroGbc); // Adiciona a imagem do livro ao painel

            JLabel tituloLabel = new JLabel("Título: " + livro.getTitulo());
            livroGbc.gridx = 1;
            livroGbc.gridy = 0;
            livroGbc.gridheight = 1;
            livroPanel.add(tituloLabel, livroGbc); // Adiciona o título do livro ao painel

            JLabel autorLabel = new JLabel("Autor: " + livro.getAutor());
            livroGbc.gridy = 1;
            livroPanel.add(autorLabel, livroGbc); // Adiciona o autor do livro ao painel

            JLabel generoLabel = new JLabel("Gênero: " + livro.getGenero());
            livroGbc.gridy = 2;
            livroPanel.add(generoLabel, livroGbc); // Adiciona o gênero do livro ao painel

            gbc.gridx = 0;
            gbc.gridy = y++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            livrosPanel.add(livroPanel, gbc); // Adiciona o painel do livro ao painel de livros
        }

        livrosPanel.revalidate(); // Revalida o layout do painel de livros
        livrosPanel.repaint(); // Repinta o painel de livros
    }

    // Método para normalizar o texto removendo acentos e convertendo para minúsculas
    private static String normalizarTexto(String texto) {
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        textoNormalizado = textoNormalizado.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return textoNormalizado.toLowerCase();
    }
}



