package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.Discount_TypeForm;
import com.dimata.demo.app.prochain_app.models.table.Discount_Type;
import com.dimata.demo.app.prochain_app.services.crude.Discount_TypeCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Discount_TypeApi {

    @Autowired
    private Discount_TypeCrude discount_TypeCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<Discount_Type> createDiscount_Type(Discount_TypeForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            Discount_TypeCrude.Option option = Discount_TypeCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(discount_TypeCrude::create);
    }

    public Flux<Discount_Type> getAllDiscount_Type(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(Discount_Type.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(Discount_Type::fromRow)
            .all();
    }

    public Mono<Discount_Type> getDiscount_Type(Long id) {
        var sql = SelectQBuilder.emptyBuilder(Discount_Type.TABLE_NAME)
            .addWhere(WhereQuery.when(Discount_Type.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(Discount_Type::fromRow)
            .one();
    }

    public Mono<Discount_Type> updateDiscount_Type(Long id, Discount_TypeForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                Discount_TypeCrude.Option option = Discount_TypeCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(discount_TypeCrude::updateRecord);
    }  
    
}
