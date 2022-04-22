package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.PosMaterialStockForm;
import com.dimata.demo.app.prochain_app.models.table.PosMaterialStock;
import com.dimata.demo.app.prochain_app.services.api.PosMaterialStockApi;

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
public class PosMaterialStockController {
    
    @Autowired
    private PosMaterialStockApi posMaterialStockApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/pos_material_stock", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PosMaterialStock> maintainerAddPosMaterialStock(@RequestBody PosMaterialStockForm form) {
       
        return posMaterialStockApi.createPosMaterialStock(form);
    }

    @GetMapping(path = BASE_URL + "/pos_material_stock")
    public Flux<PosMaterialStock> maintainerGetAllPosMaterialStock(CommonParam param) {
       
        return posMaterialStockApi.getAllPosMaterialStock(param);
    }

    @GetMapping(path = BASE_URL + "/pos_material_stock/{MATERIAL_STOCK_ID}")
    public Mono<PosMaterialStock> maintainerGetPosMaterialStock(@PathVariable("MATERIAL_STOCK_ID") Long MATERIAL_STOCK_ID) {
       
        return posMaterialStockApi.getPosMaterialStock(MATERIAL_STOCK_ID);
    }

    @PutMapping(path = BASE_URL + "/pos_material_stock/{MATERIAL_STOCK_ID}")
    public Mono<PosMaterialStock> maintainerUpdatePosMaterialStock(@PathVariable("MATERIAL_STOCK_ID") long MATERIAL_STOCK_ID, @RequestBody PosMaterialStockForm form) {
        return posMaterialStockApi.updatePosMaterialStock(MATERIAL_STOCK_ID, form);
    }

}
