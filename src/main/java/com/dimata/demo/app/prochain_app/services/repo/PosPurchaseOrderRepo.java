package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.PosPurchaseOrder;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface PosPurchaseOrderRepo extends R2dbcRepository <PosPurchaseOrder, Long>{
    Mono<PosPurchaseOrder> findById(long id);

}
