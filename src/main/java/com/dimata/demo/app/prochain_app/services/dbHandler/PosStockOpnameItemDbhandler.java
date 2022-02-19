package com.dimata.demo.app.prochain_app.services.dbHandler;

import com.dimata.demo.app.prochain_app.core.api.DbHandlerBase;
import com.dimata.demo.app.prochain_app.models.table.PosStockOpnameItem;
import com.dimata.demo.app.prochain_app.services.repo.PosStockOpnameItemRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper = true)
public class PosStockOpnameItemDbhandler extends DbHandlerBase <PosStockOpnameItem, Long>{
    @Autowired
    private PosStockOpnameItemRepo repo;

    @Override
    protected Mono<PosStockOpnameItem> setGenerateId(PosStockOpnameItem record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<PosStockOpnameItem> setGenerateIdBatch(Flux<PosStockOpnameItem> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }

    @Override
    protected R2dbcRepository<PosStockOpnameItem, Long> getRepository(){
        return repo;
    }
}
