package com.example.visualp.system002.accessor.dynamodbmapper.entity;

import java.util.List;
import javax.annotation.Nonnull;

public class Items {

  private final List<Item> items;

  public Items(@Nonnull List<Item> items) {
    this.items = items;
  }

  public boolean isEmpty() {
    return items.isEmpty();
  }

  public void print() {
    for (int i = 0; i < items.size(); i++) {
      System.out.println(items.get(i).getSort());
    }
  }
}
