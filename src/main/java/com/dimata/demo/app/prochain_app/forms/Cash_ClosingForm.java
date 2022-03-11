package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.Cash_Closing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cash_ClosingForm implements RecordAdapter<Cash_Closing> {

    private Long id;
    private Long cashCashierId;
    private String paymentType;
    private String amountRP;
    private String amountUSD;

    @Override
    public Cash_Closing convertNewRecord() {
        return Cash_Closing.Builder.createNewRecord(paymentType,amountRP,amountUSD)
                
                .build();
    }
    @Override
    public Cash_Closing convertToRecord() {
        return Cash_Closing.Builder.emptyBuilder()
                .cashCashierId(cashCashierId)
                .paymentType(paymentType)
                .amountRP(amountRP)
                .amountUSD(amountUSD)
                .build();
    }

    
}