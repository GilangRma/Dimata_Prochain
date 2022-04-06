package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.CashReturnPayment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CashReturnPaymentForm implements RecordAdapter<CashReturnPayment> {
    
    private Long id;
    private Long currencyId;
    private Long cashBillMainId;
    private double rate;
    private int paymentStatus;
    private double amount;
    @Override
    public CashReturnPayment convertNewRecord() {
            
        return CashReturnPayment.Builder.createNewRecord(rate,amount)
                .currencyId(currencyId)
                .cashBillMainId(cashBillMainId)
                .paymentStatus(paymentStatus)
                .id(id)
                .build();
    }
    @Override
    public CashReturnPayment convertToRecord() {
           
        return CashReturnPayment.Builder.emptyBuilder()
        .currencyId(currencyId)
        .cashBillMainId(cashBillMainId)
        .rate(rate)
        .paymentStatus(paymentStatus)
        .amount(amount)
        .id(id)
        .build();
    }

}
