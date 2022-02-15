package com.dimata.demo.app.prochain_app.services.repo;


import com.dimata.demo.app.prochain_app.models.table.Cash_Closing;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface Cash_ClosingRepo extends R2dbcRepository<Cash_Closing, Long>{
    Mono<Cash_Closing> findById(long id);
}
