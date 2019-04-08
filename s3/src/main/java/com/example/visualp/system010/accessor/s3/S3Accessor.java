package com.example.visualp.system010.accessor.s3;

import static com.example.visualp.system010.config.Const.S3_BUCKET_NAME;

import com.amazonaws.services.s3.AmazonS3;
import com.example.visualp.system010.config.S3Provider;

public class S3Accessor {

  public String getLocation() {
    AmazonS3 client = S3Provider.provide();

    return client.getBucketLocation(S3_BUCKET_NAME);
  }
}
