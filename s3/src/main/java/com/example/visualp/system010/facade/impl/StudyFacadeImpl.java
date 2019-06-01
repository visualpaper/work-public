package com.example.visualp.system010.facade.impl;

import com.example.visualp.system010.accessor.s3.S3Accessor;
import com.example.visualp.system010.facade.StudyFacade;
import java.io.InputStream;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import org.apache.commons.codec.binary.Base64InputStream;

public class StudyFacadeImpl implements StudyFacade {

  @Inject
  private S3Accessor accessor;

  @Override
  public void getLocation() throws Exception {
    String location = accessor.getLocation();

    System.out.println(location);
  }

  @Nonnull
  @Override
  public InputStream getBase64Binary() throws Exception {
    return accessor.getBase64Binary();
  }

  @Override
  public void putBase64Binary(@Nonnull InputStream is, long contentLength) {
    accessor.putBase64Binary(
        new Base64InputStream(is, false),
        contentLength
    );
  }
}
