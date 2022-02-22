package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.Discount_Type;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface Discount_TypeRepo extends R2dbcRepository<Discount_Type, Long>{
    Mono<Discount_Type> findById(long id);
}
