package model;
// Generated 15/05/2019 11:16:03 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Permissao generated by hbm2java
 */
@Entity
@Table(name = "Permissao", schema = "dbo", catalog = "Fin3l")
public class Permissao implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idPermissao;
	private String grupo;
	private String nome;
	private String codigo;
	private Set<Perfil> perfils = new HashSet<Perfil>(0);

	public Permissao() {
	}

	public Permissao(String grupo, String nome, String codigo) {
		this.grupo = grupo;
		this.nome = nome;
		this.codigo = codigo;
	}

	public Permissao(String grupo, String nome, String codigo, Set<Perfil> perfils) {
		this.grupo = grupo;
		this.nome = nome;
		this.codigo = codigo;
		this.perfils = perfils;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idPermissao", unique = true, nullable = false)
	public Integer getIdPermissao() {
		return this.idPermissao;
	}

	public void setIdPermissao(Integer idPermissao) {
		this.idPermissao = idPermissao;
	}

	@Column(name = "grupo", nullable = false)
	public String getGrupo() {
		return this.grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	@Column(name = "nome", nullable = false)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "codigo", nullable = false)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissaos")
	public Set<Perfil> getPerfils() {
		return this.perfils;
	}

	public void setPerfils(Set<Perfil> perfils) {
		this.perfils = perfils;
	}
	
	@Override
	public String toString() {
		return nome;
	}

}
