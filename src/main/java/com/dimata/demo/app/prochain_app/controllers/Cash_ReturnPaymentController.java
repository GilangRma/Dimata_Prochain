package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.Cash_ReturnPaymentForm;
import com.dimata.demo.app.prochain_app.models.table.Cash_ReturnPayment;
import com.dimata.demo.app.prochain_app.services.api.Cash_ReturnPaymentApi;

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
public class Cash_ReturnPaymentController {

    @Autowired
    private Cash_ReturnPaymentApi cash_ReturnPaymentApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/cash_returnpayment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Cash_ReturnPayment> maintainerAddCashReturnPayment(@RequestBody Cash_ReturnPaymentForm form) {
       
        return cash_ReturnPaymentApi.createCash_ReturnPayment(form);
    }

    @GetMapping(path = BASE_URL + "/cash_returnpayment")
    public Flux<Cash_ReturnPayment> maintainerGetAllCashReturnPayment(CommonParam param) {
       
        return cash_ReturnPaymentApi.getAllCash_ReturnPayment(param);
    }

    @GetMapping(path = BASE_URL + "/cash_returnpayment/{RETURN_ID}")
    public Mono<Cash_ReturnPayment> maintainerGetCashReturnPayment(@PathVariable("RETURN_ID") Long RETURN_ID) {
       
        return cash_ReturnPaymentApi.getCash_ReturnPayment(RETURN_ID);
    }

    @PutMapping(path = BASE_URL + "/cash_returnpayment/{RETURN_ID}")
    public Mono<Cash_ReturnPayment> maintainerUpdateCashReturnPayment(@PathVariable("RETURN_ID") long RETURN_ID, @RequestBody Cash_ReturnPaymentForm form) {
        return cash_ReturnPaymentApi.updateCash_ReturnPayment(RETURN_ID, form);
    }
    
}
