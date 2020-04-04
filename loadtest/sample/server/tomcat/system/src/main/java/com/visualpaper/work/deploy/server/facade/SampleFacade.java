package com.visualpaper.work.deploy.server.facade;

import com.visualpaper.binary.library.core.client.SStorage;
import com.visualpaper.binary.library.core.model.content.Content;
import com.visualpaper.binary.library.core.model.object.Metadata;
import com.visualpaper.binary.library.core.model.object.SObject;
import java.io.InputStream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

public class SampleFacade {

  @Inject
  private SStorage storage;

  /**
   * post binary.
   */
  public void postBinary(
      @Nonnull InputStream is,
      @Nullable String contentType,
      long contentLength
  ) throws Exception {

    try {
      storage.create(
          Content.from(is),
          createMetadata(contentType, contentLength)
      );
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  @Nonnull
  private Metadata.ChangeSet createMetadata(
      @Nullable String contentType,
      long contentLength
  ) {
    Metadata.ChangeSet.ChangeSetBuilder builder = Metadata.ChangeSet.builder();

    if (contentType != null) {
      builder.contentType(contentType);
    }
    builder.contentLength(contentLength);

    return builder.build();
  }

  @Nonnull
  public InputStream getBinary(@Nonnull String id) throws Exception {
    SObject sObject = storage.get(id);

    if (sObject == null) {
      throw new Exception("not found");
    }
    return sObject.readContent().get();
  }
}
