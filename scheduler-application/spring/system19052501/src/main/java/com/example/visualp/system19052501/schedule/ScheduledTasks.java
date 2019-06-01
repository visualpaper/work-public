package com.example.visualp.system19052501.schedule;

import static java.time.OffsetDateTime.now;

import org.apache.tomcat.util.net.NioBlockingSelector;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

  /*
  // 前回の実行完了時刻から5秒後に実行する（初回はアプリケーション起動直後に実行する）
  @Scheduled(fixedDelay = 5000)
  public void task1() {
    System.out.println("task1");
  }

  // 前回の実行開始時刻から5秒後に実行する（初回はアプリケーション起動直後に実行する）
  @Scheduled(fixedRate = 5000)
  public void task2() {
    System.out.println("task2");
  }

  // アプリケーション起動から1秒後に実行され、その次からは前回の実行開始時刻から5秒後に実行する
  @Scheduled(initialDelay=1000, fixedRate=5000)
  public void task3() {
    System.out.println("task3");
  }
  */

  // 月曜日から金曜日の間、5秒間隔で実行する
  @Scheduled(cron="*/5 * * * * MON-FRI")
  public void task4() {
    System.out.println(now() + "Run task A. ThreadId:" + Thread.currentThread().getId());
  }
}
