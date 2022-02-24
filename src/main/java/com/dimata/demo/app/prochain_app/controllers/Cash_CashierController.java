package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.Cash_CashierForm;
import com.dimata.demo.app.prochain_app.models.table.Cash_Cashier;
import com.dimata.demo.app.prochain_app.services.api.Cash_CashierApi;

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
public class Cash_CashierController {

    @Autowired
    private Cash_CashierApi cash_CashierApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/cash_cashier", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Cash_Cashier> maintainerAddCashCashier(@RequestBody Cash_CashierForm form) {
       
        return cash_CashierApi.createCash_Cashier(form);
    }

    @GetMapping(path = BASE_URL + "/cash_cashier")
    public Flux<Cash_Cashier> maintainerGetAllCashCashier(CommonParam param) {
       
        return cash_CashierApi.getAllCash_Cashier(param);
    }

    @GetMapping(path = BASE_URL + "/cash_cashier/{CASH_CASHIER_ID}")
    public Mono<Cash_Cashier> maintainerGetCashCashier(@PathVariable("CASH_CASHIER_ID") Long CASH_CASHIER_ID) {
       
        return cash_CashierApi.getCash_Cashier(CASH_CASHIER_ID);
    }

    @PutMapping(path = BASE_URL + "/cash_cashier/{CASH_CASHIER_ID}")
    public Mono<Cash_Cashier> maintainerUpdateCashCashier(@PathVariable("CASH_CASHIER_ID") long CASH_CASHIER_ID, @RequestBody Cash_CashierForm form) {
        return cash_CashierApi.updateCash_Cashier(CASH_CASHIER_ID, form);
    }

    
}
