package controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import helper.ContaPagarDAO;
import helper.DateHelper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.CentroCusto;
import model.ContaPagar;
import model.FormaPagamento;
import model.Fornecedor;
import model.PlanoConta;

public class ContaPagarViewController {
	private ContaPagar contaPagar;
	private ContaPagarDAO contaPagarDAO;

	private List<Fornecedor> listaFornecedor;
	private List<CentroCusto> listaCentroCusto;
	private List<PlanoConta> listaPlanoConta;
	private List<FormaPagamento> listaFormaPagamento;

	public ContaPagarViewController(ContaPagar contaPagar, ContaPagarDAO contaPagarDAO,
			List<Fornecedor> listaFornecedor, List<CentroCusto> listaCentroCusto, List<PlanoConta> listaPlanoConta,
			List<FormaPagamento> listaFormaPagamento) {
		this.contaPagar = contaPagar;
		this.contaPagarDAO = contaPagarDAO;
		this.listaFornecedor = listaFornecedor;
		this.listaCentroCusto = listaCentroCusto;
		this.listaPlanoConta = listaPlanoConta;
		this.listaFormaPagamento = listaFormaPagamento;
	}

	@FXML
	private DatePicker dpCompra;

	@FXML
	private TextField tfdNumero;

	@FXML
	private TextField tfdDocumento;

	@FXML
	private ComboBox<Fornecedor> cmbFornecedor;

	@FXML
	private TextField tfdValor;

	@FXML
	private ComboBox<PlanoConta> cmbPlanoConta;

	@FXML
	private ComboBox<FormaPagamento> cmbFormaPagamento;

	@FXML
	private ComboBox<CentroCusto> cmbCentroCusto;

	@FXML
	private DatePicker dpVencimento;

	@FXML
	private DatePicker dpPagamento;

	@FXML
	private TextField tfdValorPagamento;

	@FXML
	private TextField tfdNumeroBanco;

	@FXML
	private TextField tfdAgencia;

	@FXML
	private TextField tfdNumeroDocPagamento;

	@FXML
	private TextField tfdConta;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnBaixaDoc;

	@FXML
	private Button btnCancelarBaixa;

	@FXML
	private Button btnCopiar;

	@FXML
	private Button btnCancelar;

	@FXML
	void baixarDoc_Click(ActionEvent event) {
		if (contaPagar.getStatus().contentEquals("A")) {
			ativaBaixa();
		} else {
			Alert alertBaixa = new Alert(AlertType.INFORMATION, "Conta já possui baixa!", ButtonType.OK);
			alertBaixa.showAndWait();
		}
	}

	@FXML
	void cancelarBaixa_Click(ActionEvent event) {
		contaPagar.setStatus("A");
		contaPagar.setValorPagamento(null);
		contaPagar.setDataPagamento(null);
		contaPagar.setNumeroBanco(null);
		contaPagar.setNumeroAgencia(null);
		contaPagar.setNumeroBanco(null);

		Alert alert = new Alert(AlertType.WARNING, "Deseja Remover Baixa?", ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			contaPagarDAO.updateContaPagar(contaPagar);
			Alert alertConfirm = new Alert(AlertType.INFORMATION, "Baixa Removida!", ButtonType.OK);
			alertConfirm.showAndWait();
			fechar(getStage(btnCancelarBaixa));
		} else {
			Alert alertExclusao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
			alertExclusao.showAndWait();
		}
	}

	@FXML
	void cancelar_Click(ActionEvent event) {
		fechar(getStage(btnCancelar));
	}

	@FXML
	void copiar_Click(ActionEvent event) {

	}

