package demo.jms;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class TestQueueSender {
	public static void main(String args[]) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-config.xml");
		JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
		Destination destination = (Destination) context.getBean("testQueue");
		for(int i=0; i<10; i++){
			jmsTemplate.send(destination, new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage("大家好这个是测试！");
				}
			});
		}
		Connection c = jmsTemplate.getConnectionFactory().createConnection();
		Session session = c.createSession(true, 1);
	}

}