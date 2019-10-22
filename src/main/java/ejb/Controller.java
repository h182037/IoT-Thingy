package ejb;

import entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;

@Named(value = "controller")
@RequestScoped
public class Controller implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private Dao dao;

	private Users users;
	private Device device;
	private Feedback feedback;
	private Subscription sub;


	public List<Device> getReverseDeviceList() {
        List<Device> reverseDeviceList = new ArrayList<Device>();
        reverseDeviceList.addAll(this.dao.getAllDevices());
		Collections.reverse(reverseDeviceList);
		return reverseDeviceList;
	}

    public void storeUser(Users u) throws NamingException {
	    dao.persistUser(u);
    }


    public void wakeUpUser() throws JMSException, NamingException {
        users = new Users();
        users.setUsername("IoTFan123");
        users.setPassword("0x0deadbeef");

        device = new Device();
        device.setName("regn");
        device.setUrl("www.here.com");
       // device.setUsers(users);
        device.setOnline(false);
        device.setAvailable(false);
        device.setTags("nedbor, vatn, klima");

        sub = new Subscription();
        sub.setVerified(false);

        feedback = new Feedback();
        feedback.setAuthor(users.getUsername());
        feedback.setText("This rain is ruining my weekend");


        device.addFeedback(feedback);
        device.addSubscription(sub);
        users.addSubscribed(sub);
        users.addOwned(device);
        storeUser(users);
    }

}
