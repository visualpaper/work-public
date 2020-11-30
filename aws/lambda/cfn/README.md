# cfn

## 手順

1. DynamoDB 作成
2. DynamoDB Stream 有効 (新旧すべて)
3. IAM 作成 (信頼関係で lamnbda があること)
4. ツール実装

5. s3 に main.py だけが入った main.zip を用意
   ※ CodeUri で指定しているパスに用意

6. cfn.yaml を cfn に読み込み、実行
   ※ IAM は S3 と CFN が利用可能な IAM を用意すること。
   ※ 「AWS CloudFormation によって、次の機能が要求される場合があることを承認します: CAPABILITY_AUTO_EXPAND」にチェックを付けること。

## 検証

* リトライは無限実施される
* python dynamodb stream lambda であれば raise すれば再試行される。
* ただし、全レコード再試行される (stream の最初から)
  ※ INSERT/DELETE/MODIFY 関係なく、同じ stream である場合は直列

* cloudwatch でログ確認及び、Error count and success rate (失敗/成功率) の確認が可能
* logging を使う場合は、ログレベル設定が必要 (デフォルトだと warn)
