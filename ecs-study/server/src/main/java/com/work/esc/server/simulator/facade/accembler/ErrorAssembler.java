package com.work.esc.server.simulator.facade.accembler;

import com.work.esc.server.simulator.EcsWebError;
import com.work.esc.server.simulator.resources.schemas.ErrorSchema;
import javax.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class ErrorAssembler {

  @Nonnull
  public ErrorSchema to(@Nonnull EcsWebError error) {
    ErrorSchema schema = new ErrorSchema();

    schema.setCode(error.getCode());
    schema.setMessage(error.getMessage());
    return schema;
  }
}
