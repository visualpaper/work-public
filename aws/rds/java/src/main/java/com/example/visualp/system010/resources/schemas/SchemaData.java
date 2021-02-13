package com.example.visualp.system010.resources.schemas;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchemaData {

  public Integer id;
  public String value;
}
