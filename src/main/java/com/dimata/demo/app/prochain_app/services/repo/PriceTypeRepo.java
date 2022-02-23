package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.PriceType;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface PriceTypeRepo extends R2dbcRepository<PriceType, Long>{
    Mono<PriceType> findById(Long id);
}
