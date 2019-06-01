package com.example.visualp.system19053101.common.scheduling;

public class ScheduleTask implements Runnable {

  @Override
  public void run() {

    try {
      // jersey client で 非同期 API (snapshot) コール
      System.out.println("IN");
    } catch (Throwable e) {
      // ロギングし、Task は終わらせない。
    }
  }
}
