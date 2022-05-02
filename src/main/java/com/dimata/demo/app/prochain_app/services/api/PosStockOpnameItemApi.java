package com.dimata.demo.app.prochain_app.services.api;

import com.dimata.demo.app.prochain_app.core.exception.DataNotFoundException;
import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.search.JoinQuery;
import com.dimata.demo.app.prochain_app.core.search.SelectQBuilder;
import com.dimata.demo.app.prochain_app.core.search.WhereQuery;
import com.dimata.demo.app.prochain_app.forms.MaterialAndstockForm;
import com.dimata.demo.app.prochain_app.forms.PosStockOpnameItemForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosStockOpnameItemRelation;
import com.dimata.demo.app.prochain_app.forms.relation.StockOpnameItemRelation;
import com.dimata.demo.app.prochain_app.models.table.PosMaterial;
import com.dimata.demo.app.prochain_app.models.table.PosStockOpnameItem;
import com.dimata.demo.app.prochain_app.services.crude.PosStockOpnameItemCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import lombok.var;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PosStockOpnameItemApi {
    @Autowired
    private PosStockOpnameItemCrude posStockOpnameItemCrude;
    @Autowired
    private PosMaterialApi posMaterialApi;
    @Autowired
	private R2dbcEntityTemplate template;
    @Autowired
    private PosStockOpnameApi posStockOpnameApi;
    public Mono<PosStockOpnameItem> createPosStockOpnameItem(PosStockOpnameItemForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            PosStockOpnameItemCrude.Option option = PosStockOpnameItemCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(posStockOpnameItemCrude::create);
    }

    public Flux<PosStockOpnameItem> getAllPosStockOpnameItem(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(PosStockOpnameItem.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosStockOpnameItem::fromRow)
            .all();
    }

    public Mono<PosStockOpnameItem> getPosStockOpnameItem(Long id) {
        var sql = SelectQBuilder.emptyBuilder(PosStockOpnameItem.TABLE_NAME)
            .addWhere(WhereQuery.when(PosStockOpnameItem.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(PosStockOpnameItem::fromRow)
            .one();
    }

    public Mono<PosStockOpnameItem> updatePosStockOpnameItem(Long id, PosStockOpnameItemForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                PosStockOpnameItemCrude.Option option = PosStockOpnameItemCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(posStockOpnameItemCrude::updateRecord);
    }
     //relation
public Mono<PosStockOpnameItem> checkAvailableData(PosStockOpnameItemRelation form){
    var sql = SelectQBuilder.emptyBuilder(PosStockOpnameItem.TABLE_NAME)
    .addJoin(JoinQuery.doLeftJoin(
        PosMaterial.TABLE_NAME
        )
        .on(WhereQuery.when((PosStockOpnameItem.TABLE_NAME + "." + PosStockOpnameItem.MATERIAL_ID_COL))
        .is( PosMaterial.TABLE_NAME + "." + PosMaterial.ID_COL)))
        
    .addWhere(WhereQuery.when(PosStockOpnameItem.TABLE_NAME + "." + PosStockOpnameItem.MATERIAL_ID_COL).is(form.getMaterialId()))
    .build();

    System.out.println(sql);
    return template.getDatabaseClient()
    .sql(sql)
    .map(PosStockOpnameItem::fromRow)
    .one()
    .switchIfEmpty(Mono.error(new DataNotFoundException("id material anda salah")));
}
    public Mono<PosMaterial> getDataByMaterial(Long id) {
        return posMaterialApi.getDataByMaterial(id);
    }
//relation new
    public Mono<StockOpnameItemRelation> createMaterialAndStock (MaterialAndstockForm form){
        return Mono.just(form)
        .flatMap(f -> {
            var material = posMaterialApi.createPosMaterial(f.getMaterial())
            .flatMap(d -> {
                StockOpnameItemRelation data = new StockOpnameItemRelation();
                data.setMaterial(d);
                return Mono.just(data);
            });
            return Mono.zip(Mono.just(f), material);
        })
        .flatMap(z -> {
            return posStockOpnameApi.createPosStockOpname(z.getT1().getOpname())
                .flatMap(f -> {
                    z.getT2().setStockOpname(f);
                    return Mono.zip(Mono.just(z.getT1()), Mono.just(z.getT2()));
                });
            // var opname = posStockOpnameApi.createPosStockOpname(z.get)
            // .map(z -> {
            //     z.getT2().setOpname(f);
            //     return z.getT2();
            // });
            // return Mono.zip(Mono.just(z.getT1()), opname);
        })
        .flatMap(z -> {
            PosStockOpnameItemForm postData = new PosStockOpnameItemForm();
            postData.setMaterialId(z.getT1().getMaterial().getId());
            postData.setStockOpnameId(z.getT2().getStockOpname().getId());
            return createPosStockOpnameItem(postData)
            .flatMap(f ->{
                z.getT2().setRelation(f);
                return Mono.just(z.getT2());
            });
        });
    }

}

