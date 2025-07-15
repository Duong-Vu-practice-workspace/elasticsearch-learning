package com.example.elasticsearch.business_search.dto;

public record Pagination(int page,
                         int size,
                         long totalElements,
                         int totalPages) {
}
