package com.dimata.demo.app.prochain_app.services.api;


import com.dimata.demo.app.prochain_app.core.exception.DataNotFoundException;
import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.JoinQuery;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.PosDiscountQtyMappingForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosDiscountQTYMappingRelation;
import com.dimata.demo.app.prochain_app.models.table.DiscountType;
import com.dimata.demo.app.prochain_app.models.table.Location;
import com.dimata.demo.app.prochain_app.models.table.PosDiscountQtyMapping;
import com.dimata.demo.app.prochain_app.services.crude.PosDiscountQtyMappingCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PosDiscountQtyMappingApi {
    @Autowired
    private PosDiscountQtyMappingCrude posDiscountQtyMappingCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<PosDiscountQtyMapping> createPosDiscountQtyMapping(PosDiscountQtyMappingForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            PosDiscountQtyMappingCrude.Option option = PosDiscountQtyMappingCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(posDiscountQtyMappingCrude::create);
    }

    public Flux<PosDiscountQtyMapping> getAllPosDiscountQtyMapping(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(PosDiscountQtyMapping.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosDiscountQtyMapping::fromRow)
            .all();
    }

    public Mono<PosDiscountQtyMapping> getPosDiscountQtyMapping(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PosDiscountQtyMapping.TABLE_NAME)
            .addWhere(WhereQuery.when(PosDiscountQtyMapping.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosDiscountQtyMapping::fromRow)
            .one();
    }

    public Mono<PosDiscountQtyMapping> updatePosDiscountQtyMapping(Long id, PosDiscountQtyMappingForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                PosDiscountQtyMappingCrude.Option option = PosDiscountQtyMappingCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(posDiscountQtyMappingCrude::updateRecord);
    }
    public Mono<PosDiscountQtyMapping> checkAvailableData(PosDiscountQTYMappingRelation form){
        var sql = SelectQBuilder.emptyBuilder(PosDiscountQtyMapping.TABLE_NAME)
        .addJoin(JoinQuery.doLeftJoin(
            PosDiscountQtyMapping.TABLE_NAME
            )
            .on(WhereQuery.when((PosDiscountQtyMapping.TABLE_NAME + "." + PosDiscountQtyMapping.ID_COL + "." + PosDiscountQtyMapping.LOCATION_ID_COL))
            .is(DiscountType.TABLE_NAME + "." + DiscountType.ID_COL + "." + Location.TABLE_NAME + "." + Location.ID_COL)))
            
        .addWhere(WhereQuery.when(PosDiscountQtyMapping.ID_COL).is(form.getId())
        .and(WhereQuery.when(PosDiscountQtyMapping.LOCATION_ID_COL).is(form.getLocationId())))
        .build();
        return template.getDatabaseClient()
        .sql(sql)
        .map(PosDiscountQtyMapping::fromRow)
        .one()
        .switchIfEmpty(Mono.error(new DataNotFoundException("id price type salah")));
}
}
