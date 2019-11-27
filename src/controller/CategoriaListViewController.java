package controller;

import java.io.IOException;
import java.util.List;

import helper.CategoriaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Categoria;

public class CategoriaListViewController {
	private CategoriaDAO categoriaDAO = new CategoriaDAO();

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnSair;
	@FXML
	private TreeView<Categoria> trvCategorias;

	@FXML
	void editar_Click(ActionEvent event) {
		Categoria catSelected = trvCategorias.getSelectionModel().getSelectedItem().getValue();
		if (catSelected != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CategoriaView.fxml"));
				loader.setController(new CategoriaViewController(catSelected,categoriaDAO));
				Parent root = loader.load();
				Stage stage = new Stage();
				Scene scene = new Scene(root, 800, 600);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Editar Categoria");
				stage.setScene(scene);
				stage.showAndWait();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void inserir_Click(ActionEvent event) {
		Categoria catSelected = trvCategorias.getSelectionModel().getSelectedItem().getValue();

		if (catSelected != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CategoriaView.fxml"));
				loader.setController(new CategoriaViewController(catSelected,categoriaDAO));
				Parent root = loader.load();
				Stage stage = new Stage();
				Scene scene = new Scene(root, 800, 600);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Nova Categoria");
				stage.setScene(scene);
				stage.showAndWait();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@FXML
	void sair_Click(ActionEvent event) {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void initialize() {
		pesquisar();
	}

	private void pesquisar() {

		List<Categoria> categoriasPai = categoriaDAO.listCategoriasPai();
		TreeItem<Categoria> fakeRoot = new TreeItem<Categoria>();

		for (Categoria categoria : categoriasPai) {
			TreeItem<Categoria> itemPai = new TreeItem<Categoria>(categoria);
			List<Categoria> categoriasFilho = categoriaDAO.listCategoriasFilhos(categoria.getIdCategoria());

			for (Categoria filho : categoriasFilho) {
				itemPai.getChildren().add(new TreeItem<Categoria>(filho));
			}
			fakeRoot.getChildren().add(itemPai);
		}

		trvCategorias.setRoot(fakeRoot);
		trvCategorias.setShowRoot(false);

	}

}
