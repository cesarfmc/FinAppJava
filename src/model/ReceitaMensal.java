package model;
// Generated 18/10/2019 10:39:39 by Hibernate Tools 5.2.12.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ReceitaMensal generated by hbm2java
 */
@Entity
@Table(name = "ReceitaMensal", schema = "dbo", catalog = "FIN")
public class ReceitaMensal implements java.io.Serializable {

	private Integer idReceitaMensal;
	private CentroCusto centroCusto;
	private Cliente cliente;
	private FormaPagamento formaPagamento;
	private PlanoConta planoConta;
	private int diaVencimento;
	private BigDecimal valor;

	public ReceitaMensal() {
	}

	public ReceitaMensal(int diaVencimento, BigDecimal valor) {
		this.diaVencimento = diaVencimento;
		this.valor = valor;
	}

	public ReceitaMensal(CentroCusto centroCusto, Cliente cliente, FormaPagamento formaPagamento, PlanoConta planoConta,
			int diaVencimento, BigDecimal valor) {
		this.centroCusto = centroCusto;
		this.cliente = cliente;
		this.formaPagamento = formaPagamento;
		this.planoConta = planoConta;
		this.diaVencimento = diaVencimento;
		this.valor = valor;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idReceitaMensal", unique = true, nullable = false)
	public Integer getIdReceitaMensal() {
		return this.idReceitaMensal;
	}

	public void setIdReceitaMensal(Integer idReceitaMensal) {
		this.idReceitaMensal = idReceitaMensal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCentroCusto")
	public CentroCusto getCentroCusto() {
		return this.centroCusto;
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCliente")
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idFormaPagamento")
	public FormaPagamento getFormaPagamento() {
		return this.formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPlanoConta")
	public PlanoConta getPlanoConta() {
		return this.planoConta;
	}

	public void setPlanoConta(PlanoConta planoConta) {
		this.planoConta = planoConta;
	}

	@Column(name = "diaVencimento", nullable = false)
	public int getDiaVencimento() {
		return this.diaVencimento;
	}

	public void setDiaVencimento(int diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	@Column(name = "valor", nullable = false)
	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
