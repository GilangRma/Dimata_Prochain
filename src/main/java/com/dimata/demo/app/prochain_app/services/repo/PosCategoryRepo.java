package com.dimata.demo.app.prochain_app.services.repo;


import com.dimata.demo.app.prochain_app.models.table.PosCategory;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface PosCategoryRepo extends R2dbcRepository<PosCategory, Long> {
    Mono<PosCategory> findById(Long id);
}
