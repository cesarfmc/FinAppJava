package model;
// Generated 18/10/2019 10:39:39 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Categoria generated by hbm2java
 */
@Entity
@Table(name = "Categoria", schema = "dbo", catalog = "FIN")
public class Categoria implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idCategoria;
	private Categoria categoria;
	private String nome;
	private String descricao;
	private Set<Produto> produtos = new HashSet<Produto>(0);
	private Set<Categoria> categorias = new HashSet<Categoria>(0);

	public Categoria() {
	}

	public Categoria(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public Categoria(Categoria categoria, String nome, String descricao, Set<Produto> produtos,
			Set<Categoria> categorias) {
		this.categoria = categoria;
		this.nome = nome;
		this.descricao = descricao;
		this.produtos = produtos;
		this.categorias = categorias;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idCategoria", unique = true, nullable = false)
	public Integer getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCategoriaPai")
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Column(name = "nome", nullable = false)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "descricao", nullable = false)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
	public Set<Produto> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
	public Set<Categoria> getCategorias() {
		return this.categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	

}
