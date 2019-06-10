package com.example.visualp.system002.accessor.dynamodbmapper.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "REPLACE_TABLE_NAME")
public class Item {

  private String id;

  private Integer sima;

  private Integer sort;

  private String info;

  @DynamoDBHashKey(attributeName = "id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @DynamoDBIndexHashKey(attributeName = "sima", globalSecondaryIndexName = "sima-sort-index")
  public Integer getSima() {
    return sima;
  }

  public void setSima(Integer sima) {
    this.sima = sima;
  }

  @DynamoDBIndexRangeKey(attributeName = "sort", globalSecondaryIndexName = "sima-sort-index")
  public Integer getSort() {
    return sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
  }

  @DynamoDBAttribute(attributeName = "info")
  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public void print() {
    System.out.println(id);
    System.out.println(sort);
    System.out.println(info);
  }
}
