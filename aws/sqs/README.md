# SQS
* 2 種類のメッセージキューを利用できます。
  - 標準キューでは、最大限のスループットが得られ、配信順序はベストエフォート型で、配信は少なくとも 1 回行われます。
  - SQS FIFO キューは、メッセージが送信される順序のとおりに 1 回のみ確実に処理されるように設計されています。

* 転送時にメッセージが失われることも、他のサービスが利用可能である必要もありません。

* 各メッセージの複数のコピーが複数のアベイラビリティーゾーンで冗長的に保存されるため、必要な時にはいつでも使用できます。

* サーバー側の暗号化 (SSE) によって各メッセージの本文を暗号化できるため、アプリケーション間で機密データをやり取りできます。

## キューの種類
### スタンダードキュー
* 全 Region で利用可能
* 無制限。1 秒あたりのトランザクション (TPS) をサポート
* 1 回は確実に配信されますが、複数回配信される可能性がある
* メッセージが送信されたときと異なる順序で配信される可能性がある

### FIFO (ファーストインファーストアウト) キュー
* US East (N. Virginia), US East (Ohio), US West (Oregon), EU (Ireland), Asia Pacific (Sydney), and Asia Pacific (Tokyo) のリージョンで使用可能
* 1 秒あたり最大 3,000 件のメッセージをサポート
* 1 回だけの処理 – メッセージは 1 度配信されると、ユーザーがそれを処理して削除するまでは使用可能な状態に保たれます。このキューでは、重複は導入されていません。
* あるメッセージグループ内にあるメッセージについては、メッセージが受信された順序が厳密に保持されます。
  ※ ただし、異なるメッセージグループ内のメッセージは順序どおりに処理されない場合がある。

## キューの設定
### メッセージ保持期間
* キューに設定された保持期間よりも長いキューに残っているメッセージを自動的に削除する
  ※ キューの設定⇒メッセージ保持期間 より設定可能

### デッドレターキュー
* メッセージ送信時にうまく処理されなかった場合、正常に処理 (消費) できないメッセージの送信先として別のキューを利用する仕組み
  ※ 別途、送信時に本当に送りたいキューとは別に、デッドレターキューの ARN も指定して送信する必要がある。
  ※ さまざまな条件でソースキュー⇒デッドレターキューに移動する場合がある。
     例として、キューの設定から設定可能な「最大受信回数」(メッセージを受信した後に削除されずに再度コンシューマーに表示されるまでの回数) が規定数を超えた場合に
     デッドレターキューに移動される。
  ※ キューの設定⇒デットレターキュー設定 より設定可能

### 可視性タイムアウト
* メッセージを受信した後、他のコンシューマーから操作されることを防止させるためのタイムアウト時間
  > メッセージが受信された直後は、メッセージはキューに残ったままです。
  > 他のコンシューマーが同じメッセージを再び処理しないように、Amazon SQS は可視性タイムアウトを設定しています。
  > この時間内では、他のコンシューマーによる同じメッセージの受信と処理が防止されます。
  ※ キューの設定⇒可視性タイムアウト より設定可能

### 遅延キュー
* メッセージを送信した後、コンシューマーへ表示する時間を遅延させることが可能
  > 遅延キューを使用すると、キューへの新しいメッセージの配信を数秒延期することができます。
  > 遅延キューを作成した場合、キューに送信したメッセージはいずれも、遅延期間の間コンシューマーに表示されなくなります。
  ※ キューの設定⇒配信遅延 より設定可能

### ロングポーリング
* デフォルトは 0 (ショートポーリング)
> 空のレスポンス (ReceiveMessage リクエストに対して使用できるメッセージがない場合) と、
> 偽の空のレスポンス (メッセージが利用できるがレスポンスに含まれていない場合) の数を削減することで、
> Amazon SQS の使用コストを削減するために役立ちます。
  ※ キューの設定⇒メッセージ受信待機時間 より設定可能

## メッセージ送信
### タイマー
* メッセージタイマーを使用すると、キューに追加するメッセージの初期非表示期間を指定できます。
  たとえば、タイマーを 45 秒に設定してメッセージを送信すると、そのメッセージはキューに入ってから 45 秒間はコンシューマーに表示されません。

### 属性
* メッセージに構造化メタデータ (タイムスタンプ、地理空間データ、署名、識別子など) を含めることができます。 

## メッセージ受信
* 受け取れる情報は以下
> メッセージ本文
> メッセージ本文のMD5ダイジェスト。MD5の詳細については、参照RFC1321を。
> MessageIdあなたがキューにメッセージを送信したときに受け取りました。
> メッセージの識別子
> メッセージ属性
> メッセージ属性のMD5ダイジェスト。
⇒ ハッシュがあるので、破損検知等も可能

## メッセージ削除
* ReceiptHandle を指定することで削除できる。
> 指定されたメッセージを指定されたキューから削除します。
> 削除するメッセージを選択するには、使用し ReceiptHandleたメッセージの（ではないMessageId、あなたがメッセージを送信するときに受信します）。

* ReceiptHandle は、メッセージを受信するごとに変化する。
> ReceiptHandleはメッセージを受信する特定のインスタンスに関連付けられています。
> 複数回メッセージを受信した場合、メッセージを受信するReceiptHandleたびにが異なります。

※ 最後にメッセージを受信した際の ReceiptHandle を指定しなければ、削除されない。
> DeleteMessageアクションを使用するときReceiptHandleは、メッセージに対して最後に受信したものを指定する必要があり ます
>（そうでない場合、要求は成功しますが、メッセージは削除されない可能性があります）。

* 他のコンシューマーによってロックされていても削除できる。
> 表示タイムアウト設定によってメッセージが別のコンシューマによってロックされている場合でも、Amazon SQSはキューからメッセージを削除できます。

* 結果整合である（☆説明文を見る限り、どっちとも取れるのが気になるのと、色々考える必要がありそうな気がする)
> 標準キューの場合は、削除した後でもメッセージを受信する可能性があります。
> メッセージを削除する要求を送信したときに、メッセージのコピーを保管しているサーバーの1つが使用不可になっていると、まれにこれが起こることがあります。
> コピーはサーバー上に残り、後続の受信要求中に返却される可能性があります。
> アプリケーションがべき等であることを確認してください。

## Reference
### Client
* https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/sqs/AmazonSQS.html

### Request
* https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/sqs/model/SendMessageRequest.html
* https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/sqs/model/ReceiveMessageRequest.html
* https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/sqs/model/DeleteMessageResult.html

### Standard
* https://docs.aws.amazon.com/ja_jp/AWSSimpleQueueService/latest/SQSDeveloperGuide/standard-queues-getting-started-java.html

### FIFO
* https://docs.aws.amazon.com/ja_jp/AWSSimpleQueueService/latest/SQSDeveloperGuide/FIFO-queues-getting-started-java.html

### JMS
* https://docs.aws.amazon.com/ja_jp/AWSSimpleQueueService/latest/SQSDeveloperGuide/getting-started.html
