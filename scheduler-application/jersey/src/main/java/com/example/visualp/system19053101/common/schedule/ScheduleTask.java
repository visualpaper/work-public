package com.example.visualp.system19053101.common.schedule;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.example.visualp.system19053101.accessor.dynamodb.DynamoDbAccessor;
import com.example.visualp.system19053101.accessor.dynamodb.entity.Item;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.Nonnull;
import javax.inject.Inject;

public class ScheduleTask {

  public static final int READ_SANDBOX_ID = 0;

  private final DynamoDbAccessor accessor;

  private final ExecutorService executorService = Executors.newFixedThreadPool(10);

  @Inject
  public ScheduleTask(@Nonnull DynamoDbAccessor accessor) {
    this.accessor = accessor;
  }

  public void execute() throws Exception {
    PaginatedList<Item> items = accessor.query(READ_SANDBOX_ID);

    items.forEach(v -> {
      // 各々の Task 発火
      System.out.println(v.getId());
    });
    /*
    for (Item item : items) {
      System.out.println(item.getId());
    }
    */
  }
}
