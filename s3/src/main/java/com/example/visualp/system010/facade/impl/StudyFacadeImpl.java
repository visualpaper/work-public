package com.example.visualp.system010.facade.impl;

import com.example.visualp.system010.accessor.s3.S3Accessor;
import com.example.visualp.system010.facade.StudyFacade;
import javax.inject.Inject;

public class StudyFacadeImpl implements StudyFacade {

  @Inject
  private S3Accessor accessor;

  @Override
  public void getLocation() throws Exception {
    String location = accessor.getLocation();

    System.out.println(location);
  }
}
