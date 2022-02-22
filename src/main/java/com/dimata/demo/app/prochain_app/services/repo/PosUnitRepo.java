package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.PosUnit;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface PosUnitRepo extends R2dbcRepository<PosUnit, Long>{
    Mono<PosUnit> findById(Long id);
}
