package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import helper.CentroCustoDAO;
import helper.ClienteDAO;
import helper.ContaReceberDAO;
import helper.DateHelper;
import helper.FormaPagamentoDAO;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

	private Date dataVencimentoInicio = null, dataVencimentoFim = null;
	private Date dataVendaInicio = null, dataVendaFim = null;
	private Date dataRecebimentoInicio = null, dataRecebimentoFim = null;
	private BigDecimal valorInicial, valorFinal;
	
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
		List<ContaReceber> lista= new ArrayList<ContaReceber>(listaContaReceber);
		for (ContaReceber contaReceber : lista) {
			if(contaReceber.getStatus().contentEquals("B")) {
				tbvContaReceber.getItems().remove(contaReceber);
			}
		}
		tfdValorTotal.setText(calcTotal());
		tfdQtd.setText(totalItens());
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
		if(!listaContaReceber.isEmpty()) {
			listaContaReceber.clear();
		}

		tfdNumero.clear();
		tfdValorInicio.clear();
		tfdValorFinal.clear();
		tfdQtd.clear();
		tfdValorTotal.clear();
		
		cmbCentroCusto.getSelectionModel().clearSelection();
		cmbFormaPagamento.getSelectionModel().clearSelection();
		cmbCliente.getSelectionModel().clearSelection();
		cmbPlanoConta.getSelectionModel().clearSelection();
	
		dpVendaInicio.setValue(null);
		dpVendaFinal.setValue(null);
		dpVencimentoInicio.setValue(null);
		dpVencimentoFinal.setValue(null);
		dpRecebimentoInicio.setValue(null);
		dpRecebimentoFinal.setValue(null);

    }

    @FXML
    void pesquisar_Click(ActionEvent event) {
    	if (tfdNumero.getText().contentEquals("") && dpVendaInicio.getValue() == null && dpVencimentoInicio.getValue() == null && dpRecebimentoInicio.getValue() == null && 
				cmbCliente.getSelectionModel().getSelectedItem() == null && cmbCentroCusto.getSelectionModel().getSelectedItem() == null
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
    void recebido_Click(ActionEvent event) {
    	if(!listaContaReceber.isEmpty()) {
    		List<ContaReceber> lista= new ArrayList<ContaReceber>(listaContaReceber);
    		for (ContaReceber contaReceber : lista) {
    			if(contaReceber.getStatus().contentEquals("A")) {
    				tbvContaReceber.getItems().remove(contaReceber);
    			}
    		}
    		tfdValorTotal.setText(calcTotal());
    		tfdQtd.setText(totalItens());
    	}
    }

    @FXML
    void relatorio_Click(ActionEvent event) {
    	try {
			Parent root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ContaReceberReportView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Relatorio Contas a Receber");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void sair_Click(ActionEvent event) {
    	fechar(getStage(btnSair));
    }

    @FXML
    void todo_Click(ActionEvent event) {
    	if(!listaContaReceber.isEmpty()) {
			tbvContaReceber.setItems(FXCollections.observableArrayList(listaContaReceber));
			tfdValorTotal.setText(calcTotal());
			tfdQtd.setText(totalItens());
		}    }
    
    public String calcTotal() {
		BigDecimal total = new BigDecimal("0.0");
		List<ContaReceber> lista= new ArrayList<ContaReceber>(tbvContaReceber.getItems());
		for (ContaReceber contaReceber : lista) {
			total = total.add(contaReceber.getValor());
		}
		return total.toString();
	}
    public String totalItens() {
		int totalItens = tbvContaReceber.getItems().size();
		return String.valueOf(totalItens);
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
	public void verificaLista() {
		if (listaContaReceber.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION, "Nenhum Dado Encontrado!", ButtonType.OK);
			alert.showAndWait();
		} else {
			tbvContaReceber.setItems(FXCollections.observableArrayList(listaContaReceber));
		}
	}
	public void pesquisaTudo() {
		if(getDatasVenda() != 1 || getDatasVencimento() != 1 || getDatasRecebimento() != 1) {
			Alert alert = new Alert(AlertType.INFORMATION, "Data Invalida!", ButtonType.OK);
			alert.showAndWait();
	}else {
		if(getFaixaValores() != 1) {
			Alert alert = new Alert(AlertType.INFORMATION, "Valor Invalido!", ButtonType.OK);
			alert.showAndWait();
		}else {
			listaContaReceber = contaReceberDAO.listContaReceberTudo(getNumero(),valorInicial,valorFinal,dataVendaInicio, dataVendaFim, dataVencimentoInicio, dataVencimentoFim, dataRecebimentoInicio,
					dataRecebimentoFim,getCliente(), getCentroCusto(), getPlanoConta(), getFormaPagamento());

			verificaLista();
		}
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

	public int getDatasVenda() {
		dataVendaInicio = null;
		dataVendaFim = null;
		
		if (dpVendaInicio.getValue() != null && dpVendaFinal.getValue() != null) {
			if(dpVendaFinal.getValue().isAfter(dpVendaInicio.getValue()) || dpVendaFinal.getValue().isEqual(dpVendaInicio.getValue())) {
				dataVendaInicio = DateHelper.getDate(dpVendaInicio.getValue());
				dataVendaFim = DateHelper.getDate(dpVendaFinal.getValue());
				
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

	public int getDatasRecebimento() {
		dataRecebimentoInicio = null;
		dataRecebimentoFim = null;
		if (dpRecebimentoInicio.getValue() != null && dpRecebimentoFinal.getValue() != null) {
			if(dpRecebimentoFinal.getValue().isAfter(dpRecebimentoInicio.getValue()) || dpRecebimentoFinal.getValue().isEqual(dpRecebimentoInicio.getValue())) {
				dataRecebimentoInicio = DateHelper.getDate(dpRecebimentoInicio.getValue());
				dataRecebimentoFim = DateHelper.getDate(dpRecebimentoFinal.getValue());
				
			}else {
				return 0;
			}
		}
		return 1;
	}

	private void iniciaBotoes() {
		tfdQtd.setDisable(true);
		tfdValorTotal.setDisable(true);
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
	
	@FXML
	private void initialize() {
    	iniciaBotoes();
		inicializaCampos();
		MaskFieldUtil.monetaryField(tfdValorInicio);
		MaskFieldUtil.monetaryField(tfdValorFinal);
    }
}
