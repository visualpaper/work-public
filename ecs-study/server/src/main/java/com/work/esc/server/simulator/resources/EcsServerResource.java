package com.work.esc.server.simulator.resources;

import com.work.esc.server.simulator.facade.EcsServerFacade;
import com.work.esc.server.simulator.resources.schemas.EcsServerSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ecs-server")
public class EcsServerResource {

  @Autowired
  private EcsServerFacade facade;

  @CrossOrigin(origins = "*")
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public EcsServerSchema getAll() throws Exception {
    return facade.getAll();
  }
}
