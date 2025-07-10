package com.example.elasticsearch.demo.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.cat.IndicesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ElasticsearchService {
    private final ElasticsearchClient client;
    private final RestClient restClient;
    @Value("${elasticsearch.uri}")
    private String uri;
    public ElasticsearchService(ElasticsearchClient client, RestClient restClient) {
        this.client = client;
        this.restClient = restClient;
    }

    public String listIndices() throws Exception {
        IndicesResponse response = client.cat().indices();
        StringBuilder res = new StringBuilder();
        response.valueBody().forEach(index -> {
            res.append("Index: " + index.toString()).append("\n");
        });
        return res.toString();
    }

    public String listIndexMatchCase(String key) {
        String url = String.format("http://%s/_cat/indices/*%s*?v", uri, key);
        return this.restClient.get().uri(url).retrieve().body(String.class);
    }

    public String createIndex(String index) {
        String url = String.format("http://%s/%s", uri, index);
        return this.restClient.put().uri(url).retrieve().body(String.class);
    }

    public String deleteIndex(String index) {
        String url = String.format("http://%s/%s", uri, index);
        return this.restClient.delete().uri(url).retrieve().body(String.class);
    }
}
