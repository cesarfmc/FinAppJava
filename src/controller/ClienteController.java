package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import helper.ClienteDAO;
import helper.ClienteTabela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;

public class ClienteController {
	private ClienteDAO clienteDao = new ClienteDAO();

	private List<Cliente> clienteList = clienteDao.listCliente();

	private ObservableList<ClienteTabela> listClienteTabela = FXCollections.observableArrayList();

	public Cliente getCliente() {
		return clienteDao.retornaCliente(tbvClienteList.getSelectionModel().getSelectedItem().getId());
	}

    @FXML
    private TableView<ClienteTabela> tbvClienteList;

    @FXML
    private TableColumn<ClienteTabela, String> tcNome;

    @FXML
    private TableColumn<ClienteTabela, String> tcCnpj;

    @FXML
    private TableColumn<ClienteTabela, String> tcCpf;

    @FXML
    private TableColumn<ClienteTabela, String> tcCelular;

    @FXML
    private TableColumn<ClienteTabela, String> tcTelefone;

    @FXML
    private TableColumn<ClienteTabela, String> tcEndereco;

    @FXML
    private TableColumn<ClienteTabela, String> tcNumero;

    @FXML
    private TableColumn<ClienteTabela, String> tcComplemento;

    @FXML
    private TableColumn<ClienteTabela, String> tcBairro;

    @FXML
    private TableColumn<ClienteTabela, String> tcCidade;

    @FXML
    private TableColumn<ClienteTabela, String> tcEmail;

    @FXML
    private TableColumn<ClienteTabela, Date> tcDataNascimento;

    @FXML
    void inserir_Click(ActionEvent event) {
    	Cliente cliente = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/clientView.fxml"));
			loader.setController(new ClienteViewController(cliente));
			Parent root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root, 800, 600);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Nova Cliente");
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }

    @FXML
    void limpar_Click(ActionEvent event) {
    	listClienteTabela.clear();
    }

    @FXML
    void listar_Click(ActionEvent event) {
    	if (!listClienteTabela.isEmpty()) {
			listClienteTabela.clear();
			clienteList.clear();
			clienteList = clienteDao.listCliente();
			
		}

		for (Cliente cliente : clienteList) {
			ClienteTabela c = new ClienteTabela(cliente.getIdCliente(), cliente.getNome(), cliente.getCnpj(),
					cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getEndereco()
					,null,cliente.getNumero(),cliente.getBairro(),cliente.getComplemento(),cliente.getEmail());
			listClienteTabela.add(c);
		}
		
		tcNome.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("nome"));
		tcCnpj.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("cnpj"));
		tcCpf.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("cpf"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("telefone"));
		tcCelular.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("celular"));
		tcEndereco.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("endereco"));
		tcNumero.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("numero"));
		tbvClienteList.setItems(listClienteTabela);

    }

    @FXML
    void pesquisar_Click(ActionEvent event) {

    }

    @FXML
    void sair_Click(ActionEvent event) {

    }

}
