package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.Location;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface LocationRepo extends R2dbcRepository <Location, Long>{
    Mono<Location> findById(long id);
    Mono<Integer> countById(long id);
}
