package model;
// Generated 21/08/2019 02:04:11 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Banco generated by hbm2java
 */
@Entity
@Table(name = "Banco", catalog = "FIN")
public class Banco implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4163094970606136205L;
	private int idBanco;
	private String numero;
	private String nome;
	private Set<Conta> contas = new HashSet<Conta>(0);

	public Banco() {
	}

	public Banco(int idBanco, String numero, String nome) {
		this.idBanco = idBanco;
		this.numero = numero;
		this.nome = nome;
	}

	public Banco(int idBanco, String numero, String nome, Set<Conta> contas) {
		this.idBanco = idBanco;
		this.numero = numero;
		this.nome = nome;
		this.contas = contas;
	}

	@Id

	@Column(name = "idBanco", unique = true, nullable = false)
	public int getIdBanco() {
		return this.idBanco;
	}

	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}

	@Column(name = "numero", nullable = false)
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "nome", nullable = false)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "banco")
	public Set<Conta> getContas() {
		return this.contas;
	}

	public void setContas(Set<Conta> contas) {
		this.contas = contas;
	}

}
