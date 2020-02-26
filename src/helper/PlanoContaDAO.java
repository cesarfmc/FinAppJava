package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.PlanoConta;

public class PlanoContaDAO {
	private Session s;
	private long tempoInicial;
	
	public PlanoContaDAO(Session session) {
		s = session;
	}

	public void addPlanoConta(PlanoConta planoConta) {
		tempoInicial = System.currentTimeMillis();
		
		s.beginTransaction();
		s.save(planoConta);
		s.getTransaction().commit();

		GeradorCsv.tempoFinal(tempoInicial, "Adicionar PlanoConta");
	}

	public List<PlanoConta> listPlanoConta() {
		List<PlanoConta> list = new ArrayList<PlanoConta>();
		s.beginTransaction();

		list = s.createQuery("from PlanoConta",PlanoConta.class).list();
		s.getTransaction().commit();

		return list;
	}

	public void removePlanoConta(Integer id) {
		tempoInicial = System.currentTimeMillis();
		
		s.beginTransaction();
		PlanoConta planoConta = (PlanoConta) s.load(PlanoConta.class, id);
		s.delete(planoConta);
		s.getTransaction().commit();
		
		GeradorCsv.tempoFinal(tempoInicial, "Remover PlanoConta");
	}

	public void updatePlanoConta(PlanoConta planoConta) {
		tempoInicial = System.currentTimeMillis();
		
		s.beginTransaction();
		s.update(planoConta);
		s.getTransaction().commit();
		
		GeradorCsv.tempoFinal(tempoInicial, "Alterar PlanoConta");
	}

	public PlanoConta retornaPlanoConta(Integer id) {
		s.beginTransaction();
		PlanoConta planoConta = (PlanoConta) s.get(PlanoConta.class, id);
		s.getTransaction().commit();

		return planoConta;
	}
	
	public List<PlanoConta> listPlanoContaPai() {
		List<PlanoConta> list = new ArrayList<PlanoConta>();
		s.beginTransaction();

		list = s.createQuery("from PlanoConta where idPlanoContaPai is null",PlanoConta.class).list();
		s.getTransaction().commit();

		return list;
	}
	
	public List<PlanoConta> listPlanoContaFilhos(Integer idPlanoContaPai) {
		List<PlanoConta> list = new ArrayList<PlanoConta>();
		s.beginTransaction();
		
		String sql = "from PlanoConta where ";
		StringBuilder hql = new StringBuilder();
		hql.append(sql);

		
		  if (idPlanoContaPai != null) {
			  hql.append(" idPlanoContaPai = :idPlanoContaPai "); 
		  }
		    
		  if (idPlanoContaPai != null) { 
			  hql.append(" ORDER BY nome "); 
		  }
		  Query<PlanoConta> query = s.createQuery(hql.toString(),PlanoConta.class);
		  
		  if (idPlanoContaPai != null) { 
			  query.setParameter("idPlanoContaPai",idPlanoContaPai); 
		  }
		 
		 list = query.list();
		 
		s.getTransaction().commit();
		
		return list;

	}
}
