package helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Cidade;

public class ClienteTabela {
	private final SimpleIntegerProperty id;
	private final SimpleStringProperty nome;
	private final SimpleStringProperty cpf;
	private final SimpleStringProperty cnpj;
	private final SimpleStringProperty telefone;
	private final SimpleStringProperty endereco;
	private final SimpleStringProperty celular;
	private final SimpleStringProperty cidade;
	private final SimpleStringProperty numero;
	private final SimpleStringProperty complemento;
	private final SimpleStringProperty bairro;
	private final SimpleStringProperty email;
	//private final SimpleDateFormat dataNascimento;

	public ClienteTabela(Integer id,String nome,String cpf,String cnpj,String telefone,String endereco,String celular,String cidade,
						  String numero,String bairro,String email,String complemento) {
		super();
		
		this.id = new SimpleIntegerProperty(id);
		this.nome = new SimpleStringProperty(nome);
		this.cpf = new SimpleStringProperty(cpf);
		this.cnpj = new SimpleStringProperty(cnpj);
		this.telefone = new SimpleStringProperty(telefone);
		this.endereco = new SimpleStringProperty(endereco);
		this.celular = new SimpleStringProperty(celular);
		this.cidade = new SimpleStringProperty(cidade);
		this.numero = new SimpleStringProperty(numero);
		this.complemento = new SimpleStringProperty(complemento);
		this.bairro = new SimpleStringProperty(bairro);
		this.email = new SimpleStringProperty(email);
		//this.dataNascimento = new SimpleDateFormat(dataNascimento);
	}
	
	public String getNome() {
		return nome.get();
	}

	public String getCpf() {
		return cpf.get();
	}

	public String getCnpj() {
		return cnpj.get();
	}

	public String getTelefone() {
		return telefone.get();
	}

	public String getEndereco() {
		return endereco.get();
	}

	public String getCelular() {
		return celular.get();
	}
	public Integer getId() {
		return id.get();
	}

	public String getCidade() {
		return cidade.get();
	}

	public String getNumero() {
		return numero.get();
	}

	public String getComplemento() {
		return complemento.get();
	}

	public String getBairro() {
		return bairro.get();
	}

	public String getEmail() {
		return email.get();
	}
	
}
