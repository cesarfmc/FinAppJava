package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.CentroCusto;
import model.Cliente;
import model.ContaReceber;
import model.FormaPagamento;

public class ContaReceberListViewController {

    @FXML
    private TextField tfdNumero;

    @FXML
    private ComboBox<Cliente> cmbCliente;

    @FXML
    private ComboBox<CentroCusto> cmbCentroCusto;

    @FXML
    private DatePicker dpVendaInicio;

    @FXML
    private DatePicker dpVendaFinal;

    @FXML
    private DatePicker dpRecebimentoInicio;

    @FXML
    private DatePicker dpRecebimentoFinal;

    @FXML
    private ComboBox<FormaPagamento> cmbFormaPagamento;

    @FXML
    private DatePicker dpVencimentoInicio;

    @FXML
    private DatePicker dpVencimentoFinal;

    @FXML
    private TextField tfdValorInicio;

    @FXML
    private TextField tfdValorFinal;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnRelatorio;

    @FXML
    private Button btnSair;

    @FXML
    private TableView<ContaReceber> trvContaReceber;

    @FXML
    private Button btnAberto;

    @FXML
    private Button btnRecebidos;

    @FXML
    private Button btnTodos;

    @FXML
    private TextField tfdQtd;

    @FXML
    private TextField tfdValorTotal;

    @FXML
    void aberto_Click(ActionEvent event) {

    }

    @FXML
    void inserir_Click(ActionEvent event) {

    }

    @FXML
    void limpar_Click(ActionEvent event) {

    }

    @FXML
    void pesquisar_Click(ActionEvent event) {

    }

    @FXML
    void recebido_Click(ActionEvent event) {

    }

    @FXML
    void relatorio_Click(ActionEvent event) {

    }

    @FXML
    void sair_Click(ActionEvent event) {

    }

    @FXML
    void todo_Click(ActionEvent event) {

    }
    
    private void fechar(Stage stage) {
		stage.close();
	}

	private Stage getStage(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();

		return stage;
	}

}
