package com.example.visualp.system010.facade;

import com.example.visualp.system010.resources.schemas.SchemaData;
import javax.annotation.Nonnull;

public interface StudyFacade {

  @Nonnull
  SchemaData read() throws Exception;

  @Nonnull
  SchemaData update() throws Exception;
}
