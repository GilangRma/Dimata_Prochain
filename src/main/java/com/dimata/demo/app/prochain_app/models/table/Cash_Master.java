package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;


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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cash_Master implements UpdateAvailable<Cash_Master>, Persistable<Long> {
    
    public static final String TABLE_NAME = "cash_master";
    public static final String ID_COL = "CASH_MASTER_ID";
    public static final String CASHIER_NUMBER_COL = "CASHIER_NUMBER";
    public static final String LOCATION_ID_COL = "LOCATION_ID";
    public static final String TAX_COL = "TAX";
    public static final String SERVICE_COL = "SERVICE";
    public static final String PRICE_TYPE_COL = "PRICE_TYPE";
    public static final String CABANG_COL = "CABANG";

    @Accessors(fluent = true)
    @Setter
    public static class Builder {
        
        private long id;
        private String CASHIER_NUMBER;
        private long LOCATION_ID;
        private double TAX;
        private double SERVICE;
        private String PRICE_TYPE;
        private String CABANG;

        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder updateBuilder(Cash_Master oldRecord, Cash_Master newRecord) {
            return new Builder()
            .id(oldRecord.getId())
            .CASHIER_NUMBER(changeItOrNot(newRecord.getCASHIER_NUMBER(), oldRecord.getCASHIER_NUMBER()))
            .LOCATION_ID(changeItOrNot(newRecord.getLOCATION_ID(), oldRecord.getLOCATION_ID()))
            .TAX(changeItOrNot(newRecord.getTAX(), oldRecord.getTAX()))
            .SERVICE(changeItOrNot(newRecord.getSERVICE(), oldRecord.getSERVICE()))
            .PRICE_TYPE(changeItOrNot(newRecord.getPRICE_TYPE(), oldRecord.getPRICE_TYPE()))
            .CABANG(changeItOrNot(newRecord.getCABANG(), oldRecord.getCABANG()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public Cash_Master build() {
            Cash_Master result = new Cash_Master();

            result.setId(id);
            result.setCASHIER_NUMBER(CASHIER_NUMBER);
            result.setLOCATION_ID(LOCATION_ID);
            result.setTAX(TAX);
            result.setSERVICE(SERVICE);
            result.setPRICE_TYPE(PRICE_TYPE);
            result.setCABANG(CABANG);
            return result;
        }

    }

    @Id
    @Column(ID_COL)
    private long id;
    private String CASHIER_NUMBER;
    private long LOCATION_ID;
    private double TAX;
    private double SERVICE;
    private String PRICE_TYPE;
    private String CABANG;

    @JsonSerialize(converter = DateSerialize.class)

    @Transient
    @JsonIgnore
    private Long insertId;

    public static Cash_Master fromRow(Row row) {
        var result = new Cash_Master();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setCASHIER_NUMBER(ManipulateUtil.parseRow(row, CASHIER_NUMBER_COL, String.class));
        result.setLOCATION_ID(ManipulateUtil.parseRow(row, LOCATION_ID_COL, Long.class));
        result.setTAX(ManipulateUtil.parseRow(row, TAX_COL, Double.class));
        result.setSERVICE(ManipulateUtil.parseRow(row, SERVICE_COL, Double.class));  
        result.setPRICE_TYPE(ManipulateUtil.parseRow(row, PRICE_TYPE_COL, String.class));  
        result.setCABANG(ManipulateUtil.parseRow(row, CABANG_COL, String.class));  
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
    public Cash_Master update(Cash_Master newData) {
        return Builder.updateBuilder(this, newData).build();
    }


}
