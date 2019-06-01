package com.example.visualp.system19053101;

import com.example.visualp.system19053101.common.scheduling.ScheduleTask;
import com.example.visualp.system19053101.common.scheduling.ScheduledTaskHolder;
import com.example.visualp.system19053101.common.scheduling.ScheduledTaskRegistrar;
import com.example.visualp.system19053101.facade.ScheduleFacade;
import com.example.visualp.system19053101.facade.SnapshotFacade;
import com.example.visualp.system19053101.facade.impl.ScheduleFacadeImpl;
import com.example.visualp.system19053101.facade.impl.SnapshotFacadeImpl;
import com.example.visualp.system19053101.resources.HealthCheckResource;
import com.example.visualp.system19053101.resources.ScheduleResource;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class App extends ResourceConfig {

  private ScheduledTaskRegistrar registrar;

  public App() {
    registrar = new ScheduledTaskRegistrar();

    packages(this.getClass().getPackage().getName());

    register(new AbstractBinder() {
      @Override
      protected void configure() {

        // Facade
        bind(ScheduleFacadeImpl.class).to(ScheduleFacade.class);
        bind(SnapshotFacadeImpl.class).to(SnapshotFacade.class);

        // Resources
        bind(HealthCheckResource.class).to(HealthCheckResource.class);
        bind(ScheduleResource.class).to(ScheduleResource.class);

        bind(registrar).to(ScheduledTaskHolder.class);
      }
    });

    Runtime.getRuntime().addShutdownHook(new Thread(registrar::shutdown));
  }

  @PostConstruct
  void postConstruct() {

    // 時間とかは Config で差し込む。
    registrar.initialize(
        new ScheduleTask(),
        0,
        3,
        TimeUnit.SECONDS
    );
  }
}
