package com.example.visualp.jersey.sandbox;

import com.example.visualp.jersey.sandbox.facade.StudyFacade;
import com.example.visualp.jersey.sandbox.facade.impl.StudyFacadeImpl;
import com.example.visualp.jersey.sandbox.resources.StudyResources;
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

        // Facade
        bind(StudyFacadeImpl.class).to(StudyFacade.class);

        // Resources
        bind(StudyResources.class).to(StudyResources.class);
      }
    });
  }
}
