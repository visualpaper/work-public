package com.visualpaper.work.deploy.server.config.sofa;

import static com.visualpaper.work.deploy.server.config.Const.REGION;
import static com.visualpaper.work.deploy.server.config.Const.S3_BUCKET_NAME;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.visualpaper.binary.library.aws.config.mine.S3Configuration;
import com.visualpaper.work.deploy.server.config.AwsCredentialsProvider;
import javax.annotation.Nonnull;

public class S3ConfigurationProvider {

  @Nonnull
  public static S3Configuration provide() {
    AmazonS3 client = AmazonS3ClientBuilder.standard()
        .withRegion(REGION)
        .withCredentials(new AWSStaticCredentialsProvider(AwsCredentialsProvider.provide()))
        .build();

    return S3Configuration.builder()
        .client(client)
        .bucketName(S3_BUCKET_NAME)
        .build();
  }
}
