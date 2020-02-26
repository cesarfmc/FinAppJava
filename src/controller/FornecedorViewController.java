package controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import helper.DateHelper;
import helper.EstadoDAO;
import helper.FornecedorDAO;
import helper.MaskFieldUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Cidade;
import model.Estado;
import model.Fornecedor;

public class FornecedorViewController implements Initializable {
	private Fornecedor fornecedor;
	private FornecedorDAO fornecedorDao;
	private EstadoDAO estadoDao  = new EstadoDAO();

	public FornecedorViewController(FornecedorDAO fornecedorDAO) {
		this.fornecedorDao = fornecedorDAO;
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
	private DatePicker dpDataNascimento;

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
	void cancelar_Click(ActionEvent event) {
		fechar(getStage(btnCancelar));
	}

	@FXML
	void excluir_Click(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING, "Deseja Remover Fornecedor?", ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			fornecedorDao.removeFornecedor(fornecedor.getIdFornecedor());

			Alert alertExclusao = new Alert(AlertType.INFORMATION, "Fornecedor Removido", ButtonType.OK);
			alertExclusao.showAndWait();

			fechar(getStage(btnExcluir));
		} else {
			Alert alertExclusao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
			alertExclusao.showAndWait();
		}
	}

	@FXML
	void salvar_Click(ActionEvent event) {
		if (tfdNome.getText().contentEquals("")) {
			Alert alertConfirmacao = new Alert(AlertType.WARNING, "Nome Obrigatório!", ButtonType.OK);
			alertConfirmacao.showAndWait();
		} else {
			if (fornecedor != null) {
				Alert alert = new Alert(AlertType.WARNING, "Deseja Alterar Dados do Fornecedor?", ButtonType.YES,
						ButtonType.NO);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.YES) {
					fornecedorDao.updateFornecedor(getFornecedor());

					Alert alertConfirmacao = new Alert(AlertType.INFORMATION, "Fornecedor Alterado", ButtonType.OK);
					alertConfirmacao.showAndWait();

					fechar(getStage(btnExcluir));
				} else {
					Alert alertConfirmacao = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
					alertConfirmacao.showAndWait();
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING, "Deseja Inserir Fornecedor?", ButtonType.YES, ButtonType.NO);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.YES) {
					fornecedorDao.addFornecedor(getFornecedor());

					Alert alertConfirmacao = new Alert(AlertType.INFORMATION, "Fornecedor Inserido", ButtonType.OK);
					alertConfirmacao.showAndWait();
					fechar(getStage(btnSalvar));
				} else {
					Alert alertCancelar = new Alert(AlertType.INFORMATION, "Operacao Cancelada", ButtonType.OK);
					alertCancelar.showAndWait();
				}

			}
		}
	}

	@FXML
	void selecionar_estado(ActionEvent event) {
		if(cmbEstado.getSelectionModel().getSelectedIndex() != 0) {
			setCidade(cmbEstado.getSelectionModel().getSelectedItem());
		}else {
			cmbCidade.getItems().clear();
		}
	}

	public FornecedorViewController(Fornecedor fornecedor, FornecedorDAO fornecedorDAO) {
		this.fornecedor = fornecedor;
		this.fornecedorDao = fornecedorDAO;
	}

	public void setDados() {
		if(fornecedor.getBairro() != null) {
			this.tfdBairro.setText(fornecedor.getBairro());
		}
		
		if(fornecedor.getCelular() != null) {
			this.tfdCelular.setText(fornecedor.getCelular());
		}
		
		if(fornecedor.getCep() != null) {
			tfdCep.setText(fornecedor.getCep());
		}
		
		if(fornecedor.getCnpj() != null) {
			this.tfdCnpj.setText(fornecedor.getCnpj());
		}
		
		if(fornecedor.getComplemento() != null) {
			this.tfdComplemento.setText(fornecedor.getComplemento());
		}
		
		if(fornecedor.getCpf() != null) {
			this.tfdCpf.setText(fornecedor.getCpf());
		}
		
		if(fornecedor.getDataNascimento() != null) {
			this.dpDataNascimento.setValue(DateHelper.getLocaltDate(fornecedor.getDataNascimento().toString()));
		}
		
		if(fornecedor.getEmail() != null) {
			this.tfdEmail.setText(fornecedor.getEmail());
		}
		
		if(fornecedor.getEndereco() != null) {
			this.tfdEndereco.setText(fornecedor.getEndereco());
		}
		
		if(fornecedor.getIe() != null) {
			this.tfdIe.setText(fornecedor.getIe());
		}
		
		if(fornecedor.getNome() != null) {
			this.tfdNome.setText(fornecedor.getNome());
		}
		
		if(fornecedor.getRg() != null) {
			this.tfdRg.setText(fornecedor.getRg());
		}
		
		if(fornecedor.getTelefone() != null) {
			this.tfdTelefone.setText(fornecedor.getTelefone());
		}
		
		if(fornecedor.getNumero() != null) {
			this.tfdNumero.setText(fornecedor.getNumero());
		}

	}

	public Fornecedor getFornecedor() {
		if (fornecedor == null) {
			fornecedor = new Fornecedor();
		}

		if (tfdBairro.getText() != null) {
			fornecedor.setBairro(this.tfdBairro.getText());
		}

		if (tfdCelular.getText() != null) {
			fornecedor.setCelular(this.tfdCelular.getText());
		}

		if (tfdCep.getText() != null) {
			fornecedor.setCep(this.tfdCep.getText());
		}

		if (cmbCidade.getSelectionModel().getSelectedIndex() != 0) {
			fornecedor.setCidade(this.cmbCidade.getSelectionModel().getSelectedItem());
		}
		
		if (cmbEstado.getSelectionModel().getSelectedIndex() != 0) {
			fornecedor.getCidade().setEstado(this.cmbEstado.getSelectionModel().getSelectedItem());
		}
		
		if (tfdCnpj.getText() != null) {
			fornecedor.setCnpj(this.tfdCnpj.getText());
		}

		if (tfdComplemento.getText() != null) {
			fornecedor.setComplemento(this.tfdComplemento.getText());
		}

		if (tfdCpf.getText() != null) {
			fornecedor.setCpf(this.tfdCpf.getText());
		}

		if (dpDataNascimento.getValue() != null) {
			fornecedor.setDataNascimento(DateHelper.getDate(this.dpDataNascimento.getValue()));
		}

		if (tfdEmail.getText() != null) {
			fornecedor.setEmail(this.tfdEmail.getText());
		}

		if (tfdEndereco.getText() != null) {
			fornecedor.setEndereco(this.tfdEndereco.getText());
		}

		if (tfdIe.getText() != null) {
			fornecedor.setIe(this.tfdIe.getText());
		}

		if (tfdNome.getText() != null) {
			fornecedor.setNome(this.tfdNome.getText());
		}

		if (tfdRg.getText() != null) {
			fornecedor.setRg(this.tfdRg.getText());
		}

		if (tfdTelefone.getText() != null) {
			fornecedor.setTelefone(this.tfdTelefone.getText());
		}

		if (tfdNumero.getText() != null) {
			fornecedor.setNumero(this.tfdNumero.getText());
		}
		return fornecedor;

	}

	private void setEstado() {
		List<Estado> listaEstados = estadoDao.listEstado();

		cmbEstado.setItems(FXCollections.observableArrayList(listaEstados));
		cmbEstado.getItems().add(0, new Estado());
		setEstadoSelecionado(listaEstados);
	}

	public void setEstadoSelecionado(List<Estado> listaEstados) {
		if (fornecedor != null && fornecedor.getCidade().getEstado() != null) {
			for (Estado estado : listaEstados) {
				if (fornecedor.getCidade() != null) {
					if (fornecedor.getCidade().getEstado().getNome().equals(estado.getNome())) {
						cmbEstado.getSelectionModel().select(listaEstados.indexOf(estado));
						setCidade(estado);
					}
				}
			}
		} else {
			cmbEstado.getSelectionModel().select(0);
		}
	}

	private void setCidade(Estado estado) {
		this.cmbCidade.setItems(FXCollections.observableArrayList(estado.getCidades()));
		cmbCidade.getItems().add(0, new Cidade());
		if (fornecedor != null && fornecedor.getCidade() != null) {
			for (Cidade cidade : estado.getCidades()) {
				if (fornecedor.getCidade() != null) {
					if (fornecedor.getCidade().getNome().equals(cidade.getNome())) {
						cmbCidade.getSelectionModel().select(cidade);
					}
				}
			}
		} else {
			cmbCidade.getSelectionModel().select(0);
		}
	}

	private void inicia() {
		MaskFieldUtil.cnpjField(tfdCnpj);
		MaskFieldUtil.cpfField(tfdCpf);
		MaskFieldUtil.cepField(tfdCep);
		MaskFieldUtil.foneField(tfdCelular);
		MaskFieldUtil.foneField(tfdTelefone);
		setEstado();
		
		if(fornecedor != null) {
			setDados();
			btnExcluir.setDisable(false);
		}else {
			btnExcluir.setDisable(true);
		}
	}
	
	private void fechar(Stage stage) {
		stage.close();
	}

	private Stage getStage(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();

		return stage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inicia();
	}

}
