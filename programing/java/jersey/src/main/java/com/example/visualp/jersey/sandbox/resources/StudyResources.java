package com.example.visualp.jersey.sandbox.resources;

import com.example.visualp.jersey.sandbox.facade.StudyFacade;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

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

  @Path("bitTransfer")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response bitTransfer() throws Exception {
    facade.bitTransfer();

    return Response
        .noContent()
        .build();
  }

  @Path("urlEncode")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response urlEncode(@Context ContainerRequestContext context) throws Exception {

    // Fragment は Http リクエストでは飛ばない (要求内に入らない)
    // getPath() + "?" + getQuery() で Decode 後の値が取得可能。(内部で Encode された値が Decode される)
    System.out.println(context.getUriInfo().getRequestUri());
    return Response
            .noContent()
            .build();
  }
}
