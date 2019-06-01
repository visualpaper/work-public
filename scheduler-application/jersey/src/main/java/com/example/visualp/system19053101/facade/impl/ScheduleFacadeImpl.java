package com.example.visualp.system19053101.facade.impl;

import com.example.visualp.system19053101.facade.ScheduleFacade;
import javax.annotation.Nonnull;

public class ScheduleFacadeImpl implements ScheduleFacade {

  @Override
  public void add(@Nonnull String cronTab) {
    System.out.println(cronTab);
  }
}
