package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import model.CentroCusto;

public class CentroCustoDAO {
	private Session s;
	
	public CentroCustoDAO(Session session) {
		s = session;
	}

	public void addCentroCusto(CentroCusto centroCusto) {
		s.beginTransaction();
		s.save(centroCusto);
		s.getTransaction().commit();

	}

	public List<CentroCusto> listCentroCusto() {
		List<CentroCusto> list = new ArrayList<CentroCusto>();
		s.beginTransaction();

		list = s.createQuery("from CentroCusto",CentroCusto.class).list();
		s.getTransaction().commit();

		return list;
	}

	public void removeCentroCusto(Integer id) {
		s.beginTransaction();
		CentroCusto centroCusto = (CentroCusto) s.load(CentroCusto.class, id);
		s.delete(centroCusto);
		s.getTransaction().commit();
	}

	public void updateCentroCusto(CentroCusto centroCusto) {
		s.beginTransaction();
		s.update(centroCusto);
		s.getTransaction().commit();
	}

	public CentroCusto retornaCentroCusto(Integer id) {
		s.beginTransaction();
		CentroCusto centroCusto = (CentroCusto) s.get(CentroCusto.class, id);
		s.getTransaction().commit();

		return centroCusto;
	}
	
	public List<CentroCusto> listCentroCustoPai() {
		List<CentroCusto> list = new ArrayList<CentroCusto>();
		s.beginTransaction();

		list = s.createQuery("from CentroCusto where idCentroCustoPai is null",CentroCusto.class).list();
		s.getTransaction().commit();

		return list;
	}
	
	public List<CentroCusto> listCentroCustoFilhos(Integer id) {
		List<CentroCusto> list = new ArrayList<CentroCusto>();
		s.beginTransaction();

		list = s.createQuery("from CentroCusto where idCentroCustoPai = "+id,CentroCusto.class).list();
		s.getTransaction().commit();

		return list;
	}
}
