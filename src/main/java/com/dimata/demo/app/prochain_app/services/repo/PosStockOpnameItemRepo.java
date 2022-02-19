package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.PosStockOpnameItem;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface PosStockOpnameItemRepo extends R2dbcRepository<PosStockOpnameItem, Long>{
    Mono<PosStockOpnameItem> findById(Long id);
}
