package com.example.visualp.system19053101.facade;

import com.example.visualp.system19053101.resources.schema.ScheduleSchema;
import javax.annotation.Nonnull;

public interface ScheduleFacade {

  void add(@Nonnull String cronTab);
}
