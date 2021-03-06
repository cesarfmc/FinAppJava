package model;
// Generated 15/05/2019 11:16:03 by Hibernate Tools 5.2.12.Final

import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Conta generated by hbm2java
 */
@Entity
@Table(name = "Conta", schema = "dbo", catalog = "Fin3l")
public class Conta implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idConta;
	private Banco banco;
	private TipoConta tipoConta;
	private String nome;
	private String agencia;
	private String numeroConta;
	private Date dataAbertura;
	private BigDecimal depositoInicial;
	private Date dataSaldoIni;
	private Date dataSaldoFin;
	private Integer numeroTransacao;
	private BigDecimal saldo;
	private Set<Transacao> transacaos = new HashSet<Transacao>(0);

	public Conta() {
	}

	public Conta(TipoConta tipoConta, String nome, String agencia, String numeroConta, Date dataAbertura,
			BigDecimal depositoInicial) {
		this.tipoConta = tipoConta;
		this.nome = nome;
		this.agencia = agencia;
		this.numeroConta = numeroConta;
		this.dataAbertura = dataAbertura;
		this.depositoInicial = depositoInicial;
	}

	public Conta(Banco banco, TipoConta tipoConta, String nome, String agencia, String numeroConta, Date dataAbertura,
			BigDecimal depositoInicial, Date dataSaldoIni, Date dataSaldoFin, Integer numeroTransacao, BigDecimal saldo,
			Set<Transacao> transacaos) {
		this.banco = banco;
		this.tipoConta = tipoConta;
		this.nome = nome;
		this.agencia = agencia;
		this.numeroConta = numeroConta;
		this.dataAbertura = dataAbertura;
		this.depositoInicial = depositoInicial;
		this.dataSaldoIni = dataSaldoIni;
		this.dataSaldoFin = dataSaldoFin;
		this.numeroTransacao = numeroTransacao;
		this.saldo = saldo;
		this.transacaos = transacaos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idConta", unique = true, nullable = false)
	public Integer getIdConta() {
		return this.idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idBanco")
	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTipoConta", nullable = false)
	public TipoConta getTipoConta() {
		return this.tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	@Column(name = "nome", nullable = false)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "agencia", nullable = false)
	public String getAgencia() {
		return this.agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	@Column(name = "numeroConta", nullable = false)
	public String getNumeroConta() {
		return this.numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dataAbertura", nullable = false, length = 10)
	public Date getDataAbertura() {
		return this.dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	@Column(name = "depositoInicial", nullable = false)
	public BigDecimal getDepositoInicial() {
		return this.depositoInicial;
	}

	public void setDepositoInicial(BigDecimal depositoInicial) {
		this.depositoInicial = depositoInicial;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dataSaldoIni", length = 10)
	public Date getDataSaldoIni() {
		return this.dataSaldoIni;
	}

	public void setDataSaldoIni(Date dataSaldoIni) {
		this.dataSaldoIni = dataSaldoIni;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dataSaldoFin", length = 10)
	public Date getDataSaldoFin() {
		return this.dataSaldoFin;
	}

	public void setDataSaldoFin(Date dataSaldoFin) {
		this.dataSaldoFin = dataSaldoFin;
	}

	@Column(name = "numeroTransacao")
	public Integer getNumeroTransacao() {
		return this.numeroTransacao;
	}

	public void setNumeroTransacao(Integer numeroTransacao) {
		this.numeroTransacao = numeroTransacao;
	}

	@Column(name = "saldo")
	public BigDecimal getSaldo() {
		return this.saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "conta")
	public Set<Transacao> getTransacaos() {
		return this.transacaos;
	}

	public void setTransacaos(Set<Transacao> transacaos) {
		this.transacaos = transacaos;
	}
	
	@Override
	public String toString() {
		return nome;
	}

}
