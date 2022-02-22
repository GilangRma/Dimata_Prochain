package com.dimata.demo.app.prochain_app.services.dbHandler;

import com.dimata.demo.app.prochain_app.core.api.DbHandlerBase;
import com.dimata.demo.app.prochain_app.models.table.Discount_Type;
import com.dimata.demo.app.prochain_app.services.repo.Discount_TypeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper = true)
public class Discount_TypeDbHandler extends DbHandlerBase<Discount_Type, Long>{

    @Autowired
    private Discount_TypeRepo repo;

    @Override
    protected R2dbcRepository<Discount_Type, Long> getRepository() {
        return repo;
    }

    @Override
    protected Mono<Discount_Type> setGenerateId(Discount_Type record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<Discount_Type> setGenerateIdBatch(Flux<Discount_Type> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }
    
}
