package com.dimata.demo.app.prochain_app.services.dbHandler;

import com.dimata.demo.app.prochain_app.core.api.DbHandlerBase;
import com.dimata.demo.app.prochain_app.models.table.Contact_List;
import com.dimata.demo.app.prochain_app.services.repo.Contact_ListRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper = true)
public class Contact_ListDbHandler extends DbHandlerBase <Contact_List, Long>{
    
    @Autowired
    private Contact_ListRepo repo;

    @Override
    protected R2dbcRepository<Contact_List, Long> getRepository() {
        return repo;
    }

    @Override
    protected Mono<Contact_List> setGenerateId(Contact_List record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<Contact_List> setGenerateIdBatch(Flux<Contact_List> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }
}
