package com.example.visualp.system002;

import com.amazon.sqs.javamessaging.SQSConnection;
import com.example.visualp.system002.accessor.sqs.jms.ConnectionFactory;
import com.example.visualp.system002.accessor.sqs.jms.MyMessageListener;
import com.example.visualp.system002.config.Const;
import com.example.visualp.system002.handler.SQSEventHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

  public static boolean isBoot;
  private SQSConnection connection;
  private Session session;
  private MessageConsumer consumer;
  @Autowired
  private ConnectionFactory connectionFactory;

  @Autowired
  private SQSEventHandler handler;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @PostConstruct
  public void start() throws Exception {

    // SQS Client を用いて、同期的に SQS を操作
    // sync();

    // JMS を用いて、非同期的に SQS を操作
    async();
  }

  void sync() {
    if (isBoot) {
      return;
    }
    isBoot = true;

    Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    Thread.setDefaultUncaughtExceptionHandler(
        (thread, exception) ->
            exception.printStackTrace()
    );

    ExecutorService executor = Executors.newSingleThreadExecutor();

    try {
      executor.submit(handler);
    } catch (RejectedExecutionException e) {
      // タスクの実行をスケジュールできない場合
      e.printStackTrace();
    } finally {
      executor.shutdown();
    }
  }

  void async() throws JMSException {
    connection = connectionFactory.create();

    // 非トランザクション JMS セッションで、かつ、メッセージを自動で呼び出す設定
    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

    // コンシューマーの作成
    consumer = session.createConsumer(session.createQueue(Const.STANDARD_QUEUE_NAME));

    // メッセージ受信ハンドラの設定
    consumer.setMessageListener(new MyMessageListener());

    // コネクションを開始する。(ハンドリングを開始する)
    connection.start();
  }

  void shutdown() {
    isBoot = false;

    if (connection != null) {
      try {
        consumer.close();
        session.close();
        connection.close();
      } catch (JMSException e) {
        e.printStackTrace();
      }
    }
  }
}
