package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.Cahs_ClosingForm;
import com.dimata.demo.app.prochain_app.models.table.Cash_Closing;
import com.dimata.demo.app.prochain_app.services.crude.Cash_ClosingCrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Cash_ClosingApi {

    @Autowired
    private Cash_ClosingCrud cash_ClosingCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<Cash_Closing> createCash_Closing(Cahs_ClosingForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            Cash_ClosingCrud.Option option = Cash_ClosingCrud.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(cash_ClosingCrude::create);
    }

    public Flux<Cash_Closing> getAllCash_Closing(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(Cash_Closing.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(Cash_Closing::fromRow)
            .all();
    }

    public Mono<Cash_Closing> getCash_Closing(Long id) {
        var sql = SelectQBuilder.emptyBuilder(Cash_Closing.TABLE_NAME)
            .addWhere(WhereQuery.when(Cash_Closing.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(Cash_Closing::fromRow)
            .one();
    }

    public Mono<Cash_Closing> updateCash_Closing(Long id, Cahs_ClosingForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                Cash_ClosingCrud.Option option = Cash_ClosingCrud.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(cash_ClosingCrude::updateRecord);
    }  
}
