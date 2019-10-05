package com.example.visualp.system002.accessor.sqs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.example.visualp.system002.config.AwsCredentialsProvider;
import com.example.visualp.system002.config.Const;
import javax.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class SQSClientProvider {

  @Nonnull
  public AmazonSQS provide() {
    return AmazonSQSClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(AwsCredentialsProvider.provide()))
        .withRegion(Const.REGION)
        .build();
  }
}
