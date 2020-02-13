package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import helper.CentroCustoDAO;
import helper.ContaPagarDAO;
import helper.DateHelper;
import helper.FormaPagamentoDAO;
import helper.FornecedorDAO;
import helper.HibernateUtil2;
import helper.MaskFieldUtil;
import helper.PlanoContaDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
	
	private Date dataVencimentoInicio = null, dataVencimentoFim = null;
	private Date dataCompraInicio = null, dataCompraFim = null;
	private Date dataPagamentoInicio = null, dataPagamentoFim = null;
	private BigDecimal valorInicial, valorFinal;
	
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
		List<ContaPagar> lista= new ArrayList<ContaPagar>(listaContaPagar);
		for (ContaPagar contaPagar : lista) {
			if(contaPagar.getStatus().contentEquals("B")) {
				tbvContaPagar.getItems().remove(contaPagar);
			}
		}
		tfdValorTotal.setText(calcTotal());
		tfdQtd.setText(totalItens());
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
			stage.setTitle("Editar Conta Pagar");
			stage.setScene(new Scene(loader.load()));
			stage.showAndWait();
		}
	}

	@FXML
	void limpar_Click(ActionEvent event) {
		tbvContaPagar.getItems().clear();
		listaContaPagar.clear();

		tfdNumero.clear();
		tfdValorInicio.clear();
		tfdValorFinal.clear();
		tfdQtd.clear();
		tfdValorTotal.clear();
		
		cmbCentroCusto.getSelectionModel().clearSelection();
		cmbFormaPagamento.getSelectionModel().clearSelection();
		cmbFornecedor.getSelectionModel().clearSelection();
		cmbPlanoConta.getSelectionModel().clearSelection();
	
		dpCompraInicio.setValue(null);
		dpCompraFinal.setValue(null);
		dpVencimentoInicio.setValue(null);
		dpVencimentoFinal.setValue(null);
		dpPagamentoInicio.setValue(null);
		dpPagamentoFinal.setValue(null);
	}

	@FXML
	void pago_Click(ActionEvent event) {
		List<ContaPagar> lista= new ArrayList<ContaPagar>(listaContaPagar);
		for (ContaPagar contaPagar : lista) {
			if(contaPagar.getStatus().contentEquals("A")) {
				tbvContaPagar.getItems().remove(contaPagar);
			}
		}
		tfdValorTotal.setText(calcTotal());
		tfdQtd.setText(totalItens());
	}

	public String calcTotal() {
		BigDecimal total = new BigDecimal("0.0");
		List<ContaPagar> lista= new ArrayList<ContaPagar>(tbvContaPagar.getItems());
		for (ContaPagar contaPagar : lista) {
			total = total.add(contaPagar.getValor());
		}
		return total.toString();
	}
	
	public Fornecedor getFornecedor() {
		return cmbFornecedor.getSelectionModel().getSelectedItem();
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
	
	public void verificaLista() {
		if (listaContaPagar.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION, "Nenhum Dado Encontrado!", ButtonType.OK);
			alert.showAndWait();
		} else {
			tbvContaPagar.setItems(FXCollections.observableArrayList(listaContaPagar));
		}
	}
	
	public String getNumero() {
		return tfdNumero.getText();
	}
	
	public int getFaixaValores() {
		if (!tfdValorInicio.getText().contentEquals("") && !tfdValorFinal.getText().contentEquals("")) {
			if(MaskFieldUtil.monetaryValueFromField(tfdValorInicio).compareTo(MaskFieldUtil.monetaryValueFromField(tfdValorFinal)) <= 0)  {
				valorInicial = MaskFieldUtil.monetaryValueFromField(tfdValorInicio);
				valorFinal = MaskFieldUtil.monetaryValueFromField(tfdValorFinal);
				
				return 1;
			}else {
				return 0;
			}
		}else {
			return 1;
		}
	}

	
	public int getDatasCompra() {
		dataCompraInicio = null;
		dataCompraFim = null;
		
		if (dpCompraInicio.getValue() != null && dpCompraFinal.getValue() != null) {
			if(dpCompraFinal.getValue().isAfter(dpCompraInicio.getValue()) || dpCompraFinal.getValue().isEqual(dpCompraInicio.getValue())) {
				dataCompraInicio = DateHelper.getDate(dpCompraInicio.getValue());
				dataCompraFim = DateHelper.getDate(dpCompraFinal.getValue());
				
			}else {
				return 0;
			}
		}
		return 1;
	}

	public int getDatasVencimento() {
		dataVencimentoInicio = null;
		dataVencimentoFim = null;

		if (dpVencimentoInicio.getValue() != null && dpVencimentoFinal.getValue() != null) {
			if(dpVencimentoFinal.getValue().isAfter(dpVencimentoInicio.getValue()) || dpVencimentoFinal.getValue().isEqual(dpVencimentoInicio.getValue())) {
				dataVencimentoInicio = DateHelper.getDate(dpVencimentoInicio.getValue());
				dataVencimentoFim = DateHelper.getDate(dpVencimentoFinal.getValue());
				
			}else {
				return 0;
			}
		}
		return 1;
	}

	public int getDatasPagamento() {
		dataPagamentoInicio = null;
		dataPagamentoFim = null;
		if (dpPagamentoInicio.getValue() != null && dpPagamentoFinal.getValue() != null) {
			if(dpPagamentoFinal.getValue().isAfter(dpPagamentoInicio.getValue()) || dpPagamentoFinal.getValue().isEqual(dpPagamentoInicio.getValue())) {
				dataPagamentoInicio = DateHelper.getDate(dpPagamentoInicio.getValue());
				dataPagamentoFim = DateHelper.getDate(dpPagamentoFinal.getValue());
				
			}else {
				return 0;
			}
		}
		return 1;
	}

	public void pesquisaTudo() {
		if(getDatasCompra() != 1 || getDatasVencimento() != 1 || getDatasPagamento() != 1) {
				Alert alert = new Alert(AlertType.INFORMATION, "Data Invalida!", ButtonType.OK);
				alert.showAndWait();
		}else {
			if(getFaixaValores() != 1) {
				Alert alert = new Alert(AlertType.INFORMATION, "Valor Invalido!", ButtonType.OK);
				alert.showAndWait();
			}else {
				listaContaPagar = contaPagarDAO.listContaPagarTudo(getNumero(),valorInicial,valorFinal,dataCompraInicio, dataCompraFim, dataVencimentoInicio, dataVencimentoFim, dataPagamentoInicio,dataPagamentoFim, 
						getFornecedor(), getCentroCusto(), getPlanoConta(), getFormaPagamento());

				verificaLista();
			}
		}
	}

	public String totalItens() {
		int totalItens = tbvContaPagar.getItems().size();
		return String.valueOf(totalItens);
	}
	@FXML
	void pesquisar_Click(ActionEvent event) {
		if (tfdNumero.getText().contentEquals("") && dpCompraInicio.getValue() == null && dpVencimentoInicio.getValue() == null && dpPagamentoInicio.getValue() == null && 
				cmbFornecedor.getSelectionModel().getSelectedItem() == null && cmbCentroCusto.getSelectionModel().getSelectedItem() == null
				&& cmbPlanoConta.getSelectionModel().getSelectedItem() == null && cmbFormaPagamento.getSelectionModel().getSelectedItem() == null
				&& tfdValorInicio.getText().contentEquals("")) {

			Alert alert = new Alert(AlertType.INFORMATION, "Preencha ao menos um campo!", ButtonType.OK);
			alert.showAndWait();
		} else {
			pesquisaTudo();
			tfdValorTotal.setText(calcTotal());
			tfdQtd.setText(totalItens());
		}
	}

	@FXML
	void relatorio_Click(ActionEvent event) {
		try {
			Parent root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ContaPagarReportView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Relatorio Contas a Pagar");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void sair_Click(ActionEvent event) {
		sessao.close();
		fechar(getStage(btnSair));
	}

	@FXML
	void todos_Click(ActionEvent event) {
		if(!listaContaPagar.isEmpty()) {
			tbvContaPagar.setItems(FXCollections.observableArrayList(listaContaPagar));
			tfdValorTotal.setText(calcTotal());
			tfdQtd.setText(totalItens());
		}
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

	private void iniciaBotoes() {
		tfdQtd.setDisable(true);
		tfdValorTotal.setDisable(true);
	}
	@FXML
	private void initialize() {
		iniciaBotoes();
		inicializaCampos();
		MaskFieldUtil.monetaryField(tfdValorInicio);
		MaskFieldUtil.monetaryField(tfdValorFinal);
	}

}
