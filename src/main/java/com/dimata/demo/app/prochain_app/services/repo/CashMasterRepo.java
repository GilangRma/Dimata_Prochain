package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.CashMaster;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface CashMasterRepo extends R2dbcRepository<CashMaster, Long>{
    Mono<CashMaster> findById(long id);
}