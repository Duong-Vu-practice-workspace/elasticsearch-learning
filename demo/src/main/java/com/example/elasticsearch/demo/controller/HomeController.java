package com.example.elasticsearch.demo.controller;

import com.example.elasticsearch.demo.service.ElasticsearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/elasticsearch")
public class HomeController {
    private final ElasticsearchService service;

    public HomeController(ElasticsearchService service) {
        this.service = service;
    }

//    @GetMapping("/_cat/indices")
//    public String getIndices() throws Exception {
//        return service.listIndices();
//    }

    @GetMapping("/_cat/indices/*{key}*")
    public String getIndicesMatchCase(@PathVariable String key) {
        return service.listIndexMatchCase(key);
    }
}
