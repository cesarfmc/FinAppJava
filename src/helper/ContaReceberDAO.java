package helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.CentroCusto;
import model.Cliente;
import model.ContaReceber;
import model.FormaPagamento;
import model.PlanoConta;

public class ContaReceberDAO {
	private Session s;

	public ContaReceberDAO(Session sessao) {
		this.s = sessao;
	}

	public void addContaReceber(ContaReceber contaReceber) {
		s.beginTransaction();
		s.save(contaReceber);
		s.getTransaction().commit();

	}

	public List<ContaReceber> listContaReceber() {
		List<ContaReceber> list = new ArrayList<ContaReceber>();
		s.beginTransaction();

		list = s.createQuery("from ContaReceber", ContaReceber.class).list();
		s.getTransaction().commit();

		return list;
	}

	public List<ContaReceber> listContaReceberStatus(String status) {
		List<ContaReceber> list = new ArrayList<ContaReceber>();
		s.beginTransaction();

		list = s.createQuery("from ContaReceber where status = :status", ContaReceber.class)
				.setParameter("status", status).list();
		s.getTransaction().commit();

		return list;
	}

	public List<ContaReceber> listContaReceberTudo(Date dataInicio, Date dataFim, Cliente cliente,
			CentroCusto centroCusto, PlanoConta planoConta, FormaPagamento formaPagamento) {
		List<ContaReceber> list = new ArrayList<ContaReceber>();
		s.beginTransaction();
		String sql = "from ContaReceber where";
		StringBuilder hql = new StringBuilder();
		hql.append(sql);
		if (dataInicio != null) {
			hql.append(" dataVencimento between :dataInicio and :dataFinal");
			if (cliente != null || centroCusto != null || planoConta != null || formaPagamento != null) {
				hql.append(" and ");
			}
		}

		if (cliente != null) {
			hql.append(" idCliente =  :idCliente");
			if (centroCusto != null || planoConta != null || formaPagamento != null) {
				hql.append(" and ");
			}
		}

		if (centroCusto != null) {
			hql.append(" idCentroCusto =  :idCentroCusto");
			if (planoConta != null || formaPagamento != null) {
				hql.append(" and ");
			}
		}

		if (planoConta != null) {
			hql.append(" idPlanoConta =  :idPlanoConta");
			if (formaPagamento != null) {
				hql.append(" and ");
			}
		}

		if (formaPagamento != null) {
			System.out.println(formaPagamento.getIdFormaPagamento());
			hql.append(" idFormaPagamento =  :idFormaPagamento");
		}

		Query<ContaReceber> query = s.createQuery(hql.toString(), ContaReceber.class);
		if (dataInicio != null) {
			query.setParameter("dataInicio", dataInicio);
			query.setParameter("dataFinal", dataFim);
		}

		if (cliente != null) {
			query.setParameter("idCliente", cliente.getIdCliente());
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

		return list;
	}

	public void removeContaReceber(Integer id) {
		s.beginTransaction();
		ContaReceber contaReceber = (ContaReceber) s.load(ContaReceber.class, id);
		s.delete(contaReceber);
		s.getTransaction().commit();
	}

	public void updateContaReceber(ContaReceber contaReceber) {
		s.beginTransaction();
		s.update(contaReceber);
		s.getTransaction().commit();
	}

	public ContaReceber retornaFormaPagamento(Integer id) {
		s.beginTransaction();
		ContaReceber contaReceber = (ContaReceber) s.get(ContaReceber.class, id);
		s.getTransaction().commit();

		return contaReceber;
	}
}
