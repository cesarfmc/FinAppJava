package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import helper.CentroCustoDAO;
import helper.ClienteDAO;
import helper.ContaReceberDAO;
import helper.DateHelper;
import helper.FormaPagamentoDAO;
import helper.HibernateUtil2;
import helper.PlanoContaDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.CentroCusto;
import model.Cliente;
import model.ContaReceber;
import model.FormaPagamento;
import model.PlanoConta;

public class ContaReceberListViewController {

	private List<Cliente> listaCliente;
	private List<CentroCusto> listaCentroCusto;
	private List<PlanoConta> listaPlanoConta;
	private List<FormaPagamento> listaFormaPagamento;
	private List<ContaReceber> listaContaReceber;

	private Session sessao = HibernateUtil2.getSessionFactory().openSession();
	private ClienteDAO clienteDAO = new ClienteDAO(sessao);
	private CentroCustoDAO centroCustoDAO = new CentroCustoDAO(sessao);
	private PlanoContaDAO planoContaDAO = new PlanoContaDAO(sessao);
	private FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO(sessao);
	private ContaReceberDAO contaReceberDAO = new ContaReceberDAO(sessao);

	
    @FXML
    private TextField tfdNumero;

    @FXML
    private ComboBox<Cliente> cmbCliente;

    @FXML
    private ComboBox<CentroCusto> cmbCentroCusto;
    
    @FXML
    private ComboBox<PlanoConta> cmbPlanoConta;
    
    @FXML
    private ComboBox<FormaPagamento> cmbFormaPagamento;
    
    @FXML
    private DatePicker dpVendaInicio;

    @FXML
    private DatePicker dpVendaFinal;

    @FXML
    private DatePicker dpRecebimentoInicio;

    @FXML
    private DatePicker dpRecebimentoFinal;

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
    private TableView<ContaReceber> tbvContaReceber;

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
    	listaContaReceber = contaReceberDAO.listContaReceber();

		tbvContaReceber.setItems(FXCollections.observableArrayList(listaContaReceber));
    }

    @FXML
    void inserir_Click(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ContaReceberView.fxml"));
		loader.setController(new ContaReceberViewController(null, contaReceberDAO, listaCliente, listaCentroCusto,
				listaPlanoConta, listaFormaPagamento));
		Stage stage = new Stage();

		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Nova Conta Receber");
		stage.setScene(new Scene(loader.load()));
		stage.showAndWait();
    }
    @FXML
	void editarContaReceber_Click(MouseEvent event) throws IOException {
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			ContaReceber contaReceber = tbvContaReceber.getSelectionModel().getSelectedItem();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ContaReceberView.fxml"));
			loader.setController(new ContaReceberViewController(contaReceber, contaReceberDAO, listaCliente,
					listaCentroCusto, listaPlanoConta, listaFormaPagamento));
			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Editar Conta Receber");
			stage.setScene(new Scene(loader.load()));
			stage.showAndWait();
		}
	}
    
    @FXML
    void limpar_Click(ActionEvent event) {
    	tbvContaReceber.getItems().clear();
    }

    @FXML
    void pesquisar_Click(ActionEvent event) {
    	if (dpVencimentoInicio.getValue() == null && cmbCliente.getSelectionModel().getSelectedItem() == null
				&& cmbCentroCusto.getSelectionModel().getSelectedItem() == null
				&& cmbPlanoConta.getSelectionModel().getSelectedItem() == null
				&& cmbFormaPagamento.getSelectionModel().getSelectedItem() == null) {

			Alert alert = new Alert(AlertType.INFORMATION, "Preencha ao menos um campo!", ButtonType.OK);
			alert.showAndWait();
		} else {
			pesquisaTudo();
		}
    }

    @FXML
    void recebido_Click(ActionEvent event) {
    	listaContaReceber = contaReceberDAO.listContaReceberStatus("B");

		tbvContaReceber.setItems(FXCollections.observableArrayList(listaContaReceber));
    }

    @FXML
    void relatorio_Click(ActionEvent event) {

    }

    @FXML
    void sair_Click(ActionEvent event) {
    	fechar(getStage(btnSair));
    }

    @FXML
    void todo_Click(ActionEvent event) {
    	listaContaReceber = contaReceberDAO.listContaReceber();

		tbvContaReceber.setItems(FXCollections.observableArrayList(listaContaReceber));
    }
    @FXML
	private void initialize() {
		inicializaCampos();
	}
    private void fechar(Stage stage) {
		stage.close();
	}
    public Cliente getCliente() {
		return cmbCliente.getSelectionModel().getSelectedItem();
	}

	public CentroCusto getCentroCusto() {
		return cmbCentroCusto.getSelectionModel().getSelectedItem();
	}

	public PlanoConta getPlanoConta() {
		return cmbPlanoConta.getSelectionModel().getSelectedItem();
	}

	public FormaPagamento getFormaPagamento() {
		return cmbFormaPagamento.getSelectionModel().getSelectedItem();
	}

	private Stage getStage(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();

		return stage;
	}
	
	public void pesquisaTudo() {
		Date dataInicio = null, dataFim = null;
		if (dpVencimentoInicio.getValue() != null && dpVencimentoFinal.getValue() != null) {
			dataInicio = DateHelper.getDate(dpVencimentoInicio.getValue());
			dataFim = DateHelper.getDate(dpVencimentoFinal.getValue());
		}

		listaContaReceber = contaReceberDAO.listContaReceberTudo(dataInicio, dataFim, getCliente(), getCentroCusto(),
				getPlanoConta(), getFormaPagamento());

		if (listaContaReceber.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION, "Nenhum Dado Encontrado!", ButtonType.OK);
			alert.showAndWait();
		} else {
			tbvContaReceber.setItems(FXCollections.observableArrayList(listaContaReceber));
		}
	}
	
	private void inicializaCampos() {
		listaCliente = clienteDAO.listCliente();
		listaCentroCusto = centroCustoDAO.listCentroCusto();
		listaFormaPagamento = formaPagamentoDAO.listFormaPagamento();
		listaPlanoConta = planoContaDAO.listPlanoConta();

		cmbCentroCusto.setItems(FXCollections.observableArrayList(listaCentroCusto));
		cmbCliente.setItems(FXCollections.observableArrayList(listaCliente));
		cmbFormaPagamento.setItems(FXCollections.observableArrayList(listaFormaPagamento));
		cmbPlanoConta.setItems(FXCollections.observableArrayList(listaPlanoConta));
	}
	

}
