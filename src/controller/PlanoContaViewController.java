package controller;

import java.util.Optional;

import helper.PlanoContaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.PlanoConta;

public class PlanoContaViewController {
	private PlanoConta planoConta;
	private PlanoConta planoContaPai;
	private PlanoContaDAO planoContaDAO;

	public PlanoContaViewController(PlanoConta planoConta, PlanoConta planoContaPai, PlanoContaDAO planoContaDAO) {
		this.planoConta = planoConta;
		this.planoContaPai = planoContaPai;
		this.planoContaDAO = planoContaDAO;
	}

	@FXML
	private TextField tfdPlanoContaPai;

	@FXML
	private TextField tfdNome;

	@FXML
	private TextField txaDescricao;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnCancelar;

	@FXML
	void cancelar_Click(ActionEvent event) {
		fechar(getStage(btnCancelar));
	}

	@FXML
	void excluir_Click(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING, 
				"Deseja Remover Plano de Conta?", 
				ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES){
			planoContaDAO.removePlanoConta(planoConta.getIdPlanoConta());
			
			Alert alertExclusao = new Alert(AlertType.INFORMATION, "Plano de Conta Removido", ButtonType.OK);
			alertExclusao.showAndWait();
			
			fechar(getStage(btnExcluir));
		} else {
			Alert alertExclusao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
			alertExclusao.showAndWait();
		}
		
	}

	@FXML
	void salvar_Click(ActionEvent event) {
		if(planoConta != null) {
			planoContaDAO.updatePlanoConta(getDados());
    		
    		Alert alert = new Alert(AlertType.INFORMATION, "Plano de Conta Alterado", ButtonType.OK);
			alert.showAndWait();
    	}else {
    		planoContaDAO.addPlanoConta(getDados());
    		
    		Alert alert = new Alert(AlertType.INFORMATION, "Plano de Conta Inserido", ButtonType.OK);
			alert.showAndWait();
    	}
    	
    	fechar(getStage(btnSalvar));
	}

	@FXML
	private void initialize() {
		inicializaCampos();
	}

	public void inicializaCampos() {
		if (planoConta != null) {
			tfdNome.setText(planoConta.getNome());
			txaDescricao.setText(planoConta.getDescricao());
			tfdPlanoContaPai.setText(planoConta.getPlanoConta().getNome());
		} else {
			tfdPlanoContaPai.setText(planoContaPai.getNome());
		}
	}

	public PlanoConta getDados() {
		if (planoConta == null) {
			planoConta = new PlanoConta();
		}
		if(tfdNome.getText() != null ) {
			planoConta.setNome(tfdNome.getText());	
		}
		
		planoConta.setDescricao(txaDescricao.getText());
		planoConta.setPlanoConta(planoContaPai);

		return planoConta;
	}
	
	private void fechar(Stage stage) {
		stage.close();
	}

	private Stage getStage(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();

		return stage;
	}
}
