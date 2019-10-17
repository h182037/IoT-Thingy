package ejb;

import entities.Device;
import entities.Feedback;
import entities.Subscription;
import entities.Users;

import java.util.List;

import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.xml.registry.infomodel.User;


@Stateless
public class Dao {

    // Injected database connection:
	@PersistenceContext(unitName="Dat250TweetAdvanced")
    private EntityManager em;

    // Stores a new tweet:
    public void persistUser(Users users) throws NamingException{
        em.persist(users);
    }
    public void updateUser(Users u){
        em.merge(u);
    }

    public void persistDevice(Device device) throws NamingException, JMSException{
        em.persist(device);
    }

    public void persistSubscription(Subscription subscription) throws NamingException, JMSException{
        em.persist(subscription);
    }
    public void persistFeedback(Feedback f) throws NamingException, JMSException{
        em.persist(f);
    }

	public List<Device> getAllDevices() {
        Query query = em.createQuery("SELECT d FROM Device d");
        List<Device> devices;
        devices = query.getResultList();
        return devices;
    }
}