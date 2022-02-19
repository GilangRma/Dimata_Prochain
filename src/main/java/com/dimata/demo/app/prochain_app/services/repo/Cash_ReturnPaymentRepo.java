package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.Cash_ReturnPayment;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface Cash_ReturnPaymentRepo extends R2dbcRepository<Cash_ReturnPayment, Long>{
    Mono<Cash_ReturnPayment> findById(long id);
}
