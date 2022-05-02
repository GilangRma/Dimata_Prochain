package com.dimata.demo.app.prochain_app.services.dbHandler;

import com.dimata.demo.app.prochain_app.core.api.DbHandlerBase;
import com.dimata.demo.app.prochain_app.models.table.PosMaterialStockCode;
import com.dimata.demo.app.prochain_app.services.repo.PosMaterialStockCodeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper = true)
public class PosMaterialStockCodeDbHandler extends DbHandlerBase <PosMaterialStockCode, Long>{
    @Autowired
    private PosMaterialStockCodeRepo repo;

    @Override
    protected R2dbcRepository<PosMaterialStockCode, Long> getRepository() {
        // TODO Auto-generated method stub
        return repo;
    }

    @Override
    protected Mono<PosMaterialStockCode> setGenerateId(PosMaterialStockCode record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<PosMaterialStockCode> setGenerateIdBatch(Flux<PosMaterialStockCode> records) {
        return records
        .map(rec -> {
            long id = getGenerateUtil().generateOID();
            rec.setInsertId(id);
            return rec;
        });
    }


}
