package com.example.visualp.system002.resources;

import com.example.visualp.system002.facade.StudyFacade;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("study")
public class StudyResources {

  @Inject
  private StudyFacade facade;

  @Path("read/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response simpleRead(@PathParam("id") String id) throws Exception {
    facade.studyRead(id);

    return Response
        .noContent()
        .build();
  }

  @Path("count/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response simpleCount(@PathParam("id") String id) throws Exception {
    facade.studyCount(id);

    return Response
        .noContent()
        .build();
  }

  @Path("batchWrite/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response batchWrite(@PathParam("id") String id) throws Exception {
    facade.batchWrite(id);

    return Response
        .noContent()
        .build();
  }

  @Path("add")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response simplePost() throws Exception {
    facade.studyAdd();

    return Response
        .noContent()
        .build();
  }
}
