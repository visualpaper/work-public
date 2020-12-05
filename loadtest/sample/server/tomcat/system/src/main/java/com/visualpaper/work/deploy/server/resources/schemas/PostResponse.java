package com.visualpaper.work.deploy.server.resources.schemas;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponse {

  public String objectId;
}
