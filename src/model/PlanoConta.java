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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * PlanoConta generated by hbm2java
 */
@Entity
@Table(name = "PlanoConta", schema = "dbo", catalog = "Fin3l", uniqueConstraints = @UniqueConstraint(columnNames = "nome"))
public class PlanoConta implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idPlanoConta;
	private PlanoConta planoConta;
	private String nome;
	private String descricao;
	private Set<PlanoConta> planoContas = new HashSet<PlanoConta>(0);
	private Set<ContaReceber> contaRecebers = new HashSet<ContaReceber>(0);
	private Set<ReceitaMensal> receitaMensals = new HashSet<ReceitaMensal>(0);
	private Set<DespesaMensal> despesaMensals = new HashSet<DespesaMensal>(0);
	private Set<ContaPagar> contaPagars = new HashSet<ContaPagar>(0);

	public PlanoConta() {
	}

	public PlanoConta(String nome) {
		this.nome = nome;
	}

	public PlanoConta(PlanoConta planoConta, String nome, String descricao, Set<PlanoConta> planoContas,
			Set<ContaReceber> contaRecebers, Set<ReceitaMensal> receitaMensals, Set<DespesaMensal> despesaMensals,
			Set<ContaPagar> contaPagars) {
		this.planoConta = planoConta;
		this.nome = nome;
		this.descricao = descricao;
		this.planoContas = planoContas;
		this.contaRecebers = contaRecebers;
		this.receitaMensals = receitaMensals;
		this.despesaMensals = despesaMensals;
		this.contaPagars = contaPagars;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idPlanoConta", unique = true, nullable = false)
	public Integer getIdPlanoConta() {
		return this.idPlanoConta;
	}

	public void setIdPlanoConta(Integer idPlanoConta) {
		this.idPlanoConta = idPlanoConta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPlanoContaPai")
	public PlanoConta getPlanoConta() {
		return this.planoConta;
	}

	public void setPlanoConta(PlanoConta planoConta) {
		this.planoConta = planoConta;
	}

	@Column(name = "nome", unique = true, nullable = false)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "descricao")
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "planoConta")
	public Set<PlanoConta> getPlanoContas() {
		return this.planoContas;
	}

	public void setPlanoContas(Set<PlanoConta> planoContas) {
		this.planoContas = planoContas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "planoConta")
	public Set<ContaReceber> getContaRecebers() {
		return this.contaRecebers;
	}

	public void setContaRecebers(Set<ContaReceber> contaRecebers) {
		this.contaRecebers = contaRecebers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "planoConta")
	public Set<ReceitaMensal> getReceitaMensals() {
		return this.receitaMensals;
	}

	public void setReceitaMensals(Set<ReceitaMensal> receitaMensals) {
		this.receitaMensals = receitaMensals;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "planoConta")
	public Set<DespesaMensal> getDespesaMensals() {
		return this.despesaMensals;
	}

	public void setDespesaMensals(Set<DespesaMensal> despesaMensals) {
		this.despesaMensals = despesaMensals;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "planoConta")
	public Set<ContaPagar> getContaPagars() {
		return this.contaPagars;
	}

	public void setContaPagars(Set<ContaPagar> contaPagars) {
		this.contaPagars = contaPagars;
	}
	
	@Override
	public String toString() {
		return nome;
	}

}
