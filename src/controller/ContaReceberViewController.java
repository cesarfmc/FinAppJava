package controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import helper.ContaReceberDAO;
import helper.DateHelper;
import helper.MaskFieldUtil;
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
import model.Cliente;
import model.ContaReceber;
import model.FormaPagamento;
import model.PlanoConta;

public class ContaReceberViewController {
	private ContaReceber contaReceber;
	private ContaReceberDAO contaReceberDAO;

	private List<Cliente> listaCliente;
	private List<CentroCusto> listaCentroCusto;
	private List<PlanoConta> listaPlanoConta;
	private List<FormaPagamento> listaFormaPagamento;

	public ContaReceberViewController(ContaReceber contaReceber, ContaReceberDAO contaReceberDAO,
			List<Cliente> listaCliente, List<CentroCusto> listaCentroCusto, List<PlanoConta> listaPlanoConta,
			List<FormaPagamento> listaFormaPagamento) {
		this.contaReceber = contaReceber;
		this.contaReceberDAO = contaReceberDAO;
		this.listaCliente = listaCliente;
		this.listaCentroCusto = listaCentroCusto;
		this.listaPlanoConta = listaPlanoConta;
		this.listaFormaPagamento = listaFormaPagamento;
	}

	@FXML
	private DatePicker dpVenda;

	@FXML
	private DatePicker dpVencimento;

	@FXML
	private TextField tfdNumero;

	@FXML
	private TextField tfdDocumento;

	@FXML
	private TextField tfdValor;
	
	@FXML
	private TextField tfdValorRecebimento;

	@FXML
	private ComboBox<Cliente> cmbCliente;

	@FXML
	private ComboBox<PlanoConta> cmbPlanoConta;

	@FXML
	private ComboBox<CentroCusto> cmbCentroCusto;

	@FXML
	private ComboBox<FormaPagamento> cmbFormaPagamento;

	@FXML
	private TextField tfdNumeroBanco;

	@FXML
	private TextField tfdNumDocRecto;

	@FXML
	private TextField tfdConta;

	@FXML
	private TextField tfdAgencia;

	@FXML
	private DatePicker dpRecto;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnBaixaDoc;

	@FXML
	private Button btnCopiar;

	@FXML
	private Button btnCancelaBaixa;

	@FXML
	private Button btnCancelar;

	@FXML
	void baixaDoc_Click(ActionEvent event) {
		if (contaReceber.getStatus().contentEquals("A")) {
			ativaBaixa();
		} else {
			Alert alertBaixa = new Alert(AlertType.INFORMATION, "Conta ja possui baixa!", ButtonType.OK);
			alertBaixa.showAndWait();
		}
	}

	@FXML
	void cancelaBaixa_Click(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING, "Deseja Remover Baixa?", ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			contaReceber.setStatus("A");
			contaReceber.setValorRecebimento(null);
			contaReceber.setDataRecebimento(null);
			contaReceber.setNumeroBanco(null);
			contaReceber.setNumeroAgencia(null);
			contaReceber.setNumeroBanco(null);

			contaReceberDAO.updateContaReceber(contaReceber);
			Alert alertConfirm = new Alert(AlertType.INFORMATION, "Baixa Removida!", ButtonType.OK);
			alertConfirm.showAndWait();
			fechar(getStage(btnCancelaBaixa));
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
		Alert alert = new Alert(AlertType.WARNING, "Deseja Remover Conta a Receber?", ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			contaReceberDAO.removeContaReceber(contaReceber.getIdContaReceber());
			Alert alertConfirm = new Alert(AlertType.INFORMATION, "Conta a Receber Excluida!", ButtonType.OK);
			alertConfirm.showAndWait();
		} else {
			Alert alertExclusao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
			alertExclusao.showAndWait();
		}
		fechar(getStage(btnExcluir));
	}

