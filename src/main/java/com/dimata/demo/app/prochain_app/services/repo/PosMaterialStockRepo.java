package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.PosMaterialStock;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface PosMaterialStockRepo extends R2dbcRepository <PosMaterialStock, Long> {

    Mono<PosMaterialStock> findById(long id);
    
}
