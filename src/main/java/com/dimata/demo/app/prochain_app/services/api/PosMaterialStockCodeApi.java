package com.dimata.demo.app.prochain_app.services.api;

import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.exception.FormatException;
import com.dimata.demo.app.prochain_app.core.search.CollumnQuery;
import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.PosMaterialAndStockForm;
import com.dimata.demo.app.prochain_app.forms.PosMaterialStockCodeForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosMaterialAndStockRelation;
import com.dimata.demo.app.prochain_app.models.table.Location;
import com.dimata.demo.app.prochain_app.models.table.PosMaterialStockCode;
import com.dimata.demo.app.prochain_app.services.crude.PosMaterialStockCodeCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PosMaterialStockCodeApi {
    @Autowired
    private PosMaterialStockCodeCrude posMaterialStockCodeCrude;
    @Autowired
    private PosMaterialApi posMaterialApi;
    @Autowired
    private LocationApi locationApi;
    @Autowired
    private PosMaterialStockApi posMaterialStockApi;
    @Autowired
    private R2dbcEntityTemplate template;

    public Mono<PosMaterialStockCode> createPosMaterialStockCode(PosMaterialStockCodeForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            PosMaterialStockCodeCrude.Option option = PosMaterialStockCodeCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(posMaterialStockCodeCrude::create);
    }

    public Flux<PosMaterialStockCode> getAllPosMaterialStockCode(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(PosMaterialStockCode.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosMaterialStockCode::fromRow)
            .all();
    }

    public Flux<PosMaterialStockCode> getAllPosMaterialStockCodeByLocation(Long id) {
        var sql = SelectQBuilder.builder(PosMaterialStockCode.TABLE_NAME, id)
            .addColumn(CollumnQuery.add(PosMaterialStockCode.TABLE_NAME + "." + "*"))
            .addWhere(WhereQuery.when(PosMaterialStockCode.LOCATION_ID).is(id))
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosMaterialStockCode::fromRow)
            .all();
    }
//relation
    public Mono<PosMaterialAndStockRelation> createMaterialAndStock (PosMaterialAndStockForm form) {
        return Mono.just(form)
            //TODO: Aktifkan jika sudah buat service untuk CRUD pada table Location
            .filterWhen(f -> locationApi.checkDataExist(f.getLocationId()))
            .switchIfEmpty(Mono.error(new FormatException("Id lokasi tidak ditemukan")))
            .flatMap(f -> {
                var material = posMaterialApi.createPosMaterial(f.getMaterial())
                    .flatMap(d -> {
                        PosMaterialAndStockRelation data = new PosMaterialAndStockRelation();
                        data.setMaterial(d);
                        return Mono.just(data);
                    });
                return Mono.zip(Mono.just(f), material);
            })
            .flatMap(z -> {
                var stock = posMaterialStockApi.createPosMaterialStock(z.getT1().getStock())
                    .map(f -> {
                        z.getT2().setStock(f);
                        return z.getT2();
                    });
                return Mono.zip(Mono.just(z.getT1()), stock);
            })
            .flatMap(z -> {
                PosMaterialStockCodeForm postData = new PosMaterialStockCodeForm();
                postData.setLocationId(z.getT1().getLocationId());
                postData.setMaterialId(z.getT2().getMaterial().getId());
                postData.setStockCode(z.getT2().getStock().getId());
                postData.setStockStatus(z.getT1().getStockStatus());
                postData.setValue(z.getT1().getValue());
                postData.setStockDate(LocalDateTime.now());

                return createPosMaterialStockCode(postData)
                    .flatMap(f -> {
                        z.getT2().setRelation(f);
                        return Mono.just(z.getT2());
                    });
            });
    }
//relation
    public Flux<PosMaterialAndStockRelation> getMaterialAndStockByLocation(Long locationId) {
        return Flux.just(locationId)
            .flatMap(f -> getAllPosMaterialStockCodeByLocation(f))
            .flatMap(f -> {
                System.out.println(f.toString());
                PosMaterialAndStockRelation data = new PosMaterialAndStockRelation();
                var material = posMaterialApi.getPosMaterial(f.getMaterialId());
                var stock = posMaterialStockApi.getPosMaterialStock(f.getStockCode());
                return Mono.zip(Mono.just(f), material, stock)
                    .map(z -> {
                        data.setMaterial(z.getT2());
                        data.setRelation(z.getT1());
                        data.setStock(z.getT3());
                        return data;
                    });
            });
    }

    public Mono<PosMaterialStockCode> getPosMaterial(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PosMaterialStockCode.TABLE_NAME)
            .addWhere(WhereQuery.when(PosMaterialStockCode.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosMaterialStockCode::fromRow)
            .one();
    }

    public Mono<PosMaterialStockCode> updatePosMaterial(Long id, PosMaterialStockCodeForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                PosMaterialStockCodeCrude.Option option = PosMaterialStockCodeCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(posMaterialStockCodeCrude::updateRecord);
    }

    public  Mono<PosMaterialStockCode> getDataByMaterial(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PosMaterialStockCode.TABLE_NAME)
            .addWhere(WhereQuery.when(PosMaterialStockCode.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosMaterialStockCode::fromRow)
            .one();
    }

    public Mono<Location> getDataLocation(Long id) {
        return locationApi.getDataByLocation(id);
    }

    
}
