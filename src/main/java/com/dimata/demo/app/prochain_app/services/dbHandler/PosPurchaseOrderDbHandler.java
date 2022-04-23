package com.dimata.demo.app.prochain_app.services.dbHandler;

import com.dimata.demo.app.prochain_app.core.api.DbHandlerBase;
import com.dimata.demo.app.prochain_app.models.table.PosPurchaseOrder;
import com.dimata.demo.app.prochain_app.services.repo.PosPurchaseOrderRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper = true)
public class PosPurchaseOrderDbHandler extends DbHandlerBase<PosPurchaseOrder, Long> {

    @Autowired
    private PosPurchaseOrderRepo repo;

    @Override
    protected R2dbcRepository<PosPurchaseOrder, Long> getRepository() {
        return repo;
    }

    @Override
    protected Mono<PosPurchaseOrder> setGenerateId(PosPurchaseOrder record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<PosPurchaseOrder> setGenerateIdBatch(Flux<PosPurchaseOrder> records) {
        return records 
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }
}
