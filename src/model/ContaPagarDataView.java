package model;
// Generated 30/01/2020 13:44:51 by Hibernate Tools 5.2.12.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ContaPagarDataView generated by hbm2java
 */
@Entity
@Table(name = "ContaPagarDataView", schema = "dbo", catalog = "Fin3l")
public class ContaPagarDataView implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idContaPagar;
	private Date dataCompra;
	private String numero;
	private String documento;
	private String descricao;
	private BigDecimal valor;
	private Date dataVencimento;
	private Date dataPagamento;
	private String observacao;
	private BigDecimal valorPagamento;
	private String numeroBanco;
	private String numeroAgencia;
	private String numeroConta;
	private String numeroDocPagto;
	private String status;
	private Date dataInsercao;
	private Integer idFornecedor;
	private String fornecedorNome;
	private Integer idCentroCusto;
	private String centroCustoNome;
	private Integer idPlanoConta;
	private String planoContaNome;
	private Integer idFormaPagamento;
	private String codigo;

	public ContaPagarDataView() {
	}

	public ContaPagarDataView(int idContaPagar, Date dataCompra, BigDecimal valor, Date dataVencimento, String status,
			Date dataInsercao) {
		this.idContaPagar = idContaPagar;
		this.dataCompra = dataCompra;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.status = status;
		this.dataInsercao = dataInsercao;
	}

	public ContaPagarDataView(int idContaPagar, Date dataCompra, String numero, String documento, String descricao,
			BigDecimal valor, Date dataVencimento, Date dataPagamento, String observacao, BigDecimal valorPagamento,
			String numeroBanco, String numeroAgencia, String numeroConta, String numeroDocPagto, String status,
			Date dataInsercao, Integer idFornecedor, String fornecedorNome, Integer idCentroCusto,
			String centroCustoNome, Integer idPlanoConta, String planoContaNome, Integer idFormaPagamento,
			String codigo) {
		this.idContaPagar = idContaPagar;
		this.dataCompra = dataCompra;
		this.numero = numero;
		this.documento = documento;
		this.descricao = descricao;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.observacao = observacao;
		this.valorPagamento = valorPagamento;
		this.numeroBanco = numeroBanco;
		this.numeroAgencia = numeroAgencia;
		this.numeroConta = numeroConta;
		this.numeroDocPagto = numeroDocPagto;
		this.status = status;
		this.dataInsercao = dataInsercao;
		this.idFornecedor = idFornecedor;
		this.fornecedorNome = fornecedorNome;
		this.idCentroCusto = idCentroCusto;
		this.centroCustoNome = centroCustoNome;
		this.idPlanoConta = idPlanoConta;
		this.planoContaNome = planoContaNome;
		this.idFormaPagamento = idFormaPagamento;
		this.codigo = codigo;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idContaPagar", nullable = false)
	public int getIdContaPagar() {
		return this.idContaPagar;
	}

	public void setIdContaPagar(int idContaPagar) {
		this.idContaPagar = idContaPagar;
	}

	@Column(name = "dataCompra", nullable = false, length = 10)
	public Date getDataCompra() {
		return this.dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
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

	@Column(name = "dataVencimento", nullable = false, length = 10)
	public Date getDataVencimento() {
		return this.dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Column(name = "dataPagamento", length = 10)
	public Date getDataPagamento() {
		return this.dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Column(name = "observacao")
	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Column(name = "valorPagamento")
	public BigDecimal getValorPagamento() {
		return this.valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
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

	@Column(name = "numeroDocPagto")
	public String getNumeroDocPagto() {
		return this.numeroDocPagto;
	}

	public void setNumeroDocPagto(String numeroDocPagto) {
		this.numeroDocPagto = numeroDocPagto;
	}

	@Column(name = "status", nullable = false)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "dataInsercao", nullable = false, length = 23)
	public Date getDataInsercao() {
		return this.dataInsercao;
	}

	public void setDataInsercao(Date dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	@Column(name = "idFornecedor")
	public Integer getIdFornecedor() {
		return this.idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	@Column(name = "fornecedorNome")
	public String getFornecedorNome() {
		return this.fornecedorNome;
	}

	public void setFornecedorNome(String fornecedorNome) {
		this.fornecedorNome = fornecedorNome;
	}

	@Column(name = "idCentroCusto")
	public Integer getIdCentroCusto() {
		return this.idCentroCusto;
	}

	public void setIdCentroCusto(Integer idCentroCusto) {
		this.idCentroCusto = idCentroCusto;
	}

	@Column(name = "centroCustoNome")
	public String getCentroCustoNome() {
		return this.centroCustoNome;
	}

	public void setCentroCustoNome(String centroCustoNome) {
		this.centroCustoNome = centroCustoNome;
	}

	@Column(name = "idPlanoConta")
	public Integer getIdPlanoConta() {
		return this.idPlanoConta;
	}

	public void setIdPlanoConta(Integer idPlanoConta) {
		this.idPlanoConta = idPlanoConta;
	}

	@Column(name = "planoContaNome")
	public String getPlanoContaNome() {
		return this.planoContaNome;
	}

	public void setPlanoContaNome(String planoContaNome) {
		this.planoContaNome = planoContaNome;
	}

	@Column(name = "idFormaPagamento")
	public Integer getIdFormaPagamento() {
		return this.idFormaPagamento;
	}

	public void setIdFormaPagamento(Integer idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}

	@Column(name = "codigo")
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String toString() {
		return "ContaPagarDataViewId [dataCompra=" + dataCompra + ", numero=" + numero + ", valor=" + valor
				+ ", dataVencimento=" + dataVencimento + ", dataPagamento=" + dataPagamento + ", valorPagamento="
				+ valorPagamento + ", fornecedorNome=" + fornecedorNome + ", centroCustoNome=" + centroCustoNome
				+ ", planoContaNome=" + planoContaNome + ", codigo=" + codigo + "]";
	}
	
}
