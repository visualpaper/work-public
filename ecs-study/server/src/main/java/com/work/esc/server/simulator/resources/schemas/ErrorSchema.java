package com.work.esc.server.simulator.resources.schemas;

import lombok.Data;

@Data
public class ErrorSchema {

  private String code;
  private String message;
}
