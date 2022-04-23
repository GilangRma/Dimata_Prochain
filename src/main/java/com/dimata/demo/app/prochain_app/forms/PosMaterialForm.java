package com.dimata.demo.app.prochain_app.forms;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.core.util.jackson.DateSerialize;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeSerialize;
import com.dimata.demo.app.prochain_app.models.table.PosMaterial;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class PosMaterialForm implements RecordAdapter<PosMaterial> {



    private Long  id;
    private String sku;
    private String barcode;
    private String name;
    private Long merkId;
    private Long categoryId;
    private Long subcategoryId;
    private Long defaultStockUnitId;
    private Double defaultPrice;
    private Long defaultPriceCurrencyId;
    private Long supplierId;
    private Double defaultCost;
    private Double defaultSuplierType;
    private Double priceType1;
    private Double priceType2;
    private Double priceType3;
    private Long defaultCostCurrencyId;
    private Integer materialType;
    private Double lastvat;
    private Double currBuyPrice;
    private Integer processStatus;
    private Long buyUnitId;
    private Double profit;
    private Double currSellPriceRecomentation;
    private Double averagePrice;
    private Integer minimumPoint;
    private Integer requiredSerialNumber;
    private Long locationId;
    private String description;
    private Double itemCostUsd;
    private Double sellingPrice;
    private Integer itemtype;
    private Double lastDiscount;
    private Integer consigmentType;
    private Integer gondolaCode;
    private Double cogsPrice;
    private Long colorId; 
    private Long sizeId;
    private Double lastCostCargo;
    private Integer editMaterial;
    private Integer viewInShopingChart;
    private Integer pointSalesPerson;
    private String materialDescription;
    private Long kadarId;
    private Long kepemilikanId;
    private Integer materialMain;
    private String materialImage;
    private Integer selesRule;
    private Integer returnRule;
    @JsonDeserialize(converter = DateSerialize.class)
    private LocalDate expiredDate;
    @JsonDeserialize(converter = TimeSerialize.class)
    private LocalDateTime lastUPdate;
    private LocalDateTime updateDate;
    @Override
    public PosMaterial convertNewRecord() {
        
        return PosMaterial.Builder.createNewRecord()
        .id(id)
        .build();
    }

    @Override
    public PosMaterial convertToRecord() {
        return PosMaterial.Builder.emptyBuilder()
        .sku(sku)
        .barcode(barcode)
        .name(name)
        .merkId(merkId)
        .categoryId(categoryId)
        .subcategoryId(subcategoryId)
        .defaultStockUnitId(defaultStockUnitId)
        .defaultPrice(defaultPrice)
        .defaultPriceCurrencyId(defaultPriceCurrencyId)
        .supplierId(supplierId)
        .defaultCost(defaultCost)
        .defaultSuplierType(defaultSuplierType)
        .priceType1(priceType1)
        .priceType2(priceType2)
        .priceType3(priceType3)
        .defaultCostCurrencyId(defaultCostCurrencyId)
        .materialType(materialType)
        .lastvat(lastvat)
        .currBuyPrice(currBuyPrice)
        .processStatus(processStatus)
        .expiredDate(expiredDate)
        .buyUnitId(buyUnitId)
        .profit(profit)
        .currSellPriceRecomentation(currSellPriceRecomentation)
        .averagePrice(averagePrice)
        .minimumPoint(minimumPoint)
        .lastUPdate(lastUPdate)
        .requiredSerialNumber(requiredSerialNumber)
        .locationId(locationId)
        .description(description)
        .itemCostUsd(itemCostUsd)
        .sellingPrice(sellingPrice)
        .itemtype(itemtype)
        .lastDiscount(lastDiscount)
        .consigmentType(consigmentType)
        .gondolaCode(gondolaCode)
        .lastCostCargo(lastCostCargo)
        .updateDate(updateDate)
        .editMaterial(editMaterial)
        .viewInShopingChart(viewInShopingChart)
        .pointSalesPerson(pointSalesPerson)
        .materialDescription(materialDescription)
        .kadarId(kadarId)
        .kepemilikanId(kepemilikanId)
        .materialMain(materialMain)
        .materialImage(materialImage)
        .selesRule(selesRule)
        .returnRule(returnRule)
        .cogsPrice(cogsPrice)
        .colorId(colorId)
        .sizeId(sizeId)
        .id(id)
        .build();
    }
    
}
