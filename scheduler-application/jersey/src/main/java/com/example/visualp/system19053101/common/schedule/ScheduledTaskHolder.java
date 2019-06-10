package com.example.visualp.system19053101.common.schedule;

public interface ScheduledTaskHolder {

  void initialize();

  void shutdown();

  boolean isShutdown();
}
