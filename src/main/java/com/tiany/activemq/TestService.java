package com.tiany.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class TestService {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    ActiveMQQueue queueDestination;

    public void test(){
        // 调用日志服务
        // 发送一个消息给消息中间件
        // 发送mq消息
        final int userid = 1;
        jmsTemplate.send(queueDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(""+userid);
            }
        });
    }
}
