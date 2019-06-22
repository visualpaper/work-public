package com.example.visualp.system19053101.common.schedule;

import com.example.visualp.system19053101.common.ScheduleAppLocator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

// [メモ] 正しく Executor はクローズされている。(変なスレッドが残りっぱになっていないことは確認済み)
public class ScheduledTaskRegistrar implements ScheduledTaskHolder {

  private final ScheduleAppLocator locator;
  private ScheduledExecutorService executor;

  public ScheduledTaskRegistrar(@Nonnull ScheduleAppLocator locator) {
    this.locator = locator;
  }

  public void initialize() {

    if (executor != null && !executor.isTerminated()) {
      throw new IllegalStateException();
    }
    executor = Executors.newSingleThreadScheduledExecutor();
    executor.scheduleWithFixedDelay(() -> {
      try {
        locator.getService(ScheduleTask.class).execute();
      } catch (Throwable e) {
        // ロギングするだけで抑える
        e.printStackTrace();
      }
    }, 0, 3, TimeUnit.SECONDS);
  }


  @Override
  public void shutdown() {

    // shutdown では interrpted 状態にならない。
    // (新しいタスクの受付をしないだけで、稼働している Task は正常/異常終了まで走り続ける)
    // ※ executor は終了していない点に注意
    executor.shutdown();

    // ここで executor を終了する。
    // 10 秒としている理由は、本 Schedule で稼働する Task はその時間見込みで正常/異常終了まで
    // たどり着ける仕組みとしているためである。
    try {
      if (executor.awaitTermination(10, TimeUnit.SECONDS)) {
        System.out.println("ALL TASKS END");
      }
    } catch (InterruptedException e) {
      // タスクが ↑ で指定した時間内に全て完了しなかった。
      throw new IllegalStateException(e);
    }
  }

  @Override
  public boolean isShutdown() {

    if (executor == null) {
      return true;
    }
    return executor.isTerminated();
  }
}
