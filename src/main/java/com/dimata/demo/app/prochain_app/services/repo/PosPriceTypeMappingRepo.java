package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.PosPriceTypeMapping;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface PosPriceTypeMappingRepo extends R2dbcRepository<PosPriceTypeMapping, Long>{
    Mono<PosPriceTypeMapping> findById(Long id);
}
