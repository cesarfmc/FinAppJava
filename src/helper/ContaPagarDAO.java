package helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

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
	

	public List<ContaPagar> listContaPagarDataVencimento(Date dataInicio, Date dataFim) {
		List<ContaPagar> list = new ArrayList<ContaPagar>();
		s.beginTransaction();
		list = s.createQuery("from ContaPagar where dataVencimento between :dataInicio and :dataFinal ", ContaPagar.class)
				.setParameter("dataInicio", dataInicio)
				.setParameter("dataFinal", dataFim)
				.list();
		s.getTransaction().commit();

		return list;
	}

	public List<ContaPagar> listContaPagarFornecedor(Fornecedor fornecedor) {
		List<ContaPagar> list = new ArrayList<ContaPagar>();
		s.beginTransaction();
		list = s.createQuery("from ContaPagar where idFornecedor =  :idFornecedor", ContaPagar.class)
				.setParameter("idFornecedor", fornecedor.getIdFornecedor())
				.list();
		s.getTransaction().commit();

		return list;
	}

	
	public List<ContaPagar> listContaPagarCentroCusto(CentroCusto centroCusto) {
		List<ContaPagar> list = new ArrayList<ContaPagar>();
		s.beginTransaction();
		list = s.createQuery("from ContaPagar where idCentroCusto =  :idCentroCusto", ContaPagar.class)
				.setParameter("idCentroCusto", centroCusto.getIdCentroCusto())
				.list();
		s.getTransaction().commit();

		return list;
	}

	public List<ContaPagar> listContaPagarPlanoConta(PlanoConta planoConta) {
		List<ContaPagar> list = new ArrayList<ContaPagar>();
		s.beginTransaction();
		list = s.createQuery("from ContaPagar where idPlanoConta =  :idPlanoConta", ContaPagar.class)
				.setParameter("idPlanoConta", planoConta.getIdPlanoConta())
				.list();
		s.getTransaction().commit();

		return list;
	}

	
	public List<ContaPagar> listContaPagarFormaPagamento(FormaPagamento formaPagamento) {
		List<ContaPagar> list = new ArrayList<ContaPagar>();
		s.beginTransaction();
		list = s.createQuery("from ContaPagar where idFormaPagamento =  :idFormaPagamento", ContaPagar.class)
				.setParameter("idFormaPagamento", formaPagamento.getIdFormaPagamento())
				.list();
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
