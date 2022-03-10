package com.dimata.demo.app.prochain_app.services.repo;

import com.dimata.demo.app.prochain_app.models.table.Contact_List;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface Contact_ListRepo extends R2dbcRepository <Contact_List, Long>{
    Mono<Contact_List> findById(long id);
}
