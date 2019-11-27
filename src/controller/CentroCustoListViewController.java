package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import helper.CentroCustoDAO;
import helper.HibernateUtil2;
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
import model.CentroCusto;

public class CentroCustoListViewController {
	private Session sessao = HibernateUtil2.getSessionFactory().openSession();
	private CentroCustoDAO centroCustoDAO = new CentroCustoDAO(sessao);

	@FXML
	private TreeView<CentroCusto> trvCentroDeCustos;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnSair;

	@FXML
	void editar_Click(ActionEvent event) {
		CentroCusto centroSelected = trvCentroDeCustos.getSelectionModel().getSelectedItem().getValue();
		if (centroSelected != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CentroCustoView.fxml"));
				loader.setController(
						new CentroCustoViewController(centroSelected, centroSelected.getCentroCusto(), centroCustoDAO));
				Parent root = loader.load();
				Stage stage = new Stage();
				Scene scene = new Scene(root, 800, 600);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Editar Centro de Custo");
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
		CentroCusto centroSelected = trvCentroDeCustos.getSelectionModel().getSelectedItem().getValue();
		if (centroSelected != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CentroCustoView.fxml"));
				loader.setController(new CentroCustoViewController(null, centroSelected, centroCustoDAO));
				Parent root = loader.load();
				Stage stage = new Stage();
				Scene scene = new Scene(root, 800, 600);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Inserir Centro de Custo");
				stage.setScene(scene);
				stage.showAndWait();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void fechar(Stage stage) {
		stage.close();
	}

	@FXML
	void sair_Click(ActionEvent event) {
		sessao.close();
		Stage stage = (Stage) btnSair.getScene().getWindow();
		fechar(stage);
	}

	private void addFilho(TreeItem<CentroCusto> itemPai, CentroCusto centroCustoPai) {
		Iterator<CentroCusto> centro = centroCustoPai.getCentroCustos().iterator();
		TreeItem<CentroCusto> filho = new TreeItem<CentroCusto>(centroCustoPai);
		CentroCusto c;

		itemPai.getChildren().add(filho);
		
		while (centro.hasNext()) {
			c = centro.next();
			
			addFilho(filho, c);
		}

	}

	private void pesquisar() {
		List<CentroCusto> centroCustoPai = centroCustoDAO.listCentroCustoPai();
		TreeItem<CentroCusto> fakeRoot = new TreeItem<CentroCusto>();

		for (CentroCusto centroCusto : centroCustoPai) {
			TreeItem<CentroCusto> itemPai = new TreeItem<CentroCusto>(centroCusto);
			List<CentroCusto> listaFilhos = centroCustoDAO.listCentroCustoFilhos(centroCusto.getIdCentroCusto());

			for (CentroCusto filho : listaFilhos) {
				addFilho(itemPai, filho);
			}
			fakeRoot.getChildren().add(itemPai);
		}

		trvCentroDeCustos.setRoot(fakeRoot);
		trvCentroDeCustos.setShowRoot(false);
	}

	@FXML
	private void initialize() {
		pesquisar();
	}
}
