package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.PosMaterial;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface PosMaterialRepo extends R2dbcRepository <PosMaterial, Long> {
    Mono<PosMaterial> findById(long id);
    Mono<Integer> countById(long id);
}
