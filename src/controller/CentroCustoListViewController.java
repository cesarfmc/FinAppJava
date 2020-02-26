package controller;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import helper.CentroCustoDAO;
import helper.GeradorCsv;
import helper.HibernateUtil2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.CentroCusto;

public class CentroCustoListViewController {
	private Session sessao = HibernateUtil2.getSessionFactory().openSession();
	private CentroCustoDAO centroCustoDAO = new CentroCustoDAO(sessao);
	private long tempoInicial;

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
		CentroCusto centroSelected = verificaCentro();
		if (centroSelected != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CentroCustoView.fxml"));
				loader.setController(
						new CentroCustoViewController(centroSelected, centroSelected.getCentroCusto(), centroCustoDAO,this));
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
		CentroCusto centroSelected = verificaCentro();
		if (centroSelected != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CentroCustoView.fxml"));
				loader.setController(new CentroCustoViewController(null, centroSelected, centroCustoDAO,this));
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

	@FXML
	void sair_Click(ActionEvent event) {
		sessao.close();
		Stage stage = (Stage) btnSair.getScene().getWindow();
		fechar(stage);
	}

	private CentroCusto verificaCentro() {
		if(trvCentroDeCustos.getSelectionModel().getSelectedItem() != null) {
			return trvCentroDeCustos.getSelectionModel().getSelectedItem().getValue();
		}else {
			Alert alert = new Alert(AlertType.WARNING, "Nenhum Centro de Custo Selecionado", ButtonType.OK);
			alert.showAndWait();
		}
		
		return null;
		 
	}
	private void addFilho(TreeItem<CentroCusto> itemPai, CentroCusto centroCustoPai) {
		List<CentroCusto> centro = centroCustoDAO.listCentroCustoFilhos(centroCustoPai.getIdCentroCusto());
		TreeItem<CentroCusto> filho = new TreeItem<CentroCusto>(centroCustoPai);

		itemPai.getChildren().add(filho);
		
		for (CentroCusto c : centro) {
			
			addFilho(filho, c);
		}
		filho.setExpanded(true);

	}

	public void pesquisar() {
		List<CentroCusto> centroCustoPai = centroCustoDAO.listCentroCustoPai();
		TreeItem<CentroCusto> fakeRoot = new TreeItem<CentroCusto>();

		for (CentroCusto centroCusto : centroCustoPai) {
			TreeItem<CentroCusto> itemPai = new TreeItem<CentroCusto>(centroCusto);
			List<CentroCusto> listaFilhos = centroCustoDAO.listCentroCustoFilhos(centroCusto.getIdCentroCusto());
			

			for (CentroCusto filho : listaFilhos) {
				addFilho(itemPai, filho);
			}
			itemPai.setExpanded(true);
			fakeRoot.getChildren().add(itemPai);
		}

		trvCentroDeCustos.setRoot(fakeRoot);
		trvCentroDeCustos.setShowRoot(false);
	}
	
	private void fechar(Stage stage) {
		stage.close();
	}
	
	@FXML
	private void initialize() {
		tempoInicial = System.currentTimeMillis();
		pesquisar();
		GeradorCsv.tempoFinal(tempoInicial, "Listar CentroCusto");
	}
}
