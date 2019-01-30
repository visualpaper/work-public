package com.visualpaper.ecs.system;

import com.visualpaper.ecs.system.facade.BinaryFacade;
import com.visualpaper.ecs.system.facade.assembler.BinaryAssembler;
import com.visualpaper.ecs.system.facade.impl.BinaryFacadeImpl;
import com.visualpaper.ecs.system.resources.BinaryResources;
import com.visualpaper.ecs.system.resources.CORSFilter;
import javax.ws.rs.ApplicationPath;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/app")
public class App extends ResourceConfig {

  public App() {
    packages(this.getClass().getPackage().getName());

    register(new AbstractBinder() {
      @Override
      protected void configure() {

        // Facade
        bind(BinaryFacadeImpl.class).to(BinaryFacade.class);

        // Assembler
        bind(BinaryAssembler.class).to(BinaryAssembler.class);

        // Resources
        bind(BinaryResources.class).to(BinaryResources.class);
      }
    });

    register(CORSFilter.class);
  }
}
