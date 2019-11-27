package model;
// Generated 18/10/2019 10:39:39 by Hibernate Tools 5.2.12.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ContaReceber generated by hbm2java
 */
@Entity
@Table(name = "ContaReceber", schema = "dbo", catalog = "FIN")
public class ContaReceber implements java.io.Serializable {

	private Integer idContaReceber;
	private CentroCusto centroCusto;
	private Cliente cliente;
	private FormaPagamento formaPagamento;
	private PlanoConta planoConta;
	private Transacao transacao;
	private Date dataVenda;
	private String numero;
	private String documento;
	private String descricao;
	private BigDecimal valor;
	private Date dataVencimento;
	private Date dataRecebimento;
	private String observacao;
	private BigDecimal valorRecebimento;
	private String numeroBanco;
	private String numeroAgencia;
	private String numeroConta;
	private String numeroDocRecto;
	private String status;
	private Date dataInsercao;
	private String tag;

	public ContaReceber() {
	}

	public ContaReceber(Date dataVenda, BigDecimal valor, Date dataVencimento, String status, Date dataInsercao) {
		this.dataVenda = dataVenda;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.status = status;
		this.dataInsercao = dataInsercao;
	}

	public ContaReceber(CentroCusto centroCusto, Cliente cliente, FormaPagamento formaPagamento, PlanoConta planoConta,
			Transacao transacao, Date dataVenda, String numero, String documento, String descricao, BigDecimal valor,
			Date dataVencimento, Date dataRecebimento, String observacao, BigDecimal valorRecebimento,
			String numeroBanco, String numeroAgencia, String numeroConta, String numeroDocRecto, String status,
			Date dataInsercao, String tag) {
		this.centroCusto = centroCusto;
		this.cliente = cliente;
		this.formaPagamento = formaPagamento;
		this.planoConta = planoConta;
		this.transacao = transacao;
		this.dataVenda = dataVenda;
		this.numero = numero;
		this.documento = documento;
		this.descricao = descricao;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.dataRecebimento = dataRecebimento;
		this.observacao = observacao;
		this.valorRecebimento = valorRecebimento;
		this.numeroBanco = numeroBanco;
		this.numeroAgencia = numeroAgencia;
		this.numeroConta = numeroConta;
		this.numeroDocRecto = numeroDocRecto;
		this.status = status;
		this.dataInsercao = dataInsercao;
		this.tag = tag;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idContaReceber", unique = true, nullable = false)
	public Integer getIdContaReceber() {
		return this.idContaReceber;
	}

	public void setIdContaReceber(Integer idContaReceber) {
		this.idContaReceber = idContaReceber;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTransacao")
	public Transacao getTransacao() {
		return this.transacao;
	}

	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dataVenda", nullable = false, length = 10)
	public Date getDataVenda() {
		return this.dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	@Column(name = "numero")
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "documento")
	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	@Column(name = "descricao")
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "valor", nullable = false)
	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dataVencimento", nullable = false, length = 10)
	public Date getDataVencimento() {
		return this.dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dataRecebimento", length = 10)
	public Date getDataRecebimento() {
		return this.dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	@Column(name = "observacao")
	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Column(name = "valorRecebimento")
	public BigDecimal getValorRecebimento() {
		return this.valorRecebimento;
	}

	public void setValorRecebimento(BigDecimal valorRecebimento) {
		this.valorRecebimento = valorRecebimento;
	}

	@Column(name = "numeroBanco")
	public String getNumeroBanco() {
		return this.numeroBanco;
	}

	public void setNumeroBanco(String numeroBanco) {
		this.numeroBanco = numeroBanco;
	}

	@Column(name = "numeroAgencia")
	public String getNumeroAgencia() {
		return this.numeroAgencia;
	}

	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	@Column(name = "numeroConta")
	public String getNumeroConta() {
		return this.numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	@Column(name = "numeroDocRecto")
	public String getNumeroDocRecto() {
		return this.numeroDocRecto;
	}

	public void setNumeroDocRecto(String numeroDocRecto) {
		this.numeroDocRecto = numeroDocRecto;
	}

	@Column(name = "status", nullable = false)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataInsercao", nullable = false, length = 23)
	public Date getDataInsercao() {
		return this.dataInsercao;
	}

	public void setDataInsercao(Date dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	@Column(name = "tag")
	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
