package com.visualpaper.work.deploy.server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("healthCheck")
public class HealthCheckResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response healthCheck() throws Exception {

    return Response
        .ok()
        .build();
  }
}
