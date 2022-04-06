package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.ContactListForm;
import com.dimata.demo.app.prochain_app.models.table.ContactList;
import com.dimata.demo.app.prochain_app.services.api.ContactListApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactListController {

    @Autowired
    private ContactListApi contactListApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/contact_list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ContactList> maintainerAddContactList(@RequestBody ContactListForm form) {
       
        return contactListApi.createContactList(form);
    }

    @GetMapping(path = BASE_URL + "/contact_list")
    public Flux<ContactList> maintainerGetAllContactList(CommonParam param) {
       
        return contactListApi.getAllContactList(param);
    }

    @GetMapping(path = BASE_URL + "/contact_list/{CONTACT_ID}")
    public Mono<ContactList> maintainerGetContactList(@PathVariable("CONTACT_ID") Long CONTACT_ID) {
       
        return contactListApi.getContactList(CONTACT_ID);
    }

    @PutMapping(path = BASE_URL + "/contact_list/{CONTACT_ID}")
    public Mono<ContactList> maintainerUpdateContacList(@PathVariable("CONTACT_ID") long CONTACT_ID, @RequestBody ContactListForm form) {
        return contactListApi.updateContactList(CONTACT_ID, form);
    }
    
}
