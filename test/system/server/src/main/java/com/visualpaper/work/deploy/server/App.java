package com.visualpaper.work.deploy.server;

import com.visualpaper.binary.library.core.client.SStorage;
import com.visualpaper.work.deploy.server.config.sofa.SStorageAwsProvider;
import com.visualpaper.work.deploy.server.tool.resources.HealthCheckResource;
import javax.ws.rs.ApplicationPath;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class App extends ResourceConfig {

  public App() {
    packages(this.getClass().getPackage().getName());

    AbstractBinder binder = new AbstractBinder() {
      @Override
      protected void configure() {

        // Sofa
        bindFactory(SStorageAwsProvider.class).to(SStorage.class);

        // Resources
        bind(HealthCheckResource.class).to(HealthCheckResource.class);
      }
    };

    register(binder);
  }
}
