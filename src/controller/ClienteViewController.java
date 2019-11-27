package controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import helper.ClienteDAO;
import helper.DateHelper;
import helper.EstadoDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cidade;
import model.Cliente;
import model.Estado;

public class ClienteViewController implements Initializable {

	private Cliente cliente;
	private List<Estado> listaEstados;

	private EstadoDAO estadoDAO;
	private ClienteDAO clienteDAO;

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteViewController(Cliente cliente, ClienteDAO clienteDAO) {
		this.cliente = cliente;
		this.clienteDAO = clienteDAO;

	}

	@FXML
	private TextField tfdNome;

	@FXML
	private TextField tfdCnpj;

	@FXML
	private TextField tfdCpf;

	@FXML
	private TextField tfdCelular;

	@FXML
	private TextField tfdEndereco;

	@FXML
	private TextField tfdNumero;

	@FXML
	private TextField tfdBairro;

	@FXML
	private ComboBox<Estado> cmbEstado;

	@FXML
	private ComboBox<Cidade> cmbCidade;

	@FXML
	private TextField tfdCep;

	@FXML
	private TextField tfdEmail;

	@FXML
	private DatePicker dtpDatanascimento;

	@FXML
	private TextField tfdIe;

	@FXML
	private TextField tfdRg;

	@FXML
	private TextField tfdTelefone;

	@FXML
	private TextField tfdComplemento;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnCancelar;

	@FXML
	void action_cancelar(ActionEvent event) {
		fechar(getStage(btnCancelar));
	}

	@FXML
	void action_excluir(ActionEvent event) {
		if (cliente != null) {
			Alert alert = new Alert(AlertType.WARNING, "Deseja Remover Cliente?", ButtonType.YES, ButtonType.NO);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.YES) {
				clienteDAO.removeCliente(cliente.getIdCliente());
				
				Alert alertExclusao = new Alert(AlertType.INFORMATION, "Cliente Removido", ButtonType.OK);
				alertExclusao.showAndWait();
				
				fechar(getStage(btnExcluir));
			} else {
				Alert alertExclusao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
				alertExclusao.showAndWait();
			}
		}

	}

	@FXML
	void action_salvar(ActionEvent event) {

		if (cliente != null) {
			clienteDAO.updateCliente(getClienteDados());

			Alert alert = new Alert(AlertType.INFORMATION, "Cliente Alterado", ButtonType.OK);
			alert.showAndWait();

		} else {
			clienteDAO.addCliente(getClienteDados());

			Alert alert = new Alert(AlertType.INFORMATION, "Cliente Inserido", ButtonType.OK);
			alert.showAndWait();
		}
		fechar(getStage(btnSalvar));
	}

	private void fechar(Stage stage) {
		stage.close();
	}

	private Stage getStage(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();

		return stage;
	}

	public void limpaCampos() {
		this.tfdBairro.setText("");
		this.tfdCelular.setText("");
		this.tfdCep.setText("");
		this.tfdCnpj.setText("");
		this.tfdComplemento.setText("");
		this.tfdCpf.setText("");

		this.tfdEmail.setText("");
		this.tfdEndereco.setText("");
		this.tfdIe.setText("");
		this.tfdNome.setText("");
		this.tfdRg.setText("");
		this.tfdTelefone.setText("");
		this.tfdNumero.setText("");
	}

	public Cliente getClienteDados() {
		if (cliente == null) {
			cliente = new Cliente();
		}

		cliente.setBairro(this.tfdBairro.getText());
		cliente.setCelular(this.tfdCelular.getText());
		cliente.setCep(this.tfdCep.getText());
		cliente.setCidade(this.cmbCidade.getSelectionModel().getSelectedItem());
		cliente.setCnpj(this.tfdCnpj.getText());
		cliente.setComplemento(this.tfdComplemento.getText());
		cliente.setCpf(this.tfdCpf.getText());
		cliente.setDataNascimento(DateHelper.getDate(this.dtpDatanascimento.getValue()));
		cliente.setEmail(this.tfdEmail.getText());
		cliente.setEndereco(this.tfdEndereco.getText());
		cliente.setIe(this.tfdIe.getText());
		cliente.setNome(this.tfdNome.getText());
		cliente.setRg(this.tfdRg.getText());
		cliente.setTelefone(this.tfdTelefone.getText());
		cliente.setNumero(this.tfdNumero.getText());

		return cliente;

	}

	public void iniciaView() {
		listEstado();

		if (this.cliente != null) {
			preencheCampos();
		}
	}

	public void listEstado() {
		estadoDAO = new EstadoDAO();
		listaEstados = estadoDAO.listEstado();

		this.cmbEstado.setItems(FXCollections.observableArrayList(listaEstados));
		setEstadoSelected();

		Estado estado = this.cmbEstado.getSelectionModel().getSelectedItem();

		this.cmbCidade.setItems(FXCollections.observableArrayList(estado.getCidades()));
		setCidadeSelected();
	}

	public void setEstadoSelected() {
		int cont = 0;
		if (cliente != null) {
			for (Estado estado : listaEstados) {
				if (estado.getNome().contentEquals(cliente.getCidade().getEstado().getNome())) {
					cont = listaEstados.indexOf(estado);
				}
			}
			this.cmbEstado.getSelectionModel().select(cont);
		} else {
			this.cmbEstado.getSelectionModel().select(cont);
		}
	}

	public void setCidadeSelected() {
		int cont = 0;

		if (cliente != null) {
			for (Cidade cidade : cmbCidade.getItems()) {
				if (cidade.getNome().contentEquals(cliente.getCidade().getNome())) {
					cont = cmbCidade.getItems().indexOf(cidade);
				}
			}
			this.cmbCidade.getSelectionModel().select(cont);
		} else {
			this.cmbCidade.getSelectionModel().select(cont);
		}
	}

	@FXML
	void listarCidades(ActionEvent event) {
		Estado estado = this.cmbEstado.getSelectionModel().getSelectedItem();

		this.cmbCidade.setItems(FXCollections.observableArrayList(estado.getCidades()));
		setCidadeSelected();
	}

	public void preencheCampos() {
		this.tfdNome.setText(cliente.getNome());
		this.tfdCnpj.setText(cliente.getCnpj());
		this.tfdCpf.setText(cliente.getCpf());
		this.tfdBairro.setText(cliente.getBairro());
		this.tfdCelular.setText(cliente.getCelular());
		this.tfdCep.setText(cliente.getCep());
		this.tfdComplemento.setText(cliente.getComplemento());
		this.tfdEmail.setText(cliente.getEmail());
		this.tfdEndereco.setText(cliente.getEndereco());
		this.tfdIe.setText(cliente.getIe());
		this.tfdNumero.setText(cliente.getNumero());
		this.tfdRg.setText(cliente.getRg());
		this.dtpDatanascimento.setValue(DateHelper.getLocaltDate(cliente.getDataNascimento().toString()));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		iniciaView();

	}

}
