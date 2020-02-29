package com.example.visualp.system003.accessor.dynamodbmapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.visualp.system003.accessor.dynamodbmapper.entity.Item;
import com.example.visualp.system003.config.DynamoDbMapperProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class DynamoDbMapperAccessor {

  @Nullable
  public Item get(@Nonnull String id) {
    DynamoDBMapper mapper = DynamoDbMapperProvider.provide();

    return mapper.load(Item.class, id);
  }

  public void updateWithout(@Nonnull String id, @Nonnull String value, @Nullable Integer version) throws Exception {
    DynamoDBMapper mapper = DynamoDbMapperProvider.provide();

    Item item = new Item();
    item.setId(id);
    item.setValue(value);
    item.setVersion(version);

    try {
      mapper.save(item, null, new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.CLOBBER));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void update(@Nonnull String id, @Nonnull String value, @Nullable Integer version) throws Exception {
    DynamoDBMapper mapper = DynamoDbMapperProvider.provide();

    Item item = new Item();
    item.setId(id);
    item.setValue(value);
    item.setVersion(version);

    try {
      DynamoDBSaveExpression saveExpression = new       DynamoDBSaveExpression();
      Map expected = new HashMap();
      expected.put("id", new ExpectedAttributeValue().withExists(true));

      saveExpression.setExpected(expected);
      mapper.save(item);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
