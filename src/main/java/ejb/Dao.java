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

    public Dao(){
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
        Query query = em.createNamedQuery(Device.FIND_ALL);
        List<Device> devices;
        devices = query.getResultList();
        return devices;
    }

    public List<Users> getAllUsers() {
        Query query = em.createNamedQuery(Users.FIND_ALL);
        List<Users> usersList;
        usersList = query.getResultList();
        return usersList;
    }



    public Device getDevice(long id){
        Device device = em.find(Device.class, id);
        return device;
    }
    public Users getUsers(long id){
        Users users = em.find(Users.class, id);
        return users;
    }
}