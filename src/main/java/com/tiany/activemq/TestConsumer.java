package com.tiany.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TestConsumer {

	public static void main(String[] args) throws Exception {
		testQueueConsumer();
		//testTopicConsumer();
	}

	public static void testTopicConsumer() throws Exception {

		// mq的服务器地址
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.setClientID("A");
		connection.start();

		// 通过connection创建mq的消息发送或者接受对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("testTopic01");
		MessageConsumer consumer = session.createDurableSubscriber(topic, "A");
		
		// 接收消息
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				// 打印结果
				TextMessage textMessage = (TextMessage) message;
				String text = "";
				try {
					text = textMessage.getText();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(text + "，我今天来加班！我是努力的员工A！");
			}
		});
		// 等待接收消息
		System.in.read();
	}

	public static void testQueueConsumer() throws Exception {

		// mq的服务器地址
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// 通过connection创建mq的消息发送或者接受对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("testQueue01");
		//Queue queue = session.createQueue("logQueue");
		MessageConsumer consumer = session.createConsumer(queue);
		// 接收消息
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				// 打印结果
				TextMessage textMessage = (TextMessage) message;
				String text = "";
				try {
					text = textMessage.getText();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(text + "，我来！我是努力的员工A！");
			}
		});
		// 等待接收消息
		System.in.read();
	}

}
