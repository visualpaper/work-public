package com.visualpaper.work.deploy.server.tool.resources;

import com.visualpaper.binary.library.core.client.options.WriteOption;
import com.visualpaper.binary.library.core.model.content.Content;
import com.visualpaper.binary.library.core.model.object.Attributes;
import com.visualpaper.work.binary.transfer.context.upload.UploadBinaryContext;
import com.visualpaper.work.binary.transfer.filter.BinaryTransfer;
import com.visualpaper.work.deploy.server.tool.resources.schemas.PostData;

import javax.annotation.Nonnull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

@Path("sample1")
public class Sample1Resource {

    @POST
    @Path("api")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postBinary(@Nonnull PostData data) throws Exception {

        for (int i = 0; i < 10000000L; i++) {
            new PostData(i, "aaaaaaa");
        }
        return Response
                .ok(data)
                .build();
    }
}
