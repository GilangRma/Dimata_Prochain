package com.dimata.demo.app.prochain_app.services.repo;


import com.dimata.demo.app.prochain_app.models.table.PosDiscountMapping;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface PosDiscountMappingRepo extends R2dbcRepository<PosDiscountMapping, Long> {
    Mono<PosDiscountMapping> findById(Long id);
}
