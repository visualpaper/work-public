package com.example.visualp.system010;

import com.example.visualp.system010.facade.StudyFacade;
import com.example.visualp.system010.facade.impl.StudyFacadeImpl;
import com.example.visualp.system010.resources.StudyResources;
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

        // Facade
        bind(StudyFacadeImpl.class).to(StudyFacade.class);

        // Resources
        bind(StudyResources.class).to(StudyResources.class);
      }
    });
  }
}
