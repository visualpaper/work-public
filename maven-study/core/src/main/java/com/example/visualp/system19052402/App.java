package com.example.visualp.system19052402;

import com.example.visualp.system19052402.facade.StudyFacade;
import com.example.visualp.system19052402.facade.impl.StudyFacadeImpl;
import com.example.visualp.system19052402.resources.StudyResources;
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
