import javax.swing.*;

public class TelaSair {

    public static void mostrarTelaSair(JFrame currentFrame) {
        int confirm = JOptionPane.showConfirmDialog(currentFrame, "Tem certeza de que deseja sair?", "Confirmar Saída", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            MenuScreen.salvarBancoDeDadosLivros(); 
            System.exit(0); 
        }
    }
}



