package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.CashReturnPaymentForm;
import com.dimata.demo.app.prochain_app.models.table.CashReturnPayment;
import com.dimata.demo.app.prochain_app.services.api.CashReturnPaymentApi;

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
public class CashReturnPaymentController {

    @Autowired
    private CashReturnPaymentApi cashReturnPaymentApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/cash_returnpayment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CashReturnPayment> maintainerAddCashReturnPayment(@RequestBody CashReturnPaymentForm form) {
       
        return cashReturnPaymentApi.createCashReturnPayment(form);
    }

    @GetMapping(path = BASE_URL + "/cash_returnpayment")
    public Flux<CashReturnPayment> maintainerGetAllCashReturnPayment(CommonParam param) {
       
        return cashReturnPaymentApi.getAllCashReturnPayment(param);
    }

    @GetMapping(path = BASE_URL + "/cash_returnpayment/{RETURN_ID}")
    public Mono<CashReturnPayment> maintainerGetCashReturnPayment(@PathVariable("RETURN_ID") Long RETURN_ID) {
       
        return cashReturnPaymentApi.getCashReturnPayment(RETURN_ID);
    }

    @PutMapping(path = BASE_URL + "/cash_returnpayment/{RETURN_ID}")
    public Mono<CashReturnPayment> maintainerUpdateCashReturnPayment(@PathVariable("RETURN_ID") long RETURN_ID, @RequestBody CashReturnPaymentForm form) {
        return cashReturnPaymentApi.updateCashReturnPayment(RETURN_ID, form);
    }
    
}
