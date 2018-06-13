/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.doshoes.controllers;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import shop.doshoes.beans.CustOrderManagedBean;
import shop.doshoes.entities.CustOrder;
import shop.doshoes.jpaejb.CustOrderFacade;

@Named(value = "custOrderController")
@SessionScoped
public class CustOrderController implements Serializable {

    @EJB
    CustOrderFacade custOrderFacade;
    
    @Inject
    CustOrderManagedBean custOrderBean;
    
    public CustOrderController() {
    }
    
    public List<CustOrder> getAllOrders() {
        return custOrderFacade.findAll();
    }
}
