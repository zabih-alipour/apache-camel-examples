package com.alipour.learning.activemqcamel;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivemqToConsul {
    @Autowired
    private CamelContext camelContext;

    public void printQueueMessage() throws Exception {
        try {
            camelContext.addComponent("activemq", ActiveMQComponent.activeMQComponent("vm://localhost?broker.persistent=false"));
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("activemq:queue:test.queue")
                            .to("stream:out") //---- Print in consul
                            .bean(ActivemqMessagePrinter.class, "printQueueMessage") //--- Send message to bean method
                    ;

                    from("timer://myTimer?period=2000")
                            .setBody()
                            .simple("Hello World Camel fired at ${header.firedTime}")
                            .to("stream:out");

                }
            });
            ProducerTemplate template = camelContext.createProducerTemplate();
            camelContext.start();
            template.sendBody("activemq:test.queue", "Hello World1");
            template.sendBody("activemq:test.queue", "Hello World2");
            template.sendBody("activemq:test.queue", "Hello World3");
            Thread.sleep(10000);
        } finally {
            camelContext.stop();
        }


    }
}
