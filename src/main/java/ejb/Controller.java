package ejb;

import entities.Device;
import entities.Subscription;
import entities.Users;

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

	public void storeUser(Users u) throws NamingException {
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

        Device device = new Device();
        device.setName("regn");
        device.setUrl("www.here.com");
        device.setOnline(false);
        device.setAvailable(false);
        storeDevice(device);

    }
	public Device getDevice() {
		if (this.device == null) {
			device = new Device();
		}
		return device;

	}

}
