package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.ContactListForm;
import com.dimata.demo.app.prochain_app.models.table.ContactList;
import com.dimata.demo.app.prochain_app.services.crude.ContactListCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ContactListApi {

    @Autowired
    private ContactListCrude contactListCrude;
    @Autowired
    private R2dbcEntityTemplate template;

    public Mono<ContactList> createContactList(ContactListForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            ContactListCrude.Option option = ContactListCrude.initOption (f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(contactListCrude::create);
    }

    public Flux<ContactList> getAllContactList(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(ContactList.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(ContactList::fromRow)
            .all();
    }

    public Mono<ContactList> getContactList(Long id) {
        var sql = SelectQBuilder.emptyBuilder(ContactList.TABLE_NAME)
            .addWhere(WhereQuery.when(ContactList.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(ContactList::fromRow)
            .one();
    }

    public Mono<ContactList> updateContactList(Long id, ContactListForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                ContactListCrude.Option option = ContactListCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(contactListCrude::updateRecord);
    }  


}
