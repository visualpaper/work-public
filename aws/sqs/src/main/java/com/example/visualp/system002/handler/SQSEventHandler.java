package com.example.visualp.system002.handler;

import com.amazonaws.services.sqs.model.Message;
import com.example.visualp.system002.App;
import com.example.visualp.system002.accessor.sqs.SQSFifoAccessor;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SQSEventHandler implements Runnable {

  @Autowired
  private SQSFifoAccessor standardAccessor;

  @Override
  public void run() {

    while (App.isBoot) {

      // メッセージを送信する。
      standardAccessor.send(String.valueOf(new Date().getTime()));

      // 次のポーリングまで sleep
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
        break;
      }

      // メッセージを受信する。
      List<Message> messages = standardAccessor.receive();

      // メッセージを削除する。
      messages.forEach(v -> {
        standardAccessor.delete(v.getReceiptHandle());
      });
    }
  }
}
