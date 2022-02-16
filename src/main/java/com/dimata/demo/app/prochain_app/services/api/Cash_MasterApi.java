package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.Cash_MasterForm;
import com.dimata.demo.app.prochain_app.models.table.Cash_Master;
import com.dimata.demo.app.prochain_app.services.crude.Cash_MasterCrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Cash_MasterApi {

    @Autowired
    private Cash_MasterCrud cash_MasterCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<Cash_Master> createCash_Master(Cash_MasterForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            Cash_MasterCrud.Option option = Cash_MasterCrud.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(cash_MasterCrude::create);
    }

    public Flux<Cash_Master> getAllCash_Master(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(Cash_Master.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(Cash_Master::fromRow)
            .all();
    }

    public Mono<Cash_Master> getCash_Master(Long id) {
        var sql = SelectQBuilder.emptyBuilder(Cash_Master.TABLE_NAME)
            .addWhere(WhereQuery.when(Cash_Master.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(Cash_Master::fromRow)
            .one();
    }

    public Mono<Cash_Master> updateCash_Master(Long id, Cash_MasterForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                Cash_MasterCrud.Option option = Cash_MasterCrud.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(cash_MasterCrude::updateRecord);
    }  
    
}
