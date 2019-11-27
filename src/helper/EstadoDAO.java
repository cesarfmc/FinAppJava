package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Estado;

public class EstadoDAO {
	public void addEstado(Estado estado) {
		Session s = HibernateUtil2.getSessionFactory().openSession();

		s.beginTransaction();
		s.save(estado);
		s.getTransaction().commit();
	}

	public List<Estado> listEstado() {
		List<Estado> list = new ArrayList<Estado>();
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();
		Query<Estado> q = s.createQuery("FROM Estado",Estado.class);
		list = q.list();
		
		s.getTransaction().commit();

		return list;
	}

	public void removeEstado(Integer id) {
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();
		Estado estado = (Estado) s.load(Estado.class, id);
		s.delete(estado);
		s.getTransaction().commit();
	}

	public void updateEstado(Estado estado) {
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(estado);
		s.getTransaction().commit();
	}

	public Estado retornaEstado(Integer id) {
		
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();

		Estado estado = (Estado) s.get(Estado.class, id);
		s.getTransaction().commit();
	
		return estado;
	}
}
