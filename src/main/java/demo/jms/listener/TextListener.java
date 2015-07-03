package demo.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TextListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage msg = null;
		try {
			System.out.println("Thread ID: "+Thread.currentThread().getId()+" Thread name: "+Thread.currentThread().getName());
			Thread.sleep(500);
			if (message instanceof TextMessage) {
				msg = (TextMessage) message;
				System.out.println("Reading message:" + msg.getText());
			} else {
				System.out.println("Message of wrong type: "
						+ message.getClass().getName());
			}
		} catch (JMSException e) {
			System.out.println("JMSEXCEPTION:" + e.toString());
		} catch (Throwable e) {
			System.out.println("EXCEPTION:" + e.getMessage());
		}
	}

}