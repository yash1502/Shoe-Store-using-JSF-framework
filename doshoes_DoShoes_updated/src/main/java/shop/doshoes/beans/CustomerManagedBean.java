/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.doshoes.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "custBean")
@SessionScoped
public class CustomerManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int custId;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String address;
	private String city;
	private String state;
	private int zip;
        
        public CustomerManagedBean() {
	}

	public int getCustId() {
		return custId;
	}

	public void setCustid(int custId) {
		this.custId = custId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

//	//validate login
//	public String validateEmailPassword() {
//            boolean valid = LoginDAO.validate(email, password);
//            if (valid) {
//                    HttpSession session = SessionUtils.getSession();
//                    session.setAttribute("email", email);
//                    session.setAttribute("firstname", firstname);
//                    session.setAttribute("lastname", lastname);
//                    session.setAttribute("address", address);
//                    session.setAttribute("state", state);
//                    session.setAttribute("zip", zip);
//                    return "index";
//            } else {
//                    FacesContext.getCurrentInstance().addMessage(
//                                    null,
//                                    new FacesMessage(FacesMessage.SEVERITY_WARN,
//                                                    "Incorrect Email and Password",
//                                                    "Please enter correct Email and Password"));
//                    return "login";
//            }
//	}
//
//	//logout event, invalidate session
//	public String logout() {
//		HttpSession session = SessionUtils.getSession();
//		session.invalidate();
//		return "login";
//	}
	
}
