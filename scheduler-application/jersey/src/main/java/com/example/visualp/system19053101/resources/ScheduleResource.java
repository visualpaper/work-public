package com.example.visualp.system19053101.resources;

import com.example.visualp.system19053101.facade.ScheduleFacade;
import com.example.visualp.system19053101.resources.schema.ScheduleSchema;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("schedule")
public class ScheduleResource {

  @Inject
  private ScheduleFacade facade;

  @POST
  @Path("add")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response schedule(ScheduleSchema schema) throws Exception {

    facade.add(schema.getCronTab());

    return Response
        .ok()
        .build();
  }
}
