package com.gz.messagingjms;

import org.springframework.jms.annotation.JmsListener;

public class Receiver {
    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email) {
        System.out.println("Received <" + email + ">");
    }
}
