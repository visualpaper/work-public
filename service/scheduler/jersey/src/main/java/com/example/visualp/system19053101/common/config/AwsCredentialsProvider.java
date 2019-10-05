package com.example.visualp.system19053101.common.config;

import com.amazonaws.auth.BasicAWSCredentials;
import javax.annotation.Nonnull;

public class AwsCredentialsProvider {

  @Nonnull
  public static BasicAWSCredentials provide() {
    return new BasicAWSCredentials(
        Const.AWS_ACCESS_KEY,
        Const.AWS_SECRET_KEY
    );
  }
}
