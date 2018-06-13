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
import javax.persistence.criteria.*;
import shop.doshoes.entities.Inventory;

@Stateless
public class InventoryFacade extends AbstractFacade<Inventory> {

    @PersistenceContext(unitName = "edu.ilstu_DoShoes_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventoryFacade() {
        super(Inventory.class);
    }
    
    public List<Inventory> findByProductId(int pid) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Inventory> q = cb.createQuery(Inventory.class);
        Root<Inventory> i = q.from(Inventory.class);
        Join join = i.join("productId");
        Predicate predicate = cb.equal(join.get("productId"), pid);
        q.select(i).where(predicate);
        return em.createQuery(q).getResultList();
    }
  
}
