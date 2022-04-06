package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.CashCashierForm;
import com.dimata.demo.app.prochain_app.forms.relation.CashCashierRelation;
import com.dimata.demo.app.prochain_app.models.table.CashCashier;
import com.dimata.demo.app.prochain_app.models.table.CashMaster;
import com.dimata.demo.app.prochain_app.services.api.CashCashierApi;

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
public class CashCashierController {

    @Autowired
    private CashCashierApi cashCashierApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/cash_cashier", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CashCashier> maintainerAddCashCashier(@RequestBody CashCashierForm form) {
       
        return cashCashierApi.createCashCashier(form);
    }

    @GetMapping(path = BASE_URL + "/cash_cashier")
    public Flux<CashCashier> maintainerGetAllCashCashier(CommonParam param) {
       
        return cashCashierApi.getAllCashCashier(param);
    }

    @GetMapping(path = BASE_URL + "/cash_cashier/{CASH_CASHIER_ID}")
    public Mono<CashCashier> maintainerGetCashCashier(@PathVariable("CASH_CASHIER_ID") Long CASH_CASHIER_ID) {
       
        return cashCashierApi.getCashCashier(CASH_CASHIER_ID);
    }

    @PutMapping(path = BASE_URL + "/cash_cashier/{CASH_CASHIER_ID}")
    public Mono<CashCashier> maintainerUpdateCashCashier(@PathVariable("CASH_CASHIER_ID") long CASH_CASHIER_ID, @RequestBody CashCashierForm form) {
        return cashCashierApi.updateCashCashier(CASH_CASHIER_ID, form);
    }

    @PostMapping(path = BASE_URL + "/cash_cashier/cash_master_id")
    public Mono<CashMaster> maintainerIdCashier(@RequestBody CashCashierRelation form) {
        return cashCashierApi.checkAvailableData(form)
            .flatMap(f -> cashCashierApi.getUserCashMaster(f.getId()));
    }
    
    
}
