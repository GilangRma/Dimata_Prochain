package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.PosUnitForm; 
import com.dimata.demo.app.prochain_app.models.table.PosUnit;
import com.dimata.demo.app.prochain_app.services.crude.PosUnitCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PosUnitApi {
    @Autowired
    private PosUnitCrude posUnitCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<PosUnit> createPosUnit(PosUnitForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            PosUnitCrude.Option option = PosUnitCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(posUnitCrude::create);
    }

    public Flux<PosUnit> getAllPosUnit(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(PosUnit.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosUnit::fromRow)
            .all();
    }

    public Mono<PosUnit> getPosUnit(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PosUnit.TABLE_NAME)
            .addWhere(WhereQuery.when(PosUnit.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosUnit::fromRow)
            .one();
    }

    public Mono<PosUnit> updatePosUnit(Long id, PosUnitForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                PosUnitCrude.Option option = PosUnitCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(posUnitCrude::updateRecord);
    }
}
