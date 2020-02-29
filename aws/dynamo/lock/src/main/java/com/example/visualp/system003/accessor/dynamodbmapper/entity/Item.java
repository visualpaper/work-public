package com.example.visualp.system003.accessor.dynamodbmapper.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "REPLACE_TABLE_NAME")
public class Item {

  private String id;

  private String value;

  private Integer version;

  @DynamoDBHashKey(attributeName = "id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @DynamoDBAttribute(attributeName = "value")
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @DynamoDBVersionAttribute(attributeName = "version")
  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public void print() {
    System.out.println(id);
    System.out.println(value);
    System.out.println(version);
  }
}
