package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import model.Categoria;

public class CategoriaDAO {
	private Session s = HibernateUtil2.getSessionFactory().openSession();

	public void addCategoria(Categoria categoria) {
		s.beginTransaction();
		s.save(categoria);
		s.getTransaction().commit();

	}

	public List<Categoria> listCategoria() {
		List<Categoria> list = new ArrayList<Categoria>();
		s.beginTransaction();

		list = s.createQuery("from Categoria",Categoria.class).list();
		s.getTransaction().commit();

		return list;
	}

	public void removeCategoria(Integer id) {
		s.beginTransaction();
		Categoria categoria = (Categoria) s.load(Categoria.class, id);
		s.delete(categoria);
		s.getTransaction().commit();
	}

	public void updateCategoria(Categoria categoria) {
		s.beginTransaction();
		s.update(categoria);
		s.getTransaction().commit();
	}

	public Categoria retornaCategoria(Integer id) {
		s.beginTransaction();
		Categoria categoria = (Categoria) s.get(Categoria.class, id);
		s.getTransaction().commit();

		return categoria;
	}
	
	public List<Categoria> listCategoriasPai() {
		List<Categoria> list = new ArrayList<Categoria>();
		s.beginTransaction();

		list = s.createQuery("from Categoria where idCategoriaPai is null",Categoria.class).list();
		s.getTransaction().commit();

		return list;
	}
	
	public List<Categoria> listCategoriasFilhos(Integer id) {
		List<Categoria> list = new ArrayList<Categoria>();
		s.beginTransaction();

		list = s.createQuery("from Categoria where idCategoriaPai = "+id,Categoria.class).list();
		s.getTransaction().commit();

		return list;
	}
}
