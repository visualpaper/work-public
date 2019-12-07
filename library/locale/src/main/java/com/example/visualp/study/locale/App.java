package com.example.visualp.study.locale;

import com.example.visualp.study.locale.resources.StudyResources;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.ws.rs.ApplicationPath;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class App extends ResourceConfig {

  public static final ExecutorService SERVICE = Executors.newFixedThreadPool(1);

  public App() {

    packages(this.getClass().getPackage().getName());

    register(new AbstractBinder() {
      @Override
      protected void configure() {

        // Resources
        bind(StudyResources.class).to(StudyResources.class);
      }
    });
  }
}
