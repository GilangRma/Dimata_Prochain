package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.exception.DataNotFoundException;
import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.JoinQuery;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.PosMaterialForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosMaterialRelation;
import com.dimata.demo.app.prochain_app.models.table.Location;
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
    private LocationApi locationApi;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<PosMaterial> createPosMaterial(PosMaterialForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            PosMaterialCrude.Option option = PosMaterialCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(posMaterialCrude::create);
    }

    public Flux<PosMaterial> getAllPosMaterial(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(PosMaterial.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosMaterial::fromRow)
            .all();
    }

    public Mono<PosMaterial> getPosMaterial(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PosMaterial.TABLE_NAME)
            .addWhere(WhereQuery.when(PosMaterial.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosMaterial::fromRow)
            .one();
    }

    public Mono<PosMaterial> updatePosMaterial(Long id, PosMaterialForm form) {
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
//relation
public  Mono<PosMaterial> getDataByMaterial(Long id) {
    var sql = SelectQBuilder.emptyBuilder(PosMaterial.TABLE_NAME)
        .addWhere(WhereQuery.when(PosMaterial.ID_COL).is(id))
        .build();
    System.out.println(sql);
    return template.getDatabaseClient()
        .sql(sql)
        .map(PosMaterial::fromRow)
        .one();
}
//relation
public Mono<PosMaterial> checkAvailableData(PosMaterialRelation form){
    var sql = SelectQBuilder.emptyBuilder(PosMaterial.TABLE_NAME)
    .addJoin(JoinQuery.doLeftJoin(
        Location.TABLE_NAME
        )
        .on(WhereQuery.when((PosMaterial.TABLE_NAME + "." + PosMaterial.LOCATION_ID_COL))
        .is( Location.TABLE_NAME + "." + Location.ID_COL)))
        
    .addWhere(WhereQuery.when(PosMaterial.TABLE_NAME + "." + PosMaterial.LOCATION_ID_COL).is(form.getLocationId()))
    .build();

    System.out.println(sql);
    return template.getDatabaseClient()
    .sql(sql)
    .map(PosMaterial::fromRow)
    .one()
    .switchIfEmpty(Mono.error(new DataNotFoundException("id lokasi anda salah")));
}
public Mono<Location> getDataLocation(Long id) {
return locationApi.getDataByLocation(id);
}
}
