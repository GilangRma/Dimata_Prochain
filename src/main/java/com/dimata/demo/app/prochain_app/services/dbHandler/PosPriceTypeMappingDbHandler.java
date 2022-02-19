package com.dimata.demo.app.prochain_app.services.dbHandler;

import com.dimata.demo.app.prochain_app.core.api.DbHandlerBase;
import com.dimata.demo.app.prochain_app.models.table.PosPriceTypeMapping;
import com.dimata.demo.app.prochain_app.services.repo.PosPriceTypeMappingRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper = true)
public class PosPriceTypeMappingDbHandler extends DbHandlerBase<PosPriceTypeMapping, Long> {
    @Autowired
    private PosPriceTypeMappingRepo repo;

    @Override
    protected Mono<PosPriceTypeMapping> setGenerateId(PosPriceTypeMapping record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<PosPriceTypeMapping> setGenerateIdBatch(Flux<PosPriceTypeMapping> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }

    @Override
    protected R2dbcRepository<PosPriceTypeMapping, Long> getRepository(){
        return repo;
    }
}
