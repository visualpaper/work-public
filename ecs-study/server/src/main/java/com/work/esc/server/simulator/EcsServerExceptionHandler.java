package com.work.esc.server.simulator;

import com.work.esc.server.simulator.common.LogConst;
import com.work.esc.server.simulator.common.exceptions.EcsServerException;
import com.work.esc.server.simulator.facade.accembler.ErrorAssembler;
import com.work.esc.server.simulator.resources.schemas.ErrorSchema;
import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EcsServerExceptionHandler {

  private static final String LOG_PATTERN = "{} {}";

  private static final Logger INFO_LOGGER = LoggerFactory.getLogger(LogConst.INFO_LOG);
  private static final Logger APP_LOGGER = LoggerFactory.getLogger(EcsServerExceptionHandler.class);

  @Autowired
  private ErrorAssembler assembler;

  @ExceptionHandler(Exception.class)
  public ErrorSchema handleException(
      @Nonnull Exception cause,
      @Nonnull HttpServletResponse response
  ) {
    EcsServerLogCode logCode = toLogCode(cause);
    EcsWebError error = toWebError(cause);

    switch (logCode.getLogLevel()) {
      case ERROR:
        APP_LOGGER.error(LOG_PATTERN, logCode.getCode(), logCode.getMessage());
        break;

      case WARN:
        APP_LOGGER.warn(LOG_PATTERN, logCode.getCode(), logCode.getMessage());
        break;

      default:
        break;
    }
    INFO_LOGGER.info("exception: ", cause);

    response.setStatus(error.getHttpStatus().value());
    return assembler.to(error);
  }

  @Nonnull
  private EcsServerLogCode toLogCode(@Nonnull Throwable cause) {

    if (cause instanceof EcsServerException) {
      return ((EcsServerException) cause).getLogCode();
    } else {
      return EcsServerLogCode.UNEXPECTED;
    }
  }

  @Nonnull
  private EcsWebError toWebError(@Nonnull Throwable cause) {

    if (cause instanceof EcsServerException) {
      return ((EcsServerException) cause).getWebError();
    } else {
      return EcsWebError.UNEXPECTED;
    }
  }
}
