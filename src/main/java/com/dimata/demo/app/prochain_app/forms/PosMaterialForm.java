package com.dimata.demo.app.prochain_app.forms;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.core.util.jackson.DateDeserialize;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeDeserialize;
import com.dimata.demo.app.prochain_app.enums.MaterialEnum;
import com.dimata.demo.app.prochain_app.enums.ProccesStatusEnum;
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
    private Long subCategoryId;
    private Long defaultStockUnitId;
    private Double defaultPrice;
    private Long defaultPriceCurrencyId;
    private Long supplierId;
    private Double defaultCost;
    private Integer defaultSupplierType;
    private Double priceType_01;
    private Double priceType_02;
    private Double priceType_03;
    private Long defaultCostCurrencyId;
    private Integer materialType;
    private Double lastVat;
    private Double currBuyPrice;
    private ProccesStatusEnum processStatus;
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
    private Integer itemType;
    private Double lastDiscount;
    private Integer consigmentType;
    private Integer gondolaCode;
    private Double cogsPrice;
    private Long colorId; 
    private Long sizeId;
    private Double lastCostCargo;
    private Integer editMaterial;
    private Integer viewInShoppingChart;
    private Integer pointSalesPerson;
    private String materialDescription;
    private Long kadarId;
    private Long kepemilikanId;
    private MaterialEnum materialMain;
    private String materialImage;
    private Integer salesRule;
    private Integer returnRule;
    @JsonDeserialize(converter = DateDeserialize.class)
    private LocalDate expiredDate;
    @JsonDeserialize(converter = TimeDeserialize.class)
    private LocalDateTime lastUpdate;
    private LocalDateTime updateDate;
    @Override
    public PosMaterial convertNewRecord() {
        
        return PosMaterial.Builder.createNewRecord( defaultSupplierType, priceType_01, priceType_02, priceType_03, salesRule,viewInShoppingChart)
        .sku(sku)
        .barcode(barcode)
        .name(name)
        .merkId(merkId)
        .categoryId(categoryId)
        .subCategoryId(subCategoryId)
        .defaultStockUnitId(defaultStockUnitId)
        .defaultPrice(defaultPrice)
        .defaultPriceCurrencyId(defaultPriceCurrencyId)
        .supplierId(supplierId)
        .defaultCost(defaultCost)
        .defaultCostCurrencyId(defaultCostCurrencyId)
        .materialType(materialType)
        .lastVat(lastVat)
        .currBuyPrice(currBuyPrice)
        .processStatus(processStatus)
        .expiredDate(expiredDate)
        .buyUnitId(buyUnitId)
        .profit(profit)
        .currSellPriceRecomentation(currSellPriceRecomentation)
        .averagePrice(averagePrice)
        .minimumPoint(minimumPoint)
        .lastUpdate(lastUpdate)
        .requiredSerialNumber(requiredSerialNumber)
        .locationId(locationId)
        .description(description)
        .itemCostUsd(itemCostUsd)
        .sellingPrice(sellingPrice)
        .itemType(itemType)
        .lastDiscount(lastDiscount)
        .consigmentType(consigmentType)
        .gondolaCode(gondolaCode)
        .lastCostCargo(lastCostCargo)
        .updateDate(updateDate)
        .editMaterial(editMaterial)
        .pointSalesPerson(pointSalesPerson)
        .materialDescription(materialDescription)
        .kadarId(kadarId)
        .kepemilikanId(kepemilikanId)
        .materialMain(materialMain)
        .materialImage(materialImage)
        .returnRule(returnRule)
        .cogsPrice(cogsPrice)
        .colorId(colorId)
        .sizeId(sizeId)
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
        .subCategoryId(subCategoryId)
        .defaultStockUnitId(defaultStockUnitId)
        .defaultPrice(defaultPrice)
        .defaultPriceCurrencyId(defaultPriceCurrencyId)
        .supplierId(supplierId)
        .defaultCost(defaultCost)
        .defaultSupplierType(defaultSupplierType)
        .priceType_01(priceType_01)
        .priceType_02(priceType_02)
        .priceType_03(priceType_03)
        .defaultCostCurrencyId(defaultCostCurrencyId)
        .materialType(materialType)
        .lastVat(lastVat)
        .currBuyPrice(currBuyPrice)
        .processStatus(processStatus)
        .expiredDate(expiredDate)
        .buyUnitId(buyUnitId)
        .profit(profit)
        .currSellPriceRecomentation(currSellPriceRecomentation)
        .averagePrice(averagePrice)
        .minimumPoint(minimumPoint)
        .lastUpdate(lastUpdate)
        .requiredSerialNumber(requiredSerialNumber)
        .locationId(locationId)
        .description(description)
        .itemCostUsd(itemCostUsd)
        .sellingPrice(sellingPrice)
        .itemType(itemType)
        .lastDiscount(lastDiscount)
        .consigmentType(consigmentType)
        .gondolaCode(gondolaCode)
        .lastCostCargo(lastCostCargo)
        .updateDate(updateDate)
        .editMaterial(editMaterial)
        .viewInShoppingChart(viewInShoppingChart)
        .pointSalesPerson(pointSalesPerson)
        .materialDescription(materialDescription)
        .kadarId(kadarId)
        .kepemilikanId(kepemilikanId)
        .materialMain(materialMain)
        .materialImage(materialImage)
        .salesRule(salesRule)
        .returnRule(returnRule)
        .cogsPrice(cogsPrice)
        .colorId(colorId)
        .sizeId(sizeId)
        .id(id)
        .build();
    }
    
}
