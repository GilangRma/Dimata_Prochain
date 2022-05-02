package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.forms.PosMaterialAndStockForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosMaterialAndStockRelation;
import com.dimata.demo.app.prochain_app.services.api.PosMaterialStockCodeApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PosMaterialStockCodeController {
    @Autowired
    private PosMaterialStockCodeApi posMaterialStockCodeApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/pos_relation_material_stock", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PosMaterialAndStockRelation> maintainerPosRelation(@RequestBody PosMaterialAndStockForm form) {
        return posMaterialStockCodeApi.createMaterialAndStock(form);
    }

    @GetMapping(path = BASE_URL + "/pos_relation_material_stock/{locationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Flux<PosMaterialAndStockRelation> maintainerGetPosRelation(@PathVariable("locationId") Long id) {
        return posMaterialStockCodeApi.getMaterialAndStockByLocation(id);
    }
}
