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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import shop.doshoes.beans.CustomerManagedBean;
import shop.doshoes.entities.Customer;

@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "edu.ilstu_DoShoes_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }
    
    public Customer getValidUser(CustomerManagedBean cust) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        String p1 = cust.getEmail();
        String p2 = cust.getPassword();
        
        System.out.println("p1 is " + p1);
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> q = cb.createQuery(Customer.class);
        Root<Customer> c = q.from(Customer.class);
        predicates.add(cb.equal(c.get("email"), p1));
        predicates.add(cb.equal(c.get("password"), p2));
//        ParameterExpression<String> email = cb.parameter(String.class);
//        ParameterExpression<String> password = cb.parameter(String.class);
//        System.out.println("email is " + c.get("email"));
//        System.out.println("password is " + c.get("password"));
//        q.where(
//                cb.equal(c.get("email"), email),
//                cb.equal(c.get("password"), password)
//        );
        q.select(c).where(predicates.toArray(new Predicate[]{}));
        if (em.createQuery(q).getResultList().isEmpty()) {
            return null;
        } else {
            return em.createQuery(q).getResultList().get(0);
        }
    }
	
	public String updatePassword(Customer c) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<Customer> update = cb.createCriteriaUpdate(Customer.class);
		Root<Customer> customer = update.from(Customer.class);
		update.set(customer.get("password"), c.getPassword());
		update.where(cb.equal(customer.get("email"), c.getEmail()));
		em.createQuery(update).executeUpdate();
		
		return "index?faces-redirect=true";
	}
}
