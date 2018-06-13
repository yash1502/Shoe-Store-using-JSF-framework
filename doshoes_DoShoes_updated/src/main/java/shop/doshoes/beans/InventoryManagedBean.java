/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.doshoes.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named(value = "inventoryBean")
@SessionScoped
public class InventoryManagedBean implements Serializable {

//    private int inventoryId;
    private int productId;
//    private String size;
//    private int stock;
    Map inventoryMap = new HashMap();
    
    public InventoryManagedBean() {
    }
    
    public Map getInventoryMap() {
        return inventoryMap;
    }

    public void setInventoryMap(Map inventoryMap) {
        this.inventoryMap = inventoryMap;
    }

//    public int getInventoryId() {
//        return inventoryId;
//    }
//
//    public void setInventoryId(int inventoryId) {
//        this.inventoryId = inventoryId;
//    }
//
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
//
//    public String getSize() {
//        return size;
//    }
//
//    public void setSize(String size) {
//        this.size = size;
//    }
//
//    public int getStock() {
//        return stock;
//    }
//
//    public void setStock(int stock) {
//        this.stock = stock;
//    }
    

}
