package controller;

import java.io.IOException;

import helper.GeradorCsv;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class mainController {

	private Parent root;
	private long tempoInicial;

	@FXML
	private Menu mnuCad;

	@FXML
	private MenuItem itemCadClientes;

	@FXML
	private MenuItem itemCadFornecedores;

	@FXML
	private MenuItem itemCadProduto;

	@FXML
	private MenuItem itemCadCategoria;

	@FXML
	private MenuItem itemCadMarca;

	@FXML
	private MenuItem itemCadCentroDeCusto;

	@FXML
	private Menu mnuFinanceiro;

	@FXML
	private MenuItem itemFinanceiroContasPagar;

	@FXML
	private MenuItem itemFinanceiroContasReceber;

	@FXML
	private MenuItem itemFinanceiroContasCorrentes;

	@FXML
	private MenuItem itemFinanceiroDespesasMensais;

	@FXML
	private MenuItem itemFinanceiroReceitasMensais;

	@FXML
	private MenuItem itemFinanceiroPlanoConta;

	@FXML
	private MenuItem itemFinanceiroContaReceber;

	@FXML
	private MenuItem itemFinanceiroFormaPagamento;
	
	@FXML
	private Menu mnuRelatorios;

	@FXML
	private MenuItem itemRelatoriosContasPagar;

	@FXML
	private MenuItem itemRelatoriosContasReceber;

	@FXML
	private MenuItem itemRelatoriosContasCorrentes;

	@FXML
	private MenuItem itemRelatoriosTransacoes;

	@FXML
	private Menu mnuUtilitarios;

	@FXML
	private MenuItem itemUtilitariosOpcoes;

	@FXML
	private MenuItem itemUtilitariosUsuarios;

	@FXML
	void mnuCentroDeCusto_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/CentroCustoListView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			stage.setTitle("Lista de Centro de Custos");
			stage.setScene(scene);
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela Centro de Custo");
			
			stage.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mnuFormaPagamento_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FormaPagamentoListView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			stage.setTitle("Lista Formas Pagamento");
			stage.setScene(scene);
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela Forma de Pagamento");
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mnCategoria_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (VBox) FXMLLoader.load(getClass().getResource("/view/CategoriaListView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			stage.setTitle("Lista de Categorias");
			stage.setScene(scene);
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela Categorias");
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void mnMarca_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/MarcaListView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Lista de Marcas");
			stage.setScene(scene);
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela Marcas");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void mnuPlanoConta_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/PlanoContaListView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			stage.setTitle("Lista Plano de Contas");
			stage.setScene(scene);
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela Plano de Contas");
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mnProduto_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ProdutoListView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Lista de Produtos");
			stage.setScene(scene);
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela de Produtos");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void mnFornecedor_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FornecedorListView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Lista de Fornecedores");
			stage.setScene(scene);
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela de Fornecedores");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mnuCliente_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (VBox) FXMLLoader.load(getClass().getResource("/view/ClienteListView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Lista de Clientes");
			stage.setScene(scene);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela de Clientes");
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mnuContaPagarReport_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ContaPagarReportView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Relatorio de Contas a pagar");
			stage.setScene(scene);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela de Relatorio Contas a Pagar");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mnuContaPagar_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ContaPagarListView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Lista de Contas Pagar");
			stage.setScene(scene);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela de Contas a Pagar");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mnuContaReceberReport_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ContaReceberReportView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Relatorio de Contas a Receber");
			stage.setScene(scene);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela de Relatorio de Contas a Receber");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mnuContaReceber_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ContaReceberListView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Lista de Contas Receber");
			stage.setScene(scene);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela de Contas a Receber");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mnuContaReport_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ContaReportView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Relatorio de Contas");
			stage.setScene(scene);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela Relatorio de Contas");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mnuConta_Click(ActionEvent event) {

		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ContaListView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Lista de Contas");
			stage.setScene(scene);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela de Contas");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mnuDespesaMensal_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/DespesaMensalListView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Lista de Despesas Mensais");
			stage.setScene(scene);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela de Despesas Mensais");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mnuOpcoes_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/OpcoesView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Opcoes");
			stage.setScene(scene);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela Opcoes");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mnuReceitaMensal_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ReceitaMensalListView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Lista de Receitas Mensais");
			stage.setScene(scene);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela de Receitas Mensais");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mnuTransacaoReport_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/TransacaoReportView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Lista de Transacoes");
			stage.setScene(scene);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela de Relatorio de Transacoes");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mnuUsuario_Click(ActionEvent event) {
		try {
			tempoInicial = System.currentTimeMillis();
			
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/PerfilListView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);

			stage.setTitle("Lista de Usuarios");
			stage.setScene(scene);
			
			//Janela Modal
			//stage.initModality(Modality.APPLICATION_MODAL);
			
			GeradorCsv.tempoFinal(tempoInicial, "Iniciar Tela de Usuarios");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
