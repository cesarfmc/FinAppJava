package controller;

import java.io.InputStream;
import java.util.List;

import org.hibernate.Session;

import helper.ContaPagarDAO;
import helper.HibernateUtil2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import model.ContaPagar;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ContaPagarReportViewController {

	private Session sessao = HibernateUtil2.getSessionFactory().openSession();
	private ContaPagarDAO contaPagarDAO = new ContaPagarDAO(sessao);

    @FXML
    private ComboBox<?> cmbFornecedor;

    @FXML
    private ComboBox<?> cmbCentroCusto;

    @FXML
    private ComboBox<?> cmbPlanoConta;

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

    }

    @FXML
    void tela_Click(ActionEvent event) throws JRException {
    	List<ContaPagar> list = contaPagarDAO.listContaPagar();
    	System.out.println(ContaPagarReportViewController.class.getResourceAsStream("/helper/RelatorioContaPagar.jrxml"));
    	InputStream fonte = ContaPagarReportViewController.class.getResourceAsStream("/helper/RelatorioContaPagar.jrxml");
    	
    	JasperReport report = JasperCompileManager.compileReport(fonte);
    	JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(list));
    	JasperViewer.viewReport(print,false);
    	
    }

}
