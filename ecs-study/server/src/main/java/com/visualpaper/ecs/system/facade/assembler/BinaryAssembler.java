package com.visualpaper.ecs.system.facade.assembler;

import com.visualpaper.ecs.system.resources.schemas.SimpleResponseSchema;
import javax.annotation.Nonnull;

public class BinaryAssembler {

  @Nonnull
  public SimpleResponseSchema to(@Nonnull String name) {
    SimpleResponseSchema schema = new SimpleResponseSchema();

    schema.setName(name);
    return schema;
  }
}
