package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.Cash_ReturnPaymentForm;
import com.dimata.demo.app.prochain_app.models.table.Cash_ReturnPayment;
import com.dimata.demo.app.prochain_app.services.crude.Cash_ReturnPaymentCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Cash_ReturnPaymentApi {
    
    @Autowired
    private Cash_ReturnPaymentCrude cash_ReturnPaymentCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<Cash_ReturnPayment> createCash_ReturnPayment(Cash_ReturnPaymentForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            Cash_ReturnPaymentCrude.Option option = Cash_ReturnPaymentCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(cash_ReturnPaymentCrude::create);
    }

    public Flux<Cash_ReturnPayment> getAllCash_ReturnPayment(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(Cash_ReturnPayment.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(Cash_ReturnPayment::fromRow)
            .all();
    }

    public Mono<Cash_ReturnPayment> getCash_ReturnPayment(Long id) {
        var sql = SelectQBuilder.emptyBuilder(Cash_ReturnPayment.TABLE_NAME)
            .addWhere(WhereQuery.when(Cash_ReturnPayment.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(Cash_ReturnPayment::fromRow)
            .one();
    }

    public Mono<Cash_ReturnPayment> updateCash_ReturnPayment(Long id, Cash_ReturnPaymentForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                Cash_ReturnPaymentCrude.Option option = Cash_ReturnPaymentCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(cash_ReturnPaymentCrude::updateRecord);
    }  
}
