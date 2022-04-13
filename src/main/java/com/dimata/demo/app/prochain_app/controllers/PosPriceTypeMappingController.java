package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam; 
import com.dimata.demo.app.prochain_app.forms.PosPriceTypeMappingForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosPriceMappingRelation;
import com.dimata.demo.app.prochain_app.models.table.PosPriceTypeMapping;
import com.dimata.demo.app.prochain_app.models.table.PriceType;
import com.dimata.demo.app.prochain_app.services.api.PosPriceTypeMappingApi;

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
public class PosPriceTypeMappingController {
    @Autowired
    private PosPriceTypeMappingApi posPriceTypeMappingApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/pos_price_type_mapping", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PosPriceTypeMapping> maintainerAddPosPriceTypeMapping(@RequestBody PosPriceTypeMappingForm form) {
        return posPriceTypeMappingApi.createPosPriceTypeMapping(form);
    }

    @GetMapping(path = BASE_URL + "/pos_price_type_mapping")
    public Flux<PosPriceTypeMapping> maintainerGetAllPosPriceTypeMapping(CommonParam param) {
        return posPriceTypeMappingApi.getAllPosPriceTypeMapping(param);
    }

    @GetMapping(path = BASE_URL + "/pos_price_type_mapping/{PRICE_TYPE_ID}")
    public Mono<PosPriceTypeMapping> maintainerGetPosPriceTypeMapping(@PathVariable("PRICE_TYPE_ID") Long PRICE_TYPE_ID) {
        return posPriceTypeMappingApi.getPosPriceTypeMapping(PRICE_TYPE_ID);
    }

    @PutMapping(path = BASE_URL + "/pos_price_type_mapping/{PRICE_TYPE_ID}")
    public Mono<PosPriceTypeMapping> maintainerUpdatePosPriceTypeMapping(@PathVariable("PRICE_TYPE_ID") long PRICE_TYPE_ID, @RequestBody PosPriceTypeMappingForm form) {
        return posPriceTypeMappingApi.updatePosPriceTypeMapping(PRICE_TYPE_ID, form);
    }
    @PostMapping(path = BASE_URL +"/pos_price_type/price_type_id")
    public Mono<PriceType> maintainerPriceTypeId(@RequestBody PosPriceMappingRelation form) {
        return posPriceTypeMappingApi.checkAvailableData(form)
            .flatMap(f -> posPriceTypeMappingApi.getDataPriceType(f.getId()));
    }
}
