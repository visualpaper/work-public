package com.example.visualp.system19053101;

import com.example.visualp.system19053101.accessor.dynamodb.DynamoDbAccessor;
import com.example.visualp.system19053101.common.schedule.ScheduleTask;
import com.example.visualp.system19053101.common.schedule.ScheduledTaskHolder;
import com.example.visualp.system19053101.common.schedule.ScheduledTaskRegistrar;
import com.example.visualp.system19053101.resources.HealthCheckResource;
import com.example.visualp.system19053101.resources.ScheduleResource;
import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.ServiceLocatorProvider;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class App extends ResourceConfig {

  private final ServiceLocator locator;

  private ScheduledTaskRegistrar scheduledTaskRegistrar;

  @Inject
  public App(@Nonnull ServiceLocator locator) {
    packages(this.getClass().getPackage().getName());

    register(new AbstractBinder() {
      @Override
      protected void configure() {

        // Accessor
        bind(DynamoDbAccessor.class).to(DynamoDbAccessor.class);

        // Facade

        // Schedule
        bind(ScheduleTask.class).to(ScheduleTask.class);
        bind(scheduledTaskRegistrar).to(ScheduledTaskHolder.class);

        // Resources
        bind(HealthCheckResource.class).to(HealthCheckResource.class);
        bind(ScheduleResource.class).to(ScheduleResource.class);
      }
    });
    this.locator = locator;
  }

  @PostConstruct
  void postConstruct() {
    scheduledTaskRegistrar = new ScheduledTaskRegistrar();

    Runtime.getRuntime().addShutdownHook(
        new Thread(() -> {
          scheduledTaskRegistrar.shutdown();
        })
    );

    // 時間とかは Config で差し込む。
    //scheduledTaskRegistrar.initialize();
  }
}
