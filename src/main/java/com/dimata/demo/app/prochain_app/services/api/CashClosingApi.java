package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.CashClosingForm;
import com.dimata.demo.app.prochain_app.models.table.CashClosing;
import com.dimata.demo.app.prochain_app.services.crude.CashClosingCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CashClosingApi {

    @Autowired
    private CashClosingCrude cashClosingCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<CashClosing> createCashClosing(CashClosingForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            CashClosingCrude.Option option = CashClosingCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(cashClosingCrude::create);
    }

    public Flux<CashClosing> getAllCashClosing(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(CashClosing.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(CashClosing::fromRow)
            .all();
    }

    public Mono<CashClosing> getCashClosing(Long id) {
        var sql = SelectQBuilder.emptyBuilder(CashClosing.TABLE_NAME)
            .addWhere(WhereQuery.when(CashClosing.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(CashClosing::fromRow)
            .one();
    }

    public Mono<CashClosing> updateCashClosing(Long id, CashClosingForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                CashClosingCrude.Option option = CashClosingCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(cashClosingCrude::updateRecord);
    }  
}
