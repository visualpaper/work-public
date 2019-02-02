package com.visualpaper.ecs.system.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visualpaper.ecs.system.facade.BinaryFacade;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class BinaryResources {

  private static ObjectMapper mapper = new ObjectMapper();

  @Inject
  private BinaryFacade facade;

  @GET
  @Path("ecs-server")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAll() throws Exception {
    return Response
        .ok()
        .entity(mapper.writeValueAsString(facade.getAll()))
        .build();
  }
}
