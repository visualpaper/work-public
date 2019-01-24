package com.work.esc.server.simulator.facade;

import com.work.esc.server.simulator.resources.schemas.EcsServerSchema;
import javax.annotation.Nonnull;
import org.springframework.stereotype.Service;

@Service
public interface EcsServerFacade {

  @Nonnull
  EcsServerSchema getAll();
}
