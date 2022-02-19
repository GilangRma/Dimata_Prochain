package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.PosStockOpnameItemForm;
import com.dimata.demo.app.prochain_app.models.table.PosStockOpnameItem;
import com.dimata.demo.app.prochain_app.services.api.PosStockOpnameItemApi;

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
public class PosStockOpnameItemControler {
    @Autowired
    private PosStockOpnameItemApi posStockOpnameItemApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/pos_stock_opname_item", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PosStockOpnameItem> maintainerAddPosStockOpnameItem(@RequestBody PosStockOpnameItemForm form) {
        return posStockOpnameItemApi.createPosStockOpnameItem(form);
    }

    @GetMapping(path = BASE_URL + "/pos_stock_opname_item")
    public Flux<PosStockOpnameItem> maintainerGetAllPosStockOpnameItem(CommonParam param) {
        return posStockOpnameItemApi.getAllPosStockOpnameItem(param);
    }

    @GetMapping(path = BASE_URL + "/pos_stock_opname_item/{STOCK_OPNAME_ITEM_ID}")
    public Mono<PosStockOpnameItem> maintainerGetPosStockOpnameItem(@PathVariable("STOCK_OPNAME_ITEM_ID") Long STOCK_OPNAME_ITEM_ID) {
        return posStockOpnameItemApi.getPosStockOpnameItem(STOCK_OPNAME_ITEM_ID);
    }

    @PutMapping(path = BASE_URL + "/pos_stock_opname_item/{STOCK_OPNAME_ITEM_ID}")
    public Mono<PosStockOpnameItem> maintainerUpdatePosStockOpnameItem(@PathVariable("STOCK_OPNAME_ITEM_ID") long STOCK_OPNAME_ITEM_ID, @RequestBody PosStockOpnameItemForm form) {
        return posStockOpnameItemApi.updatePosStockOpnameItem(STOCK_OPNAME_ITEM_ID, form);
    }
}
