package com.samsung.gilsoo.cm;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.samsung.gilsoo.cm.scheduler.CrawlingScheduler;
import com.samsung.gilsoo.cm.scheduler.Schedulers;

// Crawling Manager
public class CMMain {
	public static void main(String[] args) throws IOException, URISyntaxException, TimeoutException {
		
		for(String name : CMContext.getContext().getBeanDefinitionNames()) {
			System.out.println(name);
		}
		
		// crawling
		try {
//				Schedulers.execute(CrawlingScheduler.class, 0, 1*60*60*1000);
		} catch (Exception e) {
			e.printStackTrace();
		}


//		## rabbit message queue
//		ConnectionFactory connFactory = new ConnectionFactory();
//		connFactory.setHost("127.0.0.1");
//		Connection conn = connFactory.newConnection();
//		Channel channel = conn.createChannel();
//		channel.queueDeclare("gilQueue2", false, false, false, null);
//		channel.basicPublish("amqp.direct", "foo", null, new String("Hi GILSOO ! ").getBytes());
//		channel.close();
//		conn.close();
	}

	private static void springRabbitMq() {
		CachingConnectionFactory cf = new CachingConnectionFactory("127.0.0.1", 5672);
		cf.setUsername("guest");
		cf.setPassword("guest");
				
		RabbitTemplate template = new RabbitTemplate(cf);
		template.setExchange("amqp.direct");
		template.setQueue("gilQueue");
		template.convertAndSend("foo", "Hi gilsoo..!!");
		
		cf.destroy();
	}
	
	
}
