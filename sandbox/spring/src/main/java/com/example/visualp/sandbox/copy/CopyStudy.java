package com.example.visualp.sandbox.copy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CopyStudy {

  public void execute() {
    List<String> sample = new ArrayList<>();

    sample.add("aaa");
    sample.add("bbb");

    List<String> copy = new ArrayList<>(sample);

    copy.add("ccc");

    // 検証1: String 等であればディープになっている
    System.out.println(sample);
    System.out.println(copy);

    List<Entity> sampleEntities = new ArrayList<>();

    sampleEntities.add(new Entity("aaa"));
    sampleEntities.add(new Entity("bbb"));

    List<Entity> copyEntities = new ArrayList<>(sampleEntities);
    copyEntities.add(new Entity("ccc"));

    // 検証2: なんらかのクラスの場合、(リスト自体は別物として生成されるが) 各々のインスタンスの参照はディープではない
    System.out.println(sampleEntities);
    System.out.println(copyEntities);
  }
}
