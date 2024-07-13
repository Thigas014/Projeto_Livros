package src.classes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HistoricoLivros implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Usuario> usuarios;

    public HistoricoLivros() {
        this.usuarios = new ArrayList<>();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public Usuario getUsuarioPorNome(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome)) {
                return usuario;
            }
        }
        return null;
    }
}