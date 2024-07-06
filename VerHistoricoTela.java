import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerHistoricoTela {

    // Método para mostrar a tela de histórico de livros
    public static void mostrarHistorico() {
        // Criação do JFrame para exibir o histórico de livros
        JFrame historicoFrame = new JFrame("Histórico de Livros");
        historicoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define a ação padrão de fechar o frame
        historicoFrame.setSize(800, 500); // Define o tamanho do frame para 800x500 pixels
        historicoFrame.setResizable(false); // Impede o redimensionamento do frame

        JPanel historicoPanel = new JPanel(new BorderLayout()); // Painel principal usando BorderLayout
        

        // Botão para limpar o histórico de livros
        JButton limparHistoricoButton = new JButton("Limpar Histórico");
        limparHistoricoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuScreen.historico.clear(); // Limpa a lista de histórico de livros
                MenuScreen.salvarHistoricoLivros(); // Salva o histórico atualizado
                JOptionPane.showMessageDialog(historicoFrame, "Histórico limpo com sucesso!"); // Exibe uma mensagem de sucesso
                historicoFrame.dispose(); // Fecha o frame de histórico
                MenuScreen.mostrarTelaMenu(); // Volta para o menu principal
            }
        });

        // Botão para voltar ao menu principal
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historicoFrame.dispose(); // Fecha o frame de histórico
                MenuScreen.mostrarTelaMenu(); // Volta para o menu principal
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Layout vertical para os botões
        buttonPanel.setBackground(Color.GRAY); // Define a cor de fundo do painel de botões
        buttonPanel.add(Box.createVerticalGlue()); // Adiciona um espaço vertical flexível
        buttonPanel.add(limparHistoricoButton); // Adiciona o botão de limpar histórico
        buttonPanel.add(Box.createVerticalStrut(10)); // Adiciona um espaço vertical fixo
        buttonPanel.add(voltarButton); // Adiciona o botão de voltar
        buttonPanel.add(Box.createVerticalGlue()); // Adiciona um espaço vertical flexível

        JPanel livrosPanel = new JPanel(new GridBagLayout()); // Painel para exibir a lista de livros
        JScrollPane scrollPane = new JScrollPane(livrosPanel); // Adiciona um JScrollPane ao painel de livros
        scrollPane.getViewport().setBackground(Color.WHITE); // Define a cor de fundo da área visível do scrollPane

        GridBagConstraints gbc = new GridBagConstraints(); // Constraints para o GridBagLayout
        gbc.insets = new Insets(5, 5, 5, 5); // Define o espaçamento interno dos componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Preenchimento horizontal

        int y = 0; // Contador para a posição vertical dos componentes no livrosPanel
        for (Livro livro : MenuScreen.historico) { // Itera sobre a lista de livros no histórico
            JPanel livroPanel = new JPanel(new GridBagLayout()); // Painel para exibir informações do livro
            livroPanel.setBackground(Color.WHITE); // Define a cor de fundo do painel do livro
            livroPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Adiciona uma borda ao painel do livro

            JLabel imagemLabel = new JLabel();
            if (livro.getImagem() != null) {
                imagemLabel.setIcon((Icon) livro.getImagem()); // Define o ícone do livro, se houver
            }
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridheight = 3;
            gbc.fill = GridBagConstraints.BOTH;
            livroPanel.add(imagemLabel, gbc); // Adiciona a imagem do livro ao painel

            JLabel tituloLabel = new JLabel("Título: " + livro.getTitulo());
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            livroPanel.add(tituloLabel, gbc); // Adiciona o título do livro ao painel

            JLabel autorLabel = new JLabel("Autor: " + livro.getAutor());
            gbc.gridy = 1;
            livroPanel.add(autorLabel, gbc); // Adiciona o autor do livro ao painel

            JLabel generoLabel = new JLabel("Gênero: " + livro.getGenero());
            gbc.gridy = 2;
            livroPanel.add(generoLabel, gbc); // Adiciona o gênero do livro ao painel

            gbc.gridx = 0;
            gbc.gridy = y++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            livrosPanel.add(livroPanel, gbc); // Adiciona o painel do livro ao livrosPanel
        }

        historicoPanel.add(scrollPane, BorderLayout.CENTER); // Adiciona o scrollPane ao painel principal
        historicoPanel.add(buttonPanel, BorderLayout.EAST); // Adiciona o painel de botões à direita do painel principal

        historicoFrame.add(historicoPanel); // Adiciona o painel principal ao frame de histórico
        historicoFrame.setVisible(true); // Torna o frame de histórico visível
    }
}





