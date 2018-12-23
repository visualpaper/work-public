package com.example.visualp.system005.accessor.es.client.rest;

import com.example.visualp.system005.accessor.es.Consts;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class RestClientProvider {

    @NonNull
    public RestClient get() {
        RestClientBuilder builder = RestClient
            .builder(new HttpHost(Consts.HOST, Consts.PORT));

        return builder.build();
    }
}
