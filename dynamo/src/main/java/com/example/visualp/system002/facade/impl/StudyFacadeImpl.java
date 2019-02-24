package com.example.visualp.system002.facade.impl;

import com.example.visualp.system002.accessor.dynamodb.DynamoDbAccessor;
import com.example.visualp.system002.facade.StudyFacade;
import javax.inject.Inject;

public class StudyFacadeImpl implements StudyFacade {

  @Inject
  private DynamoDbAccessor accessor;

  @Override
  public void studyAdd() {
    accessor.update();
  }
}
