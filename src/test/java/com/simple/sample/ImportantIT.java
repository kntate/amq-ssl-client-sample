package com.simple.sample;

import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
public class ImportantIT {
	
    @Produce
    ProducerTemplate template;    
    

    @Autowired
    CamelContext context;
	
    @Test
    public void simpleTest() throws InterruptedException {
    	
    	System.out.println("Init");
        
        template.sendBody("activemq:test1", "Its a message from camel");
    	
        //template.sendBody("log:test", "just trying out");
    	//Thread.sleep(5000);
    	
    	//template.sendBody("log:here", "testing");
    	
    	//String body = context.createConsumerTemplate().receiveBody("activemq:testng", String.class);
    	//System.out.println(body);
    	Thread.sleep(1500l);
    	
    }

}
