package com.example.elasticsearch.demo.controller;

import com.example.elasticsearch.demo.service.ElasticsearchService;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/create-index/{index}")
    public String createIndex(@PathVariable String index) {
        return service.createIndex(index);
    }

    @DeleteMapping("/delete-index/{index}")
    public String deleteIndex(@PathVariable String index) {
        return service.deleteIndex(index);
    }
}
