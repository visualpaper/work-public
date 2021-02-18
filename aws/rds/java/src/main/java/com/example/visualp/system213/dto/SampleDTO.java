package com.example.visualp.system213.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.annotation.Nullable;

@JsonSerialize
public class SampleDTO {

  public int id;
  public String value;

  public SampleDTO(int id, @Nullable String value) {
    this.id = id;
    this.value = value;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setValue(@Nullable String value) {
    this.value = value;
  }

  public int getId() {
    return id;
  }

  @Nullable
  public String getValue() {
    return value;
  }
}
