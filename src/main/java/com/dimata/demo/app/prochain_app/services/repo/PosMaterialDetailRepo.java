package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.PosMaterialDetail;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface PosMaterialDetailRepo extends R2dbcRepository <PosMaterialDetail, Long> {
    Mono<PosMaterialDetail> findById(long id);
}
