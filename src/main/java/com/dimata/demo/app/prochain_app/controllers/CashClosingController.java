package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.CashClosingForm;
import com.dimata.demo.app.prochain_app.models.table.CashClosing;
import com.dimata.demo.app.prochain_app.services.api.CashClosingApi;

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
public class CashClosingController {

    @Autowired
    private CashClosingApi cashClosingApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/cash_closing", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CashClosing> maintainerAddCashClosing(@RequestBody CashClosingForm form) {
       
        return cashClosingApi.createCashClosing(form);
    }

    @GetMapping(path = BASE_URL + "/cash_closing")
    public Flux<CashClosing> maintainerGetAllCashClosing(CommonParam param) {
       
        return cashClosingApi.getAllCashClosing(param);
    }

    @GetMapping(path = BASE_URL + "/cash_closing/{CASH_CLOSING_ID}")
    public Mono<CashClosing> maintainerGetCashClosing(@PathVariable("CASH_CLOSING_ID") Long CASH_CLOSING_ID) {
       
        return cashClosingApi.getCashClosing(CASH_CLOSING_ID);
    }

    @PutMapping(path = BASE_URL + "/cash_closing/{CASH_CLOSING_ID}")
    public Mono<CashClosing> maintainerUpdateCashClosing(@PathVariable("CASH_CLOSING_ID") long CASH_CLOSING_ID, @RequestBody CashClosingForm form) {
        return cashClosingApi.updateCashClosing(CASH_CLOSING_ID, form);
    }

    
}
