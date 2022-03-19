package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.CashReturnPayment;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface CashReturnPaymentRepo extends R2dbcRepository<CashReturnPayment, Long>{
    Mono<CashReturnPayment> findById(long id);
}
