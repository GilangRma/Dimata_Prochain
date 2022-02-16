package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.Cash_MasterForm;
import com.dimata.demo.app.prochain_app.models.table.Cash_Master;
import com.dimata.demo.app.prochain_app.services.api.Cash_MasterApi;

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
public class Cash_MasterController {
    
    @Autowired
    private Cash_MasterApi cash_MasterApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/cash_master", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Cash_Master> maintainerAddCashClosing(@RequestBody Cash_MasterForm form) {
       
        return cash_MasterApi.createCash_Master(form);
    }

    @GetMapping(path = BASE_URL + "/cash_master")
    public Flux<Cash_Master> maintainerGetAllCashMaster(CommonParam param) {
       
        return cash_MasterApi.getAllCash_Master(param);
    }

    @GetMapping(path = BASE_URL + "/cash_master/{CASH_MASTER_ID}")
    public Mono<Cash_Master> maintainerGetCashMaster(@PathVariable("CASH_MASTER_ID") Long CASH_MASTER_ID) {
       
        return cash_MasterApi.getCash_Master(CASH_MASTER_ID);
    }

    @PutMapping(path = BASE_URL + "/cash_master/{CASH_MASTER_ID}")
    public Mono<Cash_Master> maintainerUpdateCashMaster(@PathVariable("CASH_MASTER_ID") long CASH_MASTER_ID, @RequestBody Cash_MasterForm form) {
        return cash_MasterApi.updateCash_Master(CASH_MASTER_ID, form);
    }
}
