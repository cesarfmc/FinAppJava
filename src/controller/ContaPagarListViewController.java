package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import helper.CentroCustoDAO;
import helper.ContaPagarDAO;
import helper.DateHelper;
import helper.FormaPagamentoDAO;
import helper.FornecedorDAO;
import helper.HibernateUtil2;
import helper.PlanoContaDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.CentroCusto;
import model.ContaPagar;
import model.FormaPagamento;
import model.Fornecedor;
import model.PlanoConta;

public class ContaPagarListViewController {

	private List<Fornecedor> listaFornecedor;
	private List<CentroCusto> listaCentroCusto;
	private List<PlanoConta> listaPlanoConta;
	private List<FormaPagamento> listaFormaPagamento;
	private List<ContaPagar> listaContaPagar;

	private Session sessao = HibernateUtil2.getSessionFactory().openSession();
	private FornecedorDAO fornecedorDAO = new FornecedorDAO(sessao);
	private CentroCustoDAO centroCustoDAO = new CentroCustoDAO(sessao);
	private PlanoContaDAO planoContaDAO = new PlanoContaDAO(sessao);
	private FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO(sessao);
	private ContaPagarDAO contaPagarDAO = new ContaPagarDAO(sessao);

	@FXML
	private TextField tfdNumero;

	@FXML
	private TextField tfdValorInicio;

	@FXML
	private TextField tfdValorFinal;

	@FXML
	private ComboBox<Fornecedor> cmbFornecedor;

	@FXML
	private ComboBox<PlanoConta> cmbPlanoConta;

	@FXML
	private ComboBox<CentroCusto> cmbCentroCusto;

	@FXML
	private ComboBox<FormaPagamento> cmbFormaPagamento;

	@FXML
	private DatePicker dpCompraInicio;

	@FXML
	private DatePicker dpPagamentoInicio;

	@FXML
	private DatePicker dpVencimentoInicio;

	@FXML
	private DatePicker dpVencimentoFinal;
	
	@FXML
	private DatePicker dpCompraFinal;

	@FXML
	private DatePicker dpPagamentoFinal;

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
	private Button btnAberto;

	@FXML
	private Button btnPago;

	@FXML
	private Button btnTodos;

	@FXML
	private TextField tfdQtd;

	@FXML
	private TextField tfdValorTotal;

	@FXML
	private TableView<ContaPagar> tbvContaPagar;

	@FXML
	private TableColumn<?, ?> tcDataCompra;

	@FXML
	private TableColumn<?, ?> tcNumero;

	@FXML
	private TableColumn<?, ?> tcDocumento;

	@FXML
	private TableColumn<?, ?> tcFornecedor;

	@FXML
	private TableColumn<?, ?> tcValor;

	@FXML
	private TableColumn<?, ?> tcDataVencimento;

	@FXML
	private TableColumn<?, ?> tcPlanoConta;

	@FXML
	private TableColumn<?, ?> tcCeentroCusto;

	@FXML
	private TableColumn<?, ?> tcFormaPagamento;

	@FXML
	private TableColumn<?, ?> tcValorPagamento;

	@FXML
	private TableColumn<?, ?> tcDataPagamento;

	@FXML
	private TableColumn<?, ?> tcBanco;

	@FXML
	private TableColumn<?, ?> tcAgencia;

	@FXML
	private TableColumn<?, ?> tcNumeroConta;

	@FXML
	private TableColumn<?, ?> tcNumeroDocumento;

	@FXML
	void aberto_Click(ActionEvent event) {

	}

	@FXML
	void inserir_Click(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ContaPagarView.fxml"));
		loader.setController(new ContaPagarViewController(null, contaPagarDAO, listaFornecedor, listaCentroCusto,
				listaPlanoConta, listaFormaPagamento));
		Stage stage = new Stage();

		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Nova Conta Pagar");
		stage.setScene(new Scene(loader.load()));
		stage.showAndWait();
	}

