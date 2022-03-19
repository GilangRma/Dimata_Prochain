package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.CashReturnPaymentForm;
import com.dimata.demo.app.prochain_app.models.table.CashReturnPayment;
import com.dimata.demo.app.prochain_app.services.crude.CashReturnPaymentCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CashReturnPaymentApi {
    
    @Autowired
    private CashReturnPaymentCrude cashReturnPaymentCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<CashReturnPayment> createCashReturnPayment(CashReturnPaymentForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            CashReturnPaymentCrude.Option option = CashReturnPaymentCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(cashReturnPaymentCrude::create);
    }

    public Flux<CashReturnPayment> getAllCashReturnPayment(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(CashReturnPayment.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(CashReturnPayment::fromRow)
            .all();
    }

    public Mono<CashReturnPayment> getCashReturnPayment(Long id) {
        var sql = SelectQBuilder.emptyBuilder(CashReturnPayment.TABLE_NAME)
            .addWhere(WhereQuery.when(CashReturnPayment.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(CashReturnPayment::fromRow)
            .one();
    }

    public Mono<CashReturnPayment> updateCashReturnPayment(Long id, CashReturnPaymentForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                CashReturnPaymentCrude.Option option = CashReturnPaymentCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(cashReturnPaymentCrude::updateRecord);
    }  
}
