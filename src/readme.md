# 与String整合
<code>
    <!-- jsm消息工厂 -->
	<bean id="targetConnectionFactory" class="org.apache.activemq.ActiveMQConnectionFactory">
		<property name="brokerURL" value="tcp://localhost:61616" />
	</bean>
	<bean id="connectionFactory"
		class="org.springframework.jms.connection.SingleConnectionFactory">
		<!--产生JMS Connection的ConnectionFactory -->
		<property name="targetConnectionFactory" ref="targetConnectionFactory" />
	</bean>

	<bean id="queueDestination" class="org.apache.activemq.command.ActiveMQQueue">
		<constructor-arg value="logQueue" />
	</bean>
	<bean id="topicDestination" class="org.apache.activemq.command.ActiveMQTopic">
		<constructor-arg value="LogTopic" />
	</bean>

	<bean id="myMessageListener" class="com.atguigu.messageListener.MyMessageListener" />
	<!-- 消息监听容器 -->
	<bean
		class="org.springframework.jms.listener.DefaultMessageListenerContainer">
		<property name="connectionFactory" ref="connectionFactory" />
		<property name="destination" ref="queueDestination" />
		<property name="messageListener" ref="myMessageListener" />
	</bean>
	<!--订阅模式监听容器 -->
	<bean
		class="org.springframework.jms.listener.DefaultMessageListenerContainer">
		<property name="connectionFactory" ref="connectionFactory" />
		<property name="destination" ref="topicDestination" />
		<property name="messageListener" ref="myMessageListener" />
	</bean>
</code>