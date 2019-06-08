package com.example.visualp.system19053101;

import com.example.visualp.system19053101.common.scheduling.ScheduledTaskHolder;
import com.example.visualp.system19053101.common.scheduling.ScheduledTaskRegistrar;
import com.example.visualp.system19053101.resources.HealthCheckResource;
import com.example.visualp.system19053101.resources.ScheduleResource;
import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class App extends ResourceConfig {

  private ScheduledTaskRegistrar scheduledTaskRegistrar;

  public App() {
    scheduledTaskRegistrar = new ScheduledTaskRegistrar();
    packages(this.getClass().getPackage().getName());

    register(new AbstractBinder() {
      @Override
      protected void configure() {

        // Facade

        // Resources
        bind(HealthCheckResource.class).to(HealthCheckResource.class);
        bind(ScheduleResource.class).to(ScheduleResource.class);

        bind(scheduledTaskRegistrar).to(ScheduledTaskHolder.class);
      }
    });
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
