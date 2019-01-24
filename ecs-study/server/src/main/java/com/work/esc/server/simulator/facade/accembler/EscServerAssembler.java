package com.work.esc.server.simulator.facade.accembler;

import com.work.esc.server.simulator.resources.schemas.EcsServerSchema;
import javax.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class EscServerAssembler {


  @Nonnull
  public EcsServerSchema to(@Nonnull String name) {
    EcsServerSchema schema = new EcsServerSchema();

    schema.setName(name);
    return schema;
  }
}
