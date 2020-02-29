package com.example.visualp.system003;

import com.example.visualp.system003.accessor.dynamodbmapper.DynamoDbMapperAccessor;
import com.example.visualp.system003.resources.StudyResources;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class App extends ResourceConfig {

  private boolean onBoot = false;

  public App() {

    packages(this.getClass().getPackage().getName());

    register(new AbstractBinder() {
      @Override
      protected void configure() {

        // Accessor
        bind(DynamoDbMapperAccessor.class).to(DynamoDbMapperAccessor.class);

        // Resources
        bind(StudyResources.class).to(StudyResources.class);
      }
    });
  }
}
