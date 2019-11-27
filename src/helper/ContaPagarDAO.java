package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import model.ContaPagar;

public class ContaPagarDAO {
	private Session s;

	public ContaPagarDAO(Session sessao) {
		this.s = sessao;
	}

	public void addContaPagar(ContaPagar contaPagar) {
		s.beginTransaction();
		s.save(contaPagar);
		s.getTransaction().commit();

	}

	public List<ContaPagar> listContaPagar() {
		List<ContaPagar> list = new ArrayList<ContaPagar>();
		s.beginTransaction();

		list = s.createQuery("from ContaPagar", ContaPagar.class).list();
		s.getTransaction().commit();

		return list;
	}

	public void removeContaPagar(Integer id) {
		s.beginTransaction();
		ContaPagar contaPagar = (ContaPagar) s.load(ContaPagar.class, id);
		s.delete(contaPagar);
		s.getTransaction().commit();
	}

	public void updateContaPagar(ContaPagar contaPagar) {
		s.beginTransaction();
		s.update(contaPagar);
		s.getTransaction().commit();
	}

	public ContaPagar retornaFormaPagamento(Integer id) {
		s.beginTransaction();
		ContaPagar contaPagar = (ContaPagar) s.get(ContaPagar.class, id);
		s.getTransaction().commit();

		return contaPagar;
	}
}
