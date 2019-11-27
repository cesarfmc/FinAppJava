package controller;

import java.util.Optional;

import helper.FormaPagamentoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.FormaPagamento;

public class FormaPagamentoViewController {
	private FormaPagamentoDAO formaPagamentoDAO;
	private FormaPagamento formaPagamento;

	public FormaPagamentoViewController(FormaPagamento formaPagamento, FormaPagamentoDAO formaPagamentoDAO) {
		this.formaPagamento = formaPagamento;
		this.formaPagamentoDAO = formaPagamentoDAO;
	}

	@FXML
	private TextField tfdCodigo;

	@FXML
	private TextField tfdNome;

	@FXML
	private TextArea txaDescricao;

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
		Alert alert = new Alert(AlertType.WARNING, "Deseja Remover Forma de Pagamento?", ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			formaPagamentoDAO.removeFormaPagamento(formaPagamento.getIdFormaPagamento());
			
			Alert alertConfirm = new Alert(AlertType.INFORMATION, "Forma Pagamento Removida!", ButtonType.OK);
			alertConfirm.showAndWait();
			
			fechar(getStage(btnExcluir));
		} else {
			Alert alertExclusao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
			alertExclusao.showAndWait();
		}
	}

	@FXML
	void salvar_Click(ActionEvent event) {
		if (formaPagamento == null) {
			Alert alert = new Alert(AlertType.INFORMATION, "Forma de Pagamento Inserida", ButtonType.OK);
			alert.showAndWait();
			
			formaPagamentoDAO.addFormaPagamento(getFormaPagamento());
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Forma de Pagamento Alterada", ButtonType.OK);
			alert.showAndWait();
			
			formaPagamentoDAO.updateFormaPagamento(getFormaPagamento());
		}
		fechar(getStage(btnSalvar));
	}

	private void inicializaCampos() {
		if (formaPagamento != null) {
			tfdCodigo.setText(formaPagamento.getCodigo());
			tfdNome.setText(formaPagamento.getNome());
			txaDescricao.setText(formaPagamento.getDescricao());
		} else {
			tfdCodigo.setText("");
			tfdNome.setText("");
			txaDescricao.setText("");
		}
	}

	private FormaPagamento getFormaPagamento() {
		if (formaPagamento == null) {
			formaPagamento = new FormaPagamento();
		}
		formaPagamento.setCodigo(tfdCodigo.getText());
		formaPagamento.setNome(tfdNome.getText());
		formaPagamento.setDescricao(txaDescricao.getText());

		return formaPagamento;
	}

	private void fechar(Stage stage) {
		stage.close();
	}

	private Stage getStage(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();

		return stage;
	}

	@FXML
	private void initialize() {
		inicializaCampos();
	}

}
