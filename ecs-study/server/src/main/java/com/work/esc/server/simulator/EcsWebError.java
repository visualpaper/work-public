package com.work.esc.server.simulator;

import javax.annotation.Nonnull;
import org.springframework.http.HttpStatus;

public enum EcsWebError {
  UNEXPECTED(
      "0001",
      "予期せぬエラーが発生しました",
      HttpStatus.INTERNAL_SERVER_ERROR
  ),
  ;

  private String code;
  private String message;
  private HttpStatus httpStatus;

  EcsWebError(
      @Nonnull String code,
      @Nonnull String message,
      @Nonnull HttpStatus httpStatus
  ) {
    this.code = code;
    this.message = message;
    this.httpStatus = httpStatus;
  }

  @Nonnull
  public String getCode() {
    return code;
  }

  @Nonnull
  public String getMessage() {
    return message;
  }

  @Nonnull
  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
