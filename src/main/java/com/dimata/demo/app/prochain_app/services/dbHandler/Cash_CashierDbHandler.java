package com.dimata.demo.app.prochain_app.services.dbHandler;

import com.dimata.demo.app.prochain_app.core.api.DbHandlerBase;
import com.dimata.demo.app.prochain_app.models.table.Cash_Cashier;
import com.dimata.demo.app.prochain_app.services.repo.Cash_CashierRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper = true)
public class Cash_CashierDbHandler extends DbHandlerBase<Cash_Cashier, Long>{
    
    @Autowired
    private Cash_CashierRepo repo;

    @Override
    protected R2dbcRepository<Cash_Cashier, Long> getRepository() {
        return repo;
    }

    @Override
    protected Mono<Cash_Cashier> setGenerateId(Cash_Cashier record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<Cash_Cashier> setGenerateIdBatch(Flux<Cash_Cashier> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }

}
