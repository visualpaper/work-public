package com.example.visualp.system19053101.common.scheduling;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

public class ScheduledTaskRegistrar implements ScheduledTaskHolder {
  private final ScheduledExecutorService executor;
  private ScheduledFuture<?> future;

  public ScheduledTaskRegistrar() {
    this.executor = Executors.newSingleThreadScheduledExecutor();
  }

  public void initialize(@Nonnull Runnable command, long initialDelay, long delay, @Nonnull TimeUnit unit) {
    this.future = executor.scheduleWithFixedDelay(command, initialDelay, delay, unit);
  }

  @Override
  public void shutdown() {
    this.executor.shutdown();
  }

  @Override
  public boolean isDone() {
    return this.future.isDone();
  }
}
