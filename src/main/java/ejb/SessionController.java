package ejb;

import entities.Users;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;


@Named(value = "sessionController")
@SessionScoped
public class SessionController implements Serializable {

	@EJB
	private Dao dao;

	private static final long serialVersionUID = 1L;

	private String password;

	private String username;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String validateUsernamePassword() {
		HttpSession session = SessionUtils.getSession();
		session.setAttribute(Constants.USERNAME, this.username);
		if(validateLogin()){
			return Constants.FIND;
		}else {
			return Constants.LOGIN;
		}
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return Constants.LOGIN;
	}
	
	public String redirectOwned() throws IOException {
		HttpSession session = SessionUtils.getSession();
		if (session.getAttribute(Constants.USERNAME)==null) {
			SessionUtils.getResponse().sendRedirect(Constants.LOGIN + ".xhtml");
		}
		return Constants.OWNED;
	}

	public String redirectSubs() throws IOException {
		HttpSession session = SessionUtils.getSession();
		if (session.getAttribute(Constants.USERNAME)==null) {
			SessionUtils.getResponse().sendRedirect(Constants.LOGIN + ".xhtml");
		}
		return Constants.SUBS;
	}

	public String redirectDevices() throws IOException {
		HttpSession session = SessionUtils.getSession();
		if (session.getAttribute(Constants.USERNAME)==null) {
			SessionUtils.getResponse().sendRedirect(Constants.LOGIN + ".xhtml");
		}
		return Constants.FIND;
	}

	public boolean validateLogin(){
		List<Users> usersList = new ArrayList<>();
		usersList.addAll(this.dao.getAllUsers());
		boolean validated = false;
		for(Users u : usersList){
			if(u.getUsername().equals(username) && u.getPassword().equals(password)){
				validated = true;
			}
		}
		return validated;
	}



}
