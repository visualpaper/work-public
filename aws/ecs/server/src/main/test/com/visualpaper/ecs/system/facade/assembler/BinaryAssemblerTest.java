package com.visualpaper.ecs.system.facade.assembler;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.Test;

public class BinaryAssemblerTest {

  @Test
  public void test() {

    final SimpleDateFormat sdf =
        new SimpleDateFormat("EEE, yyyy/MM/dd HH:mm:ss:sss");

    TimeZone.setDefault(TimeZone.getTimeZone("CST"));
    sdf.setTimeZone(TimeZone.getTimeZone("CST"));
    System.out.println("JMT time: " + sdf.format(new Date().getTime()));

    TimeZone.setDefault(TimeZone.getTimeZone("CST"));
    sdf.setTimeZone(TimeZone.getTimeZone("CST"));
    System.out.println("GMT time: " + sdf.format(new Date().getTime() - TimeZone.getDefault().getOffset(new Date().getTime())));
  }
}