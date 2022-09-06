/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verificacion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author Eduardo
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {
	String email;
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	public String register() {
		return "thanks?faces-redirect=true";
	}
 
}
