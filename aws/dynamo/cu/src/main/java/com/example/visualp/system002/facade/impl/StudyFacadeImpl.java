package com.example.visualp.system002.facade.impl;

import com.example.visualp.system002.accessor.dynamodb.DynamoDbAccessor;
import com.example.visualp.system002.accessor.dynamodbmapper.DynamoDbMapperAccessor;
import com.example.visualp.system002.facade.StudyFacade;
import javax.annotation.Nonnull;
import javax.inject.Inject;

public class StudyFacadeImpl implements StudyFacade {

  @Inject
  private DynamoDbAccessor accessor;

  @Inject
  private DynamoDbMapperAccessor mapperAccessor;

  @Override
  public void studyRead(@Nonnull String id) throws Exception {
    mapperAccessor.query(id).print();
  }

  @Override
  public void studyCount(@Nonnull String id) throws Exception {
    mapperAccessor.count(id);
  }

  @Override
  public void batchWrite(@Nonnull String id) throws Exception {
    mapperAccessor.batchWrite(id);
  }

  @Override
  public void studyAdd() {
    accessor.update();
  }
}
