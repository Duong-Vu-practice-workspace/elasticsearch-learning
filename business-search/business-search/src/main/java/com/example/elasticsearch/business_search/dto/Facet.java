package com.example.elasticsearch.business_search.dto;

import java.util.List;

public record Facet(String name,
                    List<FacetItem> items) {
}
