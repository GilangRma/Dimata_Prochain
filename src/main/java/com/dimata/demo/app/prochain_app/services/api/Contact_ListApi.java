package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.Contact_ListForm;
import com.dimata.demo.app.prochain_app.models.table.Contact_List;
import com.dimata.demo.app.prochain_app.services.crude.Contact_ListCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Contact_ListApi {

    @Autowired
    private Contact_ListCrude contact_ListCrude;
    @Autowired
    private R2dbcEntityTemplate template;

    public Mono<Contact_List> createContact_List(Contact_ListForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            Contact_ListCrude.Option option = Contact_ListCrude.initOption (f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(contact_ListCrude::create);
    }

    public Flux<Contact_List> getAllContact_List(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(Contact_List.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(Contact_List::fromRow)
            .all();
    }

    public Mono<Contact_List> getContact_List(Long id) {
        var sql = SelectQBuilder.emptyBuilder(Contact_List.TABLE_NAME)
            .addWhere(WhereQuery.when(Contact_List.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(Contact_List::fromRow)
            .one();
    }

    public Mono<Contact_List> updateContact_List(Long id, Contact_ListForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                Contact_ListCrude.Option option = Contact_ListCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(contact_ListCrude::updateRecord);
    }  


}
