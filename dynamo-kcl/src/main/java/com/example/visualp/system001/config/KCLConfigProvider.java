package com.example.visualp.system001.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.InitialPositionInStream;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.KinesisClientLibConfiguration;
import javax.annotation.Nonnull;

public class KCLConfigProvider {

  @Nonnull
  public static KinesisClientLibConfiguration provide() {
    return new KinesisClientLibConfiguration(
        Const.DYNAMO_STREAM_LEASE_NAME,
        Const.DYNAMO_STREAM_ARN,
        new AWSStaticCredentialsProvider(AwsCredentialsProvider.provide()),
        Const.KCL_WORKER_ID
    )
        .withRegionName(Const.REGION.getName())
        .withFailoverTimeMillis(100000)
        .withInitialPositionInStream(InitialPositionInStream.TRIM_HORIZON);
  }
}
