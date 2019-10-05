package com.example.visualp.system003;

import java.util.List;

public class Sample {
  public String objectId;
  public String uploadId;
  public List<IncludedSample> parts;

  public static class IncludedSample {
    public Integer number;
    public String etag;
  }
}
