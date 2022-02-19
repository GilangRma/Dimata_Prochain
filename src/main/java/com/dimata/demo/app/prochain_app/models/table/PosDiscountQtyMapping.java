package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;

import java.sql.Timestamp;
import java.util.Objects;

import com.dimata.demo.app.prochain_app.core.api.UpdateAvailable;
import com.dimata.demo.app.prochain_app.core.util.GenerateUtil;
import com.dimata.demo.app.prochain_app.core.util.ManipulateUtil;
import com.dimata.demo.app.prochain_app.core.util.jackson.DateSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
public class PosDiscountQtyMapping implements UpdateAvailable<PosDiscountQtyMapping>, Persistable<Long> {
    
    public static final String TABLE_NAME = "pos_discount_qty_mapping ";
    public static final String ID_COL = "DISCOUNT_TYPE_ID";
    public static final String CURRENCY_TYPE_ID_COL = "CURRENCY_TYPE_ID";
    public static final String LOCATION_ID_COL = "LOCATION_ID";
    public static final String MATERIAL_ID_COL = "MATERIAL_ID";
    public static final String START_QTY_COL ="START_QTY";
    public static final String TO_QTY_COL = "TO_QTY";
    public static final String DISCOUNT_VALUE_COL = "DISCOUNT_VALUE";
    public static final String DISCOUNT_TYPE_COL = "DISCOUNT_TYPE";
    public static final String UPDATE_DATE_COL = "UPDATE_DATE";
    

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long  id;
        private Long currencyTypeId;
        private Long locationId;
        private Long materialId;
        private String startQty;
        private String toQty;
        private String discountValue;
        private String discountType;
        private Timestamp updateDate; 

        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord(String discountvalue, String discountType) {
            return new Builder().newRecord(true)
                .discountValue(Objects.requireNonNull(discountvalue, "discountvalue diperlukan"))
                .discountType(Objects.requireNonNull(discountType, "discountType diperlukan"));
                
                
        }

        public static Builder updateBuilder(PosDiscountQtyMapping oldRecord, PosDiscountQtyMapping  newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .currencyTypeId(changeItOrNot(newRecord.getCurrencyTypeId(), oldRecord.getCurrencyTypeId()))
                .locationId(changeItOrNot(newRecord.getLocationId(), oldRecord.getLocationId()))
                .materialId(changeItOrNot(newRecord.getMaterialId(), oldRecord.getMaterialId()))
                .startQty(changeItOrNot(newRecord.getStartQty(), oldRecord.getStartQty()))
                .toQty(changeItOrNot(newRecord.getToQty(), oldRecord.getToQty()))
                .discountValue(changeItOrNot(newRecord.getDiscountValue(), oldRecord.getDiscountValue()))
                .discountType(changeItOrNot(newRecord.getDiscountType(), oldRecord.getDiscountType()))
                .updateDate(changeItOrNot(newRecord.getUpdateDate(), oldRecord.getUpdateDate()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public PosDiscountQtyMapping  build() {
            PosDiscountQtyMapping  result = new PosDiscountQtyMapping();
            result.setId(id);
            result.setCurrencyTypeId(currencyTypeId);
            result.setLocationId(locationId);
            result.setMaterialId(materialId);
            result.setStartQty(startQty);
            result.setToQty(toQty);
            result.setDiscountValue(discountValue);
            result.setDiscountType(discountType);
            result.setUpdateDate(updateDate);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long  id;
    private Long currencyTypeId;
    private Long locationId;
    private Long materialId;
    private String startQty;
    private String toQty;
    private String discountValue;
    private String discountType;
    @JsonSerialize(converter = DateSerialize.class)
    private Timestamp updateDate;
    @Transient
    @JsonIgnore
    private Long insertId;

    

    public static PosDiscountQtyMapping  fromRow(Row row) {
        var result = new PosDiscountQtyMapping();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setCurrencyTypeId(ManipulateUtil.parseRow(row, CURRENCY_TYPE_ID_COL, Long.class));
        result.setCurrencyTypeId(ManipulateUtil.parseRow(row, LOCATION_ID_COL, Long.class));
        result.setMaterialId(ManipulateUtil.parseRow(row, MATERIAL_ID_COL, Long.class));
        result.setStartQty(ManipulateUtil.parseRow(row, START_QTY_COL, String.class));
        result.setToQty(ManipulateUtil.parseRow(row, TO_QTY_COL, String.class));
        result.setDiscountValue(ManipulateUtil.parseRow(row, DISCOUNT_VALUE_COL, String.class));
        result.setDiscountType(ManipulateUtil.parseRow(row, DISCOUNT_TYPE_COL, String.class));
        result.setUpdateDate(ManipulateUtil.parseRow(row, UPDATE_DATE_COL, Timestamp.class));
        
        
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
    public PosDiscountQtyMapping  update(PosDiscountQtyMapping  newData) {
        return Builder.updateBuilder(this, newData).build();
    }
}
