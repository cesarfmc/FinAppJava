package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import model.Produto;

public class ProdutoDAO {
	public void addProduto(Produto produto) {
		Session s = HibernateUtil2.getSessionFactory().openSession();

		s.beginTransaction();
		s.save(produto);
		s.getTransaction().commit();
		
	}

	public List<Produto> listProduto() {
		List<Produto> list = new ArrayList<Produto>();
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();

		list = s.createQuery("from Produto",Produto.class).list();
		s.getTransaction().commit();

		return list;
	}

	public void removeProduto(Integer id) {
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();
		Produto c = (Produto) s.load(Produto.class, id);
		s.delete(c);
		s.getTransaction().commit();
	}

	public void updateProduto(Produto produto) {
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(produto);
		s.getTransaction().commit();
	}

	public Produto retornaProduto(Integer id) {
		
		Session s = HibernateUtil2.getSessionFactory().openSession();
		s.beginTransaction();
		Produto produto = (Produto) s.get(Produto.class, id);
		s.getTransaction().commit();

		return produto;
	}
}
