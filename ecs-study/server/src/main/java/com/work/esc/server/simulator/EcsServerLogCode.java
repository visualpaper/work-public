package com.work.esc.server.simulator;

import javax.annotation.Nonnull;
import org.springframework.boot.logging.LogLevel;

public enum EcsServerLogCode {
  UNEXPECTED(
      "0001",
      LogLevel.ERROR,
      "予期しない例外が発生しました。"
  ),
  ;

  private String code;
  private LogLevel logLevel;
  private String message;

  EcsServerLogCode(
      @Nonnull String code,
      @Nonnull LogLevel logLevel,
      @Nonnull String message
  ) {
    this.code = code;
    this.logLevel = logLevel;
    this.message = message;
  }

  @Nonnull
  public String getCode() {
    return code;
  }

  @Nonnull
  public LogLevel getLogLevel() {
    return logLevel;
  }

  @Nonnull
  public String getMessage() {
    return message;
  }
}
