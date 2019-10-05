package com.visualpaper.ecs.system.facade;

import com.visualpaper.ecs.system.resources.schemas.SimpleResponseSchema;
import javax.annotation.Nonnull;

public interface BinaryFacade {

  @Nonnull
  SimpleResponseSchema getAll() throws Exception;
}
