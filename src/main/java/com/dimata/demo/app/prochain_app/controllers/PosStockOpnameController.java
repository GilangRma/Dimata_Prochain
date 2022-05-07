package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.PosStockOpnameForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosStockOpnameRelation;
import com.dimata.demo.app.prochain_app.models.table.PosStockOpname;
import com.dimata.demo.app.prochain_app.services.api.PosStockOpnameApi;
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
public class PosStockOpnameController {

    @Autowired
    private PosStockOpnameApi posStockOpnameApi;
    @Autowired
    private PosStockOpnameItemApi posStockOpnameItemApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/pos_stock_opname", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PosStockOpname> maintainerAddPosStockOpname(@RequestBody PosStockOpnameForm form) {
       
        return posStockOpnameApi.createPosStockOpname(form);
    }

    @GetMapping(path = BASE_URL + "/pos_stock_opname")
    public Flux<PosStockOpname> maintainerGetAllPosStockOpname(CommonParam param) {
       
        return posStockOpnameApi.getAllPosStockOpname(param);
    }

    @GetMapping(path = BASE_URL + "/pos_stock_opname/{STOCK_OPNAME_ID}")
    public Mono<PosStockOpname> maintainerGetPosStockOpname(@PathVariable("STOCK_OPNAME_ID") Long STOCK_OPNAME_ID) {
       
        return posStockOpnameApi.getPosStockOpname(STOCK_OPNAME_ID);
    }

    @PutMapping(path = BASE_URL + "/pos_stock_opname/{STOCK_OPNAME_ID}")
    public Mono<PosStockOpname> maintainerUpdatePosStockOpname(@PathVariable("STOCK_OPNAME_ID") long STOCK_OPNAME_ID, @RequestBody PosStockOpnameForm form) {
        return posStockOpnameApi.updatePosStockOpname(STOCK_OPNAME_ID, form);
    }
    
    @GetMapping(path = BASE_URL + "/locationId/{LOCATION_ID}")
    public Flux<PosStockOpname> maintainerGetAllLocationId(@PathVariable("LOCATION_ID")Long LOCATION_ID) {
       
        return posStockOpnameApi.getAllLocation(LOCATION_ID);
    }

    @GetMapping(path = BASE_URL + "/pos_relation_opname/{locationId}")
    public Flux<PosStockOpnameRelation> maintainerGetPosRelation(@PathVariable("locationId") Long id) {
        return posStockOpnameApi.getMaterialAndOpnameByLocation(id)
            .flatMap(f -> {
                return posStockOpnameItemApi.getAllPosStockOpnameItemByPosStockOpnameId(f.getId()).collectList()
                    .flatMap(k -> {
                        PosStockOpnameRelation relation = new PosStockOpnameRelation();
                        relation.setOpname(f);
                        if (k.isEmpty()) {
                            relation.setItems(k);
                        }
                        return Mono.just(relation);
                    });
            });
    }
}
