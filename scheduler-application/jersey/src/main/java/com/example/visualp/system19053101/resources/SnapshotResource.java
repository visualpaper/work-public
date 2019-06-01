package com.example.visualp.system19053101.resources;

import com.example.visualp.system19053101.facade.SnapshotFacade;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("snapshot")
public class SnapshotResource {

  @Inject
  private SnapshotFacade facade;

  @POST
  @Path("create")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response snapshot(@QueryParam("time") String time) throws Exception {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    facade.create(df.parse(time));

    return Response
        .ok()
        .build();
  }
}
