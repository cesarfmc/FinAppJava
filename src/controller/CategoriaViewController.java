package controller;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import helper.HibernateUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Categoria;
import model.Produto;

public class CategoriaViewController {
	@FXML
    private TextField tfdCategoriaPai;

    @FXML
    private TextField tfdNomeCategoria;
	@FXML
	private Button excluirButton;

	@FXML
	void excluir_Click(ActionEvent event) {

	}

	@FXML
	void salvar_Click(ActionEvent event) {

	}
	
	@FXML
	private void initialize() {
		tfdCategoriaPai.textProperty().bind(getNomeProperty(cat.getCategoria()));
	}
	private CategoriaItem cat;
	
	public StringProperty getNomeProperty(Categoria categoria) {
		 StringProperty fname = new SimpleStringProperty();
		 fname.setValue(categoria.getNome().toString());
		return fname;
	}
	

	public CategoriaViewController(CategoriaItem cat) {
		this.cat = cat;
		
	}

	public int verificaVinculo(Categoria categoria) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Produto> cr = cb.createQuery(Produto.class);

		Root<Produto> root = cr.from(Produto.class);
		cr.select(root).where(cb.equal(root.get("categoria"), categoria.getIdCategoria()));

		return session.createQuery(cr).getMaxResults();

	}

	public void salvar() {

	}

	public class CategoriaNew extends CategoriaViewController {
		private Categoria categoria;
		public CategoriaNew() 
        {
            super(cat);
			excluirButton.setDisable(true);
        }
		
		public Categoria catExiste(Categoria categoria) {
			Session session = HibernateUtil.getSessionFactory().openSession();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Categoria> cr = cb.createQuery(Categoria.class);

			Root<Categoria> root = cr.from(Categoria.class);
			cr.select(root).where(cb.equal(root.get("nome"), categoria.getNome()));

			Categoria catExiste = session.createQuery(cr).getSingleResult();

			return catExiste;
		}
        @Override
		public void salvar()
        {
            Categoria temp = catExiste(cat.getCategoria());
            if (temp == null)
            {
            	Session session = HibernateUtil.getSessionFactory().openSession();
            	
            	categoria = new Categoria();
                categoria.setIdCategoria(cat.getCategoria().getIdCategoria());
                session.save(categoria);
                try {
					this.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            else
            {
                System.out.println("Já existe uma categoria com esse nome.");
            }
        }
	}

	public class CategoriaEdit {

	}
}
