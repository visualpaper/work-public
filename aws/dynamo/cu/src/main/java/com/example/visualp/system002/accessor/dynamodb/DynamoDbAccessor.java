package com.example.visualp.system002.accessor.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeAction;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.ReturnConsumedCapacity;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;
import com.example.visualp.system002.accessor.dynamodbmapper.entity.Items;
import com.example.visualp.system002.config.Const;
import com.example.visualp.system002.config.DynamoDbClientProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DynamoDbAccessor {

  public void readScan() {
    AmazonDynamoDB client = DynamoDbClientProvider.provide();

    ScanRequest request = new ScanRequest();

    Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
    expressionAttributeValues.put(":val", new AttributeValue().withN(String.valueOf(1)));

    request
        .withTableName(Const.DYNAMO_DB_TABLE_NAME)
        .withLimit(1)
        .withFilterExpression("sort = :val")
        .withExpressionAttributeValues(expressionAttributeValues)
        .withConsistentRead(true)
        .withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL);

    ScanResult result = client.scan(request);

    List<Map<String, AttributeValue>> values =  result.getItems();
    System.out.println(result.getConsumedCapacity().getCapacityUnits().intValue());
  }

  public void update() {
    AmazonDynamoDB client = DynamoDbClientProvider.provide();

    Map<String, AttributeValueUpdate> item = new HashMap<String, AttributeValueUpdate>();
    Map<String, AttributeValue> key = new HashMap<String, AttributeValue>();

    key.put("id", new AttributeValue().withS("101"));

    item.put("lists",
        new AttributeValueUpdate()
            .withAction(AttributeAction.ADD)
            .withValue(new AttributeValue().withSS(UUID.randomUUID().toString())));

    UpdateItemRequest updateItemRequest = new UpdateItemRequest()
        .withTableName(Const.DYNAMO_DB_TABLE_NAME)
        .withKey(key)
        .withAttributeUpdates(item);

    updateItemRequest.setReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL);

    UpdateItemResult result = client.updateItem(updateItemRequest);

    if (result.getConsumedCapacity().getCapacityUnits().intValue() > 1) {
      System.out.print("IN");
    }
  }
}
