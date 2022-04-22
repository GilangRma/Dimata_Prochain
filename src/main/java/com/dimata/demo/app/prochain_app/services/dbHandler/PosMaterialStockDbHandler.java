package com.dimata.demo.app.prochain_app.services.dbHandler;

import com.dimata.demo.app.prochain_app.core.api.DbHandlerBase;
import com.dimata.demo.app.prochain_app.models.table.PosMaterialStock;
import com.dimata.demo.app.prochain_app.services.repo.PosMaterialStockRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper  = true)
public class PosMaterialStockDbHandler extends DbHandlerBase<PosMaterialStock, Long> {

    @Autowired
    private PosMaterialStockRepo repo;

    @Override
    protected R2dbcRepository<PosMaterialStock, Long> getRepository() {
        return repo;
    }

    @Override
    protected Mono<PosMaterialStock> setGenerateId(PosMaterialStock record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<PosMaterialStock> setGenerateIdBatch(Flux<PosMaterialStock> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }


    
}
