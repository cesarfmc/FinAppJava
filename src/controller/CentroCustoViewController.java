package controller;

import java.util.Optional;

import helper.CentroCustoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.CentroCusto;

public class CentroCustoViewController {
	private CentroCusto centroCusto;
	private CentroCusto centroCustoPai;
	private CentroCustoDAO centroCustoDAO;
	private CentroCustoListViewController controller;
	
	public CentroCustoViewController(CentroCusto centroCusto, CentroCusto centroCustoPai, CentroCustoDAO centroCustoDAO,CentroCustoListViewController controller) {
		this.centroCusto = centroCusto;
		this.centroCustoPai = centroCustoPai;
		this.centroCustoDAO = centroCustoDAO;
		this.controller = controller;
	}

	@FXML
    private TextField tfdCentroCustoPai;

    @FXML
    private TextField tfdNome;

    @FXML
    private TextArea txaDescricao;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnCancelar;

    @FXML
    void cancelar_Click(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
		fechar(stage);
    }

    private Stage getStage(Button btn) {
    	Stage stage = (Stage) btn.getScene().getWindow();
    	
    	return stage;
    }
    @FXML
    void excluir_Click(ActionEvent event) {
    	if(centroCusto != null) {
			Alert alert = new Alert(AlertType.WARNING, 
					"Deseja Remover Centro de Custo?", 
					ButtonType.YES, ButtonType.NO);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.YES){
				centroCustoDAO.removeCentroCusto(centroCusto.getIdCentroCusto());
				
				Alert alertExclusao = new Alert(AlertType.INFORMATION, "Centro de Custos Removido", ButtonType.OK);
				alertExclusao.showAndWait();
				
				fechar(getStage(btnExcluir));
			} else {
				Alert alertExclusao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
				alertExclusao.showAndWait();
			}
		}

    	
    }

    @FXML
    void salvar_Click(ActionEvent event) {
    	if(tfdNome.getText().contentEquals("")) {
    		Alert alert = new Alert(AlertType.WARNING, "Nome Obrigatório!", ButtonType.OK);
			alert.showAndWait();
    	}else {
    		if (centroCusto != null) {
				Alert alert = new Alert(AlertType.WARNING, "Deseja Alterar Dados do Centro de Custo?", ButtonType.YES, ButtonType.NO);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.YES) {
					centroCustoDAO.updateCentroCusto(getDados());
					
					Alert alertConfirmacao = new Alert(AlertType.INFORMATION, "Centro de Custos Alterado", ButtonType.OK);
					alertConfirmacao.showAndWait();

					fechar(getStage(btnSalvar));
				} else {
					cancelarOperacao();
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING, "Deseja Inserir Centro de Custos?", ButtonType.YES, ButtonType.NO);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.YES) {
					centroCustoDAO.addCentroCusto(getDados());

					Alert alertConfirmacao = new Alert(AlertType.INFORMATION, "Centro de Custos Inserido", ButtonType.OK);
					alertConfirmacao.showAndWait();
					fechar(getStage(btnSalvar));
				} else {
					cancelarOperacao();
				}
				
			}
    	}
    }
    
    private void cancelarOperacao() {
    	Alert alertConfirmacao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
		alertConfirmacao.showAndWait();
    }
    
    private void fechar(Stage stage) {
    	controller.pesquisar();
    	stage.close();
    }
    
    private void iniciaBotoes() {
    	inicializaCampos();
    	tfdCentroCustoPai.setDisable(true);
    	if(centroCusto != null) {
    		btnExcluir.setDisable(false);
    	}else {
    		btnExcluir.setDisable(true);
    	}
    }
    
    public void inicializaCampos() {
    	if(centroCusto != null) {
    		tfdNome.setText(centroCusto.getNome());
    		tfdCentroCustoPai.setText(centroCusto.getCentroCusto().getNome());
    		if(centroCusto.getDescricao() != null) {
    			txaDescricao.setText(centroCusto.getDescricao());
    		}
    	}else {
    		tfdCentroCustoPai.setText(centroCustoPai.getNome());
    	}
    }
    
    public CentroCusto getDados() {
    	if(centroCusto == null) {
    		centroCusto = new CentroCusto();
    	}
    	centroCusto.setNome(tfdNome.getText());
		centroCusto.setDescricao(txaDescricao.getText());
		centroCusto.setCentroCusto(centroCustoPai);
		
		return centroCusto;
    }
    
    @FXML
   	private void initialize() {
   		iniciaBotoes();
   	}

}
