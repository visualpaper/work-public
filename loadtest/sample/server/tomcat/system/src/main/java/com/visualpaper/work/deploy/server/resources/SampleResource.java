package com.visualpaper.work.deploy.server.resources;

import com.visualpaper.work.binary.transfer.context.upload.UploadBinaryContext;
import com.visualpaper.work.binary.transfer.filter.BinaryTransfer;
import com.visualpaper.work.deploy.server.facade.SampleFacade;
import com.visualpaper.work.deploy.server.resources.schemas.PostData;
import com.visualpaper.work.deploy.server.resources.schemas.PostResponse;
import java.io.InputStream;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("rest")
public class SampleResource {

  @Context
  private UploadBinaryContext context;

  @Inject
  private SampleFacade facade;

  @POST
  @Path("post")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response post(@Nonnull PostData data) throws Exception {

    return Response
        .ok(data)
        .build();
  }

  @DELETE
  @Path("deleteBinary/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteBinary(@PathParam("id") String id) throws Exception {

    facade.deleteBinary(id);
    return Response
        .ok()
        .build();
  }


  @BinaryTransfer(type = BinaryTransfer.Type.UPLOAD)
  @POST
  @Path("postBinary")
  @Consumes(MediaType.APPLICATION_OCTET_STREAM)
  @Produces(MediaType.APPLICATION_JSON)
  public Response postBinary(@Nonnull InputStream data) throws Exception {

    String objectId = facade.postBinary(
        context.getContent(),
        context.getContentType(),
        context.getContentLength()
    );
    PostResponse response = new PostResponse();

    response.setObjectId(objectId);
    return Response
        .ok(response)
        .build();
  }

  @GET
  @Path("get")
  @Produces(MediaType.APPLICATION_JSON)
  public Response get() throws Exception {

    return Response
        .ok(new PostData(1, "aaaaaaaa"))
        .build();
  }

  @GET
  @Path("getBinary/{id}")
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response getBinary(@PathParam("id") String id) throws Exception {

    return Response
        .ok(facade.getBinary(id))
        .build();
  }
}
