package com.example.visualp.system001.processor;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.kinesis.clientlibrary.exceptions.InvalidStateException;
import com.amazonaws.services.kinesis.clientlibrary.exceptions.ShutdownException;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessor;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessorCheckpointer;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.ShutdownReason;
import com.amazonaws.services.kinesis.model.Record;
import java.util.List;

public class StreamsProcessor implements IRecordProcessor {

  private final AmazonDynamoDB dynamoDBClient;
  private Integer checkpointCounter;

  public StreamsProcessor(AmazonDynamoDB dynamoDBClient2) {
    this.dynamoDBClient = dynamoDBClient2;
  }

  @Override
  public void initialize(String shardId) {
    checkpointCounter = 0;
  }

  @Override
  public void processRecords(List<Record> records, IRecordProcessorCheckpointer checkpointer) {
    for (Record record : records) {
      System.out.print(record);
    }
    try {
      checkpointer.checkpoint();
    } catch (InvalidStateException e) {
      e.printStackTrace();
    } catch (ShutdownException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void shutdown(IRecordProcessorCheckpointer checkpointer, ShutdownReason reason) {
    if (reason == ShutdownReason.TERMINATE) {
      try {
        checkpointer.checkpoint();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
