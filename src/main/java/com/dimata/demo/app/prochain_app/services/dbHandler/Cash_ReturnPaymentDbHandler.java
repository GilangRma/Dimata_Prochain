package com.dimata.demo.app.prochain_app.services.dbHandler;

import com.dimata.demo.app.prochain_app.core.api.DbHandlerBase;
import com.dimata.demo.app.prochain_app.models.table.Cash_ReturnPayment;
import com.dimata.demo.app.prochain_app.services.repo.Cash_ReturnPaymentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper = true)
public class Cash_ReturnPaymentDbHandler extends DbHandlerBase<Cash_ReturnPayment, Long> {

    @Autowired
    private Cash_ReturnPaymentRepo repo;

    @Override
    protected R2dbcRepository<Cash_ReturnPayment, Long> getRepository() {
        return repo;
    }

    @Override
    protected Mono<Cash_ReturnPayment> setGenerateId(Cash_ReturnPayment record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<Cash_ReturnPayment> setGenerateIdBatch(Flux<Cash_ReturnPayment> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }
    
}
