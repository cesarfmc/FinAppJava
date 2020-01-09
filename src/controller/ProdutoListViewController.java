package controller;

import java.io.IOException;
import java.util.List;

import helper.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Produto;

public class ProdutoListViewController {
	private ProdutoDAO produtoDAO = new ProdutoDAO();

	@FXML
	private TextField tfdNome;

	@FXML
	private TextField tfdCodigo;

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
	private TableView<Produto> tbvListaProdutos;

	@FXML
	void edita_Produto(MouseEvent event) throws IOException {
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			Produto produto = tbvListaProdutos.getSelectionModel().getSelectedItem();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProdutoViewView.fxml"));
			loader.setController(new ProdutoViewController(produto,produtoDAO));
			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Editar Produto");
			stage.setScene(new Scene(loader.load()));
			stage.showAndWait();                   
		}
	}

	@FXML
	void inserir_Click(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProdutoView.fxml"));
		loader.setController(new ProdutoViewController(null,produtoDAO));
		Stage stage = new Stage();

		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Editar Produto");
		stage.setScene(new Scene(loader.load()));
		stage.showAndWait();                   
	}

	@FXML
	void limpar_Click(ActionEvent event) {
		tbvListaProdutos.getItems().clear();
	}

	@FXML
	void pesquisar_Click(ActionEvent event) {

	}

	@FXML
	void sair_Click(ActionEvent event) {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}

	@FXML
	void todos_Click(ActionEvent event) {
		List<Produto> listaProdutos = produtoDAO.listProduto();

		tbvListaProdutos.setItems(FXCollections.observableArrayList(listaProdutos));
	}

}
