package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Cliente;

public class ClienteDAO {
	private Session s;
	private long tempoInicial;
	
	public ClienteDAO(Session session) {
		this.s = session;
	}
	public void addCliente(Cliente cliente) {
		tempoInicial = System.currentTimeMillis();
		
		s.beginTransaction();
		s.save(cliente);
		s.getTransaction().commit();
		GeradorCsv.tempoFinal(tempoInicial, "Adicionar Cliente");
		
	}

	public List<Cliente> listCliente() {
		tempoInicial = System.currentTimeMillis();
		
		List<Cliente> list = new ArrayList<Cliente>();
		s.beginTransaction();

		list = s.createQuery("from Cliente",Cliente.class).list();
		s.getTransaction().commit();
		
		GeradorCsv.tempoFinal(tempoInicial, "Listar Clientes");
		
		return list;
	}

	public void removeCliente(Integer id) {
		tempoInicial = System.currentTimeMillis();
		
		s.beginTransaction();
		Cliente c = (Cliente) s.load(Cliente.class, id);
		s.delete(c);
		s.getTransaction().commit();
		
		GeradorCsv.tempoFinal(tempoInicial, "Remover Cliente");
	}

	public void updateCliente(Cliente cliente) {
		tempoInicial = System.currentTimeMillis();
		
		s.beginTransaction();
		s.update(cliente);
		s.getTransaction().commit();
		
		GeradorCsv.tempoFinal(tempoInicial, "Atualizar Cliente");
	}

	public Cliente retornaCliente(Integer id) {
		tempoInicial = System.currentTimeMillis();
		
		s.beginTransaction();
		Cliente c = (Cliente) s.get(Cliente.class, id);
		s.getTransaction().commit();

		return c;
	}
	
	public List<Cliente> pesquisaCliente(String nomeCliente, String cnpjCliente, String cpfCliente) {
		tempoInicial = System.currentTimeMillis();
		
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
		
		GeradorCsv.tempoFinal(tempoInicial, "Pesquisar Clientes");

		return list;
	}
}
