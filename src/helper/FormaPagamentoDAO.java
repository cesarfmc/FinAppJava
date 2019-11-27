package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import model.FormaPagamento;

public class FormaPagamentoDAO {
	private Session s;

	public FormaPagamentoDAO(Session sessao) {
		this.s = sessao;
	}

	public void addFormaPagamento(FormaPagamento formaPagamento) {
		s.beginTransaction();
		s.save(formaPagamento);
		s.getTransaction().commit();

	}

	public List<FormaPagamento> listFormaPagamento() {
		List<FormaPagamento> list = new ArrayList<FormaPagamento>();
		s.beginTransaction();

		list = s.createQuery("from FormaPagamento", FormaPagamento.class).list();
		s.getTransaction().commit();

		return list;
	}

	public void removeFormaPagamento(Integer id) {
		s.beginTransaction();
		FormaPagamento f = (FormaPagamento) s.load(FormaPagamento.class, id);
		s.delete(f);
		s.getTransaction().commit();
	}

	public void updateFormaPagamento(FormaPagamento formaPagamento) {
		s.beginTransaction();
		s.update(formaPagamento);
		s.getTransaction().commit();
	}

	public FormaPagamento retornaFormaPagamento(Integer id) {
		s.beginTransaction();
		FormaPagamento f = (FormaPagamento) s.get(FormaPagamento.class, id);
		s.getTransaction().commit();

		return f;
	}
}
