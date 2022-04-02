package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.CashClosing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CashClosingForm implements RecordAdapter<CashClosing> {

    private Long id;
    private Long cashCashierId;
    private int paymentType;
    private String amountRP;
    private String amountUSD;
    
    @Override
    public CashClosing convertNewRecord() {
        return CashClosing.Builder.createNewRecord(paymentType,amountRP,amountUSD,cashCashierId)
                    .id(id)
                    .build();
    }
    @Override
    public CashClosing convertToRecord() {
        return CashClosing.Builder.emptyBuilder()
                    .cashCashierId(cashCashierId)
                    .paymentType(paymentType)
                    .amountRP(amountRP)
                    .amountUSD(amountUSD)
                    .id(id)
                    .build();
    }  
}