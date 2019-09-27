package ejb;

import entities.Device;

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

@Named(value = "tweetController")
@RequestScoped
public class Controller implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Injected DAO EJB:
	@EJB
	private Dao dao;

	private Device device;

	public List<Device> getDevices() {
		List<Device> reverseDeviceList = new ArrayList<Device>();
		reverseDeviceList.addAll(this.dao.getAllDevices());
		Collections.reverse(reverseDeviceList);
		return reverseDeviceList;

	}

	public Dao getDao() {
		return dao;
	}

	public Device getDevice() {
		if (this.device == null) {
			device = new Device();
		}
		return device;

	}

}
