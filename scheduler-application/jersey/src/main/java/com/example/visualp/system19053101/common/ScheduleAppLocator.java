package com.example.visualp.system19053101.common;

import static org.glassfish.jersey.internal.inject.Injections.createLocator;

import javax.annotation.Nonnull;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.Binder;

public class ScheduleAppLocator {

  private final ServiceLocator locator;

  public ScheduleAppLocator(@Nonnull Binder binder) {
    this.locator = createLocator(binder);
  }

  @Nonnull
  public <T> T getService(@Nonnull Class<T> clazz) {
    return locator.getService(clazz);
  }
}
