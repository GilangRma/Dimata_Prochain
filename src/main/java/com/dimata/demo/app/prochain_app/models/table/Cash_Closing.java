package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;

import java.util.Objects;

import com.dimata.demo.app.prochain_app.core.api.UpdateAvailable;
import com.dimata.demo.app.prochain_app.core.util.GenerateUtil;
import com.dimata.demo.app.prochain_app.core.util.ManipulateUtil;
import com.dimata.demo.app.prochain_app.core.util.jackson.DateSerialize;
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

public class Cash_Closing implements UpdateAvailable<Cash_Closing>, Persistable <Long>{

    public static final String TABLE_NAME = "cash_closing";
    public static final String ID_COL = "CASH_CLOSING_ID";
    public static final String CASH_CASHIER_ID_COL = "CASH_CASHIER_ID";
    public static final String PAYMENT_TYPE_COL = "PAYMENT_TYPE";
    public static final String AMOUNT_RP_COL = "AMOUNT_RP";
    public static final String AMOUNT_USD_COL = "AMOUNT_USD";

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private long id;
        private long cashCashierId;
        private String paymentType;
        private String amountRP;
        private String amountUSD;

        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord(String paymentType, String amountRP, String amountUSD ) {
            return new Builder().newRecord(true)

                .paymentType(Objects.requireNonNull(paymentType, "PAYMENT_TYPE tidak boleh kosong"))
                .amountRP(Objects.requireNonNull(amountRP, "AMOUNT_RP tidak boleh kosong"))
                .amountUSD(Objects.requireNonNull(amountUSD, "AMOUNT_USD tidak boleh kosong"));
        }

        public static Builder updateBuilder(Cash_Closing oldRecord, Cash_Closing newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .cashCashierId(changeItOrNot(newRecord.getCashCashierId(), oldRecord.getCashCashierId()))
                .paymentType(changeItOrNot(newRecord.getPaymentType(), oldRecord.getPaymentType()))
                .amountRP(changeItOrNot(newRecord.getAmountRP(), oldRecord.getAmountRP()))
                .amountUSD(changeItOrNot(newRecord.getAmountUSD(), oldRecord.getAmountUSD()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public Cash_Closing build() {
            Cash_Closing result = new Cash_Closing();

            result.setId(id);
            result.setCashCashierId(cashCashierId);
            result.setPaymentType(paymentType);
            result.setAmountRP(amountRP);
            result.setAmountUSD(amountUSD);
            return result;
        }

    }

    @Id
    @Column(ID_COL)
    private long id;
    private long cashCashierId;
    private String paymentType;
    private String amountRP;
    private String amountUSD;



    @Transient
    @JsonIgnore
    private Long insertId;

    public static Cash_Closing fromRow(Row row) {
        var result = new Cash_Closing();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setCashCashierId(ManipulateUtil.parseRow(row, CASH_CASHIER_ID_COL, Long.class));
        result.setPaymentType(ManipulateUtil.parseRow(row, PAYMENT_TYPE_COL, String.class));
        result.setAmountRP(ManipulateUtil.parseRow(row, AMOUNT_RP_COL, String.class));
        result.setAmountUSD(ManipulateUtil.parseRow(row, AMOUNT_USD_COL, String.class));  
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
    public Cash_Closing update(Cash_Closing newData) {
        return Builder.updateBuilder(this, newData).build();
    }
     
}
