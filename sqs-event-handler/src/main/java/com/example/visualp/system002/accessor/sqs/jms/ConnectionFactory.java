package com.example.visualp.system002.accessor.sqs.jms;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.example.visualp.system002.accessor.sqs.SQSClientProvider;
import javax.annotation.Nonnull;
import javax.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectionFactory {

  @Autowired
  private SQSClientProvider provider;

  @Nonnull
  public SQSConnection create() throws JMSException {
    return new SQSConnectionFactory(
        new ProviderConfiguration(),
        provider.provide()
    ).createConnection();
  }
}
