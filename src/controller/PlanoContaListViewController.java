package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import helper.HibernateUtil2;
import helper.PlanoContaDAO;
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
import model.PlanoConta;

public class PlanoContaListViewController {
	private Session sessao = HibernateUtil2.getSessionFactory().openSession();
	private PlanoContaDAO planoContaDAO = new PlanoContaDAO(sessao);
	@FXML
	private TreeView<PlanoConta> trvPlanoConta;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnSair;

	@FXML
	void editar_Click(ActionEvent event) {
		PlanoConta planoContaSelected = trvPlanoConta.getSelectionModel().getSelectedItem().getValue();
		if (planoContaSelected != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlanoContaView.fxml"));
				loader.setController(new PlanoContaViewController(planoContaSelected,
						planoContaSelected.getPlanoConta(), planoContaDAO));
				Parent root = loader.load();
				Stage stage = new Stage();
				Scene scene = new Scene(root, 800, 600);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Editar Plano de Conta");
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
		PlanoConta planoContaSelected = trvPlanoConta.getSelectionModel().getSelectedItem().getValue();
		if (planoContaSelected != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlanoContaView.fxml"));
				loader.setController(new PlanoContaViewController(null, planoContaSelected, planoContaDAO));
				Parent root = loader.load();
				Stage stage = new Stage();
				Scene scene = new Scene(root, 800, 600);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Novo Plano de Conta");
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
		fechar(getStage(btnSair));
	}

	@FXML
	private void initialize() {
		pesquisar();
	}

	private void addFilho(TreeItem<PlanoConta> itemPai, PlanoConta planoContaPai) {
		Iterator<PlanoConta> plano = planoContaPai.getPlanoContas().iterator();
		TreeItem<PlanoConta> filho = new TreeItem<PlanoConta>(planoContaPai);
		PlanoConta p;

		itemPai.getChildren().add(filho);

		while (plano.hasNext()) {
			p = plano.next();

			addFilho(filho, p);
		}

	}

	private void pesquisar() {
		List<PlanoConta> planoContaPai = planoContaDAO.listPlanoContaPai();
		TreeItem<PlanoConta> fakeRoot = new TreeItem<PlanoConta>();

		for (PlanoConta planoConta : planoContaPai) {
			TreeItem<PlanoConta> itemPai = new TreeItem<PlanoConta>(planoConta);
			List<PlanoConta> listaFilhos = planoContaDAO.listPlanoContaFilhos(planoConta.getIdPlanoConta());

			for (PlanoConta filho : listaFilhos) {
				addFilho(itemPai, filho);
			}
			fakeRoot.getChildren().add(itemPai);
		}

		trvPlanoConta.setRoot(fakeRoot);
		trvPlanoConta.setShowRoot(false);
	}

	private void fechar(Stage stage) {
		stage.close();
	}

	private Stage getStage(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();

		return stage;
	}
}
