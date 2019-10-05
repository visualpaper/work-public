package com.example.visualp.system001;

import com.amazonaws.services.kinesis.clientlibrary.lib.worker.Worker;
import com.example.visualp.system001.config.WorkerFactory;
import javax.ws.rs.ApplicationPath;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class App extends ResourceConfig {

  private boolean onBoot = false;

  public App() {

    packages(this.getClass().getPackage().getName());

    register(new AbstractBinder() {
      @Override
      protected void configure() {
      }
    });
    bootStrap();
  }

  void bootStrap() {

    if (onBoot) {
      return;
    }
    onBoot = true;

    Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

    Thread.setDefaultUncaughtExceptionHandler(
        (thread, exception) ->
            exception.printStackTrace()
    );

    Worker worker = WorkerFactory.create();
    worker.run();
  }

  void shutdown() {

  }
}
