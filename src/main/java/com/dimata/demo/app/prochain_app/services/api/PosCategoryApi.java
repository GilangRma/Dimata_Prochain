package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.exception.DataNotFoundException;
import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.JoinQuery;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;

import com.dimata.demo.app.prochain_app.forms.PosCategoryForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosCategoryRelation;
import com.dimata.demo.app.prochain_app.models.table.Location;
import com.dimata.demo.app.prochain_app.models.table.PosCategory;
import com.dimata.demo.app.prochain_app.services.crude.PosCategoryCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PosCategoryApi {
    @Autowired
    private PosCategoryCrude posCategoryCrude;
    @Autowired
    private LocationApi locationApi;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<PosCategory> createPosCategory(PosCategoryForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            PosCategoryCrude.Option option = PosCategoryCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(posCategoryCrude::create);
    }

    public Flux<PosCategory> getAllPosCatagory(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(PosCategory.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosCategory::fromRow)
            .all();
    }

    public Mono<PosCategory> getPosCatagory(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PosCategory.TABLE_NAME)
            .addWhere(WhereQuery.when(PosCategory.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosCategory::fromRow)
            .one();
    }

    public Mono<PosCategory> updatePosCatagory(Long id, PosCategoryForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                PosCategoryCrude.Option option = PosCategoryCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(posCategoryCrude::updateRecord);
    }
    public Mono<PosCategory> checkAvailableData(PosCategoryRelation form){
        var sql = SelectQBuilder.emptyBuilder(PosCategory.TABLE_NAME)
        .addJoin(JoinQuery.doLeftJoin(
            Location.TABLE_NAME
            )
            .on(WhereQuery.when((PosCategory.TABLE_NAME + "." + PosCategory.LOCATION_ID_COL))
            .is(Location.TABLE_NAME + "." + Location.ID_COL)))
            
        .addWhere(WhereQuery.when(PosCategory.TABLE_NAME + "." +PosCategory.LOCATION_ID_COL).is(form.getLocationId()))
        .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosCategory::fromRow)
            .one()
            .switchIfEmpty(Mono.error(new DataNotFoundException("id lokasi anda salah")));
}
public Mono<Location> getDataLocation(Long id) {
    return locationApi.getDataByLocation(id);
}
}
