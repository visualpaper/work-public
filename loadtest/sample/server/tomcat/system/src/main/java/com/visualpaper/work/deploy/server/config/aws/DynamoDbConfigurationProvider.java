package com.visualpaper.work.deploy.server.config.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.visualpaper.binary.library.aws.config.mine.DynamoDbConfiguration;

import javax.annotation.Nonnull;

import static com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.ConsistentReads.CONSISTENT;
import static com.visualpaper.work.deploy.server.config.Const.DYNAMO_DB_TABLE_NAME;

public class DynamoDbConfigurationProvider {

  @Nonnull
  public static DynamoDbConfiguration provide() {
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

    return DynamoDbConfiguration.builder()
        .mapper(new DynamoDBMapper(client, config))
        .build();
  }
}
