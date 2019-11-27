package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

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
}
