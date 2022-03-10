package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.Contact_ListForm;
import com.dimata.demo.app.prochain_app.models.table.Contact_List;
import com.dimata.demo.app.prochain_app.services.api.Contact_ListApi;

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
public class Contact_ListController {

    @Autowired
    private Contact_ListApi contact_ListApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/contact_list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Contact_List> maintainerAddContactList(@RequestBody Contact_ListForm form) {
       
        return contact_ListApi.createContact_List(form);
    }

    @GetMapping(path = BASE_URL + "/contact_list")
    public Flux<Contact_List> maintainerGetAllContactList(CommonParam param) {
       
        return contact_ListApi.getAllContact_List(param);
    }

    @GetMapping(path = BASE_URL + "/contact_list/{CONTACT_ID}")
    public Mono<Contact_List> maintainerGetContactList(@PathVariable("CONTACT_ID") Long CONTACT_ID) {
       
        return contact_ListApi.getContact_List(CONTACT_ID);
    }

    @PutMapping(path = BASE_URL + "/contact_list/{CONTACT_ID}")
    public Mono<Contact_List> maintainerUpdateContacList(@PathVariable("CONTACT_ID") long CONTACT_ID, @RequestBody Contact_ListForm form) {
        return contact_ListApi.updateContact_List(CONTACT_ID, form);
    }
    
}
