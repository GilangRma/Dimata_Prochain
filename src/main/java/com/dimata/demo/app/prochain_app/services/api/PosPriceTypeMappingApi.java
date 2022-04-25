package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.exception.DataNotFoundException;
import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.JoinQuery;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.PosPriceTypeMappingForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosPriceTypeMappingRelation;
import com.dimata.demo.app.prochain_app.models.table.PosMaterial;
import com.dimata.demo.app.prochain_app.models.table.PosPriceTypeMapping;
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
    private PosMaterialApi posMaterialApi;
    
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
    
    //relation
public Mono<PosPriceTypeMapping> checkAvailableData(PosPriceTypeMappingRelation form){
    var sql = SelectQBuilder.emptyBuilder(PosPriceTypeMapping.TABLE_NAME)
    .addJoin(JoinQuery.doLeftJoin(
        PosMaterial.TABLE_NAME
        )
        .on(WhereQuery.when((PosPriceTypeMapping.TABLE_NAME + "." + PosPriceTypeMapping.MATERIAL_ID_COL))
        .is( PosMaterial.TABLE_NAME + "." + PosMaterial.ID_COL)))
        
    .addWhere(WhereQuery.when(PosPriceTypeMapping.TABLE_NAME + "." + PosPriceTypeMapping.MATERIAL_ID_COL).is(form.getMaterialId()))
    .build();

    System.out.println(sql);
    return template.getDatabaseClient()
    .sql(sql)
    .map(PosPriceTypeMapping::fromRow)
    .one()
    .switchIfEmpty(Mono.error(new DataNotFoundException("id material anda salah")));
}
public Mono<PosMaterial> getDataByMaterial(Long id) {
    return posMaterialApi.getDataByMaterial(id);
}
}
