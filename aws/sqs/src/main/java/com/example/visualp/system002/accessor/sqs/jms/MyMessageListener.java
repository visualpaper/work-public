package com.example.visualp.system002.accessor.sqs.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {

  @Override
  public void onMessage(Message message) {

    try {
      System.out.println("Received: " + ((TextMessage) message).getText());
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
}
