package com.example.visualp.system002.config;

import com.amazonaws.auth.BasicAWSCredentials;
import javax.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class AwsCredentialsProvider {

  @Nonnull
  public static BasicAWSCredentials provide() {
    return new BasicAWSCredentials(
        Const.AWS_ACCESS_KEY,
        Const.AWS_SECRET_KEY
    );
  }
}