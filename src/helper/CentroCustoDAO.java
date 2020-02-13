package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

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
	
	public List<CentroCusto> listCentroCustoFilhos(Integer idCentroCustoPai) {
		List<CentroCusto> list = new ArrayList<CentroCusto>();
		s.beginTransaction();
		
		String sql = "from CentroCusto where ";
		StringBuilder hql = new StringBuilder();
		hql.append(sql);

		
		  if (idCentroCustoPai != null) {
			  hql.append(" idCentroCustoPai = :idCentroCustoPai "); 
		  }
		    
		  if (idCentroCustoPai != null) { 
			  hql.append(" ORDER BY nome "); 
		  }
		  Query<CentroCusto> query = s.createQuery(hql.toString(),CentroCusto.class);
		  
		  if (idCentroCustoPai != null) { 
			  query.setParameter("idCentroCustoPai",idCentroCustoPai); 
		  }
		 
		 list = query.list();
		 
		s.getTransaction().commit();

		return list;
	}
}
