package com.example.visualp.system002.accessor.dynamodbmapper.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnConsumedCapacity;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

public class ItemExpressions {

  /**
   * 1. 条件なし.
   */
  @Nonnull
  public static DynamoDBScanExpression scanCase1() {
    DynamoDBScanExpression expression = new DynamoDBScanExpression();

    expression
        .withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL)
        .withConsistentRead(true);

    return expression;
  }

  /**
   * 2. FilterExpression のみ.
   */
  @Nonnull
  public static DynamoDBScanExpression scanCase2(@Nonnull String id) {
    DynamoDBScanExpression expression = new DynamoDBScanExpression();

    Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
    expressionAttributeValues.put(":val", new AttributeValue().withS(id));

    expression
        .withFilterExpression("id = :val")
        .withExpressionAttributeValues(expressionAttributeValues)
        .withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL)
        .withConsistentRead(true);

    return expression;
  }

  /**
   * 3. FilterExpression + Limit.
   */
  @Nonnull
  public static DynamoDBScanExpression scanCase3(@Nonnull String id) {
    DynamoDBScanExpression expression = new DynamoDBScanExpression();

    Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
    expressionAttributeValues.put(":val", new AttributeValue().withN(String.valueOf(1)));

    expression
        .withLimit(1)
        .withFilterExpression("sort = :val")
        .withExpressionAttributeValues(expressionAttributeValues)
        .withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL)
        .withConsistentRead(true);

    return expression;
  }

  /**
   * 1. KeyConditionExpression でパーティションキーのみ指定
   */
  @Nonnull
  public static DynamoDBQueryExpression<Item> queryCase1(@Nonnull String id) {
    Map<String, AttributeValue> eav = new HashMap<>();
    DynamoDBQueryExpression<Item> expression = new DynamoDBQueryExpression<>();

    eav.put(":val1", new AttributeValue().withS(id));
    expression
        .withKeyConditionExpression("id = :val1")
        .withExpressionAttributeValues(eav)
        .withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL)
        .withConsistentRead(true);

    return expression;
  }

  /**
   * 2. KeyConditionExpression でパーティションキーとソートキー指定
   */
  @Nonnull
  public static DynamoDBQueryExpression<Item> queryCase2(@Nonnull String id) {
    Map<String, AttributeValue> eav = new HashMap<>();
    DynamoDBQueryExpression<Item> expression = new DynamoDBQueryExpression<>();

    eav.put(":val1", new AttributeValue().withS(id));
    eav.put(":val2", new AttributeValue().withN(String.valueOf(2)));
    expression
        .withKeyConditionExpression("id = :val1 and sort < :val2")
        .withExpressionAttributeValues(eav)
        .withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL)
        .withConsistentRead(true);

    return expression;
  }

  /**
   * 3. KeyConditionExpression でパーティションキーのみ指定
   *    + FilterExpression を使用し、条件に合致する Item が一件も存在しない場合
   */
  @Nonnull
  public static DynamoDBQueryExpression<Item> queryCase3(@Nonnull String id) {
    Map<String, AttributeValue> eav = new HashMap<>();
    DynamoDBQueryExpression<Item> expression = new DynamoDBQueryExpression<>();

    eav.put(":val1", new AttributeValue().withS(id));
    eav.put(":val2", new AttributeValue().withS("aaa"));
    expression
        .withKeyConditionExpression("id = :val1")
        .withFilterExpression("info = :val2")
        .withExpressionAttributeValues(eav)
        .withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL)
        .withConsistentRead(true);

    return expression;
  }

  /**
   * 4. KeyConditionExpression でパーティションキーのみ指定 + ScanIndexForward 指定
   */
  @Nonnull
  public static DynamoDBQueryExpression<Item> queryCase4(@Nonnull String id) {
    Map<String, AttributeValue> eav = new HashMap<>();
    DynamoDBQueryExpression<Item> expression = new DynamoDBQueryExpression<>();

    eav.put(":val1", new AttributeValue().withS(id));
    expression
        .withKeyConditionExpression("id = :val1")
        .withExpressionAttributeValues(eav)
        .withScanIndexForward(false)
        .withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL)
        .withConsistentRead(true);

    return expression;
  }

  /**
   * 5. KeyConditionExpression でパーティションキーのみ指定 + ScanIndexForward + Limit 指定
   */
  @Nonnull
  public static DynamoDBQueryExpression<Item> queryCase5(@Nonnull String id) {
    Map<String, AttributeValue> eav = new HashMap<>();
    DynamoDBQueryExpression<Item> expression = new DynamoDBQueryExpression<>();

    eav.put(":val1", new AttributeValue().withS(id));
    expression
        .withKeyConditionExpression("id = :val1")
        .withExpressionAttributeValues(eav)
        .withScanIndexForward(false)
        .withLimit(1)
        .withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL)
        .withConsistentRead(true);

    return expression;
  }

  /**
   * 6. KeyConditionExpression でパーティションキーのみ指定
   *    + ScanIndexForward + FilterExpression と Limit を使用し、条件に合致する Item が一件も存在しない場合
   */
  @Nonnull
  public static DynamoDBQueryExpression<Item> queryCase6(@Nonnull String id) {
    Map<String, AttributeValue> eav = new HashMap<>();
    DynamoDBQueryExpression<Item> expression = new DynamoDBQueryExpression<>();

    eav.put(":val1", new AttributeValue().withS(id));
    eav.put(":val2", new AttributeValue().withS("aaa"));
    expression
        .withKeyConditionExpression("id = :val1")
        .withFilterExpression("info = :val2")
        .withExpressionAttributeValues(eav)
        .withScanIndexForward(false)
        .withLimit(1)
        .withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL)
        .withConsistentRead(true);

    return expression;
  }

  /**
   * 7. KeyConditionExpression でソートキーのみ指定
   */
  @Nonnull
  public static DynamoDBQueryExpression<Item> queryCase7() {
    Map<String, AttributeValue> eav = new HashMap<>();
    DynamoDBQueryExpression<Item> expression = new DynamoDBQueryExpression<>();

    eav.put(":val1", new AttributeValue().withN(String.valueOf(0)));
    eav.put(":val2", new AttributeValue().withN(String.valueOf(1)));
    expression
        .withIndexName("sima-sort-index")
        .withKeyConditionExpression("sima = :val1 and sort < :val2 ")
        .withExpressionAttributeValues(eav)
        .withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL)
        .withConsistentRead(false); // gsi の場合は ConsistentRead は EVENTUAL

    return expression;
  }
}
