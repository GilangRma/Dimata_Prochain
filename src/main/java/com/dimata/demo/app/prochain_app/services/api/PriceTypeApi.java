package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.PriceTypeForm;
import com.dimata.demo.app.prochain_app.models.table.PriceType;
import com.dimata.demo.app.prochain_app.services.crude.PriceTypeCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PriceTypeApi {
    @Autowired
    private PriceTypeCrude posPriceTypeCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<PriceType> createPriceType(PriceTypeForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            PriceTypeCrude.Option option = PriceTypeCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(posPriceTypeCrude::create);
    }

    public Flux<PriceType> getAllPriceType(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(PriceType.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PriceType::fromRow)
            .all();
    }

    public Mono<PriceType> getPriceType(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PriceType.TABLE_NAME)
            .addWhere(WhereQuery.when(PriceType.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PriceType::fromRow)
            .one();
    }

    public Mono<PriceType> updatePriceType(Long id, PriceTypeForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                PriceTypeCrude.Option option = PriceTypeCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(posPriceTypeCrude::updateRecord);
    }
    public  Mono<PriceType> getDataByPriceTypeId(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PriceType.TABLE_NAME)
            .addWhere(WhereQuery.when(PriceType.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PriceType::fromRow)
            .one();
    }

}
