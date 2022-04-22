package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.PosMaterialStockForm;
import com.dimata.demo.app.prochain_app.models.table.PosMaterialStock;
import com.dimata.demo.app.prochain_app.services.crude.PosMaterialStockCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PosMaterialStockApi {

    @Autowired
    private PosMaterialStockCrude posMaterialStockCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<PosMaterialStock> createPosMaterialStock(PosMaterialStockForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            PosMaterialStockCrude.Option option = PosMaterialStockCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(posMaterialStockCrude::create);
    }

    public Flux<PosMaterialStock> getAllPosMaterialStock(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(PosMaterialStock.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosMaterialStock::fromRow)
            .all();
    }

    public Mono<PosMaterialStock> getPosMaterialStock(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PosMaterialStock.TABLE_NAME)
            .addWhere(WhereQuery.when(PosMaterialStock.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosMaterialStock::fromRow)
            .one();
    }
    public Mono<PosMaterialStock> updatePosMaterialStock(Long id, PosMaterialStockForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                PosMaterialStockCrude.Option option = PosMaterialStockCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(posMaterialStockCrude::updateRecord);
    }  
    
}
