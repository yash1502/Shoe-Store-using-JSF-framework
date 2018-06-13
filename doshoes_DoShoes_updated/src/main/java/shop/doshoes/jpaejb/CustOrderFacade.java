/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.doshoes.jpaejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import shop.doshoes.entities.CustOrder;

@Stateless
public class CustOrderFacade extends AbstractFacade<CustOrder> {

    @PersistenceContext(unitName = "edu.ilstu_DoShoes_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustOrderFacade() {
        super(CustOrder.class);
    }
    
//	public List<CustOrder> findOrderId( name) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Product> q = cb.createQuery(Product.class);
//        Root<Product> product = q.from(Product.class);
//        String queryName = "%" + name + "%";
//        Predicate predicate = cb.like(product.<String>get("productName"), queryName);
//        q.select(product).where(predicate);
////        predicates.add(cb.equal(product.get("productName"), name));
////        q.select(product).where(predicates.toArray(new Predicate[]{}));
//        if (em.createQuery(q).getResultList().isEmpty()) {
//            return null;
//        } else {
//            return em.createQuery(q).getResultList();
//        }
//    }
}
