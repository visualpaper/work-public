package com.example.visualp.system010.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import javax.annotation.Nonnull;

public class S3Provider {

  @Nonnull
  public static AmazonS3 provide() {
    return AmazonS3ClientBuilder.standard()
        .withRegion(Const.REGION)
        .withCredentials(new AWSStaticCredentialsProvider(AwsCredentialsProvider.provide()))
        .build();
  }
}
