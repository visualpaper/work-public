package com.example.visualp.jersey.sandbox.resources;

import com.example.visualp.jersey.sandbox.facade.StudyFacade;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class StudyResources {

  @Inject
  private StudyFacade facade;

  @Path("parallelStreamTest")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response parallelStreamTest() throws Exception {
    facade.parallelStreamTest();

    return Response
        .noContent()
        .build();
  }
}
