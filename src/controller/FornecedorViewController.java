package controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import helper.DateHelper;
import helper.EstadoDAO;
import helper.FornecedorDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Cidade;
import model.Estado;
import model.Fornecedor;

public class FornecedorViewController implements Initializable{
	private Fornecedor fornecedor;
	private FornecedorDAO fornecedorDAO;

	
	public FornecedorViewController(FornecedorDAO fornecedorDAO) {
		this.fornecedorDAO = fornecedorDAO;
	}

	@FXML
    private TextField tfdNome;

    @FXML
    private TextField tfdCnpj;

    @FXML
    private TextField tfdCpf;

    @FXML
    private TextField tfdCelular;

    @FXML
    private TextField tfdEndereco;

    @FXML
    private TextField tfdNumero;

    @FXML
    private TextField tfdBairro;

    @FXML
    private ComboBox<Estado> cmbEstado;

    @FXML
    private ComboBox<Cidade> cmbCidade;

    @FXML
    private TextField tfdCep;

    @FXML
    private TextField tfdEmail;

    @FXML
    private DatePicker dpDataNascimento;

    @FXML
    private TextField tfdIe;

    @FXML
    private TextField tfdRg;

    @FXML
    private TextField tfdTelefone;

    @FXML
    private TextField tfdComplemento;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnCancelar;

    @FXML
    void cancelar_Click(ActionEvent event) {
    	fechar(getStage(btnCancelar));
    }
    
    @FXML
    void excluir_Click(ActionEvent event) {
    	Alert alert = new Alert(AlertType.WARNING, 
				"Deseja Remover Fornecedor?", 
				ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES){
			fornecedorDAO.removeFornecedor(fornecedor.getIdFornecedor());
			
			Alert alertExclusao = new Alert(AlertType.INFORMATION, "Fornecedor Removido", ButtonType.OK);
			alertExclusao.showAndWait();
			
			fechar(getStage(btnExcluir));
		} else {
			Alert alertExclusao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
			alertExclusao.showAndWait();
		}
    }

    @FXML
    void salvar_Click(ActionEvent event) {
    	
    	if(fornecedor != null) {
    		fornecedorDAO.updateFornecedor(getFornecedor());
    		
    		Alert alert = new Alert(AlertType.INFORMATION, "Fornecedor Alterado", ButtonType.OK);
			alert.showAndWait();
    	}else {
    		fornecedorDAO.addFornecedor(getFornecedor());
    		
    		Alert alert = new Alert(AlertType.INFORMATION, "Fornecedor Inserido", ButtonType.OK);
			alert.showAndWait();
    	}
    	
    	fechar(getStage(btnSalvar));
    }
    
    @FXML
    void selecionar_estado(ActionEvent event) {
    	setCidade(cmbEstado.getSelectionModel().getSelectedItem());
    }

	public FornecedorViewController(Fornecedor fornecedor, FornecedorDAO fornecedorDAO) {
		this.fornecedor = fornecedor;
		this.fornecedorDAO = fornecedorDAO;
	}

	public void setDados() {
		if(fornecedor != null) {
			tfdNome.textProperty().bindBidirectional(new SimpleStringProperty(fornecedor.getNome()));
			tfdCnpj.textProperty().bindBidirectional(new SimpleStringProperty(fornecedor.getCnpj()));
			tfdIe.textProperty().bindBidirectional(new SimpleStringProperty(fornecedor.getIe()));
			tfdCpf.textProperty().bindBidirectional(new SimpleStringProperty(fornecedor.getCpf()));
			tfdRg.textProperty().bindBidirectional(new SimpleStringProperty(fornecedor.getRg()));
			tfdCelular.textProperty().bindBidirectional(new SimpleStringProperty(fornecedor.getCelular()));
			tfdTelefone.textProperty().bindBidirectional(new SimpleStringProperty(fornecedor.getTelefone()));
			tfdEndereco.textProperty().bindBidirectional(new SimpleStringProperty(fornecedor.getEndereco()));
			tfdNumero.textProperty().bindBidirectional(new SimpleStringProperty(fornecedor.getNumero()));
			tfdComplemento.textProperty().bindBidirectional(new SimpleStringProperty(fornecedor.getComplemento()));
			tfdEndereco.textProperty().bindBidirectional(new SimpleStringProperty(fornecedor.getEndereco()));
			tfdBairro.textProperty().bindBidirectional(new SimpleStringProperty(fornecedor.getBairro()));
			tfdCep.textProperty().bindBidirectional(new SimpleStringProperty(fornecedor.getCep()));
			tfdEmail.textProperty().bindBidirectional(new SimpleStringProperty(fornecedor.getEmail()));
			dpDataNascimento.setValue(DateHelper.getLocaltDate(fornecedor.getDataNascimento().toString()));
		}
		
		setEstado();
	}
	
	public Fornecedor getFornecedor() {
		if(fornecedor == null) {
			fornecedor = new Fornecedor();
		}
		
		fornecedor.setNome(tfdNome.getText());
		fornecedor.setCnpj(tfdCnpj.getText());
		fornecedor.setIe(tfdIe.getText());
		fornecedor.setCpf(tfdCpf.getText());
		fornecedor.setRg(tfdRg.getText());
		fornecedor.setCelular(tfdCelular.getText());
		fornecedor.setTelefone(tfdTelefone.getText());
		fornecedor.setEndereco(tfdEndereco.getText());
		fornecedor.setNumero(tfdNumero.getText());
		fornecedor.setComplemento(tfdComplemento.getText());
		fornecedor.setEndereco(tfdEndereco.getText());
		fornecedor.setBairro(tfdBairro.getText());
		fornecedor.setCep(tfdCep.getText());
		fornecedor.setEmail(tfdEmail.getText());
		fornecedor.setDataNascimento(DateHelper.getDate(dpDataNascimento.getValue()));
		fornecedor.setCidade(cmbCidade.getSelectionModel().getSelectedItem());
		
		return fornecedor;
	
	}
	
	private void setEstado() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> listaEstados = estadoDAO.listEstado();  

		cmbEstado.setItems(FXCollections.observableArrayList(listaEstados));
		setEstadoSelecionado(listaEstados);
	}
	
	public void setEstadoSelecionado(List<Estado> listaEstados) {
		if(fornecedor != null) {
			for (Estado estado : listaEstados) {
				if(fornecedor.getCidade() != null) {
					if(fornecedor.getCidade().getEstado().getNome().equals(estado.getNome())){
						cmbEstado.getSelectionModel().select(listaEstados.indexOf(estado));
						setCidade(estado);
					}
				}
			}
		}
	}
	
	private void setCidade(Estado estado) {
		this.cmbCidade.setItems(FXCollections.observableArrayList(estado.getCidades()));
		
		if(fornecedor == null) {
			cmbCidade.getSelectionModel().select(0);
		}else {
			for (Cidade cidade  : estado.getCidades()) {
				if(fornecedor.getCidade() != null) {
					if(fornecedor.getCidade().getNome().equals(cidade.getNome())) {
						cmbCidade.getSelectionModel().select(cidade);
					}
				}else {
					cmbCidade.getSelectionModel().select(0);
				}
			}
		}
		
	}

	private void fechar(Stage stage) {
		stage.close();
	}

	private Stage getStage(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();

		return stage;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDados();
	}
	
	
}
