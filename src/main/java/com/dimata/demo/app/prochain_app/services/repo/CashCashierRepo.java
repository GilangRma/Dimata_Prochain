package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.CashCashier;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface CashCashierRepo extends R2dbcRepository <CashCashier, Long>{
    Mono<CashCashier> findById(long id);
}