	@FXML
	void excluir_Click(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING, "Deseja Remover Conta a Pagar?", ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			contaPagarDAO.removeContaPagar(contaPagar.getIdContaPagar());
			Alert alertConfirm = new Alert(AlertType.INFORMATION, "Conta a Pagar Excluida!", ButtonType.OK);
			alertConfirm.showAndWait();
		} else {
			Alert alertExclusao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
			alertExclusao.showAndWait();
		}
		fechar(getStage(btnExcluir));
	}

	private void salvar() {
		if (contaPagar == null) {
			contaPagarDAO.addContaPagar(getDado());

			Alert alertConfirm = new Alert(AlertType.INFORMATION, "Conta a Pagar Inserida!", ButtonType.OK);
			alertConfirm.showAndWait();
			fechar(getStage(btnSalvar));
		} else {
			contaPagar = getDado();
			if (contaPagar.getValorPagamento() == null) {
				Alert alertPagamento = new Alert(AlertType.INFORMATION, "Valor de Pagamento Obrigatório!",
						ButtonType.OK);
				alertPagamento.showAndWait();
			}else {
				contaPagarDAO.updateContaPagar(getDado());
				if (btnBaixaDoc.isDisabled() == true) {
					Alert alertConfirm = new Alert(AlertType.INFORMATION, "Baixa Efetuada!", ButtonType.OK);
					alertConfirm.showAndWait();
				} else {
					Alert alertConfirm = new Alert(AlertType.INFORMATION, "Conta a Pagar Alterada!", ButtonType.OK);
					alertConfirm.showAndWait();
				}
				fechar(getStage(btnSalvar));
			}

		}
	}

	@FXML
	void salvar_Click(ActionEvent event) {
		salvar();
	}

	private void desativaBaixa() {
		tfdValorPagamento.setDisable(true);
		tfdNumeroBanco.setDisable(true);
		dpPagamento.setDisable(true);
		tfdAgencia.setDisable(true);
		tfdNumeroDocPagamento.setDisable(true);
		tfdConta.setDisable(true);

		btnCancelarBaixa.setDisable(true);
	}

	private void desativaDadosConta() {
		dpCompra.setDisable(true);
		tfdNumero.setDisable(true);
		tfdDocumento.setDisable(true);
		cmbFornecedor.setDisable(true);
		tfdValor.setDisable(true);
		cmbPlanoConta.setDisable(true);
		cmbCentroCusto.setDisable(true);
		dpVencimento.setDisable(true);
		cmbFormaPagamento.setDisable(true);
	}

	private void ativaBaixa() {
		tfdValorPagamento.setDisable(false);
		tfdNumeroBanco.setDisable(false);
		dpPagamento.setDisable(false);
		tfdAgencia.setDisable(false);
		tfdNumeroDocPagamento.setDisable(false);
		tfdConta.setDisable(false);

		btnBaixaDoc.setDisable(true);
		desativaDadosConta();
	}

	private ContaPagar getDado() {
		if (contaPagar == null) {
			contaPagar = new ContaPagar();
			contaPagar.setStatus("A");
		}

		contaPagar.setPlanoConta(cmbPlanoConta.getSelectionModel().getSelectedItem());
		contaPagar.setCentroCusto(cmbCentroCusto.getSelectionModel().getSelectedItem());
		contaPagar.setFormaPagamento(cmbFormaPagamento.getSelectionModel().getSelectedItem());
		contaPagar.setFornecedor(cmbFornecedor.getSelectionModel().getSelectedItem());

		contaPagar.setDataCompra(DateHelper.getDate(dpCompra.getValue()));
		contaPagar.setDataVencimento(DateHelper.getDate(dpVencimento.getValue()));
		contaPagar.setDataInsercao(new Date());

		contaPagar.setNumero(tfdNumero.getText());
		contaPagar.setDocumento(tfdDocumento.getText());
		contaPagar.setValor(new BigDecimal(tfdValor.getText()));

		if (btnBaixaDoc.isDisabled() == true) {

			if (tfdNumeroBanco.getText() != null) {
				contaPagar.setNumeroBanco(tfdNumeroBanco.getText());
			}
			if (dpPagamento.getValue() != null) {
				contaPagar.setDataPagamento(DateHelper.getDate(dpPagamento.getValue()));
			}
			if (tfdAgencia.getText() != null) {
				contaPagar.setNumeroAgencia(tfdAgencia.getText());
			}
			if (tfdNumeroDocPagamento.getText() != null) {
				contaPagar.setNumeroDocPagto(tfdNumeroDocPagamento.getText());
			}
			if (tfdConta.getText() != null) {
				contaPagar.setNumeroConta(tfdConta.getText());
			}

			contaPagar.setStatus("B");
		}

		return contaPagar;
	}

	private void selectCentroCusto() {
		for (CentroCusto centroCusto : listaCentroCusto) {
			if (centroCusto.getNome().equals(contaPagar.getCentroCusto().getNome())) {
				cmbCentroCusto.getSelectionModel().select(centroCusto);
			}
		}
	}

	private void selectFornecedor() {
		for (Fornecedor fornecedor : listaFornecedor) {
			if (fornecedor.getNome().equals(contaPagar.getFornecedor().getNome())) {
				cmbFornecedor.getSelectionModel().select(fornecedor);
			}
		}
	}

	private void selectFormaPagamento() {
		for (FormaPagamento formaPagamento : listaFormaPagamento) {
			if (formaPagamento.getNome().equals(contaPagar.getFormaPagamento().getNome())) {
				cmbFormaPagamento.getSelectionModel().select(formaPagamento);
			}
		}
	}

	private void selectPlanoConta() {
		for (PlanoConta planoConta : listaPlanoConta) {
			if (planoConta.getNome().equals(contaPagar.getPlanoConta().getNome())) {
				cmbPlanoConta.getSelectionModel().select(planoConta);
			}
		}
	}

	private void limpaCampos() {
		tfdNumero.setText("");
		tfdValor.setText("");
		tfdValorPagamento.setText("");
		tfdNumeroDocPagamento.setText("");
		tfdNumeroBanco.setText("");
		tfdAgencia.setText("");
		tfdConta.setText("");

	}

	private void inicializaBaixa() {
		if (contaPagar.getStatus().contentEquals("B")) {
			btnBaixaDoc.setDisable(true);
			btnCancelarBaixa.setDisable(false);
			tfdValorPagamento.setText(String.valueOf(contaPagar.getValorPagamento()));

			if (contaPagar.getNumeroDocPagto() != null) {
				tfdNumeroDocPagamento.setText(contaPagar.getNumeroDocPagto());
			}
			if (contaPagar.getNumeroBanco() != null) {
				tfdNumeroBanco.setText(contaPagar.getNumeroBanco());
			}
			if (contaPagar.getNumeroAgencia() != null) {
				tfdAgencia.setText(contaPagar.getNumeroAgencia());
			}
			if (contaPagar.getNumeroConta() != null) {
				tfdConta.setText(contaPagar.getNumeroConta());
			}
			if (contaPagar.getDataPagamento() != null) {
				dpPagamento.setValue(DateHelper.getLocaltDate(contaPagar.getDataPagamento().toString()));
			}
		} else {
			btnBaixaDoc.setDisable(false);
		}
	}

	private void inicializaCampos() {
		cmbCentroCusto.setItems(FXCollections.observableArrayList(listaCentroCusto));
		cmbFornecedor.setItems(FXCollections.observableArrayList(listaFornecedor));
		cmbFormaPagamento.setItems(FXCollections.observableArrayList(listaFormaPagamento));
		cmbPlanoConta.setItems(FXCollections.observableArrayList(listaPlanoConta));

		desativaBaixa();

		if (contaPagar != null) {
			if (contaPagar.getDataCompra() != null) {
				dpCompra.setValue(DateHelper.getLocaltDate(contaPagar.getDataCompra().toString()));
			}
			if (contaPagar.getDataVencimento() != null) {
				dpVencimento.setValue(DateHelper.getLocaltDate(contaPagar.getDataVencimento().toString()));
			}
			if (contaPagar.getNumero() != null) {
				tfdNumero.setText(contaPagar.getNumero());
			}
			if (contaPagar.getDocumento() != null) {
				tfdDocumento.setText(contaPagar.getDocumento());
			}
			if (contaPagar.getValor() != null) {
				tfdValor.setText(String.valueOf(contaPagar.getValor()));
			}

			inicializaBaixa();

			selectCentroCusto();
			selectFornecedor();
			selectFormaPagamento();
			selectPlanoConta();
		} else {
			limpaCampos();
		}
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
