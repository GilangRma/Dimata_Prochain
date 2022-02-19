package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.PosDiscountQtyMapping;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface PosDiscountQtyMappingRepo extends R2dbcRepository<PosDiscountQtyMapping, Long>{
    Mono<PosDiscountQtyMapping> findById(Long id);

}
