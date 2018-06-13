/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.doshoes.jpaejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import shop.doshoes.entities.OrderDetail;

@Stateless
public class OrderDetailFacade extends AbstractFacade<OrderDetail> {

	@PersistenceContext(unitName = "edu.ilstu_DoShoes_war_1.0-SNAPSHOTPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public OrderDetailFacade() {
		super(OrderDetail.class);
	}
	
}
