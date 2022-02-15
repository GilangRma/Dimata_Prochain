package com.dimata.demo.app.prochain_app.services.dbHandler;

import com.dimata.demo.app.prochain_app.core.api.DbHandlerBase;
import com.dimata.demo.app.prochain_app.models.table.PosCategory;
import com.dimata.demo.app.prochain_app.services.repo.PosCategoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
@EqualsAndHashCode(callSuper = true)
public class PosCategoryDbHandler extends DbHandlerBase <PosCategory, Long> {
    @Autowired
    private PosCategoryRepo repo;

    @Override
    protected Mono<PosCategory> setGenerateId(PosCategory record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<PosCategory> setGenerateIdBatch(Flux<PosCategory> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }

    @Override
    protected R2dbcRepository<PosCategory, Long> getRepository(){
        return repo;
    }
}
