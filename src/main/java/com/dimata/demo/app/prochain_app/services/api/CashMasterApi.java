package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.exception.DataNotFoundException;
import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.JoinQuery;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.CashMasterForm;
import com.dimata.demo.app.prochain_app.forms.relation.CashMasterRelation;
import com.dimata.demo.app.prochain_app.models.table.CashMaster;
import com.dimata.demo.app.prochain_app.models.table.Location;
import com.dimata.demo.app.prochain_app.services.crude.CashMasterCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CashMasterApi {

    @Autowired
    private CashMasterCrude cashMasterCrude;
    @Autowired
    private LocationApi locationApi;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<CashMaster> createCashMaster(CashMasterForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            CashMasterCrude.Option option = CashMasterCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(cashMasterCrude::create);
    }

    public Flux<CashMaster> getAllCashMaster(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(CashMaster.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(CashMaster::fromRow)
            .all();
    }

    public Mono<CashMaster> getCashMaster(Long id) {
        var sql = SelectQBuilder.emptyBuilder(CashMaster.TABLE_NAME)
            .addWhere(WhereQuery.when(CashMaster.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(CashMaster::fromRow)
            .one();
    }

    public Mono<CashMaster> updateCashMaster(Long id, CashMasterForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                CashMasterCrude.Option option = CashMasterCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(cashMasterCrude::updateRecord);
    }  

    public Mono<CashMaster> getDataByCashMasterId(Long id) {
        var sql = SelectQBuilder.emptyBuilder(CashMaster.TABLE_NAME)
            .addWhere(WhereQuery.when(CashMaster.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(CashMaster::fromRow)
            .one();
    }

    public Mono<CashMaster> checkAvailableData(CashMasterRelation form){
        var sql = SelectQBuilder.emptyBuilder(CashMaster.TABLE_NAME)
        .addJoin(JoinQuery.doLeftJoin(
            Location.TABLE_NAME
            )
            .on(WhereQuery.when((CashMaster.TABLE_NAME + "." + CashMaster.LOCATION_ID_COL))
            .is(Location.TABLE_NAME + "." + Location.ID_COL)))

        .addWhere(WhereQuery.when(CashMaster.TABLE_NAME + "." +CashMaster.LOCATION_ID_COL).is(form.getLocationId()))
        .build();
        System.out.println(sql);
        return template.getDatabaseClient()
        .sql(sql)
        .map(CashMaster::fromRow)
        .one()
        .switchIfEmpty(Mono.error(new DataNotFoundException("id Location anda salah")));

    }
    
    public Mono<Location> getDataLocation(Long id) {
        return locationApi.getDataByLocation(id);    
}
}  

