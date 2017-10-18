package com.simple.sample;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MySpringBootRouter extends FatJarRouter {	

    @Autowired
    CamelContext context;
    
    @PostConstruct
    public void init() {
    	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "ssl://broker-amq-tcp-ssl-amq-ssl.apps.os.spectrum-health.org:433");
        context.addComponent("activemq",
                JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
    }
    

    @Override
    public void configure() {
        //from("activemq:test")
    	from("vm:foo")
                //.transform().simple("ref:myBean")
                .to("log:out");
    }

    @Bean
    String myBean() {
        return "I'm Spring bean!";
    }

}
