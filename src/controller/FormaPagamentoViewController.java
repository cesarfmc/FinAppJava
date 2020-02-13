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
	private FormaPagamentoListViewController controller;

	public FormaPagamentoViewController(FormaPagamento formaPagamento, FormaPagamentoDAO formaPagamentoDAO, FormaPagamentoListViewController controller) {
		this.formaPagamento = formaPagamento;
		this.formaPagamentoDAO = formaPagamentoDAO;
		this.controller = controller;
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
			cancelarOperacao();
		}
	}

	@FXML
	void salvar_Click(ActionEvent event) {
		String texto = "";
		if(tfdNome.getText().contentEquals("")) {
			texto += "Nome é requerido!";
			if(tfdCodigo.getText().contentEquals("")) {
				texto += "\n Codigo é requerido";
			}
			Alert alertNome = new Alert(AlertType.WARNING,texto , ButtonType.OK);
			alertNome.showAndWait();
			
		}else {
			if (formaPagamento != null) {
				Alert alert = new Alert(AlertType.WARNING, "Deseja Alterar Dados de Forma de Pagamento?", ButtonType.YES, ButtonType.NO);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.YES) {
					formaPagamentoDAO.updateFormaPagamento(getFormaPagamento());
					
					Alert alertConfirmacao = new Alert(AlertType.INFORMATION, "Forma de Pagamento", ButtonType.OK);
					alertConfirmacao.showAndWait();

					fechar(getStage(btnExcluir));
				} else {
					cancelarOperacao();
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING, "Deseja Inserir Forma de Pagamento?", ButtonType.YES, ButtonType.NO);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.YES) {
					formaPagamentoDAO.addFormaPagamento(getFormaPagamento());

					Alert alertConfirmacao = new Alert(AlertType.INFORMATION, "Forma de Pagamento Inserida", ButtonType.OK);
					alertConfirmacao.showAndWait();
					fechar(getStage(btnSalvar));
				} else {
					cancelarOperacao();
				}
				
			}
		}
	}
	
	private void cancelarOperacao() {
		Alert alertConfirmacao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
		alertConfirmacao.showAndWait();
	}
	
	private void iniciaBotoes() {
		inicializaCampos();
		if(formaPagamento != null) {
			btnExcluir.setDisable(false);
		}else {
			btnExcluir.setDisable(true);
		}
	}
	
	private void inicializaCampos() {
		if (formaPagamento != null) {
			tfdCodigo.setText(formaPagamento.getCodigo());
			tfdNome.setText(formaPagamento.getNome());
			if(formaPagamento.getDescricao() != null) {
				txaDescricao.setText(formaPagamento.getDescricao());
			}
		} else {
			limpaCampos();
		}
	}

	private void limpaCampos() {
		tfdCodigo.setText("");
		tfdNome.setText("");
		txaDescricao.setText("");
	}
	
	private FormaPagamento getFormaPagamento() {
		if (formaPagamento == null) {
			formaPagamento = new FormaPagamento();
		}
		formaPagamento.setCodigo(tfdCodigo.getText());
		formaPagamento.setNome(tfdNome.getText());
		if(!txaDescricao.getText().contentEquals("")) {
			formaPagamento.setDescricao(txaDescricao.getText());
		}

		return formaPagamento;
	}

	private void fechar(Stage stage) {
		controller.iniciaTabela();
		stage.close();
	}

	private Stage getStage(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();

		return stage;
	}

	@FXML
	private void initialize() {
		iniciaBotoes();
	}

}
