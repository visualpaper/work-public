
# ElasticSearch Client 調査結果
## (Elasticsearch 標準) JavaAPI
https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.3/client.html  
・ DSL が色々あって便利  
・ が、今後非推奨であり、Java高レベルRESTクライアントを推奨している  
   ⇒ https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.3/client.html  
   > TransportClientはElasticsearch 7.0 での使用を非推奨にし、8.0で完全に削除する予定です
   > 代わりに、シリアル化されたJava要求ではなくHTTP要求を実行するJava高水準RESTクライアントを使用する必要があります。
   > 移行ガイドでは、移行するために必要なすべての手順を説明します。
   > Java高レベルRESTクライアントは現在、より一般的に使用されるAPIをサポートしていますが、さらに追加する必要のあるものがまだたくさんあります。
   > この問題にコメントを追加することで、アプリケーションに必要な欠落しているAPIを教えて、優先順位を付けることができます 。Javaの高水準RESTクライアントの完全性。


## (Elasticsearch 標準) Java Rest API
### 高レベルRESTクライアント
https://www.elastic.co/guide/en/elasticsearch/client/java-rest/5.6/index.html  
・ DSL が色々あって便利  
・ 5.6 から↑のバージョンをサポート  
   ⇒ https://github.com/elastic/elasticsearch/issues/29551  
      https://www.elastic.co/guide/en/elasticsearch/client/java-rest/5.6/java-rest-high-compatibility.html#java-rest-high-compatibility  
   ※ ES 5.1 に対して本 Rest クライアントを使うとエラーになる。
   > {"error":{"root_cause":[{"type":"illegal_argument_exception","reason":"request [/sample-index/sample-type/_search] contains unrecognized parameters: [batched_reduce_size], [typed_keys]"}],"type":"illegal_argument_exception","reason":"request [/sample-index/sample-type/_search] contains unrecognized parameters: [batched_reduce_size], [typed_keys]"},"status":400}
   > at org.elasticsearch.client.RestClient$1.completed(RestClient.java:354) ~[elasticsearch-rest-client-5.6.10.jar:5.6.10]
   > at org.elasticsearch.client.RestClient$1.completed(RestClient.java:343) ~[elasticsearch-rest-client-5.6.10.jar:5.6.10]

### RESTクライアント
https://www.elastic.co/guide/en/elasticsearch/client/java-rest/5.0/index.html  
・ DSL がいまいち  
   ⇒ https://www.elastic.co/guide/en/elasticsearch/client/java-rest/5.0/_performing_requests.html  

・ 5.0 から↑のバージョンをサポート


## (Elasticsearch 3rd Party Library) Jest
https://github.com/searchbox-io/Jest/tree/master/jest  
・ DSL が色々あって便利  
・ 5.x 2.x 系で、メジャーバージョンが同一であれば使用可能  


# 結論
現状では Jest 5.3.3 (5.x 系最新バージョン) を使用する。  
(将来的には高レベルRESTクライアントを使用する)
