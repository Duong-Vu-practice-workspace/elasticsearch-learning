package com.example.elasticsearch.business_search.controller;

import com.example.elasticsearch.business_search.dto.SuggestionRequestParameters;
import com.example.elasticsearch.business_search.service.SuggestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BusinessSearchController {
    private final SuggestionService suggestionService;

    public BusinessSearchController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping("/api/suggestions")
    public List<String> suggest(SuggestionRequestParameters parameters) {
        return this.suggestionService.fetchSuggestions(parameters);
    }
}
