package com.dimata.demo.app.prochain_app.models.table;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.Objects;

import com.dimata.demo.app.prochain_app.core.api.UpdateAvailable;
import com.dimata.demo.app.prochain_app.core.util.ManipulateUtil;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.springframework.data.annotation.Id;
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
public class PosMaterial implements UpdateAvailable <PosMaterial> {
    public static final String TABLE_NAME = "pos_material ";
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
    public static final String PRICE_TYPE_01_COL = "PRICE_TYPE_01";
    public static final String PRICE_TYPE_02_COL = "PRICE_TYPE_02";
    public static final String PRICE_TYPE_03_COL ="PRICE_TYPE_03";
    public static final String DEFAULT_COST_CURRENCY_ID_COL ="DEFAULT_COST_CURRENCY_ID";
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
        private Long materialId;
        private Long standartRateId;
        private Double price;
        private LocalDateTime updateDate;



        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord(Double price) {
            return new Builder().newRecord(true)
                .price(Objects.requireNonNull(price, "price diperlukan"));
                
                
        }

        public static Builder updateBuilder(PosMaterial oldRecord, PosMaterial newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .materialId(changeItOrNot(newRecord.getMaterialId(), oldRecord.getMaterialId()))
                .standartRateId(changeItOrNot(newRecord.getStandartRateId(), oldRecord.getStandartRateId()))
                .price(changeItOrNot(newRecord.getPrice(), oldRecord.getPrice()))
                .updateDate(changeItOrNot(newRecord.getUpdateDate(), oldRecord.getUpdateDate()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public PosMaterial  build() {
            PosMaterial  result = new PosMaterial ();
            result.setId(id);
            result.setMaterialId(materialId);
            result.setStandartRateId(standartRateId);
            result.setPrice(price);
            result.setUpdateDate(updateDate);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long  id;
    private Long materialId;
    private Long standartRateId;
    private Double price;
    @JsonDeserialize(converter = TimeSerialize.class)
    private LocalDateTime updateDate;
    @Transient
    @JsonIgnore
    private Long insertId;

    

    public static PosMaterial  fromRow(Row row) {
        var result = new PosMaterial ();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setStandartRateId(ManipulateUtil.parseRow(row, STANDART_RATE_ID_COL, Long.class));
        result.setPrice(ManipulateUtil.parseRow(row, PRICE_COL, Double.class));
        result.setUpdateDate(ManipulateUtil.parseRow(row, UPDATE_DATE_COL, LocalDateTime.class));
        result.setMaterialId(ManipulateUtil.parseRow(row, MATERIAL_ID_COL, Long.class));
        
        
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
    public PosMaterial update(PosMaterial  newData) {
        return Builder.updateBuilder(this, newData).build();
    }
}
