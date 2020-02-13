package controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import helper.ClienteDAO;
import helper.DateHelper;
import helper.EstadoDAO;
import helper.MaskFieldUtil;
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
				cancelarOperacao();
			}
		}

	}

	@FXML
	void action_salvar(ActionEvent event) {
		if(tfdNome.getText().contentEquals("")) {
			Alert alertConfirmacao = new Alert(AlertType.WARNING, "Nome Obrigatório!", ButtonType.OK);
			alertConfirmacao.showAndWait();
		}else {
			if (cliente != null) {
				Alert alert = new Alert(AlertType.WARNING, "Deseja Alterar Dados do Cliente?", ButtonType.YES, ButtonType.NO);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.YES) {
					clienteDAO.updateCliente(getClienteDados());
					
					Alert alertConfirmacao = new Alert(AlertType.INFORMATION, "Cliente Alterado", ButtonType.OK);
					alertConfirmacao.showAndWait();

					fechar(getStage(btnExcluir));
				} else {
					cancelarOperacao();
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING, "Deseja Inserir Cliente?", ButtonType.YES, ButtonType.NO);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.YES) {
					clienteDAO.addCliente(getClienteDados());

					Alert alertConfirmacao = new Alert(AlertType.INFORMATION, "Cliente Inserido", ButtonType.OK);
					alertConfirmacao.showAndWait();
					fechar(getStage(btnSalvar));
				} else {
					cancelarOperacao();
				}
				
			}
		}
	}
	
	private void cancelarOperacao() {
		Alert alertConfirmacao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
		alertConfirmacao.showAndWait();
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

		if(tfdBairro.getText() != null) {
			cliente.setBairro(this.tfdBairro.getText());
		}
		
		if(tfdCelular.getText() != null) {
			cliente.setCelular(this.tfdCelular.getText());
		}
		
		if(tfdCep.getText() != null) {
			cliente.setCep(this.tfdCep.getText());
		}
		
		if(cmbCidade.getSelectionModel().getSelectedItem() != null) {
			cliente.setCidade(this.cmbCidade.getSelectionModel().getSelectedItem());
		}
		
		if(tfdCnpj.getText() != null) {
			cliente.setCnpj(this.tfdCnpj.getText());
		}
		
		if(tfdComplemento.getText() != null) {
			cliente.setComplemento(this.tfdComplemento.getText());
		}
		
		if(tfdCpf.getText() != null) {
			cliente.setCpf(this.tfdCpf.getText());
		}
		
		if(dtpDatanascimento.getValue() != null) {
			cliente.setDataNascimento(DateHelper.getDate(this.dtpDatanascimento.getValue()));
		}
		
		if(tfdEmail.getText() != null) {
			cliente.setEmail(this.tfdEmail.getText());
		}
		
		if(tfdEndereco.getText() != null) {
			cliente.setEndereco(this.tfdEndereco.getText());
		}
		
		if(tfdIe.getText() != null) {
			cliente.setIe(this.tfdIe.getText());
		}
		
		if(tfdNome.getText() != null) {
			cliente.setNome(this.tfdNome.getText());
		}
		
		if(tfdRg.getText() != null) {
			cliente.setRg(this.tfdRg.getText());
		}
		
		if(tfdTelefone.getText() != null) {
			cliente.setTelefone(this.tfdTelefone.getText());
		}
		
		if(tfdNumero.getText() != null) {
			cliente.setNumero(this.tfdNumero.getText());
		}
		return cliente;
	}

	public void iniciaView() {
		MaskFieldUtil.cnpjField(tfdCnpj);
		MaskFieldUtil.cpfField(tfdCpf);
		MaskFieldUtil.cepField(tfdCep);
		MaskFieldUtil.foneField(tfdCelular);
		MaskFieldUtil.foneField(tfdTelefone);
		listEstado();

		if (this.cliente != null) {
			btnExcluir.setDisable(false);
			preencheCampos();
		}else {
			btnExcluir.setDisable(true);
		}
	}

	public void listEstado() {
		estadoDAO = new EstadoDAO();
		listaEstados = estadoDAO.listEstado();

		this.cmbEstado.setItems(FXCollections.observableArrayList(listaEstados));
		cmbEstado.getItems().add(0, new Estado());
		setEstadoSelected();

		Estado estado = this.cmbEstado.getSelectionModel().getSelectedItem();

		if(estado != null) {
			this.cmbCidade.setItems(FXCollections.observableArrayList(estado.getCidades()));
			setCidadeSelected();
		}
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
			this.cmbEstado.getSelectionModel().select(0);
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
			this.cmbCidade.getSelectionModel().select(0);
		}
	}

	@FXML
	void listarCidades(ActionEvent event) {
		if(cmbEstado.getSelectionModel().getSelectedIndex() != 0) {
			Estado estado = this.cmbEstado.getSelectionModel().getSelectedItem();

			if(estado != null) {
				this.cmbCidade.setItems(FXCollections.observableArrayList(estado.getCidades()));
				setCidadeSelected();
			}
		}else {
			cmbCidade.getItems().clear();
		}
	}

	public void preencheCampos() {
		if(cliente.getBairro() != null) {
			this.tfdBairro.setText(cliente.getBairro());
		}
		
		if(cliente.getCelular() != null) {
			this.tfdCelular.setText(cliente.getCelular());
		}
		
		if(cliente.getCep() != null) {
			tfdCep.setText(cliente.getCep());
		}
		
		if(cliente.getCnpj() != null) {
			this.tfdCnpj.setText(cliente.getCnpj());
		}
		
		if(cliente.getComplemento() != null) {
			this.tfdComplemento.setText(cliente.getComplemento());
		}
		
		if(cliente.getCpf() != null) {
			this.tfdCpf.setText(cliente.getCpf());
		}
		
		if(cliente.getDataNascimento() != null) {
			this.dtpDatanascimento.setValue(DateHelper.getLocaltDate(cliente.getDataNascimento().toString()));
		}
		
		if(cliente.getEmail() != null) {
			this.tfdEmail.setText(cliente.getEmail());
		}
		
		if(cliente.getEndereco() != null) {
			this.tfdEndereco.setText(cliente.getEndereco());
		}
		
		if(cliente.getIe() != null) {
			this.tfdIe.setText(cliente.getIe());
		}
		
		if(cliente.getNome() != null) {
			this.tfdNome.setText(cliente.getNome());
		}
		
		if(cliente.getRg() != null) {
			this.tfdRg.setText(cliente.getRg());
		}
		
		if(cliente.getTelefone() != null) {
			this.tfdTelefone.setText(cliente.getTelefone());
		}
		
		if(cliente.getNumero() != null) {
			this.tfdNumero.setText(cliente.getNumero());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		iniciaView();

	}

}
