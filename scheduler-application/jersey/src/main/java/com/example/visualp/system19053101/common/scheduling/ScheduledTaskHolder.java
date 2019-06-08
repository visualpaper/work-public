package com.example.visualp.system19053101.common.scheduling;

public interface ScheduledTaskHolder {

  void initialize();

  void shutdown();

  boolean isShutdown();
}
