package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.Cash_Master;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface Cash_MasterRepo extends R2dbcRepository<Cash_Master, Long>{
    Mono<Cash_Master> findById(long id);
}