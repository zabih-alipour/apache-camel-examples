package com.alipour.learning.activemqcamel;

import org.springframework.stereotype.Component;

@Component
public class ActivemqMessagePrinter {

    public void printQueueMessage(String message) {
        System.out.println("Printed from Bean: " + message);
    }
}
