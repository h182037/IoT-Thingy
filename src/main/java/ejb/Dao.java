package ejb;

import entities.Device;
import entities.Subscription;
import entities.User;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSSessionMode;
import javax.jms.Topic;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * @author Alejandro Rodriguez
 * Dat250 course
 *
 *Data Access Object connecting the Database with the business logic
 */
@Named(value = "dao")
@Stateless
public class Dao {
	
    // Injected database connection:
	@PersistenceContext(unitName="Dat250TweetAdvanced")
    private EntityManager em;

    private String returnMessage;

    public String getReturnMessage(){return returnMessage;}

    // Stores a new tweet:
    public void persistUser(User user) throws NamingException{
        em.persist(user);
    }
    public void persistDevice(Device device) throws NamingException, JMSException{
        em.persist(device);
    }

    public void persistSubscription(Subscription subscription) throws NamingException, JMSException{
        em.persist(subscription);
    }

    public void wakeUp() throws JMSException, NamingException {
        User owner = new User("Olemann", "1234");
        User owner2 = new User("Lisedame", "abcd");
        Device owned = new Device("BergenRegn", "wwww.someurl.com", owner, false, false);
        Device subscribed = new Device("BergenSol", "wwww.someotherurl.com", owner2, true, true);
        owner.addOwnedDevice(owned);
        owner2.addOwnedDevice(subscribed);
        Subscription subscription = new Subscription(owner, subscribed, true);
        owner.addSubscriptions(subscription);
        persistUser(owner);
        persistUser(owner2);
        persistDevice(subscribed);
        persistSubscription(subscription);

        List<Device> devicesList = getAllDevices();
        if(devicesList.contains(owned) && devicesList.contains(subscribed)) {
            returnMessage = "Upload successful, first entry is: " + owned.getName();
        }else{
            returnMessage = "Upload unsuccessful, contact a programming adult";
        }
    }

	public List<Device> getAllDevices() {
        Query query = em.createQuery("SELECT d FROM Device d");
        List<Device> devices;
        devices = query.getResultList();
        return devices;
    }
}