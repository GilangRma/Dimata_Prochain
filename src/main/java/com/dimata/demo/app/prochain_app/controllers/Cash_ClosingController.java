package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.Cahs_ClosingForm;
import com.dimata.demo.app.prochain_app.models.table.Cash_Closing;
import com.dimata.demo.app.prochain_app.services.api.Cash_ClosingApi;

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
public class Cash_ClosingController {

    @Autowired
    private Cash_ClosingApi cash_ClosingApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/cash_closing", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Cash_Closing> maintainerAddCashClosing(@RequestBody Cahs_ClosingForm form) {
       
        return cash_ClosingApi.createCash_Closing(form);
    }

    @GetMapping(path = BASE_URL + "/cash_closing")
    public Flux<Cash_Closing> maintainerGetAllCashClosing(CommonParam param) {
       
        return cash_ClosingApi.getAllCash_Closing(param);
    }

    @GetMapping(path = BASE_URL + "/cash_closing/{CASH_CLOSING_ID}")
    public Mono<Cash_Closing> maintainerGetCashClosing(@PathVariable("CASH_CLOSING_ID") Long CASH_CLOSING_ID) {
       
        return cash_ClosingApi.getCash_Closing(CASH_CLOSING_ID);
    }

    @PutMapping(path = BASE_URL + "/cash_closing/{CASH_CLOSING_ID}")
    public Mono<Cash_Closing> maintainerUpdateCashClosing(@PathVariable("CASH_CLOSING_ID") long CASH_CLOSING_ID, @RequestBody Cahs_ClosingForm form) {
        return cash_ClosingApi.updateCash_Closing(CASH_CLOSING_ID, form);
    }

    
}
