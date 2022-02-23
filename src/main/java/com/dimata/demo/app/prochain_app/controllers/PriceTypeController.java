package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.PriceTypeForm;
import com.dimata.demo.app.prochain_app.models.table.PriceType;
import com.dimata.demo.app.prochain_app.services.api.PriceTypeApi;

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
public class PriceTypeController {
    @Autowired
    private PriceTypeApi priceTypeApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/pos_unit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PriceType> maintainerAddPriceType(@RequestBody PriceTypeForm form) {
        return priceTypeApi.createPriceType(form);
    }

    @GetMapping(path = BASE_URL + "/price_type")
    public Flux<PriceType> maintainerGetAllPriceType(CommonParam param) {
        return priceTypeApi.getAllPriceType(param);
    }

    @GetMapping(path = BASE_URL + "/price_type/{PRICE_TYPE_ID}")
    public Mono<PriceType> maintainerGetPriceType(@PathVariable("PRICE_TYPE_ID") Long PRICE_TYPE_ID) {
        return priceTypeApi.getPriceType(PRICE_TYPE_ID);
    }

    @PutMapping(path = BASE_URL + "/price_type/{PRICE_TYPE_ID}")
    public Mono<PriceType> maintainerUpdatePriceType(@PathVariable("PRICE_TYPE_ID") long PRICE_TYPE_ID, @RequestBody PriceTypeForm form) {
        return priceTypeApi.updatePriceType(PRICE_TYPE_ID, form);
    }
}
