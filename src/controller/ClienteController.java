package controller;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import helper.ClienteDAO;
import helper.HibernateUtil2;
import helper.MaskFieldUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;

public class ClienteController {
	private Session sessao = HibernateUtil2.getSessionFactory().openSession();
	private ClienteDAO clienteDao = new ClienteDAO(sessao);

	@FXML
	private TextField tfdNome;

	@FXML
	private TextField tfdCnpj;

	@FXML
	private TextField tfdCpf;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnTodos;

	@FXML
	private Button btnPesquisar;

	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnSair;

	@FXML
	private TableView<Cliente> tbvClienteList;

	@FXML
	private TableColumn<?, ?> tcNome;

	@FXML
	private TableColumn<?, ?> tcCnpj;

	@FXML
	private TableColumn<?, ?> tcCpf;

	@FXML
	private TableColumn<?, ?> tcCelular;

	@FXML
	private TableColumn<?, ?> tcTelefone;

	@FXML
	private TableColumn<?, ?> tcEndereco;

	@FXML
	private TableColumn<?, ?> tcNumero;

	@FXML
	private TableColumn<?, ?> tcComplemento;

	@FXML
	private TableColumn<?, ?> tcBairro;

	@FXML
	private TableColumn<?, ?> tcCidade;

	@FXML
	private TableColumn<?, ?> tcEmail;

	@FXML
	private TableColumn<?, ?> tcDataNascimento;

	@FXML
	void inserir_Click(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ClientView.fxml"));
		loader.setController(new ClienteViewController(null, clienteDao));
		Stage stage = new Stage();

		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Novo Cliente");
		stage.setScene(new Scene(loader.load()));
		stage.showAndWait();
	}

	@FXML
	void action_editar(MouseEvent event) throws IOException {
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
    		Cliente cliente = tbvClienteList.getSelectionModel().getSelectedItem();
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ClientView.fxml"));
			loader.setController(new ClienteViewController(cliente, clienteDao));
			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Editar Cliente");
			stage.setScene(new Scene(loader.load()));
			stage.showAndWait();       
        }
	}

	@FXML
	void limpar_Click(ActionEvent event) {
		tbvClienteList.getItems().clear();
	}

	@FXML
	void listar_Click(ActionEvent event) {
    	List<Cliente> list = clienteDao.listCliente();
    	tbvClienteList.setItems(FXCollections.observableArrayList(list));
	}

	@FXML
	void pesquisar_Click(ActionEvent event) {
		List<Cliente> list = clienteDao.pesquisaCliente(getNomeCliente(), getCnpjCliente(), getCpfCliente());
		if(list.isEmpty()) {
			Alert alertBusca = new Alert(AlertType.INFORMATION, "Nenhum Cliente Encontrado", ButtonType.OK);
			alertBusca.showAndWait();
		}else {
			tbvClienteList.setItems(FXCollections.observableArrayList(list));
		}
	}


	private void fechar(Stage stage) {
		stage.close();
	}

	private Stage getStage(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();

		return stage;
	}
	
	@FXML
	void sair_Click(ActionEvent event) {
		sessao.close();
		fechar(getStage(btnSair));
	}
	
	 @FXML
	 private void initialize() {
		 MaskFieldUtil.cnpjField(this.tfdCnpj);
		 MaskFieldUtil.cpfField(tfdCpf);
	 }
	 
	 private String getNomeCliente() {
		 if(tfdNome.getText() != null) {
			 return tfdNome.getText();
		 }
		 return null;
	 }
	 
	 private String getCnpjCliente() {
		 if(tfdCnpj.getText() != null) {
			 return tfdCnpj.getText();
		 }
		 return null;
	 }
	 
	 private String getCpfCliente() {
		 if(tfdCpf.getText() != null) {
			 return tfdCpf.getText();
		 }
		 return null;
	 }
}
