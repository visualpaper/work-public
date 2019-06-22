package com.example.visualp.system19053101;

import com.example.visualp.system19053101.accessor.dynamodb.DynamoDbAccessor;
import com.example.visualp.system19053101.common.ScheduleAppLocator;
import com.example.visualp.system19053101.common.schedule.ScheduleTask;
import com.example.visualp.system19053101.common.schedule.ScheduledTaskHolder;
import com.example.visualp.system19053101.common.schedule.ScheduledTaskRegistrar;
import com.example.visualp.system19053101.resources.HealthCheckResource;
import com.example.visualp.system19053101.resources.ScheduleResource;
import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class App extends ResourceConfig {

  private final ScheduleAppLocator locator;

  private ScheduledTaskRegistrar scheduledTaskRegistrar;

  public App() {
    packages(this.getClass().getPackage().getName());

    AbstractBinder binder = new AbstractBinder() {
      @Override
      protected void configure() {

        // Accessor
        bind(DynamoDbAccessor.class).to(DynamoDbAccessor.class);

        // Facade

        // Schedule
        bind(ScheduleTask.class).to(ScheduleTask.class);

        // Resources
        bind(HealthCheckResource.class).to(HealthCheckResource.class);
        bind(ScheduleResource.class).to(ScheduleResource.class);
      }
    };

    register(binder);

    this.locator = new ScheduleAppLocator(binder);
    this.scheduledTaskRegistrar = new ScheduledTaskRegistrar(locator);

    binder.bind(scheduledTaskRegistrar)
        .to(ScheduledTaskHolder.class);

  }

  @PostConstruct
  void postConstruct() {

    Runtime.getRuntime().addShutdownHook(
        new Thread(() -> {
          scheduledTaskRegistrar.shutdown();
        })
    );

    // 時間とかは Config で差し込む。
    scheduledTaskRegistrar.initialize();
  }
}
