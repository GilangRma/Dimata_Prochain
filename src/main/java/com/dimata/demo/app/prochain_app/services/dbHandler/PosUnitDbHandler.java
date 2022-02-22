package com.dimata.demo.app.prochain_app.services.dbHandler;
import com.dimata.demo.app.prochain_app.core.api.DbHandlerBase;
import com.dimata.demo.app.prochain_app.models.table.PosUnit;
import com.dimata.demo.app.prochain_app.services.repo.PosUnitRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
@EqualsAndHashCode(callSuper = true)
public class PosUnitDbHandler extends DbHandlerBase<PosUnit,Long>{
    @Autowired
    private PosUnitRepo repo;

    @Override
    protected Mono<PosUnit> setGenerateId(PosUnit record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<PosUnit> setGenerateIdBatch(Flux<PosUnit> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }

    @Override
    protected R2dbcRepository<PosUnit, Long> getRepository(){
        return repo;
    }
}
