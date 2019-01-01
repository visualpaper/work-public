package com.example.visualp.system002.accessor.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.InvalidIdFormatException;
import com.amazonaws.services.sqs.model.InvalidMessageContentsException;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.OverLimitException;
import com.amazonaws.services.sqs.model.ReceiptHandleIsInvalidException;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.example.visualp.system002.config.Const;
import java.util.Date;
import java.util.List;
import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Standard と同じ部分は割愛
// ☆ RequestAttemptId については時間をかけて理解する必要有り
// * https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/sqs/model/ReceiveMessageRequest.html#withReceiveRequestAttemptId-java.lang.String-
// * https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/sqs/model/SendMessageRequest.html#getMessageDeduplicationId--
// キュー設定 ⇒ コンテンツに基づく重複排除
// ↑ 重複したメッセージを送信した場合でも、送信時に duplicateId を送れば 5 分間コンシューマーに表示されないけど重複削除するよ。って見える。
//    が、receive で RequestAttemptId を指定する意味が不明。
//
// ☆ Receive で GroupId を指定する方法が不明
// * https://stackoverflow.com/questions/46550581/sqs-fifo-using-messagegroupid-to-receive-message?rq=1
//   > Attribute で指定したって意味ないというか、属性はそう使うものでないよと言っている
// * https://dev.classmethod.jp/cloud/sqs-new-fifo/
//   > Attribute で GroupId を指定している
// * https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/sqs/model/ReceiveMessageRequest.html#withReceiveRequestAttemptId-java.lang.String-
//   > Attribute で GroupId を指定できる
// ↑ Attribute で指定できるのでは？
@Component
public class SQSFifoAccessor {

  private static final String GROUP_ID = "fifo_group_id";

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
    request.withQueueUrl(Const.FIFO_QUEUE_URL);

    // FIFOキューには必須
    // > メッセージが特定のメッセージグループに属することを指定するタグ。
    // > 同じメッセージグループに属するメッセージはFIFO方式で処理されます
    // > ただし、異なるメッセージグループ内のメッセージは順序どおりに処理されない場合があります）。
    request.withMessageGroupId(GROUP_ID);

    // ☆ ↑参照
    request.withMessageDeduplicationId(String.valueOf(new Date().getTime()));
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

    request.withQueueUrl(Const.FIFO_QUEUE_URL);
    return request;
  }


  /**
   * delete.
   */
  public void delete(@Nonnull String receiptHandle) {
    AmazonSQS sqs = provider.provide();

    try {
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

    request.withQueueUrl(Const.FIFO_QUEUE_URL);
    request.withReceiptHandle(receiptHandle);
    return request;
  }
}
