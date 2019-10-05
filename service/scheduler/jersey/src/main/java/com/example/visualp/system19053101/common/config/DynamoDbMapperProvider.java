package com.example.visualp.system19053101.common.config;

import static com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.ConsistentReads.CONSISTENT;
import static com.example.visualp.system19053101.common.config.Const.DYNAMO_DB_TABLE_NAME;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import javax.annotation.Nonnull;

public class DynamoDbMapperProvider {

  @Nonnull
  public static DynamoDBMapper provide() {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withRegion(Regions.AP_NORTHEAST_1)
        .withCredentials(new AWSStaticCredentialsProvider(AwsCredentialsProvider.provide()))
        .build();

    DynamoDBMapperConfig config = DynamoDBMapperConfig.builder()
        .withTableNameOverride(
            TableNameOverride.withTableNameReplacement(DYNAMO_DB_TABLE_NAME)
        )
        .withConsistentReads(CONSISTENT)
        .build();

    return new DynamoDBMapper(client, config);
  }
}
