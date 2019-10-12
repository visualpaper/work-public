package com.visualpaper.work.deploy.server;

import com.visualpaper.work.deploy.server.tool.resources.HealthCheckResource;
import com.visualpaper.work.deploy.server.tool.resources.RestResource;
import javax.ws.rs.ApplicationPath;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class App extends ResourceConfig {

  public App() {
    packages(this.getClass().getPackage().getName());

    AbstractBinder binder = new AbstractBinder() {
      @Override
      protected void configure() {

        // Resources
        bind(RestResource.class).to(RestResource.class);
        bind(HealthCheckResource.class).to(HealthCheckResource.class);
      }
    };

    register(MultiPartFeature.class);
    register(binder);
    register(JacksonFeature.class);
  }
}
