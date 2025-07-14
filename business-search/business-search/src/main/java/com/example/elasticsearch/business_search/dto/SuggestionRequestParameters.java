package com.example.elasticsearch.business_search.dto;

import com.example.elasticsearch.business_search.exceptions.BadRequestException;
import org.springframework.util.StringUtils;

import java.util.Objects;

public record SuggestionRequestParameters(String prefix, Integer limit) {
    public SuggestionRequestParameters {
        if(!StringUtils.hasText(prefix)) {
            throw new BadRequestException("Prefix can not be empty");
        }
        limit = Objects.requireNonNullElse(limit, 10);
    }
}
