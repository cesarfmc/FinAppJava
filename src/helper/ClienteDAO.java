package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import model.Cliente;
import helper.HibernateUtil2;

public class ClienteDAO {
	public void addCliente(Cliente cliente) {
		Session s = HibernateUtil2.getSessionFactory().openSession();

		s.beginTransaction();
		s.save(cliente);
		s.getTransaction().commit();
		
		s.close();

	}

	public List<Cliente> listCliente() {
		List<Cliente> list = new ArrayList<Cliente>();
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();

		list = s.createQuery("from Cliente",Cliente.class).list();
		s.getTransaction().commit();

		return list;
	}

	public void removeCliente(Integer id) {
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();
		Cliente c = (Cliente) s.load(Cliente.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}

	public void updateCliente(Cliente cliente) {
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(cliente);
		s.getTransaction().commit();
		System.out.println("aaaaaaaaaaaaaaa");
	}

	public Cliente retornaCliente(Integer id) {
		
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();
		Cliente c = (Cliente) s.get(Cliente.class, id);
		Hibernate.initialize(c.getCidade().getEstado());
		s.getTransaction().commit();
		s.close();		
		return c;
	}
}
