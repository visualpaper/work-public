package com.example.visualp.system005.accessor.es.client.jest;


import com.example.visualp.system005.accessor.es.Consts;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class JestClientProvider {

    @NonNull
    public JestClient get() {
        JestClientFactory factory = new JestClientFactory();

        factory
            .setHttpClientConfig(
                new HttpClientConfig.Builder("http://" + Consts.HOST + ":" + Consts.PORT).build()
            );

        return factory.getObject();
    }
}
