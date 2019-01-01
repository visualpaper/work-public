package com.example.visualp.system002.config;

import com.amazonaws.regions.Regions;

public class Const {

  // lease テーブル作成権限が必要。
  public static final String AWS_ACCESS_KEY = "AKIAJQTSHBEL7CT5FY7Q";
  public static final String AWS_SECRET_KEY = "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";

  public static final Regions REGION = Regions.AP_NORTHEAST_1;

  public static final String STANDARD_QUEUE_URL = "https://sqs.ap-northeast-1.amazonaws.com/683640743654/umejima-sample-queue";
  public static final String STANDARD_QUEUE_NAME = "umejima-sample-queue";
  public static final String FIFO_QUEUE_URL = "https://sqs.ap-northeast-1.amazonaws.com/683640743654/umejima-sample-queue.fifo";
}
