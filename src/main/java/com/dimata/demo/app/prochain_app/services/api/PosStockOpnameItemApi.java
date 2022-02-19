package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.PosStockOpnameItemForm;
import com.dimata.demo.app.prochain_app.models.table.PosStockOpnameItem;
import com.dimata.demo.app.prochain_app.services.crude.PosStockOpnameItemCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PosStockOpnameItemApi {
    @Autowired
    private PosStockOpnameItemCrude posStockOpnameItemCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<PosStockOpnameItem> createPosStockOpnameItem(PosStockOpnameItemForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            PosStockOpnameItemCrude.Option option = PosStockOpnameItemCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(posStockOpnameItemCrude::create);
    }

    public Flux<PosStockOpnameItem> getAllPosStockOpnameItem(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(PosStockOpnameItem.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosStockOpnameItem::fromRow)
            .all();
    }

    public Mono<PosStockOpnameItem> getPosStockOpnameItem(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PosStockOpnameItem.TABLE_NAME)
            .addWhere(WhereQuery.when(PosStockOpnameItem.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosStockOpnameItem::fromRow)
            .one();
    }

    public Mono<PosStockOpnameItem> updatePosStockOpnameItem(Long id, PosStockOpnameItemForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                PosStockOpnameItemCrude.Option option = PosStockOpnameItemCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(posStockOpnameItemCrude::updateRecord);
    }
}
