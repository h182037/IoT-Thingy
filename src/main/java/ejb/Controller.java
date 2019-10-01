package ejb;

import entities.Device;
import entities.Feedback;
import entities.Subscription;
import entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 * 
 * @author Alejandro Rodriguez
 * Dat250 course
 *
 *   Tweet Controller class for the management of tweets
 */

@Named(value = "controller")
@RequestScoped
public class Controller implements Serializable {

	private static final long serialVersionUID = 1L;

	// Injected DAO EJB:
	@EJB
	private Dao dao;

	private Device device;

    private String returnMessage ="Wake up!";

	public List<Device> getDevices() {
		List<Device> reverseDeviceList = new ArrayList<Device>();
		reverseDeviceList.addAll(this.dao.getAllDevices());
		Collections.reverse(reverseDeviceList);
		return reverseDeviceList;
	}

	public void storeUser(User u) throws NamingException {
	    dao.persistUser(u);
    }
    public void storeDevice(Device d) throws JMSException, NamingException {
	    dao.persistDevice(d);
    }
    public void storeSubscription(Subscription s) throws JMSException, NamingException {
	    dao.persistSubscription(s);
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void wakeUp() throws JMSException, NamingException {

        User owner = new User("Olemann", "1234");
        /*
        List<Device> ownedDevices = new ArrayList<Device>();
        List<Subscription> subscriptions = new ArrayList<Subscription>();
        List<Feedback>  feedback = new ArrayList<Feedback>();
        owner.setOwnedDevices(ownedDevices);
        owner.setSubscriptions(subscriptions);
        owner.setFeedback(feedback);



        User owner2 = new User("Lisedame", "abcd");
        List<Device> ownedDevices2= new ArrayList<Device>();
        List<Subscription> subscriptions2= new ArrayList<Subscription>();
        List<Feedback>  feedback2 = new ArrayList<Feedback>();
        owner2.setOwnedDevices(ownedDevices2);
        owner2.setSubscriptions(subscriptions2);
        owner2.setFeedback(feedback2);

        Device owned = new Device("BergenRegn", "wwww.someurl.com", owner, false, false);
        Device subscribed = new Device("BergenSol", "wwww.someotherurl.com", owner2, true, true);
        owner.addOwnedDevice(owned);
        owner2.addOwnedDevice(subscribed);
        Subscription subscription = new Subscription(owner, subscribed, true);
        owner.addSubscriptions(subscription);
        storeUser(owner);
        storeUser(owner2);
        storeDevice(subscribed);
        storeSubscription(subscription);

        List<Device> devicesList = dao.getAllDevices();
        if(devicesList.contains(owned) && devicesList.contains(subscribed)) {
            returnMessage = "Upload successful, first entry is: " + owned.getName();
        }else{
            returnMessage = "Upload unsuccessful, contact a programming adult";
        }

         */
        storeUser(owner);

    }
	public Device getDevice() {
		if (this.device == null) {
			device = new Device();
		}
		return device;

	}

}
