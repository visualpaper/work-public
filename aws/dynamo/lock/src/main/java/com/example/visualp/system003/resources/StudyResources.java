package com.example.visualp.system003.resources;

import com.example.visualp.system003.accessor.dynamodbmapper.DynamoDbMapperAccessor;
import com.example.visualp.system003.accessor.dynamodbmapper.entity.Item;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("study")
public class StudyResources {

  @Inject
  DynamoDbMapperAccessor accessor;

  @Path("update/{id}")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  public Response simpleRead(@PathParam("id") String id) throws Exception {
//    Item item = accessor.get(id);
    accessor.update(
            id,
            UUID.randomUUID().toString(),
            0
    );

    return Response
        .noContent()
        .build();
  }
}