	@FXML
	void salvar_Click(ActionEvent event) {
		if (contaReceber == null) {
			if(!tfdValor.getText().isEmpty()) {
				if(dpVenda.getValue() != null) {
					if(dpVencimento.getValue() != null) {
						contaReceberDAO.addContaReceber(getDado());

						Alert alertConfirm = new Alert(AlertType.INFORMATION, "Conta a Receber Inserida!", ButtonType.OK);
						alertConfirm.showAndWait();
						fechar(getStage(btnSalvar));	
					}else {
						Alert alertPagamento = new Alert(AlertType.INFORMATION, "Data de vencimento Obrigatoria!",
								ButtonType.OK);
						alertPagamento.showAndWait();
					}
				}else {
					Alert alertPagamento = new Alert(AlertType.INFORMATION, "Data da Venda Obrigatoria!",
							ButtonType.OK);
					alertPagamento.showAndWait();
				}
				
			}else {
				Alert alertPagamento = new Alert(AlertType.INFORMATION, "Valor Obrigatorio!",
						ButtonType.OK);
				alertPagamento.showAndWait();
			}
		} else {
			contaReceber = getDado();
			if (contaReceber.getValorRecebimento() == null) {
				Alert alertPagamento = new Alert(AlertType.INFORMATION, "Valor de Recebimento Obrigatorio!",
						ButtonType.OK);
				alertPagamento.showAndWait();
			}else {
				if (contaReceber.getDataRecebimento() == null) {
					Alert alertPagamento = new Alert(AlertType.INFORMATION, "Data de Recebimento Obrigatorio!",
							ButtonType.OK);
					alertPagamento.showAndWait();
				}else {
					contaReceberDAO.updateContaReceber(getDado());
					if (btnBaixaDoc.isDisabled()) {
						Alert alertConfirm = new Alert(AlertType.INFORMATION, "Baixa Efetuada!", ButtonType.OK);
						alertConfirm.showAndWait();
					} else {
						Alert alertConfirm = new Alert(AlertType.INFORMATION, "Conta a Receber Alterada!", ButtonType.OK);
						alertConfirm.showAndWait();
					}
					fechar(getStage(btnSalvar));
				}
			}
		}
	}
	private void desativaBaixa() {
		tfdValorRecebimento.setDisable(true);
		tfdNumeroBanco.setDisable(true);
		dpRecto.setDisable(true);
		tfdAgencia.setDisable(true);
		tfdNumDocRecto.setDisable(true);
		tfdConta.setDisable(true);

		btnBaixaDoc.setDisable(true);
		btnCancelaBaixa.setDisable(true);
	}

	private void desativaDadosConta() {
		dpVenda.setDisable(true);
		tfdNumero.setDisable(true);
		tfdDocumento.setDisable(true);
		cmbCliente.setDisable(true);
		tfdValor.setDisable(true);
		cmbPlanoConta.setDisable(true);
		cmbCentroCusto.setDisable(true);
		dpVencimento.setDisable(true);
		cmbFormaPagamento.setDisable(true);
	}

	private void ativaBaixa() {
		tfdValorRecebimento.setDisable(false);
		tfdNumeroBanco.setDisable(false);
		dpRecto.setDisable(false);
		tfdAgencia.setDisable(false);
		tfdNumDocRecto.setDisable(false);
		tfdConta.setDisable(false);

		btnBaixaDoc.setDisable(true);
		desativaDadosConta();
	}

	private ContaReceber getDado() {
		if (contaReceber == null) {
			contaReceber = new ContaReceber();
			contaReceber.setStatus("A");
			contaReceber.setDataInsercao(new Date());
		}

		if(cmbPlanoConta.getSelectionModel().getSelectedItem() != null) {
			contaReceber.setPlanoConta(cmbPlanoConta.getSelectionModel().getSelectedItem());
		}
		if(cmbCentroCusto.getSelectionModel().getSelectedItem() != null) {
			contaReceber.setCentroCusto(cmbCentroCusto.getSelectionModel().getSelectedItem());
		}
		if(cmbFormaPagamento.getSelectionModel().getSelectedItem() != null) {
			contaReceber.setFormaPagamento(cmbFormaPagamento.getSelectionModel().getSelectedItem());
		}
		if(cmbCliente.getSelectionModel().getSelectedItem() != null) {
			contaReceber.setCliente(cmbCliente.getSelectionModel().getSelectedItem());
		}
		contaReceber.setDataVenda(DateHelper.getDate(dpVenda.getValue()));
		if(dpVencimento.getValue() != null) {
			contaReceber.setDataVencimento(DateHelper.getDate(dpVencimento.getValue()));
		}
		if(!tfdNumero.getText().isEmpty()) {
			contaReceber.setNumero(tfdNumero.getText());
		}
		if(!tfdDocumento.getText().isEmpty()) {
			contaReceber.setDocumento(tfdDocumento.getText());
		}
		contaReceber.setValor(MaskFieldUtil.monetaryValueFromField(tfdValor));	

		if (btnBaixaDoc.isDisabled() && contaReceber != null) {

			if (tfdNumeroBanco.getText() != null) {
				contaReceber.setNumeroBanco(tfdNumeroBanco.getText());
			}
			if (tfdValorRecebimento.getText() != null) {
				contaReceber.setValorRecebimento(MaskFieldUtil.monetaryValueFromField(tfdValorRecebimento));
			}
			if (dpRecto.getValue() != null) {
				contaReceber.setDataRecebimento(DateHelper.getDate(dpRecto.getValue()));
			}
			if (tfdAgencia.getText() != null) {
				contaReceber.setNumeroAgencia(tfdAgencia.getText());
			}
			if (tfdNumDocRecto.getText() != null) {
				contaReceber.setNumeroDocRecto(tfdNumDocRecto.getText());
			}
			if (tfdConta.getText() != null) {
				contaReceber.setNumeroConta(tfdConta.getText());
			}

			contaReceber.setStatus("B");
		}

		return contaReceber;
	}

