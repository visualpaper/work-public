package com.example.visualp.system19053101.facade.impl;

import com.example.visualp.system19053101.facade.ScheduleFacade;
import java.text.ParseException;
import javax.annotation.Nonnull;
import org.quartz.CronExpression;

public class ScheduleFacadeImpl implements ScheduleFacade {

  @Override
  public void add(@Nonnull String cronTab) {
    CronExpression expression;

    try {
      // 秒未サポートなら、先頭に "* " を追加すれば OK
      expression = new CronExpression(cronTab);
    } catch (ParseException e) {
      throw new IllegalArgumentException(e);
    }
    // この時点で cron tab 登録可能なものなので dynamodb に登録して OK
  }
}
