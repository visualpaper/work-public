package com.example.visualp.system19053101.accessor.dynamodb.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "REPLACE_TABLE_NAME")
public class Item {

  private String id;

  private int sandboxId;

  private long fireTime;

  @DynamoDBHashKey(attributeName = "id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @DynamoDBIndexHashKey(attributeName = "sandboxId", globalSecondaryIndexName = "sandboxId-fireTime-index")
  public int getSandboxId() {
    return sandboxId;
  }

  public void setSandboxId(int sandboxId) {
    this.sandboxId = sandboxId;
  }

  @DynamoDBIndexRangeKey(attributeName = "fireTime", globalSecondaryIndexName = "sandboxId-fireTime-index")
  public long getFireTime() {
    return fireTime;
  }

  public void setFireTime(long fireTime) {
    this.fireTime = fireTime;
  }
}
