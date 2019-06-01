package com.example.visualp.system19053101.resources;

import com.example.visualp.system19053101.common.scheduling.ScheduledTaskHolder;
import com.example.visualp.system19053101.facade.StudyFacade;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("healthCheck")
public class StudyResources {

  @Inject
  ScheduledTaskHolder holder;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response healthCheck() throws Exception {

    if (holder.isDone()) {
      return Response
          .serverError()
          .build();
    }
    return Response
        .ok()
        .build();
  }
}
