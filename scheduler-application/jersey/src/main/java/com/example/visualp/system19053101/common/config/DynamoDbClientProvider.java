package com.example.visualp.system19053101.common.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import javax.annotation.Nonnull;

public class DynamoDbClientProvider {

  @Nonnull
  public static AmazonDynamoDB provide() {
    return AmazonDynamoDBClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(AwsCredentialsProvider.provide()))
        .withRegion(Const.REGION)
        .build();
  }
}
