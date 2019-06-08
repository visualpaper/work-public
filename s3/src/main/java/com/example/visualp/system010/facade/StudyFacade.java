package com.example.visualp.system010.facade;

import java.io.InputStream;
import javax.annotation.Nonnull;

public interface StudyFacade {

  void getLocation() throws Exception;

  @Nonnull
  InputStream getBase64Binary() throws Exception;

  void putBase64Binary(
      @Nonnull InputStream is,
      long contentLength
  );
}
