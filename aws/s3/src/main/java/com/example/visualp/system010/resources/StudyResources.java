package com.example.visualp.system010.resources;

import com.example.visualp.system010.facade.StudyFacade;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("study")
public class StudyResources {

  @Context
  private ContainerRequestContext context;

  @Inject
  private StudyFacade facade;

  @Path("location")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response location() throws Exception {
    facade.getLocation();

    return Response
        .ok()
        .build();
  }

  @Path("base64Binary")
  @GET
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response getBase64Binary() throws Exception {
    return Response
        .ok(facade.getBase64Binary())
        .build();
  }

  @Path("base64Binary")
  @PUT
  @Consumes(MediaType.APPLICATION_OCTET_STREAM)
  public Response putBase64Binary() throws Exception {

    facade.putBase64Binary(
        context.getEntityStream(),
        context.getLength()
    );

    return Response
        .ok()
        .build();
  }
}
