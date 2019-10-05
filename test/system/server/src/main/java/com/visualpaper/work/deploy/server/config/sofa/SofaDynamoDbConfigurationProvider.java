package com.visualpaper.work.deploy.server.config.sofa;

import static com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.ConsistentReads.CONSISTENT;
import static com.visualpaper.work.deploy.server.config.Const.REGION;
import static com.visualpaper.work.deploy.server.config.Const.SOFA_DYNAMO_DB_TABLE_NAME;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.visualpaper.binary.library.aws.config.mine.DynamoDbConfiguration;
import com.visualpaper.work.deploy.server.config.AwsCredentialsProvider;
import javax.annotation.Nonnull;

public class SofaDynamoDbConfigurationProvider {

  @Nonnull
  public static DynamoDbConfiguration provide() {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withRegion(REGION)
        .withCredentials(new AWSStaticCredentialsProvider(AwsCredentialsProvider.provide()))
        .build();

    DynamoDBMapperConfig config = DynamoDBMapperConfig.builder()
        .withTableNameOverride(
            TableNameOverride.withTableNameReplacement(SOFA_DYNAMO_DB_TABLE_NAME)
        )
        .withConsistentReads(CONSISTENT)
        .build();

    return DynamoDbConfiguration.builder()
        .mapper(new DynamoDBMapper(client, config))
        .build();
  }
}
