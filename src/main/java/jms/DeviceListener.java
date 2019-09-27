package jms;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.google.gson.JsonObject;
import entities.Device;

/**
 * @Author Alejandro Rodriguez Dat250
 * 
 * Listener triggered when a tweet is sent to the Topic JMS. It filters
 * the topic for "dweet"
 * 
 */

@MessageDriven(mappedName = "jms/dat250/Topic", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "topicUser = 'dweet'") })
public class DeviceListener implements MessageListener {

	@Override
	public void onMessage(Message message) {

		try {
			Device device = message.getBody(Device.class);
			JsonObject json = new JsonObject();
			json.addProperty("User", device.getOwner().getUsername());
			//json.addProperty("Message", device.getMessage());

			Logger logger = Logger.getLogger(getClass().getName());
			logger.info("DTWEET User: " + device.getOwner());
			//logger.info("DTWEET Message: " + device.getMessage());
			logger.info("DTWEET: Sending device to dweet...");
			logger.info("DTWEET JSON: " + json);
			try {
				DeviceConnection dc = new DeviceConnection();
				dc.publish(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}