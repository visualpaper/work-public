package com.example.visualp.system001.processor;

import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessor;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessorFactory;
import com.example.visualp.system001.config.DynamoDbClientProvider;

public class StreamsProcessorFactory implements IRecordProcessorFactory {

  @Override
  public IRecordProcessor createProcessor() {
    return new StreamsProcessor(DynamoDbClientProvider.provide());
  }
}
