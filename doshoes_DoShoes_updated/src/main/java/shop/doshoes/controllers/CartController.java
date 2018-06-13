/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.doshoes.controllers;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import shop.doshoes.beans.CartManagedBean;
import shop.doshoes.beans.CustomerManagedBean;
import shop.doshoes.beans.ProductManagedBean;
import shop.doshoes.entities.Cart;
import shop.doshoes.entities.CustOrder;
import shop.doshoes.entities.Customer;
import shop.doshoes.entities.OrderDetail;
import shop.doshoes.entities.OrderDetailPK;
import shop.doshoes.entities.Product;
import shop.doshoes.jpaejb.CartFacade;
import shop.doshoes.jpaejb.CustOrderFacade;
import shop.doshoes.jpaejb.OrderDetailFacade;

@Named(value = "cartController")
@SessionScoped
public class CartController implements Serializable {

	@EJB
	CartFacade cartFacade;
	@EJB
	CustOrderFacade coFacade;
	@EJB
	OrderDetailFacade odFacade;
	
    @Inject
    CustomerManagedBean custBean;
	@Inject
	CartManagedBean cartBean;
	@Inject
	ProductManagedBean prodBean;
	
	private List<Product> plist;

	
	public CartController() {
	}
	
	public int count() {
		return cartFacade.count();
	}
	
	public String insertItemToCart(int pid) {
		Cart cart = new Cart();
		Product p = new Product();
		p.setProductId(pid);
		System.out.println("product id is " + pid);
		cart.setProductId(p);
		cartFacade.create(cart);
		
		return "cart?faces-redirect=true";
	}
	
	public List<Product> getProductsInCart() {
		plist = cartFacade.getProductsInCart();
		double totalPrice = 0;
		for (Product p: plist) {
			totalPrice += p.getProductPrice();
		}
		System.out.println("totalPrice = " + totalPrice);
		totalPrice = round(totalPrice, 2);
		cartBean.setCartTotal(totalPrice);
		System.out.println("totalPrice after round = " + totalPrice);
		return plist;
	}
	
	public String submitOrder() {
            System.out.print("In Submit Order");
		if (custBean.getCustId() == 0) {
			return "index?faces-redirect=true";
		}
		CustOrder co = new CustOrder();
		Customer c = new Customer();
		c.setCustId(custBean.getCustId());
//		System.out.println("cust id is " + custBean.getCustId() + custBean.getFirstname());
		co.setCustId(c);
		coFacade.create(co);
		
//		co = coFacade.find();
//		
//		OrderDetail od = new OrderDetail();
//		OrderDetailPK odpk = new OrderDetailPK();
//		odpk.setOrderId(co.getOrderId());
//		for (Product p: plist) {
//			System.out.println("pid = " + p.getProductId());
//			od.setOrderDetailPK(odpk);
//			od.setProduct(p);
//			od.setQuantity(1);
//			od.setSize(p.getSize());
//			odFacade.create(od);
//		}
		
		cartFacade.deleteAll();
		return "index?faces-redirect=true";
	}
	
	private double round(double value, int places) {
		if (places < 0) {
			throw new IllegalArgumentException();
		}

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
        
        public String emptyCart() {
            cartFacade.deleteAll();
            return "cart";
        }
        
        public String removeFromCart(String cid) {
            cartFacade.removeFromCart(cid);
            return "cart";
        }
}
