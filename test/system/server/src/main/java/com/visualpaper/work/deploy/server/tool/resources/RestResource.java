package com.visualpaper.work.deploy.server.tool.resources;

import com.visualpaper.binary.library.core.client.SStorage;
import com.visualpaper.binary.library.core.client.options.WriteOption;
import com.visualpaper.binary.library.core.model.content.Content;
import com.visualpaper.binary.library.core.model.object.Attributes;
import com.visualpaper.binary.library.core.model.object.Metadata;
import com.visualpaper.work.binary.transfer.context.upload.UploadBinaryContext;
import com.visualpaper.work.binary.transfer.filter.BinaryTransfer;
import com.visualpaper.work.deploy.server.tool.resources.schemas.PostData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Path("rest")
public class RestResource {

  @Inject
  private SStorage storage;

  @Context
  private UploadBinaryContext uploadBinaryContext;

  @POST
  @Path("post")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response post(@Nonnull PostData data) throws Exception {

    return Response
        .ok(data)
        .build();
  }

  @BinaryTransfer(type = BinaryTransfer.Type.UPLOAD)
  @POST
  @Path("postBinary")
  @Consumes(MediaType.APPLICATION_OCTET_STREAM)
  @Produces(MediaType.APPLICATION_JSON)
  public Response postBinary(@Nonnull InputStream data) throws Exception {
    try {
      storage.create(
              Content.from(uploadBinaryContext.getContent()),
              createMetadata(uploadBinaryContext.getContentType(), uploadBinaryContext.getContentLength())
      );
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }

    return Response
        .ok(new PostData(1, "aaa"))
        .build();
  }

  @Nonnull
  private Metadata.ChangeSet createMetadata(
          @Nullable String contentType,
          long contentLength
  ) {
    Metadata.ChangeSet.ChangeSetBuilder builder = Metadata.ChangeSet.builder();

    if (contentType != null) {
      builder.contentType(contentType);
    }
    builder.contentLength(contentLength);

    return builder.build();
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
    try {
      return Response.ok(storage.get(id).readContent().get()).build();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
}
