package com.example.visualp.system011.resources;

import com.example.visualp.system011.facade.StudyFacade;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("study")
public class StudyResources {

  @Inject
  private StudyFacade facade;

  @Path("sample")
  @GET
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response sample() throws Exception {

    // [groovy ファイルで Java を使用する]
    // 1. クラスローダに設定
    GroovyClassLoader classLoader = new GroovyClassLoader();

    classLoader.setResourceLoader(
        s -> getClass()
            .getClassLoader()
            .getResource(s)
    );

    // 2. クラスローダーから groovy ファイル読み込み
    Class proxyClass = classLoader.loadClass("MyHttp.groovy");

    // 3. インスタンス生成して実行
    GroovyObject groovyObject = (GroovyObject) proxyClass.newInstance();
    groovyObject.setProperty("url", "http://localhost:8080/study/sampleGet");

    Response response = (Response)groovyObject.invokeMethod("request", new Object[] {});

    return response;
  }

  @Path("sampleGet")
  @GET
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response sampleGet() throws Exception {
    return Response
        .ok(facade.sampleGet())
        .build();
  }
}
