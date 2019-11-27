package controller;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import helper.FornecedorDAO;
import helper.HibernateUtil2;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Fornecedor;

public class FornecedorController{
	private Session sessao = HibernateUtil2.getSessionFactory().openSession();
	FornecedorDAO fDAO = new FornecedorDAO(sessao);
	
	@FXML
    private TextField tfdNome;

    @FXML
    private TextField tfdCNPJ;

    @FXML
    private TextField tfdCPF;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnListar;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnSair;

    @FXML
    private TableView<Fornecedor> tbvListFornecedor;
    
    @FXML
    private TableColumn<?, ?> tcNome;

    @FXML
    private TableColumn<?, ?> tcCNPJ;

    @FXML
    private TableColumn<?, ?> tcCPF;

    @FXML
    private TableColumn<?, ?> tcCelular;

    @FXML
    private TableColumn<?, ?> tcTelefone;

    @FXML
    private TableColumn<?, ?> tcEndereco;
    
    @FXML
    void inserir_Click(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FornecedorView.fxml"));
		loader.setController(new FornecedorViewController(null,fDAO));
		Stage stage = new Stage();
		
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Novo Fornecedor");
		stage.setScene(new Scene(loader.load()));
		stage.showAndWait();
    }

    @FXML
    void limpar_Click(ActionEvent event) {
    	tbvListFornecedor.getItems().clear();
    }

    @FXML
    void listar_Click(ActionEvent event) {
    	List<Fornecedor> list = fDAO.listFornecedor();
    	
    	tbvListFornecedor.setItems(FXCollections.observableArrayList(list));
    	
    }

    @FXML
    void pesquisar_Click(ActionEvent event) {

    }

    @FXML
    void sair_Click(ActionEvent event) {
    	sessao.close();
    	fechar(getStage(btnSair));
    }

    @FXML
    void editaFornecedor(MouseEvent event) throws IOException {
    	if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
    		Fornecedor fornecedor = tbvListFornecedor.getSelectionModel().getSelectedItem();
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FornecedorView.fxml"));
    		loader.setController(new FornecedorViewController(fornecedor,fDAO));
    		Stage stage = new Stage();
    		
    		stage.initModality(Modality.APPLICATION_MODAL);
    		stage.setTitle("Editar Fornecedor");
    		stage.setScene(new Scene(loader.load()));
    		stage.showAndWait();                   
        }
    }

    private void fechar(Stage stage) {
		stage.close();
	}

	private Stage getStage(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();

		return stage;
	}
}
