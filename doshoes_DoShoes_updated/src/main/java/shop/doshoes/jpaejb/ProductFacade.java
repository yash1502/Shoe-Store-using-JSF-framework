/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.doshoes.jpaejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import shop.doshoes.entities.Product;

@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "edu.ilstu_DoShoes_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
    public List<Product> findCategory(String category) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> product = q.from(Product.class);
        predicates.add(cb.equal(product.get("category"), category));
        q.select(product).where(predicates.toArray(new Predicate[]{}));
                if (em.createQuery(q).getResultList().isEmpty()) {
            return null;
        } else {
            return em.createQuery(q).getResultList();
        }
    }
    
    public List<Product> findBrand(String brand) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> product = q.from(Product.class);
        predicates.add(cb.equal(product.get("brand"), brand));
        q.select(product).where(predicates.toArray(new Predicate[]{}));
                if (em.createQuery(q).getResultList().isEmpty()) {
            return null;
        } else {
            return em.createQuery(q).getResultList();
        }
    }
    
    public List<Product> findProductName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> product = q.from(Product.class);
        String queryName = "%" + name + "%";
        Predicate predicate = cb.like(product.<String>get("productName"), queryName);
        q.select(product).where(predicate);
//        predicates.add(cb.equal(product.get("productName"), name));
//        q.select(product).where(predicates.toArray(new Predicate[]{}));
        if (em.createQuery(q).getResultList().isEmpty()) {
            return null;
        } else {
            return em.createQuery(q).getResultList();
        }
    }
	
	public void updateProduct(Product p) {
		System.out.println("Product ID = " + p.getProductId());
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<Product> update = cb.createCriteriaUpdate(Product.class);
		Root<Product> product = update.from(Product.class);
		update
				.set(product.get("productName"), p.getProductName())
				.set(product.get("productPrice"), p.getProductPrice())
				.set(product.get("productImage"), p.getProductImage())
				.set(product.get("brand"), p.getBrand())
				.set(product.get("category"), p.getCategory())
				.set(product.get("size"), p.getSize());
		update.where(cb.equal(product.get("productId"), p.getProductId()));
		em.createQuery(update).executeUpdate();
	}
    
}
