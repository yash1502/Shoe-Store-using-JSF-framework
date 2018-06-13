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
import shop.doshoes.beans.ProductManagedBean;
import shop.doshoes.entities.Product;
import shop.doshoes.jpaejb.ProductFacade;

@Named(value = "productController")
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    ProductFacade productFacade;
    
    @Inject
    ProductManagedBean productBean;
    /**
     * Creates a new instance of ProductController
     */
    public ProductController() {
    }
    
    public List<Product> getAll() {
        return productFacade.findAll();
    }
    
    public int count() {
        return productFacade.count();
    }
    
    public void insert() {
        Product p = new Product();
		p.setProductImage(productBean.getProductImage());
        p.setProductName(productBean.getProductName());
        p.setProductPrice(productBean.getProductPrice());
        p.setBrand(productBean.getBrand());
        p.setCategory(productBean.getCategory());
        p.setSize(productBean.getSize());
        productFacade.create(p);
    }
    
    public String getByPID() {
        Product p = new Product();
        p = productFacade.find(productBean.getProductId());
        if (p != null) {
            productBean.setProductName(p.getProductName());
            productBean.setProductPrice(p.getProductPrice());       
            productBean.setBrand(p.getBrand());
            productBean.setCategory(p.getCategory());
        } else {
            FacesContext.getCurrentInstance().addMessage("productSearch:productInput", new FacesMessage("No product with this ID found."));
        }
        return "findProductByID";
    }
    
    public List<Product> findByName() {
        System.out.println("findByName2 = " + productBean.getProductName());
        return productFacade.findProductName(productBean.getProductName());            
    }
	
	public List<Product> findAllNike() {
		return productFacade.findProductName("Nike");
	}
	
	public List<Product> findAllAdidas() {
		return productFacade.findProductName("Adidas");
	}
	
	public List<Product> findAllJordans() {
		return productFacade.findProductName("Jordan");
	}
    
    public String redirectToResultsPage() {
        System.out.println("findByName1 = " + productBean.getProductName());
        return "searchResults";
    }
    
    public String edit(Product p) {
            productBean.setProductId(p.getProductId());
            productBean.setProductName(p.getProductName());
            productBean.setProductPrice(p.getProductPrice());
            productBean.setProductImage(p.getProductImage());
            productBean.setCategory(p.getCategory());
            productBean.setBrand(p.getBrand());
            productBean.setSize(p.getSize());
			
			return "updateProduct";
    }
    
            
    public String save() {
            Product p = new Product();
			System.out.println("product details " + productBean.getProductName());
			System.out.println("product price " + productBean.getProductPrice());
			p.setProductId(productBean.getProductId());
            p.setProductName(productBean.getProductName());
            p.setProductPrice(productBean.getProductPrice());
            p.setProductImage(productBean.getProductImage());
            p.setCategory(productBean.getCategory());
            p.setBrand(productBean.getBrand()); 
            p.setSize(productBean.getSize());

            productFacade.updateProduct(p);

            return "admin";
    }
    
    public List<Product> getClothes() {
        return productFacade.findCategory("Clothing");
        
    }
    
    public List<Product> getShoes() {
        return productFacade.findCategory("Shoes");
    }
}
