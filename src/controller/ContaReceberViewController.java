package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.CentroCusto;
import model.Cliente;
import model.FormaPagamento;
import model.PlanoConta;

public class ContaReceberViewController {

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

    }

    @FXML
    void cancelaBaixa_Click(ActionEvent event) {

    }

    @FXML
    void cancelar_Click(ActionEvent event) {

    }

    @FXML
    void copiar_Click(ActionEvent event) {

    }

    @FXML
    void excluir_Click(ActionEvent event) {

    }

    @FXML
    void salvar_Click(ActionEvent event) {

    }

    private void fechar(Stage stage) {
		stage.close();
	}

	private Stage getStage(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();

		return stage;
	}
}
