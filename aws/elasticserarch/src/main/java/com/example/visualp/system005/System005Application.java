package com.example.visualp.system005;

import com.example.visualp.system005.accessor.es.client.jest.JestClientProvider;
import com.example.visualp.system005.accessor.es.client.jest.JestQuerySamples;
import com.example.visualp.system005.accessor.es.client.rest.RestQuerySamples;
import org.apache.http.HttpHost;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class System005Application {

	@Autowired
	JestQuerySamples jestQuerySamples;

	@Autowired
	RestQuerySamples restQuerySamples;

	public static void main(String[] args) {
		SpringApplication.run(System005Application.class, args);
	}

	@PostConstruct
	public void start() throws Exception {
		// rest
        // {"error":{"root_cause":[{"type":"illegal_argument_exception","reason":"request [/sample-index/sample-type/_search] contains unrecognized parameters: [batched_reduce_size], [typed_keys]"}],"type":"illegal_argument_exception","reason":"request [/sample-index/sample-type/_search] contains unrecognized parameters: [batched_reduce_size], [typed_keys]"},"status":400}
        // at org.elasticsearch.client.RestClient$1.completed(RestClient.java:354) ~[elasticsearch-rest-client-5.6.10.jar:5.6.10]
        // at org.elasticsearch.client.RestClient$1.completed(RestClient.java:343) ~[elasticsearch-rest-client-5.6.10.jar:5.6.10]
		// 参考: https://github.com/elastic/elasticsearch/issues/29551
		//       https://www.elastic.co/guide/en/elasticsearch/client/java-rest/5.6/java-rest-high-compatibility.html#java-rest-high-compatibility
		// ⇒ RestClient は使用不可
		restQuerySamples.search();

		// jest
		jestQuerySamples.search();
	}
}
