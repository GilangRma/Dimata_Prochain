package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.exception.DataNotFoundException;
import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.JoinQuery;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.CashCashierForm;
import com.dimata.demo.app.prochain_app.forms.relation.CashCashierRelation;
import com.dimata.demo.app.prochain_app.models.table.CashCashier;
import com.dimata.demo.app.prochain_app.models.table.CashMaster;
import com.dimata.demo.app.prochain_app.services.crude.CashCashierCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CashCashierApi {

    @Autowired
    private CashCashierCrude cashCashierCrude;
    @Autowired
    private CashMasterApi cashMasterApi;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<CashCashier> createCashCashier(CashCashierForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            CashCashierCrude.Option option = CashCashierCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(cashCashierCrude::create);
    }

    public Flux<CashCashier> getAllCashCashier(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(CashCashier.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(CashCashier::fromRow)
            .all();
    }

    public Mono<CashCashier> getCashCashier(Long id) {
        var sql = SelectQBuilder.emptyBuilder(CashCashier.TABLE_NAME)
            .addWhere(WhereQuery.when(CashCashier.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(CashCashier::fromRow)
            .one();
    }

    public Mono<CashCashier> updateCashCashier(Long id, CashCashierForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                CashCashierCrude.Option option = CashCashierCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(cashCashierCrude::updateRecord);
    }
    
    public Mono<CashCashier> checkAvailableData(CashCashierRelation form){
        var sql = SelectQBuilder.emptyBuilder(CashCashier.TABLE_NAME)
        .addJoin(JoinQuery.doLeftJoin(
            CashCashier.TABLE_NAME
            )
            .on(WhereQuery.when(CashCashier.TABLE_NAME + "." + CashCashier.ID_COL).is(CashMaster.TABLE_NAME + "." + CashMaster.ID_COL)))
        .addWhere(WhereQuery.when(CashCashier.ID_COL).is(form.getId())
        .and(WhereQuery.when(CashCashier.CASH_MASTER_ID_COL).is(form.getCashMasterId()))
        .and(WhereQuery.when(CashCashier.SHIFT_ID_COL).is(form.getShiftId())))
        .build();
        return template.getDatabaseClient()
        .sql(sql)
        .map(CashCashier::fromRow)
        .one()
        .switchIfEmpty(Mono.error(new DataNotFoundException("id atau password anda salah")));

    }
    
    public Mono<CashMaster> getUserCashMaster(Long id) {
        return cashMasterApi.getDataByCashMasterId(id);
    }
}
