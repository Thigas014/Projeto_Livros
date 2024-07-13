import src.classes.Autenticacao;
import src.classes.Menu;

public class Main {

    public static void main(String[] args) {
        Autenticacao autenticacao = new Autenticacao();
        String nomeUsuario = autenticacao.autenticarUsuario();

        if (nomeUsuario != null) {
            Menu menu = new Menu(nomeUsuario);
            menu.iniciar();
        }
    }
}


