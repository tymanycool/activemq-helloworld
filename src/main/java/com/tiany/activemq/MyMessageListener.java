package com.tiany.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


public class MyMessageListener implements MessageListener {


	@Override
	public void onMessage(Message message) {
		// 消息内容
		TextMessage textMessage = (TextMessage) message;
		String text = "";
		try {
			text = textMessage.getText();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 调用记录日志的业务方法
		System.out.println("收到消息:"+text);
	}

}
