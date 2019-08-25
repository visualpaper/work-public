package com.example.visualp.system011.facade;

import java.io.InputStream;
import javax.annotation.Nonnull;

public interface StudyFacade {

  void sample() throws Exception;

  @Nonnull
  InputStream sampleGet() throws Exception;
}
