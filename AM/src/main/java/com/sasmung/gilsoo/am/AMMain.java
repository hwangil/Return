package com.sasmung.gilsoo.am;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

// Analytics Manager
public class AMMain {
	private static final String TAG = AMMain.class.getSimpleName();

	public static void main(String[] args) throws IOException, TimeoutException, ShutdownSignalException,
			ConsumerCancelledException, InterruptedException {
		springRabbitMq();
		rabbitMq();

	}

	private static void rabbitMq() throws IOException, TimeoutException {
		ConnectionFactory connFactory = new ConnectionFactory();
		connFactory.setHost("127.0.0.1");
		Connection conn = connFactory.newConnection();
		Channel channel = conn.createChannel();
		channel.queueDeclare("gilQueue2", false, false, false, null);
		channel.queueBind("gilQueue2", "amqp.direct", "foo");
		
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(String.format("[%s] %s", TAG + "- gilQueue2", message));
			}
		};

		channel.basicConsume("gilQueue2", true, consumer);
	}

	private static void springRabbitMq() {
		CachingConnectionFactory cf = new CachingConnectionFactory("127.0.0.1", 5672);
		cf.setUsername("guest");
		cf.setPassword("guest");

		RabbitAdmin admin = new RabbitAdmin(cf);

		Queue queue = new Queue("gilQueue");
		admin.declareQueue(queue);

		DirectExchange exchange = new DirectExchange("amqp.direct");
		// should declare exchange
		admin.declareExchange(exchange);
		admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("foo"));

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cf);
		
		
		MessageListenerAdapter adapter = new MessageListenerAdapter(new Object() {
			public void handleMessage(Object foo) throws UnsupportedEncodingException {
				byte[] byteFoo = (byte[]) foo;
				System.out.println(String.format("[%s] %s", TAG + "- gilQueue1", new String(byteFoo, "UTF-8")));

			}
		});

		container.setMessageListener(adapter);
		container.setQueueNames("gilQueue");
		
		container.start();
	}
}
