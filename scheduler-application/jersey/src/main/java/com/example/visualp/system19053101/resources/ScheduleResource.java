package com.example.visualp.system19053101.resources;

import com.example.visualp.system19053101.common.scheduling.ScheduledTaskHolder;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("schedule")
public class ScheduleResource {

  @Inject
  ScheduledTaskHolder holder;

  @GET
  @Path("start")
  @Produces(MediaType.APPLICATION_JSON)
  public Response start() throws Exception {
    holder.initialize();

    return Response
        .ok()
        .build();
  }

  @GET
  @Path("stop")
  @Produces(MediaType.APPLICATION_JSON)
  public Response stop() throws Exception {
    holder.shutdown();

    return Response
        .ok()
        .build();
  }
}
