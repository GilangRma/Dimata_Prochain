package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;

import java.sql.Timestamp;
import java.util.Objects;

import com.dimata.demo.app.prochain_app.core.api.UpdateAvailable;
import com.dimata.demo.app.prochain_app.core.util.GenerateUtil;
import com.dimata.demo.app.prochain_app.core.util.ManipulateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

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

public class PosDiscountMapping implements UpdateAvailable <PosDiscountMapping>, Persistable <Long> {
    public static final String TABLE_NAME = "pos_discount_mapping ";
    public static final String ID_COL = "DISCOUNT_TYPE_ID";
    public static final String MATERIAL_ID_COL = "MATERIAL_ID";
    public static final String CURRENCY_TYPE_ID_COL = "CURRENCY_TYPE_ID";
    public static final String DISCOUNT_PCT_COL ="DISCOUNT_PCT";
    public static final String DISCOUNT_VALUE_COL = "DISCOUNT_VALUE";
    public static final String UPDATE_DATE_COL = "UPDATE_DATE";
    
    

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long  id;
        private Long materialId;
        private Long currencyTypeId;
        private String discountPct;
        private String discountValue;
        private Timestamp updateDate;



        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord(String discountPct, String discountValue) {
            return new Builder().newRecord(true)
                .discountPct(Objects.requireNonNull(discountPct, "nama diperlukan"))
                .discountValue(Objects.requireNonNull(discountValue, "nama diperlukan"));
                
                
        }

        public static Builder updateBuilder(PosDiscountMapping oldRecord, PosDiscountMapping newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .materialId(changeItOrNot(newRecord.getMaterialId(), oldRecord.getMaterialId()))
                .currencyTypeId(changeItOrNot(newRecord.getCurrencyTypeId(), oldRecord.getCurrencyTypeId()))
                .discountPct(changeItOrNot(newRecord.getDiscountPct(), oldRecord.getDiscountPct()))
                .discountValue(changeItOrNot(newRecord.getDiscountValue(), oldRecord.getDiscountValue()))
                .updateDate(changeItOrNot(newRecord.getUpdateDate(), oldRecord.getUpdateDate()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public PosDiscountMapping  build() {
            PosDiscountMapping  result = new PosDiscountMapping ();
            result.setId(id);
            result.setMaterialId(materialId);
            result.setCurrencyTypeId(currencyTypeId);
            result.setDiscountPct(discountPct);
            result.setDiscountValue(discountValue);
            result.setUpdateDate(updateDate);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long  id;
    private Long materialId;
    private Long currencyTypeId;
    private String discountPct;
    private String discountValue;
    private Timestamp updateDate;
    @Transient
    @JsonIgnore
    private Long insertId;

    

    public static PosDiscountMapping  fromRow(Row row) {
        var result = new PosDiscountMapping ();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setDiscountPct(ManipulateUtil.parseRow(row, DISCOUNT_PCT_COL, String.class));
        result.setDiscountValue(ManipulateUtil.parseRow(row, DISCOUNT_VALUE_COL, String.class));
        result.setUpdateDate(ManipulateUtil.parseRow(row, UPDATE_DATE_COL, Timestamp.class));
        result.setMaterialId(ManipulateUtil.parseRow(row, MATERIAL_ID_COL, Long.class));
        result.setCurrencyTypeId(ManipulateUtil.parseRow(row, CURRENCY_TYPE_ID_COL, Long.class));
        
        
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
    public PosDiscountMapping update(PosDiscountMapping  newData) {
        return Builder.updateBuilder(this, newData).build();
    }
}
