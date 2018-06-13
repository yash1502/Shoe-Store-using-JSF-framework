/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.doshoes.jpaejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import shop.doshoes.entities.Cart;
import shop.doshoes.entities.Product;

@Stateless
public class CartFacade extends AbstractFacade<Cart> {

    @PersistenceContext(unitName = "edu.ilstu_DoShoes_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CartFacade() {
        super(Cart.class);
    }

    public List<Product> getProductsInCart() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> q = cb.createQuery(Product.class);

        Root<Product> p = q.from(Product.class);

        Join<Product, Cart> join = p.join("cartCollection");
        q.select(p);

//        for (Product pro : em.createQuery(q).getResultList()) {
//            System.out.println("Products here " + pro.getProductName());
//        }
        return em.createQuery(q).getResultList();
    }

    public void deleteAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Cart> del = cb.createCriteriaDelete(Cart.class);
		Root e = del.from(Cart.class);
        this.em.createQuery(del).executeUpdate();
    }
    
    public void removeFromCart(String productId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Cart> del = cb.createCriteriaDelete(Cart.class);
        Root e = del.from(Cart.class);
        del.where(cb.equal(e.get("productId"), productId));
        this.em.createQuery(del).executeUpdate();
    }
}
