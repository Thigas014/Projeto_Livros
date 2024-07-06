import javax.swing.*;

public class TelaSair {

    // Método para exibir o diálogo de confirmação ao sair
    public static void mostrarTelaSair(JFrame currentFrame) {
        // Mostra um diálogo de confirmação com opções Sim e Não
        int confirm = JOptionPane.showConfirmDialog(currentFrame, "Tem certeza de que deseja sair?", "Confirmar Saída", JOptionPane.YES_NO_OPTION);

        // Se o usuário escolher Sim, executa as ações de saída
        if (confirm == JOptionPane.YES_OPTION) {
            MenuScreen.salvarBancoDeDadosLivros(); // Salva o banco de dados de livros
            System.exit(0); // Fecha o programa
        }
    }
}



