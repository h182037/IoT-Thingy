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

@Stateless
public class Dao {

    // Injected database connection:
	@PersistenceContext(unitName="Dat250TweetAdvanced")
    private EntityManager em;

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

	public List<Device> getAllDevices() {
        Query query = em.createQuery("SELECT d FROM Device d");
        List<Device> devices;
        devices = query.getResultList();
        return devices;
    }
}