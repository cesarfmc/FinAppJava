package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.FormaPagamento;

public class FormaPagamentoDAO {
	private Session s;
	private long tempoInicial;

	public FormaPagamentoDAO(Session sessao) {
		this.s = sessao;
	}

	public void addFormaPagamento(FormaPagamento formaPagamento) {
		tempoInicial = System.currentTimeMillis();
		
		s.beginTransaction();
		s.save(formaPagamento);
		s.getTransaction().commit();

		GeradorCsv.tempoFinal(tempoInicial, "Adicionar FormaPagamento");
	}

	public List<FormaPagamento> listFormaPagamento() {
		tempoInicial = System.currentTimeMillis();
		
		List<FormaPagamento> list = new ArrayList<FormaPagamento>();
		s.beginTransaction();

		list = s.createQuery("from FormaPagamento", FormaPagamento.class).list();
		s.getTransaction().commit();

		GeradorCsv.tempoFinal(tempoInicial, "Listar FormaPagamento");
		return list;
	}

	public void removeFormaPagamento(Integer id) {
		tempoInicial = System.currentTimeMillis();
		
		s.beginTransaction();
		FormaPagamento f = (FormaPagamento) s.load(FormaPagamento.class, id);
		s.delete(f);
		s.getTransaction().commit();
		
		GeradorCsv.tempoFinal(tempoInicial, "Remover FormaPagamento");
	}

	public void updateFormaPagamento(FormaPagamento formaPagamento) {
		tempoInicial = System.currentTimeMillis();
		
		s.beginTransaction();
		s.update(formaPagamento);
		s.getTransaction().commit();
		
		GeradorCsv.tempoFinal(tempoInicial, "Alterar FormaPagamento");
	}

	public FormaPagamento retornaFormaPagamento(Integer id) {
		s.beginTransaction();
		FormaPagamento f = (FormaPagamento) s.get(FormaPagamento.class, id);
		s.getTransaction().commit();

		return f;
	}
	
	public List<FormaPagamento> pesquisaFormaPagamento(String codigoFormaPagamento, String nomeFormaPagamento) {
		tempoInicial = System.currentTimeMillis();
		
		List<FormaPagamento> list = new ArrayList<FormaPagamento>();
		s.beginTransaction();
		String sql;
		
		if(codigoFormaPagamento  == null && nomeFormaPagamento == null) {
			sql = "from FormaPagamento";
		}else {
			sql = "from FormaPagamento where";
		}
		StringBuilder hql = new StringBuilder();
		hql.append(sql);

		
		  if (codigoFormaPagamento != null) {
			  hql.append(" codigo LIKE :codigoFormaPagamento "); 
			  if(nomeFormaPagamento != null ) {
				  hql.append("and "); 
			  }
		  }
		  
		  if (nomeFormaPagamento != null) { 
			  hql.append("nome LIKE :nomeFormaPagamento "); 
		  }
		  		  
		  Query<FormaPagamento> query = s.createQuery(hql.toString(),FormaPagamento.class);
		  
		  if (codigoFormaPagamento != null) { 
			  query.setParameter("codigoFormaPagamento","%"+codigoFormaPagamento+"%");
		  }
		  
		  if (nomeFormaPagamento != null) {
			  query.setParameter("nomeFormaPagamento", "%"+nomeFormaPagamento+"%");
		  }
		 list = query.list();
		 
		s.getTransaction().commit();
		
		GeradorCsv.tempoFinal(tempoInicial, "Pesquisar FormaPagamento");

		return list;
	}
}
