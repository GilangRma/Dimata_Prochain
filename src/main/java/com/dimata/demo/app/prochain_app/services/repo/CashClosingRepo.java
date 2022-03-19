package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.CashClosing;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface CashClosingRepo extends R2dbcRepository<CashClosing, Long>{
    Mono<CashClosing> findById(long id);
}
