package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import helper.HibernateUtil;
import javafx.scene.control.TreeItem;
import model.Categoria;

public class CategoriaItem {
	private Categoria categoria;
	public List<CategoriaItem> Itens = new ArrayList<CategoriaItem>();
	private TreeItem<CategoriaItem> rootLeaf;

	public CategoriaItem() {

	}

	public CategoriaItem(Categoria Categoria,TreeItem<CategoriaItem> rootLeaf) {
		this.categoria = Categoria;
		this.rootLeaf = addNode(rootLeaf);
		getItens();
	}

	public List<Categoria> getCategoriaFilhos() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Categoria> cr = cb.createQuery(Categoria.class);

		Root<Categoria> root = cr.from(Categoria.class);
		cr.select(root).where(cb.equal(root.get("categoria"), categoria.getIdCategoria()));

		List<Categoria> categoriaFilhos = session.createQuery(cr).getResultList();

		return categoriaFilhos;
	}
	
	
	@Override
	public String toString() {
		return getCategoria().getNome().toString();
	}
	
	public TreeItem<CategoriaItem> addNode(TreeItem<CategoriaItem> rootLeaf) {
		TreeItem<CategoriaItem> leaf = new TreeItem<CategoriaItem>(this);
		rootLeaf.getChildren().add(leaf);
		
		return leaf;
	}

	public List<CategoriaItem> getItens() {

		List<Categoria> categoriaFilhos = getCategoriaFilhos();

		for (Categoria categoria : categoriaFilhos) {
			Itens.add(new CategoriaItem(categoria, rootLeaf));
		}
		
		return Itens;
	}

	public void setItems(List<CategoriaItem> itens) {
		Itens = itens;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}