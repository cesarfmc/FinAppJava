package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import model.Cliente;

public class ClienteDAO {
	private Session s;
	
	public ClienteDAO(Session session) {
		this.s = session;
	}
	public void addCliente(Cliente cliente) {
		s.beginTransaction();
		s.save(cliente);
		s.getTransaction().commit();
		
	}

	public List<Cliente> listCliente() {
		List<Cliente> list = new ArrayList<Cliente>();
		s.beginTransaction();

		list = s.createQuery("from Cliente",Cliente.class).list();
		s.getTransaction().commit();

		return list;
	}

	public void removeCliente(Integer id) {
		s.beginTransaction();
		Cliente c = (Cliente) s.load(Cliente.class, id);
		s.delete(c);
		s.getTransaction().commit();
	}

	public void updateCliente(Cliente cliente) {
		s.beginTransaction();
		s.update(cliente);
		s.getTransaction().commit();
	}

	public Cliente retornaCliente(Integer id) {
		s.beginTransaction();
		Cliente c = (Cliente) s.get(Cliente.class, id);
		s.getTransaction().commit();

		return c;
	}
}
