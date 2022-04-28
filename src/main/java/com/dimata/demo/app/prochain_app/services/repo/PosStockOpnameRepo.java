package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.PosStockOpname;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface PosStockOpnameRepo extends R2dbcRepository <PosStockOpname, Long>{
    Mono<PosStockOpname> findById(Long id);
}
