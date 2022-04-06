package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.DiscountTypeForm;
import com.dimata.demo.app.prochain_app.models.table.DiscountType;
import com.dimata.demo.app.prochain_app.services.crude.DiscountTypeCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DiscountTypeApi {

    @Autowired
    private DiscountTypeCrude discountTypeCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<DiscountType> createDiscountType(DiscountTypeForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            DiscountTypeCrude.Option option = DiscountTypeCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(discountTypeCrude::create);
    }

    public Flux<DiscountType> getAllDiscountType(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(DiscountType.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(DiscountType::fromRow)
            .all();
    }

    public Mono<DiscountType> getDiscountType(Long id) {
        var sql = SelectQBuilder.emptyBuilder(DiscountType.TABLE_NAME)
            .addWhere(WhereQuery.when(DiscountType.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(DiscountType::fromRow)
            .one();
    }

    public Mono<DiscountType> updateDiscountType(Long id, DiscountTypeForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                DiscountTypeCrude.Option option = DiscountTypeCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(discountTypeCrude::updateRecord);
    }  
    
}
