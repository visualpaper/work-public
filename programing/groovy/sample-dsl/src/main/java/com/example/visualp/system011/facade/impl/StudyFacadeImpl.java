package com.example.visualp.system011.facade.impl;

import com.example.visualp.system011.facade.StudyFacade;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.annotation.Nonnull;

public class StudyFacadeImpl implements StudyFacade {

  @Override
  public void sample() throws Exception {
    System.out.println("IN");
  }

  @Nonnull
  @Override
  public InputStream sampleGet() throws Exception {
    return new FileInputStream(new File("C:/sample.txt"));
  }
}
