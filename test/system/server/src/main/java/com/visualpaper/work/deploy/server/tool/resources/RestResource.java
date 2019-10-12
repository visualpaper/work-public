package com.visualpaper.work.deploy.server.tool.resources;

import com.visualpaper.work.deploy.server.tool.resources.schemas.PostData;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import org.apache.commons.io.IOUtils;

@Path("rest")
public class RestResource {

  @POST
  @Path("post")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response post(@Nonnull PostData data) throws Exception {

    return Response
        .ok(data)
        .build();
  }

  @POST
  @Path("postBinary")
  @Consumes(MediaType.APPLICATION_OCTET_STREAM)
  @Produces(MediaType.APPLICATION_JSON)
  public Response postBinary(@Nonnull InputStream data) throws Exception {
    return Response
        .ok(new PostData(1, String.valueOf(IOUtils.toByteArray(data).length)))
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
  @Path("getBinary")
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response getBinary() throws Exception {
    return Response
        .ok(new StreamingOutput() {
          @Override
          public void write(OutputStream output) throws IOException, WebApplicationException {
            for (int i = 0; i < 1000; i++) {
              output.write(i);
            }
          }
        })
        .build();
  }
}
