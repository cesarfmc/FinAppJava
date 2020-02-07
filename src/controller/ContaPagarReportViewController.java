package controller;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.Session;

import helper.CentroCustoDAO;
import helper.ContaPagarViewDAO;
import helper.DateHelper;
import helper.FornecedorDAO;
import helper.HibernateUtil2;
import helper.PlanoContaDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import model.CentroCusto;
import model.ContaPagarDataView;
import model.Fornecedor;
import model.PlanoConta;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ContaPagarReportViewController {

	private Session sessao = HibernateUtil2.getSessionFactory().openSession();
	private ContaPagarViewDAO contaPagarViewDAO = new ContaPagarViewDAO(sessao);
	private List<Fornecedor> listaFornecedor;
	private List<CentroCusto> listaCentroCusto;
	private List<PlanoConta> listaPlanoConta;
	private FornecedorDAO fornecedorDAO = new FornecedorDAO(sessao);
	private CentroCustoDAO centroCustoDAO = new CentroCustoDAO(sessao);
	private PlanoContaDAO planoContaDAO = new PlanoContaDAO(sessao);
	private Date dataVencimentoInicio = null, dataVencimentoFim = null;
	private Date dataCompraInicio = null, dataCompraFim = null;
	private Date dataPagamentoInicio = null, dataPagamentoFim = null;

	@FXML
	private ComboBox<Fornecedor> cmbFornecedor;

	@FXML
	private ComboBox<CentroCusto> cmbCentroCusto;

	@FXML
	private ComboBox<PlanoConta> cmbPlanoConta;

	@FXML
	private DatePicker dpPagamentoInicio;

	@FXML
	private DatePicker dpCompraInicio;

	@FXML
	private DatePicker dpCompraFinal;

	@FXML
	private DatePicker dpVencimentoInicio;

	@FXML
	private DatePicker dpVencimentoFinal;

	@FXML
	private DatePicker dpPagamentoFinal;

	@FXML
	private RadioButton radBtnQuebraFornecedor;

	@FXML
	private RadioButton radBtnQuebraCentroCusto;

	@FXML
	private RadioButton radBtnQuebraPlanoConta;

	@FXML
	private RadioButton radBtnQuebraDataCompra;

	@FXML
	private RadioButton radBtnQuebraDataVencimento;

	@FXML
	private RadioButton radBtnQuebraDataPagamento;

	@FXML
	private RadioButton radBtnOrdemFornecedor;

	@FXML
	private RadioButton radBtnOrdemCentroCusto;

	@FXML
	private RadioButton radBtnOrdemPlanoConta;

	@FXML
	private RadioButton radBtnOrdemDataCompra;

	@FXML
	private RadioButton radBtnOrdemDataVencimento;

	@FXML
	private RadioButton radBtnOrdemDataPagamento;

	@FXML
	private RadioButton radBtnTipoAnalitico;

	@FXML
	private RadioButton radBtnTipoSintetico;

	@FXML
	private RadioButton radBtnSituacaoTodos;

	@FXML
	private RadioButton radBtnSituacaoAberto;

	@FXML
	private RadioButton radBtnSituacaoPagos;

	@FXML
	private Button btnTela;

	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnSair;

	@FXML
	void limpar_Click(ActionEvent event) {

	}

	@FXML
	void sair_Click(ActionEvent event) {
		sessao.close();
		fechar(getStage(btnSair));
	}

	@FXML
	void analitico_Click(ActionEvent event) {
		radBtnTipoAnalitico.setSelected(true);
		radBtnTipoSintetico.setSelected(false);
	}

	@FXML
	void sintetico_Click(ActionEvent event) {
		radBtnTipoSintetico.setSelected(true);
		radBtnTipoAnalitico.setSelected(false);
	}

	@FXML
	void aberto_Click(ActionEvent event) {
		radBtnSituacaoAberto.setSelected(true);
		radBtnSituacaoPagos.setSelected(false);
		radBtnSituacaoTodos.setSelected(false);
	}

	@FXML
	void pago_Click(ActionEvent event) {
		radBtnSituacaoPagos.setSelected(true);
		radBtnSituacaoAberto.setSelected(false);
		radBtnSituacaoTodos.setSelected(false);
	}

	@FXML
	void todos_Cick(ActionEvent event) {
		radBtnSituacaoTodos.setSelected(true);
		radBtnSituacaoPagos.setSelected(false);
		radBtnSituacaoAberto.setSelected(false);
	}

	@FXML
	void quebraCentroCusto_Click(ActionEvent event) {
		radBtnQuebraCentroCusto.setSelected(true);
		radBtnQuebraFornecedor.setSelected(false);
		radBtnQuebraPlanoConta.setSelected(false);
		radBtnQuebraDataCompra.setSelected(false);
		radBtnQuebraDataVencimento.setSelected(false);
		radBtnQuebraDataPagamento.setSelected(false);
	}

	@FXML
	void quebraDataCompra_Click(ActionEvent event) {
		radBtnQuebraDataCompra.setSelected(true);
		radBtnQuebraCentroCusto.setSelected(false);
		radBtnQuebraPlanoConta.setSelected(false);
		radBtnQuebraFornecedor.setSelected(false);
		radBtnQuebraDataVencimento.setSelected(false);
		radBtnQuebraDataPagamento.setSelected(false);
	}

	@FXML
	void quebraDataPagamento_Click(ActionEvent event) {
		radBtnQuebraDataPagamento.setSelected(true);
		radBtnQuebraCentroCusto.setSelected(false);
		radBtnQuebraPlanoConta.setSelected(false);
		radBtnQuebraDataCompra.setSelected(false);
		radBtnQuebraDataVencimento.setSelected(false);
		radBtnQuebraFornecedor.setSelected(false);
	}

	@FXML
	void quebraDataVencimento_Click(ActionEvent event) {
		radBtnQuebraFornecedor.setSelected(true);
		radBtnQuebraCentroCusto.setSelected(false);
		radBtnQuebraPlanoConta.setSelected(false);
		radBtnQuebraDataCompra.setSelected(false);
		radBtnQuebraFornecedor.setSelected(false);
		radBtnQuebraDataPagamento.setSelected(false);
	}

	@FXML
	void quebraFornecedor_Click(ActionEvent event) {
		radBtnQuebraFornecedor.setSelected(true);
		radBtnQuebraCentroCusto.setSelected(false);
		radBtnQuebraPlanoConta.setSelected(false);
		radBtnQuebraDataCompra.setSelected(false);
		radBtnQuebraDataVencimento.setSelected(false);
		radBtnQuebraDataPagamento.setSelected(false);
	}

	@FXML
	void quebraPlanoConta_Click(ActionEvent event) {
		radBtnQuebraPlanoConta.setSelected(true);
		radBtnQuebraCentroCusto.setSelected(false);
		radBtnQuebraFornecedor.setSelected(false);
		radBtnQuebraDataCompra.setSelected(false);
		radBtnQuebraDataVencimento.setSelected(false);
		radBtnQuebraDataPagamento.setSelected(false);
	}

	@FXML
	void ordenarCentroCusto_Click(ActionEvent event) {
		radBtnOrdemCentroCusto.setSelected(true);
		radBtnOrdemFornecedor.setSelected(false);
		radBtnOrdemPlanoConta.setSelected(false);
		radBtnOrdemDataCompra.setSelected(false);
		radBtnOrdemDataVencimento.setSelected(false);
		radBtnOrdemDataPagamento.setSelected(false);
	}

	@FXML
	void ordenarDataCompra_Click(ActionEvent event) {
		radBtnOrdemDataCompra.setSelected(true);
		radBtnOrdemCentroCusto.setSelected(false);
		radBtnOrdemFornecedor.setSelected(false);
		radBtnOrdemPlanoConta.setSelected(false);
		radBtnOrdemDataVencimento.setSelected(false);
		radBtnOrdemDataPagamento.setSelected(false);
	}

	@FXML
	void ordenarDataPagamento_Click(ActionEvent event) {
		radBtnOrdemDataPagamento.setSelected(true);
		radBtnOrdemDataCompra.setSelected(false);
		radBtnOrdemCentroCusto.setSelected(false);
		radBtnOrdemFornecedor.setSelected(false);
		radBtnOrdemPlanoConta.setSelected(false);
		radBtnOrdemDataVencimento.setSelected(false);
	}

	@FXML
	void ordenarDataVencimento_Click(ActionEvent event) {
		radBtnOrdemDataVencimento.setSelected(true);
		radBtnOrdemDataPagamento.setSelected(false);
		radBtnOrdemDataCompra.setSelected(false);
		radBtnOrdemCentroCusto.setSelected(false);
		radBtnOrdemFornecedor.setSelected(false);
		radBtnOrdemPlanoConta.setSelected(false);
	}

	@FXML
	void ordenarFornecedor_Click(ActionEvent event) {
		radBtnOrdemFornecedor.setSelected(true);
		radBtnOrdemDataVencimento.setSelected(false);
		radBtnOrdemDataPagamento.setSelected(false);
		radBtnOrdemDataCompra.setSelected(false);
		radBtnOrdemCentroCusto.setSelected(false);
		radBtnOrdemPlanoConta.setSelected(false);
	}

	@FXML
	void ordenarPlanoConta_Click(ActionEvent event) {
		radBtnOrdemPlanoConta.setSelected(true);
		radBtnOrdemFornecedor.setSelected(false);
		radBtnOrdemDataVencimento.setSelected(false);
		radBtnOrdemDataPagamento.setSelected(false);
		radBtnOrdemDataCompra.setSelected(false);
		radBtnOrdemCentroCusto.setSelected(false);
	}

	@FXML
	void tela_Click(ActionEvent event) throws JRException {
		getDatasCompra();
		getDatasVencimento();
		getDatasPagamento();
		List<ContaPagarDataView> listaContaPagar = contaPagarViewDAO.listContaPagarJasper(dataCompraInicio, dataCompraFim, dataVencimentoInicio, dataVencimentoFim, dataPagamentoInicio,dataPagamentoFim, 
																	getFornecedor(), getCentroCusto(), getPlanoConta(), getOrdenacao(), getStatus(),getQuebra());

		// JasperDesign desenho =
		// JRXmlLoader.load(getClass().getResourceAsStream("/helper/RelatorioContaPagar.jrxml"));

//		Map<String, List<ContaPagarDataView>> studlistGrouped =
//			    listaContaPagar.stream().collect(Collectors.groupingBy(w -> w.getFornecedorNome()));
		  
		Map parameters = new HashMap();
		  parameters.put("listaContaPagar", listaContaPagar);
		  parameters.put("ordem", getOrdenacaoJasper());
		  paramBusca(parameters);
		  
		  InputStream fonte = getQuebraJasper();
		  
		  JasperReport report = JasperCompileManager.compileReport(fonte);
		  
		  JasperPrint print = JasperFillManager.fillReport(report, parameters, 
				  new JREmptyDataSource()); JasperViewer.viewReport(print, false);
		 
	}
	
	private void paramBusca(Map parameters ) {
		if(getFornecedor() != null) {
			  parameters.put("FornecedorP", getFornecedor().getNome());
		  }
		  if(getCentroCusto() != null) {
			  System.out.println(getCentroCusto().getNome());
			  parameters.put("CentroCustoP", getCentroCusto().getNome());
		  }
		  if(getPlanoConta() != null) {
			  parameters.put("PlanoContaP", getPlanoConta().getNome());
		  }
		  if(dataCompraInicio != null) {
			  System.out.println(DateHelper.formataData(dataCompraInicio));
			  parameters.put("DataCompraP", DateHelper.formataData(dataCompraInicio)+" a " + 
					  		  DateHelper.formataData(dataCompraFim));
		  }
		  if(dataVencimentoInicio != null) {
			  parameters.put("DataVencimentoP", DateHelper.formataData(dataVencimentoInicio)+" a " + 
					  DateHelper.formataData(dataVencimentoFim));
		  }
		  if(dataPagamentoInicio != null) {
			  parameters.put("DataPagamentoP", DateHelper.formataData(dataPagamentoInicio)+" a " + 
					  DateHelper.formataData(dataPagamentoFim));
		  }
	}

	private void iniciaCentroCusto() {
		listaCentroCusto = centroCustoDAO.listCentroCusto();
		cmbCentroCusto.setItems(FXCollections.observableArrayList(listaCentroCusto));

	}

	private void iniciaFornecedor() {
		listaFornecedor = fornecedorDAO.listFornecedor();
		cmbFornecedor.setItems(FXCollections.observableArrayList(listaFornecedor));
	}

	private void iniciaPlanoConta() {
		listaPlanoConta = planoContaDAO.listPlanoConta();
		cmbPlanoConta.setItems(FXCollections.observableArrayList(listaPlanoConta));
	}

	private void fechar(Stage stage) {
		stage.close();
	}

	private Stage getStage(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();

		return stage;
	}

	public CentroCusto getCentroCusto() {
		return cmbCentroCusto.getSelectionModel().getSelectedItem();
	}

	public PlanoConta getPlanoConta() {
		return cmbPlanoConta.getSelectionModel().getSelectedItem();
	}

	public Fornecedor getFornecedor() {
		return cmbFornecedor.getSelectionModel().getSelectedItem();
	}

	public void getDatasCompra() {
		dataCompraInicio = null;
		dataCompraFim = null;
		if (dpCompraInicio.getValue() != null && dpCompraFinal.getValue() != null) {
			dataCompraInicio = DateHelper.getDate(dpCompraInicio.getValue());
			dataCompraFim = DateHelper.getDate(dpCompraFinal.getValue());
		}
	}

	public void getDatasVencimento() {
		dataVencimentoInicio = null;
		dataVencimentoFim = null;
		if (dpVencimentoInicio.getValue() != null && dpVencimentoFinal.getValue() != null) {
			dataVencimentoInicio = DateHelper.getDate(dpVencimentoInicio.getValue());
			dataVencimentoFim = DateHelper.getDate(dpVencimentoFinal.getValue());
		}
	}

	public void getDatasPagamento() {
		dataPagamentoInicio = null;
		dataPagamentoFim = null;
		if (dpPagamentoInicio.getValue() != null && dpPagamentoFinal.getValue() != null) {
			dataPagamentoInicio = DateHelper.getDate(dpPagamentoInicio.getValue());
			dataPagamentoFim = DateHelper.getDate(dpPagamentoFinal.getValue());
		}
	}
	
	private void iniciaBotoes() {
		radBtnTipoAnalitico.setSelected(true);
		radBtnSituacaoTodos.setSelected(true);
		radBtnQuebraFornecedor.setSelected(true);
		radBtnOrdemFornecedor.setSelected(true);
	}

	private int getQuebra() {
		if(radBtnQuebraFornecedor.isSelected()) {
			return 0;
		}else{
			if(radBtnQuebraCentroCusto.isSelected()) {
				return 1;
			}else {
				if(radBtnQuebraPlanoConta.isSelected()) {
					return 2;
				}else {
					if(radBtnQuebraDataCompra.isSelected()) {
						return 3;
					}else {
						if(radBtnQuebraDataVencimento.isSelected()) {
							return 4;
						}else {
							return 5;
						}
					}
				}
			}
		}
	}
	
	private InputStream getQuebraJasper() {
		if(radBtnQuebraFornecedor.isSelected()) {
			return ContaPagarReportViewController.class
					  .getResourceAsStream("/helper/RelatorioContaPagarFornecedor.jrxml");
		}else{
			if(radBtnQuebraCentroCusto.isSelected()) {
				return ContaPagarReportViewController.class
						  .getResourceAsStream("/helper/RelatorioContaPagarCentroCusto.jrxml");
			}else {
				if(radBtnQuebraPlanoConta.isSelected()) {
					return ContaPagarReportViewController.class
							  .getResourceAsStream("/helper/RelatorioContaPagarPlanoConta.jrxml");
				}else {
					if(radBtnQuebraDataCompra.isSelected()) {
						return ContaPagarReportViewController.class
								  .getResourceAsStream("/helper/RelatorioContaPagarDataCompra.jrxml");
					}else {
						if(radBtnQuebraDataVencimento.isSelected()) {
							return ContaPagarReportViewController.class
									  .getResourceAsStream("/helper/RelatorioContaPagarDataVencimento.jrxml");
						}else {
							return ContaPagarReportViewController.class
									  .getResourceAsStream("/helper/RelatorioContaPagarDataPagamento.jrxml");
						}
					}
				}
			}
		}
	}
	
	private int getOrdenacao() {
		if(radBtnOrdemFornecedor.isSelected()) {
			return 0;
		}else{
			if(radBtnOrdemCentroCusto.isSelected()) {
				return 1;
			}else {
				if(radBtnOrdemPlanoConta.isSelected()) {
					return 2;
				}else {
					if(radBtnOrdemDataCompra.isSelected()) {
						return 3;
					}else {
						if(radBtnOrdemDataVencimento.isSelected()) {
							return 4;
						}else {
							return 5;
						}
					}
				}
			}
		}
	}
	
	private String getOrdenacaoJasper() {
		if(radBtnOrdemFornecedor.isSelected()) {
			return "Fornecedor";
		}else{
			if(radBtnOrdemCentroCusto.isSelected()) {
				return "Centro de Custo";
			}else {
				if(radBtnOrdemPlanoConta.isSelected()) {
					return "Plano de Conta";
				}else {
					if(radBtnOrdemDataCompra.isSelected()) {
						return "Data da Compra";
					}else {
						if(radBtnOrdemDataVencimento.isSelected()) {
							return "Data de Vencimento";
						}else {
							return "Data de Pagamento";
						}
					}
				}
			}
		}
	}
	
	private int getStatus() {
		if(radBtnSituacaoTodos.isSelected()) {
			return 0;
		}else {
			if(radBtnSituacaoAberto.isSelected()) {
				return 1;
			}else {
				return 2;
			}
		}
	}
	@FXML
	private void initialize() {
		iniciaCentroCusto();
		iniciaPlanoConta();
		iniciaFornecedor();
		iniciaBotoes();

	}
}
