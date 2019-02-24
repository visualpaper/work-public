package com.example.visualp.system002.resources;

import com.example.visualp.system002.facade.StudyFacade;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("study")
public class StudyResources {

  @Inject
  private StudyFacade facade;

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
