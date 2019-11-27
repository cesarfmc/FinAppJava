package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import model.PlanoConta;

public class PlanoContaDAO {
private Session s;
	
	public PlanoContaDAO(Session session) {
		s = session;
	}

	public void addPlanoConta(PlanoConta planoConta) {
		s.beginTransaction();
		s.save(planoConta);
		s.getTransaction().commit();

	}

	public List<PlanoConta> listPlanoConta() {
		List<PlanoConta> list = new ArrayList<PlanoConta>();
		s.beginTransaction();

		list = s.createQuery("from PlanoConta",PlanoConta.class).list();
		s.getTransaction().commit();

		return list;
	}

	public void removePlanoConta(Integer id) {
		s.beginTransaction();
		PlanoConta planoConta = (PlanoConta) s.load(PlanoConta.class, id);
		s.delete(planoConta);
		s.getTransaction().commit();
	}

	public void updatePlanoConta(PlanoConta planoConta) {
		s.beginTransaction();
		s.update(planoConta);
		s.getTransaction().commit();
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
	
	public List<PlanoConta> listPlanoContaFilhos(Integer id) {
		List<PlanoConta> list = new ArrayList<PlanoConta>();
		s.beginTransaction();

		list = s.createQuery("from PlanoConta where idPlanoContaPai = "+id,PlanoConta.class).list();
		s.getTransaction().commit();

		return list;
	}
}
