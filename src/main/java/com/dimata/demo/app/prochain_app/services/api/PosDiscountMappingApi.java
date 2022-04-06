package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.exception.DataNotFoundException;
import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.JoinQuery;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.PosDiscountMappingForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosDiscountMappingRelation;
import com.dimata.demo.app.prochain_app.models.table.DiscountType;
import com.dimata.demo.app.prochain_app.models.table.PosDiscountMapping;
import com.dimata.demo.app.prochain_app.services.crude.PosDiscountMappingCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PosDiscountMappingApi {
    @Autowired
    private  PosDiscountMappingCrude posDiscountMappingCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<PosDiscountMapping> createPosDiscountMapping(PosDiscountMappingForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            PosDiscountMappingCrude.Option option = PosDiscountMappingCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(posDiscountMappingCrude::create);
    }

    public Flux<PosDiscountMapping> getAllPosDiscountMapping(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(PosDiscountMapping.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosDiscountMapping::fromRow)
            .all();
    }

    public Mono<PosDiscountMapping> getPosDiscountMapping(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PosDiscountMapping.TABLE_NAME)
            .addWhere(WhereQuery.when(PosDiscountMapping.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosDiscountMapping::fromRow)
            .one();
    }

    public Mono<PosDiscountMapping> updatePosDiscountMapping(Long id, PosDiscountMappingForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                PosDiscountMappingCrude.Option option = PosDiscountMappingCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(posDiscountMappingCrude::updateRecord);
    }
    public Mono<PosDiscountMapping> checkAvailableData(PosDiscountMappingRelation form){
        var sql = SelectQBuilder.emptyBuilder(PosDiscountMapping.TABLE_NAME)
        .addJoin(JoinQuery.doLeftJoin(
            PosDiscountMapping.TABLE_NAME
            )
            .on(WhereQuery.when((PosDiscountMapping.TABLE_NAME + "." + PosDiscountMapping.ID_COL))
            .is(DiscountType.TABLE_NAME + "." + DiscountType.ID_COL)))
            
        .addWhere(WhereQuery.when(PosDiscountMapping.ID_COL).is(form.getId()))
        .build();
        return template.getDatabaseClient()
        .sql(sql)
        .map(PosDiscountMapping::fromRow)
        .one()
        .switchIfEmpty(Mono.error(new DataNotFoundException("id price type salah")));
}
}
