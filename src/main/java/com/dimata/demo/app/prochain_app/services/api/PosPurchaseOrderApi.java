package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.PosPurchaseOrderForm;
import com.dimata.demo.app.prochain_app.models.table.PosPurchaseOrder;
import com.dimata.demo.app.prochain_app.services.crude.PosPurchaseOrderCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PosPurchaseOrderApi {
    
    @Autowired
    private PosPurchaseOrderCrude posPurchaseOrderCrude;
    @Autowired
    private R2dbcEntityTemplate template;

    public Mono<PosPurchaseOrder> createPosPurchaseOrder(PosPurchaseOrderForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            PosPurchaseOrderCrude.Option option = PosPurchaseOrderCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(posPurchaseOrderCrude::create);
    }

    public Flux<PosPurchaseOrder> getAllPosPurchaseOrder(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(PosPurchaseOrder.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosPurchaseOrder::fromRow)
            .all();
    }

    public Mono<PosPurchaseOrder> getPosPurchaseOrder(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PosPurchaseOrder.TABLE_NAME)
            .addWhere(WhereQuery.when(PosPurchaseOrder.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosPurchaseOrder::fromRow)
            .one();
    }

    public Mono<PosPurchaseOrder> updatePosPurchaseOrder(Long id, PosPurchaseOrderForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                PosPurchaseOrderCrude.Option option = PosPurchaseOrderCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(posPurchaseOrderCrude::updateRecord);
    }
    
}
