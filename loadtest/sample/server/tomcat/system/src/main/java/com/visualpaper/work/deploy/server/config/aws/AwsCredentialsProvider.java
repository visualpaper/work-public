package com.visualpaper.work.deploy.server.config.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

import javax.annotation.Nonnull;

import static com.visualpaper.work.deploy.server.config.Const.AWS_ACCESS_KEY;
import static com.visualpaper.work.deploy.server.config.Const.AWS_SECRET_KEY;

public class AwsCredentialsProvider {

  @Nonnull
  public static AWSCredentials provide() {
    return new BasicAWSCredentials(
        AWS_ACCESS_KEY,
        AWS_SECRET_KEY
    );
  }
}
