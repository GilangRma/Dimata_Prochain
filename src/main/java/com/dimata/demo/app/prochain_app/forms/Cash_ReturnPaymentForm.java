package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.Cash_ReturnPayment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cash_ReturnPaymentForm implements RecordAdapter<Cash_ReturnPaymentForm> {
    
    private long id;
    private long CURRENCY_ID;
    private long CASH_BILL_MAIN_ID;
    private double RATE;
    private String PAYMENT_STATUS;
    private double AMOUNT;

    @Override
    public Cash_ReturnPayment convertToRecord() {
        return Cash_ReturnPayment.Builder.emptyBuilder()
            .CURRENCY_ID(CURRENCY_ID)
            .CASH_BILL_MAIN_ID(CASH_BILL_MAIN_ID)
            .RATE(RATE)
            .PAYMENT_STATUS(PAYMENT_STATUS)
            .AMOUNT(AMOUNT)
            .id(id)
            .build();
    }

    @Override
    public Cash_ReturnPayment convertNewRecord() {
        return Cash_ReturnPayment.Builder.createNewRecord()
            .CURRENCY_ID(CURRENCY_ID)
            .CASH_BILL_MAIN_ID(CASH_BILL_MAIN_ID)
            .RATE(RATE)
            .PAYMENT_STATUS(PAYMENT_STATUS)
            .AMOUNT(AMOUNT)
            .id(id)
            .build();

    }

}
