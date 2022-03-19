package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.CashMasterForm;
import com.dimata.demo.app.prochain_app.models.table.CashMaster;
import com.dimata.demo.app.prochain_app.services.api.CashMasterApi;

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
public class CashMasterController {
    
    @Autowired
    private CashMasterApi cashMasterApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/cash_master", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CashMaster> maintainerAddCashClosing(@RequestBody CashMasterForm form) {
       
        return cashMasterApi.createCashMaster(form);
    }

    @GetMapping(path = BASE_URL + "/cash_master")
    public Flux<CashMaster> maintainerGetAllCashMaster(CommonParam param) {
       
        return cashMasterApi.getAllCashMaster(param);
    }

    @GetMapping(path = BASE_URL + "/cash_master/{CASH_MASTER_ID}")
    public Mono<CashMaster> maintainerGetCashMaster(@PathVariable("CASH_MASTER_ID") Long CASH_MASTER_ID) {
       
        return cashMasterApi.getCashMaster(CASH_MASTER_ID);
    }

    @PutMapping(path = BASE_URL + "/cash_master/{CASH_MASTER_ID}")
    public Mono<CashMaster> maintainerUpdateCashMaster(@PathVariable("CASH_MASTER_ID") long CASH_MASTER_ID, @RequestBody CashMasterForm form) {
        return cashMasterApi.updateCashMaster(CASH_MASTER_ID, form);
    }
}
