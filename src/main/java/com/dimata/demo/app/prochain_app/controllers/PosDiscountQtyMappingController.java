package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.PosDiscountQtyMappingForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosDiscountQTYMappingRelation;
import com.dimata.demo.app.prochain_app.models.table.Location;
import com.dimata.demo.app.prochain_app.models.table.PosDiscountQtyMapping;
import com.dimata.demo.app.prochain_app.models.table.PosMaterial;
import com.dimata.demo.app.prochain_app.services.api.PosDiscountQtyMappingApi;

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
public class PosDiscountQtyMappingController {
    @Autowired
    private PosDiscountQtyMappingApi posDiscountQtyMappingApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/pos_discount_qty_mapping", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PosDiscountQtyMapping> maintainerAddPosCatagory(@RequestBody PosDiscountQtyMappingForm form) {
        return posDiscountQtyMappingApi.createPosDiscountQtyMapping(form);
    }

    @GetMapping(path = BASE_URL + "/pos_discount_qty_mapping")
    public Flux<PosDiscountQtyMapping> maintainerGetAllPosDiscountQtyMapping(CommonParam param) {
        return posDiscountQtyMappingApi.getAllPosDiscountQtyMapping(param);
    }

    @GetMapping(path = BASE_URL + "/pos_discount_qty_mapping/{DISCOUNT_TYPE_ID}")
    public Mono<PosDiscountQtyMapping> maintainerGetPosDiscountQtyMapping(@PathVariable("DISCOUNT_TYPE_ID") Long DISCOUNT_TYPE_ID) {
        return posDiscountQtyMappingApi.getPosDiscountQtyMapping(DISCOUNT_TYPE_ID);
    }

    @PutMapping(path = BASE_URL + "/pos_discount_qty_mapping/{DISCOUNT_TYPE_ID}")
    public Mono<PosDiscountQtyMapping> maintainerUpdatePosDiscountQtyMapping(@PathVariable("DISCOUNT_TYPE_ID") long DISCOUNT_TYPE_ID, @RequestBody PosDiscountQtyMappingForm form) {
        return posDiscountQtyMappingApi.updatePosDiscountQtyMapping(DISCOUNT_TYPE_ID, form);
    }
    @PostMapping(path = BASE_URL +"/pos_discount_qty_mapping/location")
    public Mono<Location> maintainerLocationId(@RequestBody PosDiscountQTYMappingRelation form) {
        return posDiscountQtyMappingApi.checkAvailableData(form)
            .flatMap(f -> posDiscountQtyMappingApi.getDataLocation(f.getId()));
    }
    @PostMapping(path = BASE_URL +"/pos_discount_qty_mapping/material")
    public Mono<PosMaterial> maintainerMaterialId(@RequestBody PosDiscountQTYMappingRelation form) {
        return posDiscountQtyMappingApi.checkMaterialData(form)
            .flatMap(f -> posDiscountQtyMappingApi.getDataByMaterial(f.getId()));
    }
}
