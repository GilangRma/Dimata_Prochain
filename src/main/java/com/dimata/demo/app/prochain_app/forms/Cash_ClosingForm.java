package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.Cash_Closing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cash_ClosingForm implements RecordAdapter<Cash_ClosingForm> {

        private long id;
        private String CASH_CASHIER_ID;
        private String PAYMENT_TYPE;
        private String AMOUNT_RP;
        private String AMOUNT_USD;

    @Override
    public Cash_Closing convertToRecord() {
        return Cash_Closing.Builder.emptyBuilder()
            .CASH_CASHIER_ID(CASH_CASHIER_ID)
            .PAYMENT_TYPE(PAYMENT_TYPE)
            .AMOUNT_RP(AMOUNT_RP)
            .AMOUNT_USD(AMOUNT_USD)
            .build();
    }

    @Override
    public Cash_Closing convertNewRecord() {

        return Cash_Closing.Builder.createNewRecord(CASH_CASHIER_ID, PAYMENT_TYPE, AMOUNT_RP, AMOUNT_USD)
            .id(id)
            .build();

    }

}