package ejb;

import entities.Device;
import entities.Subscription;
import entities.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Alejandro Rodriguez
 * Dat250 course
 *
 *Session Controller for validate an user 
 */

@Named(value = "sessionController")
@SessionScoped
public class SessionController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Controller controller;

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

	private String returnMessage;

	public String getReturnMessage(){return returnMessage;}

	public String validateUsernamePassword() {
		HttpSession session = SessionUtils.getSession();
		session.setAttribute(Constants.USERNAME, this.username);
		return Constants.INDEX;
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return Constants.LOGIN;
	}
	
	public String redirect() throws IOException {
		HttpSession session = SessionUtils.getSession();
		if (session.getAttribute(Constants.USERNAME)==null) {
			SessionUtils.getResponse().sendRedirect(Constants.LOGIN + ".xhtml");
		}
		return Constants.INDEX;
	}

	public void wakeUp() throws JMSException, NamingException {
		Dao dao = new Dao();
		User owner = new User("Olemann", "1234");
		User owner2 = new User("Lisedame", "abcd");
		Device owned = new Device("BergenRegn", "wwww.someurl.com", owner, false, false);
		Device subscribed = new Device("BergenSol", "wwww.someotherurl.com", owner2, true, true);
		owner.addOwnedDevice(owned);
		owner2.addOwnedDevice(subscribed);
		Subscription subscription = new Subscription(owner, subscribed, true);
		owner.addSubscriptions(subscription);
		dao.persistUser(owner);
		dao.persistUser(owner2);
		dao.persistDevice(subscribed);
		dao.persistSubscription(subscription);

		List<Device> devicesList = dao.getAllDevices();
		if(devicesList.contains(owned) && devicesList.contains(subscribed)) {
			returnMessage = "Upload successful, first entry is: " + owned.getName();
		}else{
			returnMessage = "Upload unsuccessful, contact a programming adult";
		}
	}

}
