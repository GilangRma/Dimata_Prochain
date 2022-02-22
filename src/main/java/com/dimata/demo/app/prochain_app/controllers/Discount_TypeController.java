package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.Discount_TypeForm;
import com.dimata.demo.app.prochain_app.models.table.Discount_Type;
import com.dimata.demo.app.prochain_app.services.api.Discount_TypeApi;

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
public class Discount_TypeController {

    @Autowired
    private Discount_TypeApi discount_TypeApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/discount_type", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Discount_Type> maintainerAddDiscount_Type(@RequestBody Discount_TypeForm form) {
       
        return discount_TypeApi.createDiscount_Type(form);
    }

    @GetMapping(path = BASE_URL + "/discount_type")
    public Flux<Discount_Type> maintainerGetAllDiscount_Type(CommonParam param) {
       
        return discount_TypeApi.getAllDiscount_Type(param);
    }

    @GetMapping(path = BASE_URL + "/discount_type/{DISCOUNT_TYPE_ID}")
    public Mono<Discount_Type> maintainerGetDiscount_Type(@PathVariable("DISCOUNT_TYPE_ID") Long DISCOUNT_TYPE_ID) {
       
        return discount_TypeApi.getDiscount_Type(DISCOUNT_TYPE_ID);
    }

    @PutMapping(path = BASE_URL + "/discount_type/{DISCOUNT_TYPE_ID}")
    public Mono<Discount_Type> maintainerUpdateDiscount_Type(@PathVariable("DISCOUNT_TYPE_ID") long DISCOUNT_TYPE_ID, @RequestBody Discount_TypeForm form) {
        return discount_TypeApi.updateDiscount_Type(DISCOUNT_TYPE_ID, form);
    }

    
}
