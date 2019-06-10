package com.example.visualp.system19053101.accessor.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnConsumedCapacity;
import com.example.visualp.system19053101.accessor.dynamodb.entity.Item;
import com.example.visualp.system19053101.common.config.DynamoDbMapperProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

public class DynamoDbAccessor {

  @Nonnull
  public PaginatedList<Item> query(int sandboxId) throws Exception {
    DynamoDBMapper mapper = DynamoDbMapperProvider.provide();

    return mapper.query(Item.class, createPaginatedQuery(sandboxId));
  }

  @Nonnull
  private DynamoDBQueryExpression<Item> createPaginatedQuery(int sandboxId) {
    Map<String, AttributeValue> eav = new HashMap<>();
    DynamoDBQueryExpression<Item> expression = new DynamoDBQueryExpression<>();

    eav.put(":val1", new AttributeValue().withN(String.valueOf(sandboxId)));
    expression
        .withIndexName("sandboxId-fireTime-index")
        .withKeyConditionExpression("sandboxId = :val1")
        .withExpressionAttributeValues(eav)
        .withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL)
        .withLimit(2)
        .withConsistentRead(false); // gsi の場合は ConsistentRead は EVENTUAL

    return expression;
  }
}
