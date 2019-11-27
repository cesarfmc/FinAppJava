package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import model.Cidade;
import model.Estado;

public class CidadeDAO {
	public void addCidade(Cidade cidade) {
		Session s = HibernateUtil2.getSessionFactory().openSession();

		s.beginTransaction();
		s.save(cidade);
		s.getTransaction().commit();
		
		s.close();

	}

	public List<Cidade> listCidade() {
		List<Cidade> list = new ArrayList<Cidade>();
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();

		list = s.createQuery("from Cidade",Cidade.class).list();
		s.getTransaction().commit();

		return list;
	}

	public void removeCidade(Integer id) {
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();
		Cidade c = (Cidade) s.load(Cidade.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}

	public void updateCidade(Cidade cidade) {
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(cidade);
		s.getTransaction().commit();
	}

	public Cidade retornaCidade(Integer id) {
		
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();

		Cidade c = (Cidade) s.get(Cidade.class, id);
		Hibernate.initialize(c.getEstado());
		s.getTransaction().commit();
		s.close();		
		return c;
	}
	
public List<Cidade> retornaCidadeEstado(Estado estado) {
		
	List<Cidade> list = new ArrayList<Cidade>();
	Session s = HibernateUtil2.getSessionFactory().openSession();
	s.beginTransaction();

	list = s.createQuery("from Cidade",Cidade.class).list();
	s.getTransaction().commit();

	return list;
	}
}
