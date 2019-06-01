package com.example.visualp.system19053101.facade.impl;

import com.example.visualp.system19053101.facade.SnapshotFacade;
import java.util.Date;
import javax.annotation.Nonnull;

public class SnapshotFacadeImpl implements SnapshotFacade {

  @Override
  public void create(@Nonnull Date time) {
    System.out.println(time);
  }
}
