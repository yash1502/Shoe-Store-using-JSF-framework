/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.doshoes.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
	, @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId")
	, @NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Product p WHERE p.productName = :productName")
	, @NamedQuery(name = "Product.findByProductPrice", query = "SELECT p FROM Product p WHERE p.productPrice = :productPrice")
	, @NamedQuery(name = "Product.findByProductImage", query = "SELECT p FROM Product p WHERE p.productImage = :productImage")
	, @NamedQuery(name = "Product.findByBrand", query = "SELECT p FROM Product p WHERE p.brand = :brand")
	, @NamedQuery(name = "Product.findByCategory", query = "SELECT p FROM Product p WHERE p.category = :category")
	, @NamedQuery(name = "Product.findBySize", query = "SELECT p FROM Product p WHERE p.size = :size")})
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_id")
	private Integer productId;
	@Size(max = 40)
    @Column(name = "product_name")
	private String productName;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "product_price")
	private Double productPrice;
	@Size(max = 500)
    @Column(name = "product_image")
	private String productImage;
	@Size(max = 10)
    @Column(name = "brand")
	private String brand;
	@Size(max = 10)
    @Column(name = "category")
	private String category;
	@Size(max = 10)
    @Column(name = "size")
	private String size;
	@OneToMany(mappedBy = "productId")
	private Collection<Cart> cartCollection;

	public Product() {
	}

	public Product(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@XmlTransient
	public Collection<Cart> getCartCollection() {
		return cartCollection;
	}

	public void setCartCollection(Collection<Cart> cartCollection) {
		this.cartCollection = cartCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (productId != null ? productId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Product)) {
			return false;
		}
		Product other = (Product) object;
		if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "shop.doshoes.entities.Product[ productId=" + productId + " ]";
	}
	
}
