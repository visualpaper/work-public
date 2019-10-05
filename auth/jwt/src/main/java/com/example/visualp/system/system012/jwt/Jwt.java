package com.example.visualp.system.system012.jwt;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Jwt {
  private final String header;
  private final String payload;
  private final String sign;
}
