package com.visualpaper.ecs.system.facade.impl;

import com.visualpaper.ecs.system.facade.BinaryFacade;
import com.visualpaper.ecs.system.facade.assembler.BinaryAssembler;
import com.visualpaper.ecs.system.resources.schemas.SimpleResponseSchema;
import javax.annotation.Nonnull;
import javax.inject.Inject;

public class BinaryFacadeImpl implements BinaryFacade {

  @Inject
  private BinaryAssembler assembler;

  /**
   * 読み込み
   */
  @Nonnull
  @Override
  public SimpleResponseSchema getAll() throws Exception {
    return assembler.to("from server");
  }
}
