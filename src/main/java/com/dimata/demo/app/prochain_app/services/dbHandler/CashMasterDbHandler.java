package com.dimata.demo.app.prochain_app.services.dbHandler;

import com.dimata.demo.app.prochain_app.core.api.DbHandlerBase;
import com.dimata.demo.app.prochain_app.models.table.CashMaster;
import com.dimata.demo.app.prochain_app.services.repo.CashMasterRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper = true)
public class CashMasterDbHandler extends DbHandlerBase<CashMaster, Long>{
    
    @Autowired
    private CashMasterRepo repo;

    @Override
    protected R2dbcRepository<CashMaster, Long> getRepository() {
        return repo;
    }

    @Override
    protected Mono<CashMaster> setGenerateId(CashMaster record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<CashMaster> setGenerateIdBatch(Flux<CashMaster> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }
}
