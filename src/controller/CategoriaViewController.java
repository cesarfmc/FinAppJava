package controller;

import java.util.Optional;

import helper.CategoriaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Categoria;

public class CategoriaViewController {
	private Categoria categoria;
	private CategoriaDAO categoriaDAO;
	@FXML
	private TextField tfdCategoriaPai;

	@FXML
	private TextField tfdNomeCategoria;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnCancelar;

	@FXML
	void cancelar_Click(ActionEvent event) {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}

	@FXML
	void excluir_Click(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING, 
				"Deseja Remover Categoria?", 
				ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES){
			categoriaDAO.removeCategoria(categoria.getIdCategoria());
		} else {
			Alert alertExclusao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
			alertExclusao.showAndWait();
		}
	}

	@FXML
	void salvar_Click(ActionEvent event) {
		if(categoria.getCategorias() != null) {
			categoriaDAO.updateCategoria(getCategoria());

			Alert alert = new Alert(AlertType.INFORMATION, "Categoria Inserida", ButtonType.OK);
			alert.showAndWait();
		}else {
			categoriaDAO.addCategoria(getCategoria());

			Alert alert = new Alert(AlertType.INFORMATION, "Categoria Alterada", ButtonType.OK);
			alert.showAndWait();
		}
	}

	private Categoria getCategoria() {
		if(categoria.getCategorias() != null) {
			Categoria filho = new Categoria();
			filho.setNome(tfdNomeCategoria.getText());
			filho.setCategoria(categoria);
			
			return filho;
		}else {
			categoria.setNome(tfdNomeCategoria.getText());
			
			return categoria;
		}
	}

	@FXML
	private void initialize() {
		tfdCategoriaPai.setText(categoria.getNome());
	}

	public CategoriaViewController(Categoria categoria, CategoriaDAO categoriaDAO) {
		this.categoria = categoria;
		this.categoriaDAO = categoriaDAO;

	}

}
