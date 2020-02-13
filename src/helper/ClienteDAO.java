package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

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
	
	public List<Cliente> pesquisaCliente(String nomeCliente, String cnpjCliente, String cpfCliente) {
		List<Cliente> list = new ArrayList<Cliente>();
		s.beginTransaction();
		
		String sql = "from Cliente where";
		StringBuilder hql = new StringBuilder();
		hql.append(sql);

		
		  if (nomeCliente != null) {
			  hql.append(" nome LIKE :nomeCliente "); 
			  if(cnpjCliente != null || cpfCliente != null) {
				  hql.append("and "); 
			  }
		  }
		  
		  if (cnpjCliente != null) { 
			  hql.append("cnpj LIKE :cnpjCliente "); 
			  if(cpfCliente != null) {
				  hql.append("and "); 
			  }
		  }
		  
		  if (cpfCliente != null) { 
			  hql.append("cpf LIKE :cpfCliente "); 
		  }
		  
		  Query<Cliente> query = s.createQuery(hql.toString(),Cliente.class);
		  
		  if (nomeCliente != null) { 
			  query.setParameter("nomeCliente","%"+nomeCliente+"%");
		  }
		  
		  if (cnpjCliente != null) {
			  query.setParameter("cnpjCliente", "%"+cnpjCliente+"%");
		  }
		  
		  if (cpfCliente != null) { 
			  query.setParameter("cpfCliente", "%"+cpfCliente+"%"); 
		  }
		 
		 list = query.list();
		 
		s.getTransaction().commit();

		return list;
	}
}
