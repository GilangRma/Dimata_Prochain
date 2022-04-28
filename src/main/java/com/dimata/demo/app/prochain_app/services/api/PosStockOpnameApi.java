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

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PosStockOpnameApi {

    @Autowired
    private PosStockOpnameCrude posStockOpnameCrude;
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

    
}
