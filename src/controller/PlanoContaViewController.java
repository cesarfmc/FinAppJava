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
	private PlanoContaListViewController controller;

	public PlanoContaViewController(PlanoConta planoConta, PlanoConta planoContaPai, PlanoContaDAO planoContaDAO,
			PlanoContaListViewController controller) {
		this.planoConta = planoConta;
		this.planoContaPai = planoContaPai;
		this.planoContaDAO = planoContaDAO;
		this.controller = controller;
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
		Alert alert = new Alert(AlertType.WARNING, "Deseja Remover Plano de Conta?", ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
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
		if (tfdNome.getText().contentEquals("")) {
			Alert alert = new Alert(AlertType.WARNING, "Nome Obrigatório!", ButtonType.OK);
			alert.showAndWait();
		} else {
			if (planoConta != null) {
				Alert alert = new Alert(AlertType.WARNING, "Deseja Alterar Dados do Plano de Conta?", ButtonType.YES,
						ButtonType.NO);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.YES) {
					planoContaDAO.updatePlanoConta(getDados());

					Alert alertConfirmacao = new Alert(AlertType.INFORMATION, "Plano de Conta Alterado", ButtonType.OK);
					alertConfirmacao.showAndWait();

					fechar(getStage(btnSalvar));
				} else {
					cancelarOperacao();
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING, "Deseja Inserir Plano de Conta", ButtonType.YES,
						ButtonType.NO);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.YES) {
					planoContaDAO.addPlanoConta(getDados());

					Alert alertConfirmacao = new Alert(AlertType.INFORMATION, "Plano de Conta Inserido", ButtonType.OK);
					alertConfirmacao.showAndWait();
					fechar(getStage(btnSalvar));
				} else {
					cancelarOperacao();
				}

			}
		}
	}

	@FXML
	private void initialize() {
		iniciaBotoes();
	}

	public void inicializaCampos() {
		if (planoConta != null) {
			tfdNome.setText(planoConta.getNome());
			if (planoConta.getDescricao() != null) {
				txaDescricao.setText(planoConta.getDescricao());
			}
			tfdPlanoContaPai.setText(planoConta.getPlanoConta().getNome());
		} else {
			tfdPlanoContaPai.setText(planoContaPai.getNome());
		}
	}

	private void iniciaBotoes() {
		inicializaCampos();
		tfdPlanoContaPai.setDisable(true);
		if (planoConta != null) {
			btnExcluir.setDisable(false);
		} else {
			btnExcluir.setDisable(true);
		}
	}

	public PlanoConta getDados() {
		if (planoConta == null) {
			planoConta = new PlanoConta();
		}
		if (txaDescricao.getText() != null) {
			planoConta.setDescricao(txaDescricao.getText());
		}
		planoConta.setNome(tfdNome.getText());
		planoConta.setPlanoConta(planoContaPai);

		return planoConta;
	}

	private void cancelarOperacao() {
		Alert alertConfirmacao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
		alertConfirmacao.showAndWait();
	}

	private void fechar(Stage stage) {
		controller.pesquisar();
		stage.close();
	}

	private Stage getStage(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();

		return stage;
	}
}
