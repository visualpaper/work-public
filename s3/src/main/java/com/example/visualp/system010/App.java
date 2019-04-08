package com.example.visualp.system010;

import com.example.visualp.system010.accessor.s3.S3Accessor;
import com.example.visualp.system010.config.S3Provider;
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

        // Accessor
        bind(S3Provider.class).to(S3Provider.class);
        bind(S3Accessor.class).to(S3Accessor.class);

        // Facade
        bind(StudyFacadeImpl.class).to(StudyFacade.class);

        // Resources
        bind(StudyResources.class).to(StudyResources.class);
      }
    });
  }
}
