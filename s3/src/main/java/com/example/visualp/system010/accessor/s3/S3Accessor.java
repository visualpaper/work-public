package com.example.visualp.system010.accessor.s3;

import static com.example.visualp.system010.config.Const.S3_BUCKET_NAME;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.example.visualp.system010.config.S3Provider;
import java.io.InputStream;
import javax.annotation.Nonnull;
import org.apache.commons.codec.binary.Base64InputStream;

public class S3Accessor {

  public String getLocation() {
    AmazonS3 client = S3Provider.provide();

    return client.getBucketLocation(S3_BUCKET_NAME);
  }

  @Nonnull
  public InputStream getBase64Binary() {
    AmazonS3 client = S3Provider.provide();

    S3Object s3Object = client.getObject(S3_BUCKET_NAME, "decode-test");

    return s3Object.getObjectContent();
    //return new Base64InputStream(s3Object.getObjectContent(), true);
  }

  public void putBase64Binary(@Nonnull InputStream is, long contentLength) {
    AmazonS3 client = S3Provider.provide();

    ObjectMetadata metadata = new ObjectMetadata();

    // Base64 -> デコードすると length が減るので問題。↓ Base64 前の値を設定するとうまくいく。(REST 送信 Stream は 78 byte)
    metadata.setContentLength(contentLength);
    client.putObject(S3_BUCKET_NAME, "decode-upload", is, metadata);
  }
}
