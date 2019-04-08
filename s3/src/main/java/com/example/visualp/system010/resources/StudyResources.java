package com.example.visualp.system010.resources;

import com.example.visualp.system010.facade.StudyFacade;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("study")
public class StudyResources {

  @Inject
  private StudyFacade facade;

  @Path("location")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response s3Get() throws Exception {
    facade.getLocation();

    return Response
        .ok()
        .build();
  }
}
