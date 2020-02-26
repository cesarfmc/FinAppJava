package model;
// Generated 15/05/2019 11:16:03 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Estado generated by hbm2java
 */
@Entity
@Table(name = "Estado", schema = "dbo", catalog = "Fin3l")
public class Estado implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uf;
	private String nome;
	private int regiao;
	private Set<Cidade> cidades = new HashSet<Cidade>(0);

	public Estado() {
	}

	public Estado(String uf, String nome, int regiao) {
		this.uf = uf;
		this.nome = nome;
		this.regiao = regiao;
	}

	public Estado(String uf, String nome, int regiao, Set<Cidade> cidades) {
		this.uf = uf;
		this.nome = nome;
		this.regiao = regiao;
		this.cidades = cidades;
	}

	@Id

	@Column(name = "uf", unique = true, nullable = false)
	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Column(name = "nome", nullable = false)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "regiao", nullable = false)
	public int getRegiao() {
		return this.regiao;
	}

	public void setRegiao(int regiao) {
		this.regiao = regiao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estado")
	public Set<Cidade> getCidades() {
		return this.cidades;
	}

	public void setCidades(Set<Cidade> cidades) {
		this.cidades = cidades;
	}

	@Override
	public String toString() {
		return getUf();
	}
	

}
