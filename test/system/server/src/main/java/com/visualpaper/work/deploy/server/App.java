package com.visualpaper.work.deploy.server;

import com.visualpaper.binary.library.core.client.SStorage;
import com.visualpaper.work.binary.transfer.di.BinaryTransferBinder;
import com.visualpaper.work.binary.transfer.filter.BinaryTransferFilter;
import com.visualpaper.work.deploy.server.config.SStorageAwsProvider;
import com.visualpaper.work.deploy.server.tool.resources.HealthCheckResource;
import com.visualpaper.work.deploy.server.tool.resources.RestResource;
import com.visualpaper.work.deploy.server.tool.resources.Sample1Resource;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class App extends ResourceConfig {

  public App() {
    packages(this.getClass().getPackage().getName());

    AbstractBinder binder = new AbstractBinder() {
      @Override
      protected void configure() {

        // Binary-Library 用
        bindFactory(SStorageAwsProvider.class).to(SStorage.class);

        // Resources
        bind(RestResource.class).to(RestResource.class);
        bind(HealthCheckResource.class).to(HealthCheckResource.class);

        bind(Sample1Resource.class).to(Sample1Resource.class);
      }
    };

    register(MultiPartFeature.class);
    register(binder);
    register(JacksonFeature.class);

    // Binary-Transfer 用
    register(new BinaryTransferBinder());
    register(BinaryTransferFilter.class);
  }
}
