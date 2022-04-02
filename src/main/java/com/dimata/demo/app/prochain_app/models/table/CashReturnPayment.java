package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;

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
@Table(CashReturnPayment.TABLE_NAME)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CashReturnPayment implements UpdateAvailable<CashReturnPayment>, Persistable<Long>{

    public static final String TABLE_NAME = "cash_return_payment";
    public static final String ID_COL = "RETURN_ID";
    public static final String CURRENCY_ID_COL = "CURRENCY_ID";
    public static final String CASH_BILL_MAIN_ID_COL = "CASH_BILL_MAIN_ID";
    public static final String RATE_COL = "RATE";
    public static final String PAYMENT_STATUS_COL = "PAYMENT_STATUS";
    public static final String AMOUNT_COL = "AMOUNT";

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long id;
        private Long currencyId;
        private Long cashBillMainId;
        private double rate;
        private String paymentStatus;
        private double amount;

        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord(double rate, double amount) {
            return new Builder().newRecord(true)
                .rate(Objects.requireNonNull(rate, "rate diperlukan"))
                .amount(Objects.requireNonNull(amount, "amount diperlukan"));
                
        }


        public static Builder updateBuilder(CashReturnPayment oldRecord, CashReturnPayment newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .currencyId(changeItOrNot(newRecord.getCurrencyId(), oldRecord.getCurrencyId()))
                .cashBillMainId(changeItOrNot(newRecord.getCashBillMainId(), oldRecord.getCashBillMainId()))
                .rate(changeItOrNot(newRecord.getRate(), oldRecord.getRate()))
                .paymentStatus(changeItOrNot(newRecord.getPaymentStatus(), oldRecord.getPaymentStatus()))
                .amount(changeItOrNot(newRecord.getAmount(), oldRecord.getAmount()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public CashReturnPayment build() {
            CashReturnPayment result = new CashReturnPayment();

            result.setId(id);
            result.setCurrencyId(currencyId);
            result.setCashBillMainId(cashBillMainId);
            result.setRate(rate);
            result.setPaymentStatus(paymentStatus);
            result.setAmount(amount);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long id;
    private Long currencyId;
    private Long cashBillMainId;
    private double rate;
    private String paymentStatus;
    private double amount;


    @Transient
    @JsonIgnore
    private Long insertId;

    public static CashReturnPayment fromRow(Row row) {
        var result = new CashReturnPayment();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setCurrencyId(ManipulateUtil.parseRow(row, CURRENCY_ID_COL, Long.class));
        result.setCashBillMainId(ManipulateUtil.parseRow(row, CASH_BILL_MAIN_ID_COL, Long.class));
        result.setRate(ManipulateUtil.parseRow(row, RATE_COL, Double.class));
        result.setAmount(ManipulateUtil.parseRow(row, AMOUNT_COL, Double.class));
        result.setPaymentStatus(ManipulateUtil.parseRow(row, PAYMENT_STATUS_COL, String.class)); 
        return result;
    }

    @Override
    public boolean isNew() {
        if(id == null && insertId == null) {
            id = new GenerateUtil().generateOID();
            return true;
        } else if (id == null) {
            id = insertId;
            return true;
        }
        return false;
    }

    @Override
    public CashReturnPayment update(CashReturnPayment newData) {
        return Builder.updateBuilder(this, newData).build();
    }



    
}
