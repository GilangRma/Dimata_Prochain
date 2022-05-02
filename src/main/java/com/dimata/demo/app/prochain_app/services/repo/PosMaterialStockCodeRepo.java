package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.PosMaterialStockCode;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface PosMaterialStockCodeRepo extends R2dbcRepository <PosMaterialStockCode, Long>{
    Mono<PosMaterialStockCode> findById(long id);
}
