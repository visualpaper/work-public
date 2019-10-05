package com.example.visualp.system005.accessor.es.client.rest;

import com.example.visualp.system005.accessor.es.Consts;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchAction;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class RestQuerySamples {

    @Autowired
    RestClientProvider clientProvider;

    public void search() throws IOException {
        RestClient client = clientProvider.get();
        RestHighLevelClient highLevelClient = new RestHighLevelClient(client);

        SearchResponse searchResponse = highLevelClient.search(getQuery());
        if (searchResponse.status() == RestStatus.OK) {
            Map<String, SearchHitField> fields = searchResponse.getHits().getAt(0).getFields();

            System.out.println(fields.get("author"));
            System.out.println(fields.get("timestamp"));
        }
        client.close();
    }

    @NonNull
    private SearchRequest getQuery() {
        RestClient client = clientProvider.get();

        RestHighLevelClient highLevelClient = new RestHighLevelClient(client);

        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.matchQuery("_id", "1"));

        searchRequest.indices(Consts.INDEX);
        searchRequest.types(Consts.TYPE);
        searchRequest.source(sourceBuilder);

        return searchRequest;
    }
}
