package helper;

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

	public ContaPagarDAO(Session sessao) {
		this.s = sessao;
	}

	public void addContaPagar(ContaPagar contaPagar) {
		s.beginTransaction();
		s.save(contaPagar);
		s.getTransaction().commit();

	}

	public List<ContaPagar> listContaPagar() {
		List<ContaPagar> list = new ArrayList<ContaPagar>();
		s.beginTransaction();

		list = s.createQuery("from ContaPagar", ContaPagar.class).list();
		s.getTransaction().commit();

		return list;
	}
	
	public List<ContaPagar> listContaPagarTudo(Date dataInicio, Date dataFim,Fornecedor fornecedor,CentroCusto centroCusto,PlanoConta planoConta,FormaPagamento formaPagamento) {
		List<ContaPagar> list = new ArrayList<ContaPagar>();
		s.beginTransaction();
		String sql = "from ContaPagar where";
		StringBuilder hql = new StringBuilder();
		hql.append(sql);
		if(dataInicio != null) {
			hql.append(" dataVencimento between :dataInicio and :dataFinal");
			if(fornecedor != null || centroCusto  != null || planoConta != null || formaPagamento != null) {
				hql.append(" and ");
			}
		}
		
		if(fornecedor != null) {
				hql.append(" idFornecedor =  :idFornecedor");
				if( centroCusto  != null || planoConta != null || formaPagamento != null) {
					hql.append(" and ");
				}
		}
		
		if(centroCusto  != null) {
			hql.append(" idCentroCusto =  :idCentroCusto");
			if( planoConta != null || formaPagamento != null) {
				hql.append(" and ");
			}
		}
		
		if(planoConta != null) {
			hql.append(" idPlanoConta =  :idPlanoConta");
			if( formaPagamento != null) {
				hql.append(" and ");
			}
		}
		
		if(formaPagamento != null) {
			System.out.println(formaPagamento.getIdFormaPagamento());
			hql.append(" idFormaPagamento =  :idFormaPagamento");
		}
				
		Query<ContaPagar> query = s.createQuery(hql.toString(),ContaPagar.class);
		if(dataInicio != null) {
			query.setParameter("dataInicio", dataInicio);
			query.setParameter("dataFinal", dataFim);
		}
		
		if(fornecedor != null) {
			query.setParameter("idFornecedor", fornecedor.getIdFornecedor());
		}
		
		if(centroCusto  != null) {
			query.setParameter("idCentroCusto", centroCusto.getIdCentroCusto());
		}
		
		if(planoConta != null) {
			query.setParameter("idPlanoConta", planoConta.getIdPlanoConta());
		}
		
		if(formaPagamento != null) {
			query.setParameter("idFormaPagamento", formaPagamento.getIdFormaPagamento());
		}
		
		list = query.list();
		s.getTransaction().commit();

		return list;
	}
	
	public void removeContaPagar(Integer id) {
		s.beginTransaction();
		ContaPagar contaPagar = (ContaPagar) s.load(ContaPagar.class, id);
		s.delete(contaPagar);
		s.getTransaction().commit();
	}

	public void updateContaPagar(ContaPagar contaPagar) {
		s.beginTransaction();
		s.update(contaPagar);
		s.getTransaction().commit();
	}

	public ContaPagar retornaFormaPagamento(Integer id) {
		s.beginTransaction();
		ContaPagar contaPagar = (ContaPagar) s.get(ContaPagar.class, id);
		s.getTransaction().commit();

		return contaPagar;
	}
}
