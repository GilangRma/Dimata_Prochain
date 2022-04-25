package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.exception.DataNotFoundException;
import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.JoinQuery;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.PosMaterialDetailForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosMaterialDetailRelation;
import com.dimata.demo.app.prochain_app.models.table.PosMaterial;
import com.dimata.demo.app.prochain_app.models.table.PosMaterialDetail;
import com.dimata.demo.app.prochain_app.services.crude.PosMaterialDetailCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PosMaterialDetailApi {
    @Autowired
    private  PosMaterialDetailCrude posMaterialDetailCrude;
    @Autowired 
    private PosMaterialApi posMaterialApi;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<PosMaterialDetail> createPosMaterialDetail(PosMaterialDetailForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            PosMaterialDetailCrude.Option option = PosMaterialDetailCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(posMaterialDetailCrude::create);
    }

    public Flux<PosMaterialDetail> getAllPosMaterialDetail(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(PosMaterialDetail.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosMaterialDetail::fromRow)
            .all();
    }

    public Mono<PosMaterialDetail> getPosMaterialDetail(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PosMaterialDetail.TABLE_NAME)
            .addWhere(WhereQuery.when(PosMaterialDetail.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosMaterialDetail::fromRow)
            .one();
    }

    public Mono<PosMaterialDetail> updatePosMaterialDetail(Long id, PosMaterialDetailForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                PosMaterialDetailCrude.Option option = PosMaterialDetailCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(posMaterialDetailCrude::updateRecord);
    }
//relation
public Mono<PosMaterialDetail> checkAvailableData(PosMaterialDetailRelation form){
    var sql = SelectQBuilder.emptyBuilder(PosMaterialDetail.TABLE_NAME)
    .addJoin(JoinQuery.doLeftJoin(
        PosMaterial.TABLE_NAME
        )
        .on(WhereQuery.when((PosMaterialDetail.TABLE_NAME + "." + PosMaterialDetail.MATERIAL_ID_COL))
        .is( PosMaterial.TABLE_NAME + "." + PosMaterial.ID_COL)))
        
    .addWhere(WhereQuery.when(PosMaterialDetail.TABLE_NAME + "." + PosMaterialDetail.MATERIAL_ID_COL).is(form.getMaterialId()))
    .build();

    System.out.println(sql);
    return template.getDatabaseClient()
    .sql(sql)
    .map(PosMaterialDetail::fromRow)
    .one()
    .switchIfEmpty(Mono.error(new DataNotFoundException("id material anda salah")));
}
public Mono<PosMaterial> getDataByMaterial(Long id) {
    return posMaterialApi.getDataByMaterial(id);
}

}
