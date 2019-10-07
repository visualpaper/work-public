package com.example.visualp.jersey.sandbox.facade.impl;

import com.example.visualp.jersey.sandbox.facade.StudyFacade;
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
}
