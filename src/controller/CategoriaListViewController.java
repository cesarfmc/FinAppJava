package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import helper.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Categoria;

public class CategoriaListViewController {
	private List<Categoria> categoria;
	@FXML
	private TreeView<CategoriaItem> trvCategorias;

	@FXML
	void editar_Click(ActionEvent event) {
		CategoriaItem catSelected = trvCategorias.getSelectionModel().getSelectedItem().getValue();
		if (catSelected != null) {
			if(catSelected.getCategoriaFilhos() != null) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CategoriaView.fxml"));
					loader.setController(new CategoriaViewController(catSelected));
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
			}else {
				System.out.println("Nao é possivel editar uma categoria basica");
			}
		} else {

			System.out.println("Nenhuma Categoria Selecionada");
		}
	}

	@FXML
	void inserir_Click(ActionEvent event) {
		CategoriaItem catSelected = trvCategorias.getSelectionModel().getSelectedItem().getValue();

		if (catSelected != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CategoriaView.fxml"));
				loader.setController(new CategoriaViewController(catSelected));
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

		} else {
			System.out.println("Nenhuma Categoria Selecionada");
		}

	}

	@FXML
	private void initialize() {
		pesquisar();
	}


	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}
	
	public Scene getScene() {
		return trvCategorias.getScene();
	}
	private List<Categoria> getCategoriasPai() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Categoria> cr = cb.createQuery(Categoria.class);
		Root<Categoria> root = cr.from(Categoria.class);
		cr.select(root).where(cb.isNull(root.get("categoria")));

		List<Categoria> categoriasPai = session.createQuery(cr).getResultList();

		return categoriasPai;
	}

	private void pesquisar() {

		List<CategoriaItem> categoriaItens = new ArrayList<CategoriaItem>();
		List<Categoria> categoriasPai = getCategoriasPai();
		TreeItem<CategoriaItem> fakeRoot = new TreeItem<CategoriaItem>();

		for (Categoria categoria : categoriasPai) {
			categoriaItens.add(new CategoriaItem(categoria, fakeRoot));
		}

		trvCategorias.setRoot(fakeRoot);
		trvCategorias.setShowRoot(false);

	}

}
