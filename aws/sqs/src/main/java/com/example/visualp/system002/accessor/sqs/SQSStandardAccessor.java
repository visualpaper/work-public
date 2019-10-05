package com.example.visualp.system002.accessor.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.InvalidIdFormatException;
import com.amazonaws.services.sqs.model.InvalidMessageContentsException;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.OverLimitException;
import com.amazonaws.services.sqs.model.ReceiptHandleIsInvalidException;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.example.visualp.system002.config.Const;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SQSStandardAccessor {

  @Autowired
  private SQSClientProvider provider;

  /**
   * Send.
   */
  @Nonnull
  public String send(@Nonnull String message) {
    AmazonSQS sqs = provider.provide();

    SendMessageResult result;
    try {
      result = sqs.sendMessage(createSendRequest(message));
    } catch (InvalidMessageContentsException e) {
      // メッセージに許可されているセット以外の文字が含まれている
      throw new RuntimeException(e);
    } catch (UnsupportedOperationException e) {
      // サポートされていない操作
      throw new RuntimeException(e);
    } catch (Exception e) {
      // その他、予期せぬ例外
      throw new RuntimeException(e);
    }
    return result.getMessageId();
  }

  @Nonnull
  private SendMessageRequest createSendRequest(@Nonnull String message) {
    SendMessageRequest request = new SendMessageRequest();

    request.withMessageBody(message);
    request.withQueueUrl(Const.STANDARD_QUEUE_URL);

    // タイマー付きメッセージを送信する。
    // (メッセージがキューに入ってから、コンシューマーに表示されるまでのディレイタイムを設定)
    request.setDelaySeconds(5);

    // 属性付きメッセージを送信する。
    Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
    messageAttributes.put("Name", new MessageAttributeValue()
        .withDataType("String")
        .withStringValue("Jane"));
    request.withMessageAttributes(messageAttributes);

    return request;
  }


  /**
   * Receive.
   */
  @Nonnull
  public List<Message> receive() {
    AmazonSQS sqs = provider.provide();

    ReceiveMessageResult result;
    try {
      result = sqs.receiveMessage(createReceiveRequest());
    } catch (OverLimitException e) {
      // 指定されたアクションが、制限等に違反している場合
      throw new RuntimeException(e);
    } catch (Exception e) {
      // その他、予期せぬ例外
      throw new RuntimeException(e);
    }
    return result.getMessages();
  }

  @Nonnull
  public ReceiveMessageRequest createReceiveRequest() {
    ReceiveMessageRequest request = new ReceiveMessageRequest();

    request.withQueueUrl(Const.STANDARD_QUEUE_URL);

    // 最大取得メッセージ数を指定する。(最大 10)
    request.withMaxNumberOfMessages(10);

    // 可視性タイムアウトを指定する。 (☆ キューで設定しているはず。どちらが優先される？)
    request.withVisibilityTimeout(5);

    // > メッセージがキューに到着するのを待つまでの時間（秒単位）。
    // > メッセージがある場合は、呼び出しはより早く戻ります。
    // > 利用可能なメッセージがなく、待機時間が経過した場合、呼び出しは空のメッセージリストとともに正常に返されます。
    // ☆ つまり、メッセージが一件もない場合のポーリング時間？
    request.withWaitTimeSeconds(10);
    return request;
  }


  /**
   * delete.
   */
  public void delete(@Nonnull String receiptHandle) {
    AmazonSQS sqs = provider.provide();

    try {
      // Delete 用の Result クラスもあるが、Result として価値ある情報でなかったので無視
      // ☆ 以下の Reference に記載されている文を見る限り、例外をハンドリングできれば問題なさそうに見える。
      //    > 標準キューの場合は、削除した後でもメッセージを受信する可能性があります。
      //    > メッセージを削除する要求を送信したときに、メッセージのコピーを保管しているサーバーの1つが使用不可になっていると、まれにこれが起こることがあります。
      //    > コピーはサーバー上に残り、後続の受信要求中に返却される可能性があります。
      //    > アプリケーションがべき等であることを確認してください。
      //
      //    が、以下の説明もあって、矛盾している。
      //    > DeleteMessageアクションを使用するときReceiptHandleは、メッセージに対して最後に受信したものを指定する必要があり ます
      //    >（そうでない場合、要求は成功しますが、メッセージは削除されない可能性があります）。
      //    ↑の「要求は成功する」なら ReceiptHandleIsInvalidException は何？
      sqs.deleteMessage(createDeleteRequest(receiptHandle));
    } catch (InvalidIdFormatException e) {
      // 指定された receiptHandle は現在のバージョンでは無効
      throw new RuntimeException(e);
    } catch (ReceiptHandleIsInvalidException e) {
      // 指定された receiptHandle は無効です
      throw new RuntimeException(e);
    } catch (Exception e) {
      // その他、予期せぬ例外
      throw new RuntimeException(e);
    }
  }

  @Nonnull
  public DeleteMessageRequest createDeleteRequest(@Nonnull String receiptHandle) {
    DeleteMessageRequest request = new DeleteMessageRequest();

    request.withQueueUrl(Const.STANDARD_QUEUE_URL);
    request.withReceiptHandle(receiptHandle);
    return request;
  }
}
