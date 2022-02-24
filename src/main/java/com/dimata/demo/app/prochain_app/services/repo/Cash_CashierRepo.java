package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.Cash_Cashier;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface Cash_CashierRepo extends R2dbcRepository <Cash_Cashier, Long>{
    Mono<Cash_Cashier> findById(long id);
}