	private void selectCentroCusto() {
		if(contaReceber.getCentroCusto() != null) {
			for (CentroCusto centroCusto : listaCentroCusto) {
				if (centroCusto.getNome().equals(contaReceber.getCentroCusto().getNome())) {
					cmbCentroCusto.getSelectionModel().select(centroCusto);
				}
			}
		}
	}

	private void selectCliente() {
		if(contaReceber.getCliente() != null) {
			for (Cliente cliente : listaCliente) {
				if (cliente.getNome().equals(contaReceber.getCliente().getNome())) {
					cmbCliente.getSelectionModel().select(cliente);
				}
			}
		}
	}

	private void selectFormaPagamento() {
		if(contaReceber.getFormaPagamento() != null) {
			for (FormaPagamento formaPagamento : listaFormaPagamento) {
				if (formaPagamento.getNome().equals(contaReceber.getFormaPagamento().getNome())) {
					cmbFormaPagamento.getSelectionModel().select(formaPagamento);
				}
			}
		}
	}

	private void selectPlanoConta() {
		if(contaReceber.getPlanoConta() != null) {
			for (PlanoConta planoConta : listaPlanoConta) {
				if (planoConta.getNome().equals(contaReceber.getPlanoConta().getNome())) {
					cmbPlanoConta.getSelectionModel().select(planoConta);
				}
			}
		}
	}

	private void limpaCampos() {
		tfdNumero.clear();
		tfdValor.clear();
		tfdValorRecebimento.clear();
		tfdNumDocRecto.clear();
		tfdNumeroBanco.clear();
		tfdAgencia.clear();
		tfdConta.clear();
		
		dpVenda.setValue(null);
		dpVencimento.setValue(null);
		
		cmbCliente.getSelectionModel().clearSelection();
		cmbPlanoConta.getSelectionModel().clearSelection();
		cmbCentroCusto.getSelectionModel().clearSelection();
		cmbFormaPagamento.getSelectionModel().clearSelection();
	}

	private void inicializaBaixa() {
		if (contaReceber.getStatus().contentEquals("B")) {
			btnBaixaDoc.setDisable(true);
			btnCancelaBaixa.setDisable(false);
			tfdValorRecebimento.setText(String.valueOf(contaReceber.getValorRecebimento()));

			if (contaReceber.getNumeroDocRecto() != null) {
				tfdNumDocRecto.setText(contaReceber.getNumeroDocRecto());
			}
			if (contaReceber.getNumeroBanco() != null) {
				tfdNumeroBanco.setText(contaReceber.getNumeroBanco());
			}
			if (contaReceber.getNumeroAgencia() != null) {
				tfdAgencia.setText(contaReceber.getNumeroAgencia());
			}
			if (contaReceber.getNumeroConta() != null) {
				tfdConta.setText(contaReceber.getNumeroConta());
			}
			if (contaReceber.getDataRecebimento() != null) {
				dpRecto.setValue(DateHelper.getLocaltDate(contaReceber.getDataRecebimento().toString()));
			}
		} else {
			btnBaixaDoc.setDisable(false);
		}
	}

	private void inicializaCampos() {
		cmbCentroCusto.setItems(FXCollections.observableArrayList(listaCentroCusto));
		cmbCliente.setItems(FXCollections.observableArrayList(listaCliente));
		cmbFormaPagamento.setItems(FXCollections.observableArrayList(listaFormaPagamento));
		cmbPlanoConta.setItems(FXCollections.observableArrayList(listaPlanoConta));

		desativaBaixa();

		if (contaReceber != null) {
			if (contaReceber.getDataVenda() != null) {
				dpVenda.setValue(DateHelper.getLocaltDate(contaReceber.getDataVenda().toString()));
			}
			if (contaReceber.getDataVencimento() != null) {
				dpVencimento.setValue(DateHelper.getLocaltDate(contaReceber.getDataVencimento().toString()));
			}
			if (contaReceber.getNumero() != null) {
				tfdNumero.setText(contaReceber.getNumero());
			}
			if (contaReceber.getDocumento() != null) {
				tfdDocumento.setText(contaReceber.getDocumento());
			}
			if (contaReceber.getValor() != null) {
				tfdValor.setText(String.valueOf(contaReceber.getValor()));
			}
			btnExcluir.setDisable(false);
			inicializaBaixa();

			selectCentroCusto();
			selectCliente();
			selectFormaPagamento();
			selectPlanoConta();
		} else {
			btnExcluir.setDisable(true);
			limpaCampos();
		}
	}
	private void setMask() {
		MaskFieldUtil.monetaryField(tfdValor);
		MaskFieldUtil.monetaryField(tfdValorRecebimento);
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
		setMask();
		inicializaCampos();
	}
}
