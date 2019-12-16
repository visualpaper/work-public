package com.example.visualp.jersey.sandbox.facade.impl;

import com.example.visualp.jersey.sandbox.facade.StudyFacade;
import com.google.common.primitives.Longs;
import java.util.Arrays;
import java.util.stream.IntStream;

public class StudyFacadeImpl implements StudyFacade {

  // https://miyakawataku.hatenablog.com/entry/20171228/1514472588
  // ↑ IO が絡む時点で ThreadPoolExecutor を独自でやったほうが良さそう。
  //    つまり、Executors.newFixedThreadPool で作った ThreadPool を利用すれば良い。
  //    ので、外部ストレージから並列でデータを取得するサンプルを作れば OK
  @Override
  public void parallelStreamTest() {
      IntStream.rangeClosed(0, 10)
          .parallel()
          .forEach(v -> System.out.println(Thread.currentThread().getId()));
  }

  // 文字の byte と数値そのものを byte にした場合とでは、別物。
  @Override
  public void bitTransfer() {
    String idStr = "1234567890";
    Long id = 1234567890L;

    System.out.println(Arrays.toString(idStr.getBytes()));
    System.out.println(Arrays.toString(Longs.toByteArray(1234567890L)));
  }
}
