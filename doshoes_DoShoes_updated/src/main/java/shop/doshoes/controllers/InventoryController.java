/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.doshoes.controllers;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import shop.doshoes.beans.InventoryManagedBean;
import shop.doshoes.beans.ProductManagedBean;
import shop.doshoes.jpaejb.InventoryFacade;
import shop.doshoes.entities.Inventory;
import shop.doshoes.entities.Product;
import shop.doshoes.jpaejb.ProductFacade;

@Named(value = "inventoryController")
@SessionScoped
public class InventoryController implements Serializable {

    @EJB
    InventoryFacade invFacade;
	
    @Inject
    InventoryManagedBean invBean;
    
    @Inject
    ProductManagedBean productBean;
    
    public InventoryController() {
    }
    
    public String getInventoryByPID() {
        List<Inventory> i = new ArrayList();
        HashMap<String, Integer> map = new HashMap();
        System.out.println("product id = " + invBean.getProductId());
        i = invFacade.findByProductId(invBean.getProductId());
        if (!i.isEmpty()) {
            for (Inventory item: i ) {
                map.put(item.getSize(), item.getStock());
            }
            invBean.setInventoryMap(map);
        } else {
            FacesContext.getCurrentInstance().addMessage("productSearch:productInput", new FacesMessage("No product with this ID found."));
        }
        return "findProductByID";
    }
}
