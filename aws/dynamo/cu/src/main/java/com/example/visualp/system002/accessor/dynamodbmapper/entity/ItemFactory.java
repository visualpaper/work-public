package com.example.visualp.system002.accessor.dynamodbmapper.entity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;

public class ItemFactory {

  @Nonnull
  public static List<Item> create(@Nonnull String id, int sortStart, int sortEnd) {
    return IntStream.rangeClosed(sortStart, sortEnd)
        .mapToObj(v -> {
          Item item = new Item();

          item.setId(id);
          item.setSort(v);
          return item;
        })
        .collect(Collectors.toList());
  }
}
