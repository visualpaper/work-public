package com.work.esc.server.simulator.common.exceptions;

import com.work.esc.server.simulator.EcsServerLogCode;
import com.work.esc.server.simulator.EcsWebError;
import javax.annotation.Nonnull;

public class EcsServerException extends Exception {

  private final EcsServerLogCode logCode;
  private final EcsWebError error;

  public EcsServerException(@Nonnull EcsServerLogCode logCode, @Nonnull EcsWebError error) {
    super();
    this.logCode = logCode;
    this.error = error;
  }

  public EcsServerException(@Nonnull EcsServerLogCode logCode, @Nonnull EcsWebError error,
      @Nonnull Throwable cause) {
    super(cause);
    this.logCode = logCode;
    this.error = error;
  }

  @Nonnull
  public EcsServerLogCode getLogCode() {
    return logCode;
  }

  @Nonnull
  public EcsWebError getWebError() {
    return error;
  }
}
