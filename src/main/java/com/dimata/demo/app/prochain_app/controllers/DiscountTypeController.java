package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.DiscountTypeForm;
import com.dimata.demo.app.prochain_app.models.table.DiscountType;
import com.dimata.demo.app.prochain_app.services.api.DiscountTypeApi;

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
public class DiscountTypeController {

    @Autowired
    private DiscountTypeApi discountTypeApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/discount_type", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<DiscountType> maintainerAddDiscountType(@RequestBody DiscountTypeForm form) {
       
        return discountTypeApi.createDiscountType(form);
    }

    @GetMapping(path = BASE_URL + "/discount_type")
    public Flux<DiscountType> maintainerGetAllDiscountType(CommonParam param) {
       
        return discountTypeApi.getAllDiscountType(param);
    }

    @GetMapping(path = BASE_URL + "/discount_type/{DISCOUNT_TYPE_ID}")
    public Mono<DiscountType> maintainerGetDiscountType(@PathVariable("DISCOUNT_TYPE_ID") Long DISCOUNT_TYPE_ID) {
       
        return discountTypeApi.getDiscountType(DISCOUNT_TYPE_ID);
    }

    @PutMapping(path = BASE_URL + "/discount_type/{DISCOUNT_TYPE_ID}")
    public Mono<DiscountType> maintainerUpdateDiscountType(@PathVariable("DISCOUNT_TYPE_ID") long DISCOUNT_TYPE_ID, @RequestBody DiscountTypeForm form) {
        return discountTypeApi.updateDiscountType(DISCOUNT_TYPE_ID, form);
    }

    
}
