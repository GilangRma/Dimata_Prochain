package com.dimata.demo.app.prochain_app.services.dbHandler;

import com.dimata.demo.app.prochain_app.core.api.DbHandlerBase;
import com.dimata.demo.app.prochain_app.models.table.DiscountType;
import com.dimata.demo.app.prochain_app.services.repo.DiscountTypeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper = true)
public class DiscountTypeDbHandler extends DbHandlerBase<DiscountType, Long>{

    @Autowired
    private DiscountTypeRepo repo;

    @Override
    protected R2dbcRepository<DiscountType, Long> getRepository() {
        return repo;
    }

    @Override
    protected Mono<DiscountType> setGenerateId(DiscountType record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<DiscountType> setGenerateIdBatch(Flux<DiscountType> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }
    
}
