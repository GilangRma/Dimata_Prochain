package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.exception.DataNotFoundException;
import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.JoinQuery;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.PosPriceTypeMappingForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosPriceMappingRelation;
import com.dimata.demo.app.prochain_app.models.table.PosPriceTypeMapping;
import com.dimata.demo.app.prochain_app.models.table.PriceType;
import com.dimata.demo.app.prochain_app.services.crude.PosPriceTypeMappingCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PosPriceTypeMappingApi {
    @Autowired
    private PosPriceTypeMappingCrude posPriceTypeMappingCrude;
    @Autowired
    private PriceTypeApi priceTypeApi;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<PosPriceTypeMapping> createPosPriceTypeMapping(PosPriceTypeMappingForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            PosPriceTypeMappingCrude.Option option = PosPriceTypeMappingCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(posPriceTypeMappingCrude::create);
    }

    public Flux<PosPriceTypeMapping> getAllPosPriceTypeMapping(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(PosPriceTypeMapping.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosPriceTypeMapping::fromRow)
            .all();
    }

    public Mono<PosPriceTypeMapping> getPosPriceTypeMapping(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PosPriceTypeMapping.TABLE_NAME)
            .addWhere(WhereQuery.when(PosPriceTypeMapping.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosPriceTypeMapping::fromRow)
            .one();
    }

    public Mono<PosPriceTypeMapping> updatePosPriceTypeMapping(Long id, PosPriceTypeMappingForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                PosPriceTypeMappingCrude.Option option = PosPriceTypeMappingCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(posPriceTypeMappingCrude::updateRecord);
    }
    public Mono<PosPriceTypeMapping> checkAvailableData(PosPriceMappingRelation form){
        var sql = SelectQBuilder.emptyBuilder(PosPriceTypeMapping.TABLE_NAME)
        .addJoin(JoinQuery.doLeftJoin(
            PosPriceTypeMapping.TABLE_NAME
            )
            .on(WhereQuery.when((PosPriceTypeMapping.TABLE_NAME + "." + PosPriceTypeMapping.ID_COL)).is(PriceType.TABLE_NAME + "." + PriceType.ID_COL)))
        .addWhere(WhereQuery.when(PosPriceTypeMapping.ID_COL).is(form.getId()))
        .build();
        return template.getDatabaseClient()
        .sql(sql)
        .map(PosPriceTypeMapping::fromRow)
        .one()
        .switchIfEmpty(Mono.error(new DataNotFoundException("id price type salah")));
        
    }
    public Mono<PriceType> getDataPriceType(Long id) {
        return priceTypeApi.getDataByPriceTypeId(id);
    }
}
