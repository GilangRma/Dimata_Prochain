package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.DiscountType;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface DiscountTypeRepo extends R2dbcRepository<DiscountType, Long>{
    Mono<DiscountType> findById(long id);
}
