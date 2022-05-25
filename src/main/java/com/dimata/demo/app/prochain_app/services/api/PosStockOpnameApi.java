package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.PosStockOpnameForm;
import com.dimata.demo.app.prochain_app.models.table.PosStockOpname;
import com.dimata.demo.app.prochain_app.services.crude.PosStockOpnameCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import lombok.var;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PosStockOpnameApi {

    @Autowired
    private PosStockOpnameCrude posStockOpnameCrude;
    private PosStockOpnameItemApi posStockOpnameItemApi;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<PosStockOpname> createPosStockOpname(PosStockOpnameForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            PosStockOpnameCrude.Option option = PosStockOpnameCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(posStockOpnameCrude::create);
    }

    public Flux<PosStockOpname> getAllPosStockOpname(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(PosStockOpname.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosStockOpname::fromRow)
            .all();
    }

    public Mono<PosStockOpname> getPosStockOpname(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PosStockOpname.TABLE_NAME)
            .addWhere(WhereQuery.when(PosStockOpname.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosStockOpname::fromRow)
            .one();
    }

    public Mono<PosStockOpname> updatePosStockOpname(Long id, PosStockOpnameForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                PosStockOpnameCrude.Option option = PosStockOpnameCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(posStockOpnameCrude::updateRecord);
    }  

    public Flux<PosStockOpname> getAllLocation(Long locationId) {
        var sql = SelectQBuilder.emptyBuilder(PosStockOpname.TABLE_NAME)
            .addWhere(WhereQuery.when(PosStockOpname.LOCATION_ID_COL).is(locationId))
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosStockOpname::fromRow)
            .all();
    }

    public Flux<PosStockOpname> getAllPosStockOpnameByLocation(Long id) {
        var sql = SelectQBuilder.builder(PosStockOpname.TABLE_NAME,id)
            // .addColumn(CollumnQuery.add(PosStockOpname.TABLE_NAME + ". " + "*"))
            .addWhere(WhereQuery.when(PosStockOpname.LOCATION_ID_COL).is(id))
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosStockOpname::fromRow)
            .all();
}

public Flux<PosStockOpname> getMaterialAndOpnameByLocation(Long locatioId) {
    return Flux.just(locatioId)
    .flatMap(this::getAllPosStockOpnameByLocation);
    // .flatMap(f -> {
    //     return posStockOpnameItemApi.getAllPosStockOpnameItemByPosStockOpnameId(f.getId()).collectList()
    //         .flatMap(k -> {
    //             PosStockOpnameRelation relation = new PosStockOpnameRelation();
    //             relation.setOpname(f);
    //             if (k.isEmpty()) {
    //                 relation.setItems(k);
    //             }
    //             return Mono.just(relation);
    //         });
    // });
    // .flatMap(f -> {

        // System.out.println(f.toString());
        // StockOpnameItemRelation data = new StockOpnameItemRelation();
        // var material = posMaterialApi.getPosMaterial(f.getMaterialId());
        // var opnameType = posStockOpnameApi.getPosStockOpname(f.getStockOpnameId());
        // return Mono.zip(Mono.just(f), material, opnameType)
        //     .map(z -> {
        //         data.setMaterial(z.getT2());
        //         data.setStockOpname(z.getT3());
        //         data.setRelation(z.getT1());
        //         return data;
        //     });
    // });
}
}
