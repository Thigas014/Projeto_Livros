package src.controller;

import src.model.LivroModel;
import java.util.List;

public class VerHistoricoController {
    private List<LivroModel> historico;
    
    public VerHistoricoController(List<LivroModel> historico, MenuController menuController) {
        this.historico = historico;
    }

    public List<LivroModel> getHistorico() {
        return historico;
    }

    public void limparHistorico() {
        historico.clear();
        salvarHistorico();
    }

    private void salvarHistorico() {
        // Usa o menuController para salvar o histórico
        MenuController.salvarHistorico();
    }
}
