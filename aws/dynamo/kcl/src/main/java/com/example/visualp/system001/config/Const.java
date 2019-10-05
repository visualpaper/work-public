package com.example.visualp.system001.config;

import com.amazonaws.regions.Regions;

public class Const {

  // lease テーブル作成権限が必要。
  public static final String AWS_ACCESS_KEY = "";
  public static final String AWS_SECRET_KEY = "";

  public static final Regions REGION = Regions.AP_NORTHEAST_1;

  // Stream を発行する DynamoDB Stream ARN 名
  public static final String DYNAMO_STREAM_ARN = "";

  // lease テーブル名
  public static final String DYNAMO_STREAM_LEASE_NAME = "system001-dynamodb-lease";

  // workerId 名
  public static final String KCL_WORKER_ID = "streams-sample-worker";
}
