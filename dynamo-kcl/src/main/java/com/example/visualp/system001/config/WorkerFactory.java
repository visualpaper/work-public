package com.example.visualp.system001.config;

import com.amazonaws.services.kinesis.clientlibrary.lib.worker.Worker;
import com.example.visualp.system001.processor.StreamsProcessorFactory;
import javax.annotation.Nonnull;

public class WorkerFactory {

  @Nonnull
  public static Worker create() {
    return new Worker.Builder()
        .recordProcessorFactory(new StreamsProcessorFactory())
        .config(KCLConfigProvider.provide())
        .kinesisClient(DynamoDbStreamAdapterClientProvider.provide())
        .build();
  }
}
