package com.example.elasticsearch.playground.sec05.repository;

import com.example.elasticsearch.playground.sec05.entity.Garment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GarmentRepository extends ElasticsearchRepository<Garment, String> {
}
