package com.example.visualp.system005.accessor.es.client.jest;

import com.example.visualp.system005.accessor.es.Consts;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class JestQuerySamples {

    @Autowired
    JestClientProvider clientProvider;

    public void search() throws IOException {
        JestClient client = clientProvider.get();

        SearchResult result = client.execute(getQuery());
        if (result.isSucceeded()) {
            Map source = result.getHits(Map.class).get(0).source;

            System.out.println(source.get("author"));
            System.out.println(source.get("timestamp"));
        }
        client.close();
    }

    @NonNull
    private Search getQuery() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("_id", "1"));

        Search search = new Search.Builder(searchSourceBuilder.toString())
            .addIndex(Consts.INDEX)
            .addType(Consts.TYPE)
            .build();

        return search;
    }
}
