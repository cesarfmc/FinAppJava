package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Cliente;
import model.Fornecedor;

public class FornecedorDAO {
	private Session s;
	
	
	
	public FornecedorDAO(Session sessao) {
		this.s = sessao;
	}

	public void addFornecedor(Fornecedor fornecedor) {
		s.beginTransaction();
		s.save(fornecedor);
		s.getTransaction().commit();

	}

	public List<Fornecedor> listFornecedor() {
		List<Fornecedor> list = new ArrayList<Fornecedor>();
		s.beginTransaction();

		list = s.createQuery("from Fornecedor",Fornecedor.class).list();
		s.getTransaction().commit();

		return list;
	}

	public void removeFornecedor(Integer id) {
		s.beginTransaction();
		Fornecedor f = (Fornecedor) s.load(Fornecedor.class, id);
		s.delete(f);
		s.getTransaction().commit();
	}

	public void updateFornecedor(Fornecedor fornecedor) {
		s.beginTransaction();
		s.update(fornecedor);
		s.getTransaction().commit();
	}

	public Fornecedor retornaFornecedor(Integer id) {
		s.beginTransaction();
		Fornecedor f = (Fornecedor) s.get(Fornecedor.class, id);
		s.getTransaction().commit();

		return f;
	}
	
	public List<Fornecedor> pesquisaCliente(String nomeFornecedor, String cnpjFornecedor, String cpfFornecedor) {
		List<Fornecedor> list = new ArrayList<Fornecedor>();
		s.beginTransaction();
		
		String sql = "from Fornecedor where";
		StringBuilder hql = new StringBuilder();
		hql.append(sql);

		
		  if (nomeFornecedor != null) {
			  hql.append(" nome LIKE :nomeFornecedor "); 
			  if(cnpjFornecedor != null || cpfFornecedor != null) {
				  hql.append("and "); 
			  }
		  }
		  
		  if (cnpjFornecedor != null) { 
			  hql.append("cnpj LIKE :cnpjFornecedor "); 
			  if(cpfFornecedor != null) {
				  hql.append("and "); 
			  }
		  }
		  
		  if (cpfFornecedor != null) { 
			  hql.append("cpf LIKE :cpfFornecedor "); 
		  }
		  
		  Query<Fornecedor> query = s.createQuery(hql.toString(),Fornecedor.class);
		  
		  if (nomeFornecedor != null) { 
			  query.setParameter("nomeFornecedor",nomeFornecedor);
		  }
		  
		  if (cnpjFornecedor != null) {
			  query.setParameter("cnpjFornecedor", cnpjFornecedor);
		  }
		  
		  if (cpfFornecedor != null) { 
			  query.setParameter("cpfFornecedor",cpfFornecedor); 
		  }
		 
		 list = query.list();
		 
		s.getTransaction().commit();

		return list;
	}
}
