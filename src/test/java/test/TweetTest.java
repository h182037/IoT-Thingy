package test;


import entities.Device;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 * @Author Alejandro Rodriguez
 * Dat250
 * 
 * Test class for displaying the tweets in the database
 * 
 */

public class TweetTest {

    private static final String PERSISTENCE_UNIT_NAME = "Dat250TweetAdvanced";
    private static EntityManagerFactory factory;
	
	public static void main(String[] args) {
		 factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	        EntityManager em = factory.createEntityManager();
			Logger logger = Logger.getLogger("DeviceTest");
	        // read the existing entries and write to console
	        Query q = em.createQuery("select d from Device d");
	        @SuppressWarnings("unchecked")
			List<Device> devices = q.getResultList();
	        for (Device d : devices) {
	            logger.info("TWEET "+d.getId());
	        }
	}

}
