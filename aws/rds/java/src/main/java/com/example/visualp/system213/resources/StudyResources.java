package com.example.visualp.system213.resources;

import com.example.visualp.system213.facade.StudyFacade;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("rds")
public class StudyResources {

  @Inject
  private StudyFacade facade;

  @Path("read/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response read(@PathParam("id") int id) throws Exception {
    return Response
        .ok(facade.read(id))
        .build();
  }

  @Path("update")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  public Response update() throws Exception {
    return Response
        .ok(facade.update())
        .build();
  }
}
