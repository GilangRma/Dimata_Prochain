package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.LocationForm;
import com.dimata.demo.app.prochain_app.models.table.Location;
import com.dimata.demo.app.prochain_app.services.crude.LocationCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LocationApi {

    @Autowired
    private LocationCrude locationCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<Location> createLocation(LocationForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            LocationCrude.Option option = LocationCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(locationCrude::create);
    }

    public Flux<Location> getAllLocation(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(Location.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(Location::fromRow)
            .all();
    }

    public Mono<Location> getLocation(Long id) {
        var sql = SelectQBuilder.emptyBuilder(Location.TABLE_NAME)
            .addWhere(WhereQuery.when(Location.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(Location::fromRow)
            .one();
    }

    public Mono<Location> updateLocation(Long id, LocationForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                LocationCrude.Option option = LocationCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(locationCrude::updateRecord);
    } 
    
    public Mono<Boolean> checkDataExist(Long id) {
        return locationCrude.checkIfLocationAvailable(id);
    }
    //relation
    public  Mono<Location> getDataByLocation(Long id) {
        var sql = SelectQBuilder.emptyBuilder(Location.TABLE_NAME)
            .addWhere(WhereQuery.when(Location.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(Location::fromRow)
            .one();
    }
}
