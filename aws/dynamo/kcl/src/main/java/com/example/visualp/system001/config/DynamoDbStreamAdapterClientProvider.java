package com.example.visualp.system001.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.dynamodbv2.streamsadapter.AmazonDynamoDBStreamsAdapterClient;
import javax.annotation.Nonnull;

public class DynamoDbStreamAdapterClientProvider {

  @Nonnull
  public static AmazonDynamoDBStreamsAdapterClient provide() {
    return new AmazonDynamoDBStreamsAdapterClient(
        AwsCredentialsProvider.provide(),
        new ClientConfiguration()
    );
  }
}
