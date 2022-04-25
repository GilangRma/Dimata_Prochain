package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.PosPurchaseOrderForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosPurchaseOrderRelation;
import com.dimata.demo.app.prochain_app.models.table.Location;
import com.dimata.demo.app.prochain_app.models.table.PosPurchaseOrder;
import com.dimata.demo.app.prochain_app.services.api.PosPurchaseOrderApi;

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
public class PosPurchaseOrderController {

    @Autowired
    private PosPurchaseOrderApi posPurchaseOrderApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/pos_purchase_order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PosPurchaseOrder> maintainerAddPosPurchaseOrder(@RequestBody PosPurchaseOrderForm form) {
       
        return posPurchaseOrderApi.createPosPurchaseOrder(form);
    }

    @GetMapping(path = BASE_URL + "/pos_purchase_order")
    public Flux<PosPurchaseOrder> maintainerGetAllPosPurchaseOrder(CommonParam param) {
       
        return posPurchaseOrderApi.getAllPosPurchaseOrder(param);
    }

    @GetMapping(path = BASE_URL + "/pos_purchase_order/{PURCHASE_ORDER_ID}")
    public Mono<PosPurchaseOrder> maintainerGetPosPurchaseOrder(@PathVariable("PURCHASE_ORDER_ID") Long PURCHASE_ORDER_ID) {
       
        return posPurchaseOrderApi.getPosPurchaseOrder(PURCHASE_ORDER_ID);
    }

    @PutMapping(path = BASE_URL + "/pos_purchase_order/{PURCHASE_ORDER_ID}")
    public Mono<PosPurchaseOrder> maintainerUpdatePosPurchaseOrder(@PathVariable("PURCHASE_ORDER_ID") long PURCHASE_ORDER_ID, @RequestBody PosPurchaseOrderForm form) {
        return posPurchaseOrderApi.updatePosPurchaseOrder(PURCHASE_ORDER_ID, form);
    }

    @PostMapping(path = BASE_URL + "/pos_purchase_order/location_id")
    public Mono<Location> maintainerLocationId(@RequestBody PosPurchaseOrderRelation form) {
        return posPurchaseOrderApi.checkAvailableData(form)
            .flatMap(f -> posPurchaseOrderApi.getDataLocation(f.getId()));
    }

    
}
