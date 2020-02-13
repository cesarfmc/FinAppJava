package controller;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import helper.CentroCustoDAO;
import helper.ClienteDAO;
import helper.ContaReceberViewDAO;
import helper.DateHelper;
import helper.HibernateUtil2;
import helper.PlanoContaDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.CentroCusto;
import model.Cliente;
import model.ContaReceberDataView;
import model.PlanoConta;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ContaReceberReportViewController {

	private Session sessao = HibernateUtil2.getSessionFactory().openSession();
	private ContaReceberViewDAO contaReceberViewDAO = new ContaReceberViewDAO(sessao);
	private List<Cliente> listaCliente;
	private List<CentroCusto> listaCentroCusto;
	private List<PlanoConta> listaPlanoConta;
	private ClienteDAO clienteDAO = new ClienteDAO(sessao);
	private CentroCustoDAO centroCustoDAO = new CentroCustoDAO(sessao);
	private PlanoContaDAO planoContaDAO = new PlanoContaDAO(sessao);
	private Date dataVencimentoInicio = null, dataVencimentoFim = null;
	private Date dataVendaInicio = null, dataVendaFim = null;
	private Date dataRecebimentoInicio = null, dataRecebimentoFim = null;

	@FXML
	private ComboBox<Cliente> cmbCliente;

	@FXML
	private ComboBox<CentroCusto> cmbCentroCusto;

	@FXML
	private ComboBox<PlanoConta> cmbPlanoConta;

	@FXML
	private DatePicker dpRecebimentoInicio;

	@FXML
	private DatePicker dpVendaInicio;

	@FXML
	private DatePicker dpVendaFinal;

	@FXML
	private DatePicker dpVencimentoInicio;

	@FXML
	private DatePicker dpVencimentoFinal;

	@FXML
	private DatePicker dpRecebimentoFinal;

	@FXML
	private RadioButton radBtnQuebraCliente;

	@FXML
	private RadioButton radBtnQuebraCentroCusto;

	@FXML
	private RadioButton radBtnQuebraPlanoConta;

	@FXML
	private RadioButton radBtnQuebraDataVenda;

	@FXML
	private RadioButton radBtnQuebraDataVencimento;

	@FXML
	private RadioButton radBtnQuebraDataRecebimento;

	@FXML
	private RadioButton radBtnOrdemCliente;

	@FXML
	private RadioButton radBtnOrdemCentroCusto;

	@FXML
	private RadioButton radBtnOrdemPlanoConta;

	@FXML
	private RadioButton radBtnOrdemDataVenda;

	@FXML
	private RadioButton radBtnOrdemDataVencimento;

	@FXML
	private RadioButton radBtnOrdemDataRecebimento;

	@FXML
	private RadioButton radBtnTipoAnalitico;

	@FXML
	private RadioButton radBtnTipoSintetico;

	@FXML
	private RadioButton radBtnSituacaoTodos;

	@FXML
	private RadioButton radBtnSituacaoAberto;

	@FXML
	private RadioButton radBtnSituacaoRecebidos;

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
		radBtnSituacaoRecebidos.setSelected(false);
		radBtnSituacaoTodos.setSelected(false);
	}

	@FXML
	void recebidos_Click(ActionEvent event) {
		radBtnSituacaoRecebidos.setSelected(true);
		radBtnSituacaoAberto.setSelected(false);
		radBtnSituacaoTodos.setSelected(false);
	}

	@FXML
	void todos_Cick(ActionEvent event) {
		radBtnSituacaoTodos.setSelected(true);
		radBtnSituacaoRecebidos.setSelected(false);
		radBtnSituacaoAberto.setSelected(false);
	}

	@FXML
	void quebraCentroCusto_Click(ActionEvent event) {
		radBtnQuebraCentroCusto.setSelected(true);
		radBtnQuebraCliente.setSelected(false);
		radBtnQuebraPlanoConta.setSelected(false);
		radBtnQuebraDataVenda.setSelected(false);
		radBtnQuebraDataVencimento.setSelected(false);
		radBtnQuebraDataRecebimento.setSelected(false);
	}

	@FXML
	void quebraDataVenda_Click(ActionEvent event) {
		radBtnQuebraDataVenda.setSelected(true);
		radBtnQuebraCentroCusto.setSelected(false);
		radBtnQuebraPlanoConta.setSelected(false);
		radBtnQuebraCliente.setSelected(false);
		radBtnQuebraDataVencimento.setSelected(false);
		radBtnQuebraDataRecebimento.setSelected(false);
	}

	@FXML
	void quebraDataRecebimento_Click(ActionEvent event) {
		radBtnQuebraDataRecebimento.setSelected(true);
		radBtnQuebraCentroCusto.setSelected(false);
		radBtnQuebraPlanoConta.setSelected(false);
		radBtnQuebraDataVenda.setSelected(false);
		radBtnQuebraDataVencimento.setSelected(false);
		radBtnQuebraCliente.setSelected(false);
	}

	@FXML
	void quebraDataVencimento_Click(ActionEvent event) {
		radBtnQuebraCliente.setSelected(true);
		radBtnQuebraCentroCusto.setSelected(false);
		radBtnQuebraPlanoConta.setSelected(false);
		radBtnQuebraDataVenda.setSelected(false);
		radBtnQuebraCliente.setSelected(false);
		radBtnQuebraDataRecebimento.setSelected(false);
	}

	@FXML
	void quebraCliente_Click(ActionEvent event) {
		radBtnQuebraCliente.setSelected(true);
		radBtnQuebraCentroCusto.setSelected(false);
		radBtnQuebraPlanoConta.setSelected(false);
		radBtnQuebraDataVenda.setSelected(false);
		radBtnQuebraDataVencimento.setSelected(false);
		radBtnQuebraDataRecebimento.setSelected(false);
	}

	@FXML
	void quebraPlanoConta_Click(ActionEvent event) {
		radBtnQuebraPlanoConta.setSelected(true);
		radBtnQuebraCentroCusto.setSelected(false);
		radBtnQuebraCliente.setSelected(false);
		radBtnQuebraDataVenda.setSelected(false);
		radBtnQuebraDataVencimento.setSelected(false);
		radBtnQuebraDataRecebimento.setSelected(false);
	}

	@FXML
	void ordenarCentroCusto_Click(ActionEvent event) {
		radBtnOrdemCentroCusto.setSelected(true);
		radBtnOrdemCliente.setSelected(false);
		radBtnOrdemPlanoConta.setSelected(false);
		radBtnOrdemDataVenda.setSelected(false);
		radBtnOrdemDataVencimento.setSelected(false);
		radBtnOrdemDataRecebimento.setSelected(false);
	}

	@FXML
	void ordenarDataVenda_Click(ActionEvent event) {
		radBtnOrdemDataVenda.setSelected(true);
		radBtnOrdemCentroCusto.setSelected(false);
		radBtnOrdemCliente.setSelected(false);
		radBtnOrdemPlanoConta.setSelected(false);
		radBtnOrdemDataVencimento.setSelected(false);
		radBtnOrdemDataRecebimento.setSelected(false);
	}

	@FXML
	void ordenarDataRecebimento_Click(ActionEvent event) {
		radBtnOrdemDataRecebimento.setSelected(true);
		radBtnOrdemDataVenda.setSelected(false);
		radBtnOrdemCentroCusto.setSelected(false);
		radBtnOrdemCliente.setSelected(false);
		radBtnOrdemPlanoConta.setSelected(false);
		radBtnOrdemDataVencimento.setSelected(false);
	}

	@FXML
	void ordenarDataVencimento_Click(ActionEvent event) {
		radBtnOrdemDataVencimento.setSelected(true);
		radBtnOrdemDataRecebimento.setSelected(false);
		radBtnOrdemDataVenda.setSelected(false);
		radBtnOrdemCentroCusto.setSelected(false);
		radBtnOrdemCliente.setSelected(false);
		radBtnOrdemPlanoConta.setSelected(false);
	}

	@FXML
	void ordenarCliente_Click(ActionEvent event) {
		radBtnOrdemCliente.setSelected(true);
		radBtnOrdemDataVencimento.setSelected(false);
		radBtnOrdemDataRecebimento.setSelected(false);
		radBtnOrdemDataVenda.setSelected(false);
		radBtnOrdemCentroCusto.setSelected(false);
		radBtnOrdemPlanoConta.setSelected(false);
	}

	@FXML
	void ordenarPlanoConta_Click(ActionEvent event) {
		radBtnOrdemPlanoConta.setSelected(true);
		radBtnOrdemCliente.setSelected(false);
		radBtnOrdemDataVencimento.setSelected(false);
		radBtnOrdemDataRecebimento.setSelected(false);
		radBtnOrdemDataVenda.setSelected(false);
		radBtnOrdemCentroCusto.setSelected(false);
	}

	@FXML
	void tela_Click(ActionEvent event) throws JRException {
		if(getDatasVenda() != 1 || getDatasVencimento() != 1 || getDatasRecebimento() != 1) {
			Alert alert = new Alert(AlertType.INFORMATION, "Data Invalida!", ButtonType.OK);
			alert.showAndWait();
		}else {
			List<ContaReceberDataView> listaContaReceber = contaReceberViewDAO.listContaReceberJasper(dataVendaInicio, dataVendaFim, dataVencimentoInicio, dataVencimentoFim, dataRecebimentoInicio,dataRecebimentoFim, 
					getCliente(), getCentroCusto(), getPlanoConta(), getOrdenacao(), getStatus(),getQuebra());

			// JasperDesign desenho =
			// JRXmlLoader.load(getClass().getResourceAsStream("/helper/RelatorioContaPagar.jrxml"));
			
			//Map<String, List<ContaPagarDataView>> studlistGrouped =
			//listaContaPagar.stream().collect(Collectors.groupingBy(w -> w.getFornecedorNome()));
			
			Map parameters = new HashMap();
			parameters.put("listaContaReceber", listaContaReceber);
			parameters.put("quebra", getQuebraJasper());
			paramBusca(parameters);
			
			InputStream fonte = quebraJasper();
			
			JasperReport report = JasperCompileManager.compileReport(fonte);
			
			JasperPrint print = JasperFillManager.fillReport(report, parameters, 
			new JREmptyDataSource()); JasperViewer.viewReport(print, false);

		}
	}
	
	private void paramBusca(Map parameters ) {
		if(getCliente() != null) {
			  parameters.put("Cliente", getCliente().getNome());
		  }
		  if(getCentroCusto() != null) {
			  System.out.println(getCentroCusto().getNome());
			  parameters.put("CentroCustoP", getCentroCusto().getNome());
		  }
		  if(getPlanoConta() != null) {
			  parameters.put("PlanoContaP", getPlanoConta().getNome());
		  }
		  if(dataVendaInicio != null) {
			  parameters.put("DataVenda", DateHelper.formataData(dataVendaInicio)+" a " + 
					  		  DateHelper.formataData(dataVendaFim));
		  }
		  if(dataVencimentoInicio != null) {
			  parameters.put("DataVencimento", DateHelper.formataData(dataVencimentoInicio)+" a " + 
					  DateHelper.formataData(dataVencimentoFim));
		  }
		  if(dataRecebimentoInicio != null) {
			  parameters.put("DataRecebimento", DateHelper.formataData(dataRecebimentoInicio)+" a " + 
					  DateHelper.formataData(dataRecebimentoFim));
		  }
	}
	
	private void iniciaCentroCusto() {
		listaCentroCusto = centroCustoDAO.listCentroCusto();
		cmbCentroCusto.setItems(FXCollections.observableArrayList(listaCentroCusto));

	}

	private void iniciaCliente() {
		listaCliente = clienteDAO.listCliente();
		cmbCliente.setItems(FXCollections.observableArrayList(listaCliente));
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

	public Cliente getCliente() {
		return cmbCliente.getSelectionModel().getSelectedItem();
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
		radBtnTipoAnalitico.setSelected(true);
		radBtnSituacaoTodos.setSelected(true);
		radBtnQuebraCliente.setSelected(true);
		radBtnOrdemCliente.setSelected(true);
	}
	
	private int getOrdenacao() {
		if(radBtnOrdemCliente.isSelected()) {
			return 0;
		}else{
			if(radBtnOrdemCentroCusto.isSelected()) {
				return 1;
			}else {
				if(radBtnOrdemPlanoConta.isSelected()) {
					return 2;
				}else {
					if(radBtnOrdemDataVenda.isSelected()) {
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
	
	private int getQuebra() {
		if(radBtnQuebraCliente.isSelected()) {
			return 0;
		}else{
			if(radBtnQuebraCentroCusto.isSelected()) {
				return 1;
			}else {
				if(radBtnQuebraPlanoConta.isSelected()) {
					return 2;
				}else {
					if(radBtnQuebraDataVenda.isSelected()) {
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
	
	private String getQuebraJasper() {
		if(radBtnQuebraCliente.isSelected()) {
			return "Cliente";
		}else{
			if(radBtnQuebraCentroCusto.isSelected()) {
				return "Centro de Custo";
			}else {
				if(radBtnQuebraPlanoConta.isSelected()) {
					return "Plano de Conta";
				}else {
					if(radBtnQuebraDataVenda.isSelected()) {
						return "Data da Venda";
					}else {
						if(radBtnOrdemDataVencimento.isSelected()) {
							return "Data de Vencimento";
						}else {
							return "Data de Recebimento";
						}
					}
				}
			}
		}
	}
	private InputStream quebraJasper() {
		if(radBtnQuebraCliente.isSelected()) {
			return ContaPagarReportViewController.class
					  .getResourceAsStream("/helper/RelatorioContaReceberCliente.jrxml");
		}else{
			if(radBtnQuebraCentroCusto.isSelected()) {
				return ContaPagarReportViewController.class
						  .getResourceAsStream("/helper/RelatorioContaReceberCentroCusto.jrxml");
			}else {
				if(radBtnQuebraPlanoConta.isSelected()) {
					return ContaPagarReportViewController.class
							  .getResourceAsStream("/helper/RelatorioContaReceberPlanoConta.jrxml");
				}else {
					if(radBtnQuebraDataVenda.isSelected()) {
						return ContaPagarReportViewController.class
								  .getResourceAsStream("/helper/RelatorioContaReceberDataVenda.jrxml");
					}else {
						if(radBtnQuebraDataVencimento.isSelected()) {
							return ContaPagarReportViewController.class
									  .getResourceAsStream("/helper/RelatorioContaReceberDataVencimento.jrxml");
						}else {
							return ContaPagarReportViewController.class
									  .getResourceAsStream("/helper/RelatorioContaReceberDataRecebimento.jrxml");
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
		iniciaCliente();
		iniciaBotoes();

	}
}
