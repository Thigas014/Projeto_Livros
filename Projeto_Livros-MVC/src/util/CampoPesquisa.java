package src.util;

import javax.swing.JOptionPane;

public abstract class CampoPesquisa {
    protected boolean isCampoDePesquisaVazio(PlaceholderTextField pesquisaField) {
        String termoDePesquisa = pesquisaField.getText().trim();
        return termoDePesquisa.isEmpty() || termoDePesquisa.equals(pesquisaField.getPlaceholder());
    }

    protected void mostrarAvisoCampoVazio() {
        JOptionPane.showMessageDialog(null, "O campo de pesquisa está vazio. Por favor, insira um termo para pesquisa.", "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    protected void mostrarAvisoCampoJaVazio() {
        JOptionPane.showMessageDialog(null, "O campo de pesquisa já está vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}