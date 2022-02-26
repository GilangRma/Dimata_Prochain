package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;

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
public class Cash_ReturnPayment implements UpdateAvailable<Cash_ReturnPayment>, Persistable<Long>{

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
        private Long CURRENCY_ID;
        private Long CASH_BILL_MAIN_ID;
        private double RATE;
        private String PAYMENT_STATUS;
        private double AMOUNT;

        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;


        public static Builder updateBuilder(Cash_ReturnPayment oldRecord, Cash_ReturnPayment newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .CURRENCY_ID(changeItOrNot(newRecord.getCURRENCY_ID(), oldRecord.getCURRENCY_ID()))
                .CASH_BILL_MAIN_ID(changeItOrNot(newRecord.getCASH_BILL_MAIN_ID(), oldRecord.getCASH_BILL_MAIN_ID()))
                .RATE(changeItOrNot(newRecord.getRATE(), oldRecord.getRATE()))
                .PAYMENT_STATUS(changeItOrNot(newRecord.getPAYMENT_STATUS(), oldRecord.getPAYMENT_STATUS()))
                .AMOUNT(changeItOrNot(newRecord.getAMOUNT(), oldRecord.getAMOUNT()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public Cash_ReturnPayment build() {
            Cash_ReturnPayment result = new Cash_ReturnPayment();

            result.setId(id);
            result.setCURRENCY_ID(CURRENCY_ID);
            result.setCASH_BILL_MAIN_ID(CASH_BILL_MAIN_ID);
            result.setRATE(RATE);
            result.setPAYMENT_STATUS(PAYMENT_STATUS);
            result.setAMOUNT(AMOUNT);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long id;
    private Long CURRENCY_ID;
    private Long CASH_BILL_MAIN_ID;
    private double RATE;
    private String PAYMENT_STATUS;
    private double AMOUNT;

    @Transient
    @JsonIgnore
    private Long insertId;

    public static Cash_ReturnPayment fromRow(Row row) {
        var result = new Cash_ReturnPayment();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setCURRENCY_ID(ManipulateUtil.parseRow(row, CURRENCY_ID_COL, Long.class));
        result.setCASH_BILL_MAIN_ID(ManipulateUtil.parseRow(row, CASH_BILL_MAIN_ID_COL, Long.class));
        result.setRATE(ManipulateUtil.parseRow(row, RATE_COL, Double.class));
        result.setAMOUNT(ManipulateUtil.parseRow(row, AMOUNT_COL, Double.class));
        result.setPAYMENT_STATUS(ManipulateUtil.parseRow(row, PAYMENT_STATUS_COL, String.class)); 
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
    public Cash_ReturnPayment update(Cash_ReturnPayment newData) {
        return Builder.updateBuilder(this, newData).build();
    }



    
}
