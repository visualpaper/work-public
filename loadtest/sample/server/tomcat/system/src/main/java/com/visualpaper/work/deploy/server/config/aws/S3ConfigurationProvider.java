package com.visualpaper.work.deploy.server.config.aws;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.visualpaper.binary.library.aws.config.mine.S3Configuration;

import javax.annotation.Nonnull;

import static com.visualpaper.work.deploy.server.config.Const.S3_BUCKET_NAME;

public class S3ConfigurationProvider {

  @Nonnull
  public static S3Configuration provide() {
    AmazonS3 client = AmazonS3ClientBuilder.standard()
        .withRegion(Regions.AP_NORTHEAST_1)
        .withCredentials(new AWSStaticCredentialsProvider(AwsCredentialsProvider.provide()))
        .withClientConfiguration(
            new ClientConfiguration()
                .withMaxConnections(512)
                .withTcpKeepAlive(true)
        )
        .build();

    return S3Configuration.builder()
        .client(client)
        .bucketName(S3_BUCKET_NAME)
        .build();
  }
}
