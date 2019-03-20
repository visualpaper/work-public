package com.example.visualp.system002;

import com.example.visualp.system002.accessor.dynamodb.DynamoDbAccessor;
import com.example.visualp.system002.accessor.dynamodbmapper.DynamoDbMapperAccessor;
import com.example.visualp.system002.facade.StudyFacade;
import com.example.visualp.system002.facade.impl.StudyFacadeImpl;
import com.example.visualp.system002.resources.StudyResources;
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
        bind(DynamoDbAccessor.class).to(DynamoDbAccessor.class);
        bind(DynamoDbMapperAccessor.class).to(DynamoDbMapperAccessor.class);

        // Facade
        bind(StudyFacadeImpl.class).to(StudyFacade.class);

        // Resources
        bind(StudyResources.class).to(StudyResources.class);
      }
    });
  }
}
