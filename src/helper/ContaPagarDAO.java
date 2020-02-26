package helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.CentroCusto;
import model.ContaPagar;
import model.FormaPagamento;
import model.Fornecedor;
import model.PlanoConta;

public class ContaPagarDAO {
	private Session s;
	private long tempoInicial;

	public ContaPagarDAO(Session sessao) {
		this.s = sessao;
	}

	public void addContaPagar(ContaPagar contaPagar) {
		tempoInicial = System.currentTimeMillis();
		
		s.beginTransaction();
		s.save(contaPagar);
		s.getTransaction().commit();

		GeradorCsv.tempoFinal(tempoInicial, "Adicionar ContaPagar");
	}

	public List<ContaPagar> listContaPagar() {
		tempoInicial = System.currentTimeMillis();
		
		List<ContaPagar> list = new ArrayList<ContaPagar>();
		s.beginTransaction();

		list = s.createQuery("from ContaPagar", ContaPagar.class).list();
		s.getTransaction().commit();
		
		GeradorCsv.tempoFinal(tempoInicial, "Listar ContaPagar");

		return list;
	}

	public List<ContaPagar> listContaPagarStatus(String status) {
		tempoInicial = System.currentTimeMillis();
		
		List<ContaPagar> list = new ArrayList<ContaPagar>();
		s.beginTransaction();

		list = s.createQuery("from ContaPagar where status = :status", ContaPagar.class).setParameter("status", status)
				.list();
		s.getTransaction().commit();

		GeradorCsv.tempoFinal(tempoInicial, "Listar ContaPagar");
		
		return list;
	}

	public void removeContaPagar(Integer id) {
		tempoInicial = System.currentTimeMillis();
		
		s.beginTransaction();
		ContaPagar contaPagar = (ContaPagar) s.load(ContaPagar.class, id);
		s.delete(contaPagar);
		s.getTransaction().commit();
		
		GeradorCsv.tempoFinal(tempoInicial, "Remover ContaPagar");
	}

	public void updateContaPagar(ContaPagar contaPagar) {
		tempoInicial = System.currentTimeMillis();
		
		s.beginTransaction();
		s.update(contaPagar);
		s.getTransaction().commit();
	}

	public ContaPagar retornaPagar(Integer id) {
		s.beginTransaction();
		ContaPagar contaPagar = (ContaPagar) s.get(ContaPagar.class, id);
		s.getTransaction().commit();

		return contaPagar;
	}

	public List<ContaPagar> listContaPagarTudo(String numero, BigDecimal valorInicial, BigDecimal valorFinal, Date dataCompraInicio, Date dataCompraFim, Date dataVencimentoInicio,
			Date dataVencimentoFim, Date dataPagamentoInicio, Date dataPagamentoFim, Fornecedor fornecedor,
			CentroCusto centroCusto, PlanoConta planoConta, FormaPagamento formaPagamento) {
		tempoInicial = System.currentTimeMillis();
		
		List<ContaPagar> list = new ArrayList<ContaPagar>();
		s.beginTransaction();

		String sql = "from ContaPagar where";
		StringBuilder hql = new StringBuilder();
		hql.append(sql);

		if (!numero.isEmpty()) {
			hql.append(" numero LIKE :numero");
			if(valorInicial != null || dataCompraInicio != null || dataVencimentoInicio != null || fornecedor != null || centroCusto != null || planoConta != null || formaPagamento != null) {
				hql.append(" and");
			}
		}
		
		if (valorInicial != null) {
			hql.append(" valor between :valorInicial and :valorFinal");
			if( dataCompraInicio != null || dataPagamentoInicio != null || dataVencimentoInicio != null || fornecedor != null || centroCusto != null || planoConta != null || formaPagamento != null) {
				hql.append(" and");
			}
		}
		if (dataCompraInicio != null) {
			hql.append(" dataCompra between :dataCompraInicio and :dataCompraFinal");
			if(dataPagamentoInicio != null || dataVencimentoInicio != null || fornecedor != null || centroCusto != null || planoConta != null || formaPagamento != null) {
				hql.append(" and");
			}
		}

		if (dataVencimentoInicio != null) {
			hql.append(" dataVencimento between :dataVencimentoInicio and :dataVencimentoFinal");
			if(dataPagamentoInicio != null || fornecedor != null || centroCusto != null || planoConta != null || formaPagamento != null) {
				hql.append(" and");
			}
		}

		if (dataPagamentoInicio != null) {
			hql.append(" dataPagamento between :dataPagamentoInicio and :dataPagamentoFinal");
			if(fornecedor != null || centroCusto != null || planoConta != null || formaPagamento != null) {
				hql.append(" and");
			}
		}

		if (fornecedor != null) {
			hql.append(" idFornecedor =  :idFornecedor");
			if(centroCusto != null || planoConta != null || formaPagamento != null) {
				hql.append(" and");
			}
		}

		if (centroCusto != null) {
			hql.append(" idCentroCusto =  :idCentroCusto");
			if( planoConta != null || formaPagamento != null) {
				hql.append(" and");
			}
		}

		if (planoConta != null) {
			hql.append(" idPlanoConta =  :idPlanoConta");
			if( formaPagamento != null) {
				hql.append(" and");
			}
		}
		
		if (formaPagamento != null) {
			hql.append(" idFormaPagamento =  :idFormaPagamento ");
		}
		Query<ContaPagar> query = s.createQuery(hql.toString(), ContaPagar.class);

		if (!numero.isEmpty()) {
			query.setParameter("numero", "%"+numero+"%");
		}
		
		if (valorInicial != null) {
			query.setParameter("valorInicial", valorInicial);
			query.setParameter("valorFinal", valorFinal);
		}
		
		if (dataCompraInicio != null) {
			query.setParameter("dataCompraInicio", dataCompraInicio);
			query.setParameter("dataCompraFinal", dataCompraFim);
		}

		if (dataVencimentoInicio != null) {
			query.setParameter("dataVencimentoInicio", dataVencimentoInicio);
			query.setParameter("dataVencimentoFinal", dataVencimentoFim);
		}

		if (dataPagamentoInicio != null) {
			query.setParameter("dataPagamentoInicio", dataPagamentoInicio);
			query.setParameter("dataPagamentoFinal", dataPagamentoFim);
		}

		if (fornecedor != null) {
			query.setParameter("idFornecedor", fornecedor.getIdFornecedor());
		}

		if (centroCusto != null) {
			query.setParameter("idCentroCusto", centroCusto.getIdCentroCusto());
		}

		if (planoConta != null) {
			query.setParameter("idPlanoConta", planoConta.getIdPlanoConta());
		}

		if (formaPagamento != null) {
			query.setParameter("idFormaPagamento", formaPagamento.getIdFormaPagamento());
		}
		
		list = query.list();

		s.getTransaction().commit();
		
		GeradorCsv.tempoFinal(tempoInicial, "Pesquisar ContaPagar");

		return list;
	}
}
