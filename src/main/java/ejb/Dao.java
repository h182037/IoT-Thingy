package ejb;

import entities.Device;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
	
	
	@Inject
	@JMSConnectionFactory("jms/dat250/ConnectionFactory")
	@JMSSessionMode(JMSContext.AUTO_ACKNOWLEDGE)
	private JMSContext context;
	
	@Resource(lookup = "jms/dat250/Topic")
	private Topic topic;
	
    // Stores a new tweet:
    public void persist(Object o) throws JMSException {
        em.persist(o);
        //Send the topic to the JMS Topic
		//context.createProducer().setProperty("topicUser", tweet.getTopic()).send(topic, tweet);
    }

	public List<Device> getAllDevices() {
        Query query = em.createQuery("SELECT d FROM Device d");
        List<Device> devices;
        devices = query.getResultList();
        return devices;
    }
}