package com.visualpaper.work.deploy.server.config.sofa;

import com.visualpaper.binary.library.aws.config.DefaultGetLatestOptionConfiguration;
import com.visualpaper.binary.library.aws.config.RetryConfiguration;
import com.visualpaper.binary.library.aws.config.SStorageAwsRpcFactory;
import com.visualpaper.binary.library.aws.config.mine.SStorageAwsConfiguration;
import com.visualpaper.binary.library.core.client.SStorage;
import com.visualpaper.binary.library.core.config.SStorageFactory;
import org.glassfish.hk2.api.Factory;

public class SStorageAwsProvider implements Factory<SStorage> {

  @Override
  public SStorage provide() {
    return SStorageFactory.create(
        SStorageAwsRpcFactory.create(
            SStorageAwsConfiguration.builder()
                .dynamoDbConfiguration(SofaDynamoDbConfigurationProvider.provide())
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
                .build()
        )
    );
  }

  @Override
  public void dispose(SStorage sStorage) {
    // do - nothing
  }
}
