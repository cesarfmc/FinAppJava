package helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import model.Marca;

public class MarcaDAO {
	private Session s = HibernateUtil2.getSessionFactory().openSession();

	public void addMarca(Marca marca) {
		s.beginTransaction();
		s.save(marca);
		s.getTransaction().commit();

	}

	public List<Marca> listMarca() {
		List<Marca> list = new ArrayList<Marca>();
		s.beginTransaction();

		list = s.createQuery("from Marca",Marca.class).list();
		s.getTransaction().commit();

		return list;
	}

	public void removeMarca(Integer id) {
		s.beginTransaction();
		Marca marca = (Marca) s.load(Marca.class, id);
		s.delete(marca);
		s.getTransaction().commit();
	}

	public void updateMarca(Marca marca) {
		s.beginTransaction();
		s.update(marca);
		s.getTransaction().commit();
	}

	public Marca retornaMarca(Integer id) {
		s.beginTransaction();
		Marca marca = (Marca) s.get(Marca.class, id);
		s.getTransaction().commit();

		return marca;
	}
}
