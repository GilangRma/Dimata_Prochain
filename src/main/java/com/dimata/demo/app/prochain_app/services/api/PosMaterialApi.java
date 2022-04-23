package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.PosMaterialForm;
import com.dimata.demo.app.prochain_app.models.table.PosMaterial;
import com.dimata.demo.app.prochain_app.services.crude.PosMaterialCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PosMaterialApi {
    @Autowired
    private  PosMaterialCrude posMaterialCrude;
    
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<PosMaterial> createPosDiscountMapping(PosMaterialForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            PosMaterialCrude.Option option = PosMaterialCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(posMaterialCrude::create);
    }

    public Flux<PosMaterial> getAllPosDiscountMapping(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(PosMaterial.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosMaterial::fromRow)
            .all();
    }

    public Mono<PosMaterial> getPosDiscountMapping(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PosMaterial.TABLE_NAME)
            .addWhere(WhereQuery.when(PosMaterial.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosMaterial::fromRow)
            .one();
    }

    public Mono<PosMaterial> updatePosDiscountMapping(Long id, PosMaterialForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                PosMaterialCrude.Option option = PosMaterialCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(posMaterialCrude::updateRecord);
    }

}
