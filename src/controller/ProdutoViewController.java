package controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.hibernate.Session;

import helper.CategoriaDAO;
import helper.FornecedorDAO;
import helper.HibernateUtil2;
import helper.MarcaDAO;
import helper.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Categoria;
import model.Fornecedor;
import model.Marca;
import model.Produto;

public class ProdutoViewController implements Initializable{
	private Session sessao = HibernateUtil2.getSessionFactory().openSession();
	private Produto produto;
	private ProdutoDAO produtoDAO;
	private List<Categoria> listaCategorias;
	private List<Marca> listaMarcas;
	private List<Fornecedor> listaFornecedores;

	@FXML
	private TextField tfdNome;

	@FXML
	private TextField tfdCodigo;

	@FXML
	private TextField tfdPrecoCusto;

	@FXML
	private ComboBox<Categoria> cmbCategoria;

	@FXML
	private ComboBox<Marca> cmbMarca;

	@FXML
	private ComboBox<Fornecedor> cmbFornecedor;

	@FXML
	private TextField tfdDescricao;

	@FXML
	private TextField tfdPrecoVenda;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnCancelar;

	@FXML
	void cancelar_Click(ActionEvent event) {

	}

	@FXML
	void excluir_Click(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING, 
				"Deseja Remover Produto?", 
				ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES){
			produtoDAO.removeProduto(produto.getIdProduto());
		} else {
			Alert alertExclusao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
			alertExclusao.showAndWait();
		}
	}

	@FXML
	void salvar_Click(ActionEvent event) {
		if(produto != null) {
			produtoDAO.updateProduto(getProduto());

			Alert alert = new Alert(AlertType.INFORMATION, "Produto Inserido", ButtonType.OK);
			alert.showAndWait();
		}else {
			produtoDAO.addProduto(getProduto());

			Alert alert = new Alert(AlertType.INFORMATION, "Produto Alterado", ButtonType.OK);
			alert.showAndWait();
		}
	}

	public ProdutoViewController(Produto produto, ProdutoDAO produtoDAO) {
		super();
		this.produto = produto;
		this.produtoDAO = produtoDAO;
	}

	public void iniciaListaCategoria() {
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		listaCategorias = categoriaDAO.listCategoria();

		cmbCategoria.setItems(FXCollections.observableArrayList(listaCategorias));

	}

	public void iniciaListaMarca() {
		MarcaDAO marcaDAO = new MarcaDAO();
		listaMarcas = marcaDAO.listMarca();

		cmbMarca.setItems(FXCollections.observableArrayList(listaMarcas));
	}

	public void iniciaListaFornecedor() {
		FornecedorDAO fornecedorDAO = new FornecedorDAO(sessao);
		listaFornecedores = fornecedorDAO.listFornecedor();

		cmbFornecedor.setItems(FXCollections.observableArrayList(listaFornecedores));
	}

	public void inicia() {
		iniciaListaCategoria();
		iniciaListaFornecedor();
		iniciaListaMarca();
	}

	public Produto getProduto() {
		if(produto == null) {
			produto = new Produto();
		}

		produto.setNome(tfdNome.getText());
		produto.setCodigo(tfdCodigo.getText());
		produto.setPrecoCusto(BigDecimal.valueOf(Double.parseDouble(tfdPrecoCusto.getText())));
		produto.setPrecoVenda(BigDecimal.valueOf(Double.parseDouble(tfdPrecoVenda.getText())));
		produto.setCategoria(cmbCategoria.getSelectionModel().getSelectedItem());
		produto.setFornecedor(cmbFornecedor.getSelectionModel().getSelectedItem());
		produto.setMarca(cmbMarca.getSelectionModel().getSelectedItem());

		return produto;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inicia();
	}

}
