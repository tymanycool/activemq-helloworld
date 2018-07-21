package com.tiany.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TestProducer {

	public static void main(String[] args) {
		testQueue("tcp://localhost:61616", "testQueue01", "为中华民族伟大复兴努力奋斗！");

	}

	public static void testTopic(String url, String application, String message) {
		// mq的服务器地址
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

		Session session;
		try {
			// 通过connection创建mq的消息发送或者接受对象
			Connection connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(application);// 消息对象名
			MessageProducer producer = session.createProducer(topic);
			TextMessage textMessage = session.createTextMessage(message); // 消息内容

			producer.send(textMessage);
			producer.close();
			session.close();
			connection.close();

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testQueue(String url, String application, String message) {
		// mq的服务器地址
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

		Session session;
		try {
			// 通过connection创建mq的消息发送或者接受对象
			Connection connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue(application);// 消息对象名
			MessageProducer producer = session.createProducer(queue);
			TextMessage textMessage = session.createTextMessage(message); // 消息内容

			producer.send(textMessage);
			producer.close();
			session.close();
			connection.close();

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
