package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.Cash_CashierForm;
import com.dimata.demo.app.prochain_app.models.table.Cash_Cashier;
import com.dimata.demo.app.prochain_app.services.crude.Cash_CashierCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Cash_CashierApi {

    @Autowired
    private Cash_CashierCrude cash_CashierCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<Cash_Cashier> createCash_Cashier(Cash_CashierForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            Cash_CashierCrude.Option option = Cash_CashierCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(cash_CashierCrude::create);
    }

    public Flux<Cash_Cashier> getAllCash_Cashier(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(Cash_Cashier.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(Cash_Cashier::fromRow)
            .all();
    }

    public Mono<Cash_Cashier> getCash_Cashier(Long id) {
        var sql = SelectQBuilder.emptyBuilder(Cash_Cashier.TABLE_NAME)
            .addWhere(WhereQuery.when(Cash_Cashier.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(Cash_Cashier::fromRow)
            .one();
    }

    public Mono<Cash_Cashier> updateCash_Cashier(Long id, Cash_CashierForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                Cash_CashierCrude.Option option = Cash_CashierCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(cash_CashierCrude::updateRecord);
    }  
    
}
