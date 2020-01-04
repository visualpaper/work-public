package com.visualpaper.work.deploy.server.config;

import com.visualpaper.binary.library.aws.config.DefaultGetLatestOptionConfiguration;
import com.visualpaper.binary.library.aws.config.PartialUploadConfiguration;
import com.visualpaper.binary.library.aws.config.RetryConfiguration;
import com.visualpaper.binary.library.aws.config.SStorageAwsRpcFactory;
import com.visualpaper.binary.library.aws.config.mine.RedisConfiguration;
import com.visualpaper.binary.library.aws.config.mine.SStorageAwsConfiguration;
import com.visualpaper.binary.library.core.client.SStorage;
import com.visualpaper.binary.library.core.config.SStorageFactory;
import com.visualpaper.work.deploy.server.config.aws.DynamoDbConfigurationProvider;
import com.visualpaper.work.deploy.server.config.aws.S3ConfigurationProvider;
import io.lettuce.core.RedisClient;
import org.glassfish.hk2.api.Factory;

public class SStorageAwsProvider implements Factory<SStorage> {

  @Override
  public SStorage provide() {
    return SStorageFactory.create(
      SStorageAwsRpcFactory.create(
          SStorageAwsConfiguration.builder()
              .dynamoDbConfiguration(DynamoDbConfigurationProvider.provide())
              .s3Configuration(S3ConfigurationProvider.provide())
              .maxManagementRevision(3)
              .metadataWriteRetryConfiguration(
                  RetryConfiguration.builder()
                      .maxTries(3)
                      .delayMilliseconds(1000)
                      .build()
              )
              .defaultGetLatestOptionConfiguration(
                  DefaultGetLatestOptionConfiguration.builder()
                      .calculateMd5(true)
                      .build()
              )
              .partialUploadConfiguration(
                  PartialUploadConfiguration.builder()
                      .redisConfiguration(
                          RedisConfiguration.builder()
                              .redisClient(RedisClient.create("redis://localhost"))
                              .build()
                      )
                      .sessionTtl(3600000L)
                      .build()
              )
              .build()
      )
    );
  }

  @Override
  public void dispose(SStorage sStorage) {
    // do - nothing
  }
}
