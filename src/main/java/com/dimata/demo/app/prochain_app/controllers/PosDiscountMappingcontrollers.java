package com.dimata.demo.app.prochain_app.controllers;


import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.PosDiscountMappingForm;
import com.dimata.demo.app.prochain_app.models.table.PosDiscountMapping;
import com.dimata.demo.app.prochain_app.services.api.PosDiscountMappingApi;

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
public class PosDiscountMappingcontrollers {
    @Autowired
    private PosDiscountMappingApi posDiscountMappingApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/pos_discount_mapping", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PosDiscountMapping> maintainerAddPosDiscountMapping(@RequestBody PosDiscountMappingForm form) {
        return posDiscountMappingApi.createPosDiscountMapping(form);
    }

    @GetMapping(path = BASE_URL + "/pos_discount_mapping")
    public Flux<PosDiscountMapping> maintainerGetAllPosDiscountMapping(CommonParam param) {
        return posDiscountMappingApi.getAllPosDiscountMapping(param);
    }

    @GetMapping(path = BASE_URL + "/pos_discount_mapping/{DISCOUNT_TYPE_ID}")
    public Mono<PosDiscountMapping> maintainerGetPosDiscountMapping(@PathVariable("DISCOUNT_TYPE_ID") Long DISCOUNT_TYPE_ID) {
        return posDiscountMappingApi.getPosDiscountMapping(DISCOUNT_TYPE_ID);
    }

    @PutMapping(path = BASE_URL + "/pos_discount_mapping/{DISCOUNT_TYPE_ID}")
    public Mono<PosDiscountMapping> maintainerUpdatePosDiscountMapping(@PathVariable("DISCOUNT_TYPE_ID") long DISCOUNT_TYPE_ID, @RequestBody PosDiscountMappingForm form) {
        return posDiscountMappingApi.updatePosDiscountMapping(DISCOUNT_TYPE_ID, form);
    }
}
