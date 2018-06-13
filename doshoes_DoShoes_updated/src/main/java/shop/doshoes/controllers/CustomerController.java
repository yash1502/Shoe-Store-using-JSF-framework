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
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import shop.doshoes.beans.CustomerManagedBean;
import shop.doshoes.jpaejb.CustomerFacade;
import shop.doshoes.entities.Customer;

@Named(value = "customerController")
@SessionScoped
public class CustomerController implements Serializable {

    @EJB
    CustomerFacade custFacade;

    @Inject
    CustomerManagedBean customerBean;

    public CustomerController() {
    }

    public String validateCustomer() {
        Customer c = new Customer();
        c = custFacade.getValidUser(customerBean);
        System.out.println("customer login fn = " + c.getFirstname());
        if (c != null) {
            customerBean.setCustid(c.getCustId());
            customerBean.setEmail(c.getEmail());
            customerBean.setFirstname(c.getFirstname());
            customerBean.setLastname(c.getLastname());
            //            custBean.setPassword(c.getPassword());
            customerBean.setAddress(c.getAddress());
            customerBean.setCity(c.getCity());
            customerBean.setState(c.getState());
            customerBean.setZip(c.getZip());
        }

        return "index";
    }

    public List<Customer> getAll() {
        return custFacade.findAll();
    }

    public int count() {
        return custFacade.count();
    }

    public void remove(Customer c) {
        custFacade.remove(c);
    }

    public String add() throws AddressException, MessagingException {
        Customer c = new Customer();
        c.setCustId(customerBean.getCustId());
        c.setEmail(customerBean.getEmail());
        c.setFirstname(customerBean.getFirstname());
        c.setLastname(customerBean.getLastname());
        c.setPassword(customerBean.getPassword());
        c.setAddress(customerBean.getAddress());
        c.setCity(customerBean.getCity());
        c.setState(customerBean.getState());
        c.setZip(customerBean.getZip());
        System.out.println("custBean's firstname " + customerBean.getFirstname());
        custFacade.create(c);
         // Recipient's email ID needs to be mentioned.
        String to = customerBean.getEmail();

        // Sender's email ID needs to be mentioned
        String from = "skuma10@ilstu.edu";
        
        // Assuming you are sending email from this host
        String host = "outlook.office365.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");
        // Get the default Session object.
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("skuma10@ilstu.edu", "Myappleid12345");
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
//            message.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Congratulations! You have been registered successfully.");

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>Successfully signed in.</h1><p>Please login to the <a href=\"http://localhost:8080/DoShoes_updated/faces/index.xhtml\">DoShoes</a> website to browse through some awesome shoes and shirts.</p>",
                    "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return "index";
    }

    public String edit(Customer c) {
        customerBean.setEmail(c.getEmail());
        customerBean.setFirstname(c.getFirstname());
        customerBean.setLastname(c.getLastname());
        customerBean.setPassword(c.getPassword());
        customerBean.setAddress(c.getAddress());
        customerBean.setCity(c.getCity());
        customerBean.setState(c.getState());
        customerBean.setZip(c.getZip());

        return "index";
    }

    public void resetPassword(String email) throws AddressException, MessagingException {
           // Recipient's email ID needs to be mentioned.
        String to = email;

        // Sender's email ID needs to be mentioned
        String from = "skuma10@ilstu.edu";
        
        // Assuming you are sending email from this host
        String host = "outlook.office365.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");
        // Get the default Session object.
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("skuma10@ilstu.edu", "Myappleid12345");
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Reset your password here.");

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>Reset your password by clicking the link below.</h1><a href=\"http://localhost:8080/DoShoes_updated/faces/resetPassword.xhtml\">Reset Password.</h1>",
                    "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public String save() {
        Customer c = new Customer();
        c.setCustId(customerBean.getCustId());
        c.setEmail(customerBean.getEmail());
        c.setFirstname(customerBean.getFirstname());
        c.setLastname(customerBean.getLastname());
        c.setPassword(customerBean.getPassword());
        c.setAddress(customerBean.getAddress());
        c.setCity(customerBean.getCity());
        c.setState(customerBean.getState());
        c.setZip(customerBean.getZip());

        custFacade.edit(c);

        return "index";
    }
	
	public String updatePassword() {
        Customer c = new Customer();
        c.setEmail(customerBean.getEmail());
        c.setPassword(customerBean.getPassword());

        custFacade.updatePassword(c);

        return "index?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
}
