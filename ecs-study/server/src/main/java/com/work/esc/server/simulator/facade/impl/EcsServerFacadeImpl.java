package com.work.esc.server.simulator.facade.impl;

import com.work.esc.server.simulator.facade.EcsServerFacade;
import com.work.esc.server.simulator.facade.accembler.EscServerAssembler;
import com.work.esc.server.simulator.resources.schemas.EcsServerSchema;
import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EcsServerFacadeImpl implements EcsServerFacade {

  @Autowired
  private EscServerAssembler assembler;

  @Nonnull
  @Override
  public EcsServerSchema getAll() {
    return assembler.to("sample");
  }
}
