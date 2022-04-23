package com.dimata.demo.app.prochain_app.models.table;


import java.time.LocalDate;
import java.time.LocalDateTime;
import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;
import com.dimata.demo.app.prochain_app.core.api.UpdateAvailable;
import com.dimata.demo.app.prochain_app.core.util.GenerateUtil;
import com.dimata.demo.app.prochain_app.core.util.ManipulateUtil;
import com.dimata.demo.app.prochain_app.core.util.jackson.DateSerialize;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import io.r2dbc.spi.Row;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PosMaterial implements UpdateAvailable <PosMaterial>, Persistable<Long> {
    public static final String TABLE_NAME = "pos_material";
    public static final String ID_COL = "MATERIAL_ID";
    public static final String SKU_COL = "SKU";
    public static final String BARCODE_COL = "BARCODE";
    public static final String NAME_COL ="NAME";
    public static final String MERK_ID_COL = "MERK_ID";
    public static final String CATEGORY_ID_COL = "CATEGORY_ID";
    public static final String SUB_CATEGORY_ID_COL = "SUB_CATEGORY_ID";
    public static final String DEFAULT_STOCK_UNIT_ID_COL = "DEFAULT_STOCK_UNIT_ID";
    public static final String DEFAULT_PRICE_COL = "DEFAULT_PRICE";
    public static final String DEFAULT_PRICE_CURRENCY_ID_COL = "DEFAULT_PRICE_CURRENCY_ID";
    public static final String SUPPLIER_ID_COL = "SUPPLIER_ID";
    public static final String DEFAULT_SUPPLIER_TYPE_COL ="DEFAULT_SUPPLIER_TYPE";
    public static final String PRICE_TYPE_01_COL = "PRICE_TYPE_01";
    public static final String PRICE_TYPE_02_COL = "PRICE_TYPE_02";
    public static final String PRICE_TYPE_03_COL ="PRICE_TYPE_03";
    public static final String DEFAULT_COST_CURRENCY_ID_COL ="DEFAULT_COST_CURRENCY_ID";
    public static final String DEFAULT_COST_COL ="DEFAULT_COST";
    public static final String MATERIAL_TYPE_COL ="MATERIAL_TYPE";
    public static final String LAST_VAT_COL ="LAST_VAT";
    public static final String CURR_BUY_PRICE_COL ="CURR_BUY_PRICE";
    public static final String PROCESS_STATUS_COL ="PROCESS_STATUS";
    public static final String EXPIRED_DATE_COL ="EXPIRED_DATE";
    public static final String BUY_UNIT_ID_COL ="BUY_UNIT_ID";
    public static final String PROFIT_COL ="PROFIT";
    public static final String CURR_SELL_PRICE_RECOMENTATION_COL ="CURR_SELL_PRICE_RECOMENTATION";
    public static final String AVERAGE_PRICE_COL ="AVERAGE_PRICE";
    public static final String MINIMUM_POINT_COL ="MINIMUM_POINT";
    public static final String LAST_UPDATE_COL ="LAST_UPDATE";
    public static final String REQUIRED_SERIAL_NUMBER_COL ="REQUIRED_SERIAL_NUMBER";
    public static final String LOCATION_ID_COL ="LOCATION_ID";
    public static final String DESCRIPTION_COL ="DESCRIPTION";
    public static final String ITEM_COST_USD_COL ="ITEM_COST_USD";
    public static final String SELLING_PRICE_COL ="SELLING_PRICE";
    public static final String ITEM_TYPE_COL ="ITEM_TYPE";
    public static final String LAST_DISCOUNT_COL ="LAST_DISCOUNT";
    public static final String CONSIGMENT_TYPE_COL ="CONSIGMENT_TYPE";
    public static final String GONDOLA_CODE_COL ="GONDOLA_CODE";
    public static final String COGS_PRICE_COL ="COGS_PRICE";
    public static final String COLOR_ID_COL ="COLOR_ID";
    public static final String SIZE_ID_COL ="SIZE_ID";
    public static final String LAST_COST_CARGO_COL ="LAST_COST_CARGO";
    public static final String UPDATE_DATE_COL ="UPDATE_DATE";
    public static final String EDIT_MATERIAL_COL ="EDIT_MATERIAL";
    public static final String VIEW_IN_SHOPPING_CHART_COL ="VIEW_IN_SHOPPING_CHART";
    public static final String POINT_SALES_PERSON_COL ="POINT_SALES_PERSON";
    public static final String MATERIAL_DESCRIPTION_COL ="MATERIAL_DESCRIPTION";
    public static final String KADAR_ID_COL ="KADAR_ID";
    public static final String KEPEMILIKAN_ID_COL ="KEPEMILIKAN_ID";
    public static final String MATERIAL_MAIN_COL ="MATERIAL_MAIN";
    public static final String MATERIAL_IMAGE_COL ="MATERIAL_IMAGE";
    public static final String SALES_RULE_COL ="SALES_RULE";
    public static final String RETURN_RULE_COL ="RETURN_RULE";


    @Accessors(fluent = true)
    @Setter
    public static class Builder {
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
        private LocalDateTime lastUPdate;
        private LocalDate expiredDate;
        private LocalDateTime updateDate;
        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord() {
            return new Builder().newRecord(true);
                
                
                
        }

        public static Builder updateBuilder(PosMaterial oldRecord, PosMaterial newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .sku(changeItOrNot(newRecord.getSku(), oldRecord.getSku()))
                .barcode(changeItOrNot(newRecord.getBarcode(), oldRecord.getBarcode()))
                .name(changeItOrNot(newRecord.getName(), oldRecord.getName()))
                .merkId(changeItOrNot(newRecord.getMerkId(), oldRecord.getMerkId()))
                .categoryId(changeItOrNot(newRecord.getCategoryId(), oldRecord.getCategoryId()))
                .subcategoryId(changeItOrNot(newRecord.getSubcategoryId(), oldRecord.getSubcategoryId()))
                .defaultStockUnitId(changeItOrNot(newRecord.getDefaultStockUnitId(), oldRecord.getDefaultStockUnitId()))
                .defaultPrice(changeItOrNot(newRecord.getDefaultPrice(), oldRecord.getDefaultPrice()))
                .defaultPriceCurrencyId(changeItOrNot(newRecord.getDefaultPriceCurrencyId(), oldRecord.getDefaultPriceCurrencyId()))
                .supplierId(changeItOrNot(newRecord.getSupplierId(), oldRecord.getSupplierId()))
                .defaultCost(changeItOrNot(newRecord.getDefaultCost(), oldRecord.getDefaultCost()))
                .defaultSuplierType(changeItOrNot(newRecord.getDefaultSuplierType(), oldRecord.getDefaultSuplierType()))
                .priceType1(changeItOrNot(newRecord.getPriceType1(), oldRecord.getPriceType1()))
                .priceType2(changeItOrNot(newRecord.getPriceType2(), oldRecord.getPriceType2()))
                .priceType3(changeItOrNot(newRecord.getPriceType3(), oldRecord.getPriceType3()))
                .defaultCostCurrencyId(changeItOrNot(newRecord.getDefaultCostCurrencyId(), oldRecord.getDefaultCostCurrencyId()))
                .materialType(changeItOrNot(newRecord.getMaterialType(), oldRecord.getMaterialType()))
                .lastvat(changeItOrNot(newRecord.getLastvat(), oldRecord.getLastvat()))
                .currBuyPrice(changeItOrNot(newRecord.getCurrBuyPrice(), oldRecord.getCurrBuyPrice()))
                .processStatus(changeItOrNot(newRecord.getProcessStatus(), oldRecord.getProcessStatus()))
                .buyUnitId(changeItOrNot(newRecord.getBuyUnitId(), oldRecord.getBuyUnitId()))
                .profit(changeItOrNot(newRecord.getProfit(), oldRecord.getProfit()))
                .currSellPriceRecomentation(changeItOrNot(newRecord.getCurrSellPriceRecomentation(), oldRecord.getCurrSellPriceRecomentation()))
                .averagePrice(changeItOrNot(newRecord.getAveragePrice(), oldRecord.getAveragePrice()))
                .minimumPoint(changeItOrNot(newRecord.getMinimumPoint(), oldRecord.getMinimumPoint()))
                .requiredSerialNumber(changeItOrNot(newRecord.getRequiredSerialNumber(), oldRecord.getRequiredSerialNumber()))
                .locationId(changeItOrNot(newRecord.getLocationId(), oldRecord.getLocationId()))
                .description(changeItOrNot(newRecord.getDescription(), oldRecord.getDescription()))
                .itemCostUsd(changeItOrNot(newRecord.getItemCostUsd(), oldRecord.getItemCostUsd()))
                .sellingPrice(changeItOrNot(newRecord.getSellingPrice(), oldRecord.getSellingPrice()))
                .itemtype(changeItOrNot(newRecord.getItemtype(), oldRecord.getItemtype()))
                .lastDiscount(changeItOrNot(newRecord.getLastDiscount(), oldRecord.getLastDiscount()))
                .consigmentType(changeItOrNot(newRecord.getConsigmentType(), oldRecord.getConsigmentType()))
                .gondolaCode(changeItOrNot(newRecord.getGondolaCode(), oldRecord.getGondolaCode()))
                .cogsPrice(changeItOrNot(newRecord.getCogsPrice(), oldRecord.getCogsPrice()))
                .colorId(changeItOrNot(newRecord.getColorId(), oldRecord.getColorId()))
                .sizeId(changeItOrNot(newRecord.getSizeId(), oldRecord.getSizeId()))
                .lastCostCargo(changeItOrNot(newRecord.getLastCostCargo(), oldRecord.getLastCostCargo()))
                .editMaterial(changeItOrNot(newRecord.getEditMaterial(), oldRecord.getEditMaterial()))
                .viewInShopingChart(changeItOrNot(newRecord.getViewInShopingChart(), oldRecord.getViewInShopingChart()))
                .pointSalesPerson(changeItOrNot(newRecord.getPointSalesPerson(), oldRecord.getPointSalesPerson()))
                .materialDescription(changeItOrNot(newRecord.getMaterialDescription(), oldRecord.getMaterialDescription()))
                .kadarId(changeItOrNot(newRecord.getKadarId(), oldRecord.getKadarId()))
                .kepemilikanId(changeItOrNot(newRecord.getKepemilikanId(), oldRecord.getKepemilikanId()))
                .materialMain(changeItOrNot(newRecord.getMaterialMain(), oldRecord.getMaterialMain()))
                .materialImage(changeItOrNot(newRecord.getMaterialImage(), oldRecord.getMaterialImage()))
                .selesRule(changeItOrNot(newRecord.getSelesRule(), oldRecord.getSelesRule()))
                .returnRule(changeItOrNot(newRecord.getReturnRule(), oldRecord.getReturnRule()))
                .expiredDate(changeItOrNot(newRecord.getExpiredDate(), oldRecord.getExpiredDate()))
                .lastUPdate(changeItOrNot(newRecord.getLastUPdate(), oldRecord.getLastUPdate()))
                .updateDate(changeItOrNot(newRecord.getUpdateDate(), oldRecord.getUpdateDate()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public PosMaterial  build() {
            PosMaterial  result = new PosMaterial ();
            result.setId(id);
            result.setSku(sku);
            result.setBarcode(barcode);
            result.setName(name);
            result.setMerkId(merkId);
            result.setCategoryId(categoryId);
            result.setSubcategoryId(subcategoryId);
            result.setDefaultStockUnitId(defaultStockUnitId);
            result.setDefaultPrice(defaultPrice);
            result.setDefaultPriceCurrencyId(defaultPriceCurrencyId);
            result.setSupplierId(supplierId);
            result.setDefaultCost(defaultCost);
            result.setDefaultSuplierType(defaultSuplierType);
            result.setPriceType1(priceType1);
            result.setPriceType2(priceType2);
            result.setPriceType3(priceType3);
            result.setDefaultCostCurrencyId(defaultCostCurrencyId);
            result.setMaterialType(materialType);
            result.setLastvat(lastvat);
            result.setCurrBuyPrice(currBuyPrice);
            result.setProcessStatus(processStatus);
            result.setExpiredDate(expiredDate);
            result.setBuyUnitId(buyUnitId);
            result.setProfit(profit);
            result.setCurrSellPriceRecomentation(currSellPriceRecomentation);
            result.setAveragePrice(averagePrice);
            result.setMinimumPoint(minimumPoint);
            result.setLastUPdate(lastUPdate);
            result.setRequiredSerialNumber(requiredSerialNumber);
            result.setLocationId(locationId);
            result.setDescription(description);
            result.setItemCostUsd(itemCostUsd);
            result.setSellingPrice(sellingPrice);
            result.setItemtype(itemtype);
            result.setLastDiscount(lastDiscount);
            result.setConsigmentType(consigmentType);
            result.setGondolaCode(gondolaCode);
            result.setLastCostCargo(lastCostCargo);
            result.setUpdateDate(updateDate);
            result.setEditMaterial(editMaterial);
            result.setViewInShopingChart(viewInShopingChart);
            result.setPointSalesPerson(pointSalesPerson);
            result.setMaterialDescription(materialDescription);
            result.setKadarId(kadarId);
            result.setKepemilikanId(kepemilikanId);
            result.setMaterialMain(materialMain);
            result.setMaterialImage(materialImage);
            result.setSelesRule(selesRule);
            result.setReturnRule(returnRule);
            result.setCogsPrice(cogsPrice);
            result.setColorId(colorId);
            result.setSizeId(sizeId);
            
            return result;
        }
    }

    @Id
    @Column(ID_COL)
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
    @Transient
    @JsonIgnore
    private Long insertId;

    

    public static PosMaterial  fromRow(Row row) {
        var result = new PosMaterial ();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setSku(ManipulateUtil.parseRow(row, SKU_COL, String.class));
        result.setBarcode(ManipulateUtil.parseRow(row, BARCODE_COL, String.class));
        result.setName(ManipulateUtil.parseRow(row, NAME_COL, String.class));
        result.setMerkId(ManipulateUtil.parseRow(row, MERK_ID_COL, Long.class));
        result.setCategoryId(ManipulateUtil.parseRow(row, CATEGORY_ID_COL, Long.class));
        result.setSubcategoryId(ManipulateUtil.parseRow(row, SUB_CATEGORY_ID_COL, Long.class));
        result.setDefaultStockUnitId(ManipulateUtil.parseRow(row, DEFAULT_STOCK_UNIT_ID_COL, Long.class));
        result.setDefaultPrice(ManipulateUtil.parseRow(row, DEFAULT_PRICE_COL, Double.class));
        result.setDefaultPriceCurrencyId(ManipulateUtil.parseRow(row, DEFAULT_PRICE_CURRENCY_ID_COL, Long.class));
        result.setSupplierId(ManipulateUtil.parseRow(row, SUPPLIER_ID_COL, Long.class));
        result.setDefaultCost(ManipulateUtil.parseRow(row, DEFAULT_COST_COL, Double.class));
        result.setDefaultSuplierType(ManipulateUtil.parseRow(row, DEFAULT_SUPPLIER_TYPE_COL, Double.class));
        result.setPriceType1(ManipulateUtil.parseRow(row, PRICE_TYPE_01_COL, Double.class));
        result.setPriceType2(ManipulateUtil.parseRow(row, PRICE_TYPE_02_COL, Double.class));
        result.setPriceType3(ManipulateUtil.parseRow(row, PRICE_TYPE_03_COL, Double.class));
        result.setDefaultCostCurrencyId(ManipulateUtil.parseRow(row, DEFAULT_COST_CURRENCY_ID_COL, Long.class));
        result.setMaterialType(ManipulateUtil.parseRow(row, MATERIAL_TYPE_COL, Integer.class));
        result.setLastvat(ManipulateUtil.parseRow(row, LAST_VAT_COL, Double.class));
        result.setCurrBuyPrice(ManipulateUtil.parseRow(row, CURR_BUY_PRICE_COL, Double.class));
        result.setProcessStatus(ManipulateUtil.parseRow(row, PROCESS_STATUS_COL, Integer.class));
        result.setExpiredDate(ManipulateUtil.parseRow(row, EXPIRED_DATE_COL,LocalDate.class));
        result.setBuyUnitId(ManipulateUtil.parseRow(row, BUY_UNIT_ID_COL, Long.class));
        result.setProfit(ManipulateUtil.parseRow(row, PROFIT_COL, Double.class));
        result.setCurrSellPriceRecomentation(ManipulateUtil.parseRow(row,CURR_SELL_PRICE_RECOMENTATION_COL, Double.class));
        result.setAveragePrice(ManipulateUtil.parseRow(row, AVERAGE_PRICE_COL, Double.class));
        result.setMinimumPoint(ManipulateUtil.parseRow(row, MINIMUM_POINT_COL, Integer.class));
        result.setLastUPdate(ManipulateUtil.parseRow(row, LAST_UPDATE_COL, LocalDateTime.class));
        result.setRequiredSerialNumber(ManipulateUtil.parseRow(row, REQUIRED_SERIAL_NUMBER_COL, Integer.class));
        result.setLocationId(ManipulateUtil.parseRow(row, LOCATION_ID_COL, Long.class));
        result.setDescription(ManipulateUtil.parseRow(row, DESCRIPTION_COL, String.class));
        result.setItemCostUsd(ManipulateUtil.parseRow(row, ITEM_COST_USD_COL, Double.class));
        result.setSellingPrice(ManipulateUtil.parseRow(row, SELLING_PRICE_COL, Double.class));
        result.setItemtype(ManipulateUtil.parseRow(row, ITEM_TYPE_COL, Integer.class));
        result.setLastDiscount(ManipulateUtil.parseRow(row, LAST_DISCOUNT_COL, Double.class));
        result.setConsigmentType(ManipulateUtil.parseRow(row, CONSIGMENT_TYPE_COL, Integer.class));
        result.setGondolaCode(ManipulateUtil.parseRow(row, GONDOLA_CODE_COL, Integer.class));
        result.setLastCostCargo(ManipulateUtil.parseRow(row, LAST_COST_CARGO_COL, Double.class));
        result.setUpdateDate(ManipulateUtil.parseRow(row, UPDATE_DATE_COL, LocalDateTime.class));
        result.setEditMaterial(ManipulateUtil.parseRow(row, EDIT_MATERIAL_COL, Integer.class));
        result.setViewInShopingChart(ManipulateUtil.parseRow(row, VIEW_IN_SHOPPING_CHART_COL, Integer.class));
        result.setPointSalesPerson(ManipulateUtil.parseRow(row, POINT_SALES_PERSON_COL, Integer.class));
        result.setMaterialDescription(ManipulateUtil.parseRow(row, MATERIAL_DESCRIPTION_COL, String.class));
        result.setKadarId(ManipulateUtil.parseRow(row, KADAR_ID_COL, Long.class));
        result.setKepemilikanId(ManipulateUtil.parseRow(row, KEPEMILIKAN_ID_COL, Long.class));
        result.setMaterialMain(ManipulateUtil.parseRow(row, MATERIAL_MAIN_COL, Integer.class));
        result.setMaterialImage(ManipulateUtil.parseRow(row, MATERIAL_IMAGE_COL, String.class));
        result.setSelesRule(ManipulateUtil.parseRow(row, SALES_RULE_COL, Integer.class));
        result.setReturnRule(ManipulateUtil.parseRow(row, RETURN_RULE_COL, Integer.class));
        result.setCogsPrice(ManipulateUtil.parseRow(row, COGS_PRICE_COL, Double.class));
        result.setColorId(ManipulateUtil.parseRow(row, COLOR_ID_COL, Long.class));
        result.setSizeId(ManipulateUtil.parseRow(row, SIZE_ID_COL, Long.class));
        
        
        return result;
    }

    @Override
    public boolean isNew() {
        if (id == null && insertId == null) {
            id = new GenerateUtil().generateOID();
            return true;
        } else if (id == null) {
            id = insertId;
            return true;
        }
        return false;
    }

    @Override
    public PosMaterial  update(PosMaterial  newData) {
        return Builder.updateBuilder(this, newData).build();
    }
}
