package controller;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import helper.FormaPagamentoDAO;
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
import model.Cliente;
import model.FormaPagamento;

public class FormaPagamentoListViewController {
	private Session sessao = HibernateUtil2.getSessionFactory().openSession();
	private FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO(sessao);

	@FXML
	private TextField tfdCodigo;

	@FXML
	private TextField tfdNome;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnPesquisar;

	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnSair;

	@FXML
	private TableView<FormaPagamento> tbvFormaPagamento;

	@FXML
	private TableColumn<?, ?> tcCodigo;

	@FXML
	private TableColumn<?, ?> tcNome;

	@FXML
	private TableColumn<?, ?> tcDescricao;

	@FXML
	void inserir_Click(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FormaPagamentoView.fxml"));
		loader.setController(new FormaPagamentoViewController(null, formaPagamentoDAO,this));
		Stage stage = new Stage();

		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Nova Forma Pagamento");
		stage.setScene(new Scene(loader.load()));
		stage.showAndWait();
	}

	@FXML
	void limpar_Click(ActionEvent event) {
		tbvFormaPagamento.getItems().clear();
	}

	@FXML
	void pesquisar_Click(ActionEvent event) {
		List<FormaPagamento> list = formaPagamentoDAO.pesquisaFormaPagamento(getCodigoFormaPagamento(), getNomeFormaPagamento());
    	tbvFormaPagamento.setItems(FXCollections.observableArrayList(list));
	}

	@FXML
	void sair_Click(ActionEvent event) {
		fechar(getStage(btnSair));
	}

	@FXML
	private void initialize() {
		iniciaTabela();
	}
	
	@FXML
	void editaFormaPagamento_Click(MouseEvent event) throws IOException {
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			FormaPagamento formaPagamento = tbvFormaPagamento.getSelectionModel().getSelectedItem();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FormaPagamentoView.fxml"));
			loader.setController(new FormaPagamentoViewController(formaPagamento, formaPagamentoDAO,this));
			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Editar Forma Pagamento");
			stage.setScene(new Scene(loader.load()));
			stage.showAndWait();
		}
	}
	 private String getCodigoFormaPagamento() {
		 if(tfdCodigo.getText() == null) {
			return null;
		 }
		 return tfdCodigo.getText();
	 }
	 
	 private String getNomeFormaPagamento() {
		 if(tfdNome.getText().contentEquals("")) {
			 return null;
		 }
		 return tfdNome.getText();
	 }

	private void fechar(Stage stage) {
		stage.close();
	}

	private Stage getStage(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();

		return stage;
	}
	
	public void iniciaTabela() {
		List<FormaPagamento> listFormaPagamento = formaPagamentoDAO.listFormaPagamento();
		tbvFormaPagamento.setItems(FXCollections.observableArrayList(listFormaPagamento));
	}
}
