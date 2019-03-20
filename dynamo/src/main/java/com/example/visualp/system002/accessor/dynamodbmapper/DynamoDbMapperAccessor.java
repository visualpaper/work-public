package com.example.visualp.system002.accessor.dynamodbmapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnConsumedCapacity;
import com.example.visualp.system002.accessor.dynamodbmapper.entity.Item;
import com.example.visualp.system002.accessor.dynamodbmapper.entity.ItemExpressions;
import com.example.visualp.system002.accessor.dynamodbmapper.entity.Items;
import com.example.visualp.system002.config.DynamoDbMapperProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

public class DynamoDbMapperAccessor {

  @Nonnull
  public Items scan(@Nonnull String id) throws Exception {
    DynamoDBMapper mapper = DynamoDbMapperProvider.provide();

    PaginatedScanList<Item> paginatedScanList = mapper.scan(Item.class, ItemExpressions.scanCase3(id));

    // ↓の場合、PageList に対し全 fetch が走る。
    //return new Items(paginatedScanList);

    // ↓なら安全に 1 件のみ取得できる。
    if (paginatedScanList.isEmpty()) {
      return new Items(new ArrayList<>());
    }
    return new Items(Arrays.asList(paginatedScanList.get(0)));
  }

  @Nonnull
  public Items query(@Nonnull String id) throws Exception {
    DynamoDBMapper mapper = DynamoDbMapperProvider.provide();

    PaginatedQueryList<Item> paginatedQueryList = mapper.query(Item.class, ItemExpressions.queryCase5(id));

    // ↓の場合、PageList に対し全 fetch が走る。
    //return new Items(paginatedScanList);

    // ↓なら安全に 1 件のみ取得できる。
    if (paginatedQueryList.isEmpty()) {
      return new Items(new ArrayList<>());
    }
    return new Items(Arrays.asList(paginatedQueryList.get(0)));
  }
}
