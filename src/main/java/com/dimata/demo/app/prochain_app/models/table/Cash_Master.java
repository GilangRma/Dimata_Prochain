package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;

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
        private String cashierNumber;
        private long locationId;
        private double tax;
        private double service;
        private String priceType;
        private String cabang;

        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord(String cashierNumber, double tax, double service, String priceType, String cabang) {
            return new Builder().newRecord(true)
                .cashierNumber(Objects.requireNonNull(cashierNumber, "cashierNumber diperlukan"))
                .tax(Objects.requireNonNull(tax, "tax diperlukan"))
                .service(Objects.requireNonNull(service, "service diperlukan"))
                .priceType(Objects.requireNonNull(priceType, "priceType diperlukan"))
                .cabang(Objects.requireNonNull(cabang, "cabang diperlukan"));
                
        }

        public static Builder updateBuilder(Cash_Master oldRecord, Cash_Master newRecord) {
            return new Builder()
            .id(oldRecord.getId())
            .cashierNumber(changeItOrNot(newRecord.getCashierNumber(), oldRecord.getCashierNumber()))
            .locationId(changeItOrNot(newRecord.getLocationId(), oldRecord.getLocationId()))
            .tax(changeItOrNot(newRecord.getTax(), oldRecord.getTax()))
            .service(changeItOrNot(newRecord.getService(), oldRecord.getService()))
            .priceType(changeItOrNot(newRecord.getPriceType(), oldRecord.getPriceType()))
            .cabang(changeItOrNot(newRecord.getCabang(), oldRecord.getCabang()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public Cash_Master build() {
            Cash_Master result = new Cash_Master();

            result.setId(id);
            result.setCashierNumber(cashierNumber);
            result.setLocationId(locationId);
            result.setTax(tax);
            result.setService(service);
            result.setPriceType(priceType);
            result.setCabang(cabang);
            return result;
        }

    }

    @Id
    @Column(ID_COL)
    private long id;
    private String cashierNumber;
    private long locationId;
    private double tax;
    private double service;
    private String priceType;
    private String cabang;

    @JsonSerialize(converter = DateSerialize.class)

    @Transient
    @JsonIgnore
    private Long insertId;

    public static Cash_Master fromRow(Row row) {
        var result = new Cash_Master();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setCashierNumber(ManipulateUtil.parseRow(row, CASHIER_NUMBER_COL, String.class));
        result.setLocationId(ManipulateUtil.parseRow(row, LOCATION_ID_COL, Long.class));
        result.setTax(ManipulateUtil.parseRow(row, TAX_COL, Double.class));
        result.setService(ManipulateUtil.parseRow(row, SERVICE_COL, Double.class));  
        result.setPriceType(ManipulateUtil.parseRow(row, PRICE_TYPE_COL, String.class));  
        result.setCabang(ManipulateUtil.parseRow(row, CABANG_COL, String.class));  
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