	@FXML
	void editarContaPagar_Click(MouseEvent event) throws IOException {
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			ContaPagar contaPagar = tbvContaPagar.getSelectionModel().getSelectedItem();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ContaPagarView.fxml"));
			loader.setController(new ContaPagarViewController(contaPagar, contaPagarDAO, listaFornecedor,
					listaCentroCusto, listaPlanoConta, listaFormaPagamento));
			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Editar Fornecedor");
			stage.setScene(new Scene(loader.load()));
			stage.showAndWait();
		}
	}

	@FXML
	void limpar_Click(ActionEvent event) {
		tbvContaPagar.getItems().clear();
	}

	@FXML
	void pago_Click(ActionEvent event) {

	}

	public void pesquisaDataVencimento() {
		Date dataInicio, dataFim;
		dataInicio = DateHelper.getDate(dpVencimentoInicio.getValue());
		dataFim = DateHelper.getDate(dpVencimentoFinal.getValue());
		
		listaContaPagar = contaPagarDAO.listContaPagarDataVencimento(dataInicio, dataFim);

		tbvContaPagar.setItems(FXCollections.observableArrayList(listaContaPagar));
	}
	
	public void pesquisaFornecedor() {
		Fornecedor fornecedor = cmbFornecedor.getSelectionModel().getSelectedItem();
		
		listaContaPagar = contaPagarDAO.listContaPagarFornecedor(fornecedor);

		tbvContaPagar.setItems(FXCollections.observableArrayList(listaContaPagar));

	}
	
	public void pesquisaCentroCusto() {
		CentroCusto centroCusto= cmbCentroCusto.getSelectionModel().getSelectedItem();
		
		listaContaPagar = contaPagarDAO.listContaPagarCentroCusto(centroCusto);

		tbvContaPagar.setItems(FXCollections.observableArrayList(listaContaPagar));

	}
	
	public void pesquisaPlanoConta() {
		PlanoConta planoConta = cmbPlanoConta.getSelectionModel().getSelectedItem();
		
		listaContaPagar = contaPagarDAO.listContaPagarPlanoConta(planoConta);

		tbvContaPagar.setItems(FXCollections.observableArrayList(listaContaPagar));

	}

	public void pesquisaFormaPagamento() {
		FormaPagamento formaPagamento = cmbFormaPagamento.getSelectionModel().getSelectedItem();
		
		listaContaPagar = contaPagarDAO.listContaPagarFormaPagamento(formaPagamento);

		tbvContaPagar.setItems(FXCollections.observableArrayList(listaContaPagar));

	}

	@FXML
	void pesquisar_Click(ActionEvent event) {
		if(dpVencimentoInicio.getValue() != null) {
			pesquisaDataVencimento();
		}else {
			if(cmbFornecedor.getSelectionModel().getSelectedItem() != null) {
				pesquisaFornecedor();
			}else {
				if(cmbCentroCusto.getSelectionModel().getSelectedItem() != null) {
					pesquisaCentroCusto();
				}else {
					if(cmbPlanoConta.getSelectionModel().getSelectedItem() != null) {
						pesquisaPlanoConta();
					}else {
						if(cmbFormaPagamento.getSelectionModel().getSelectedItem() != null) {
							pesquisaFormaPagamento();
						}
					}
				}
			}
		}
		
	}

	@FXML
	void relatorio_Click(ActionEvent event) {

	}

	@FXML
	void sair_Click(ActionEvent event) {
		sessao.close();
		fechar(getStage(btnSair));
	}

	@FXML
	void todos_Click(ActionEvent event) {
		listaContaPagar = contaPagarDAO.listContaPagar();

		tbvContaPagar.setItems(FXCollections.observableArrayList(listaContaPagar));
	}

	private void inicializaCampos() {
		listaFornecedor = fornecedorDAO.listFornecedor();
		listaCentroCusto = centroCustoDAO.listCentroCusto();
		listaFormaPagamento = formaPagamentoDAO.listFormaPagamento();
		listaPlanoConta = planoContaDAO.listPlanoConta();

		cmbCentroCusto.setItems(FXCollections.observableArrayList(listaCentroCusto));
		cmbFornecedor.setItems(FXCollections.observableArrayList(listaFornecedor));
		cmbFormaPagamento.setItems(FXCollections.observableArrayList(listaFormaPagamento));
		cmbPlanoConta.setItems(FXCollections.observableArrayList(listaPlanoConta));
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