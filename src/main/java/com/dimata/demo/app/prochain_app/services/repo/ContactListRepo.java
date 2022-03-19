package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.ContactList;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface ContactListRepo extends R2dbcRepository <ContactList, Long>{
    Mono<ContactList> findById(long id);
}
