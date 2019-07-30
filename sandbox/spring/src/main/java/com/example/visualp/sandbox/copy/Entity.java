package com.example.visualp.sandbox.copy;

import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Entity {
  private String value;

  public Entity(@Nonnull String value) {
    this.value = value;
  }
}
