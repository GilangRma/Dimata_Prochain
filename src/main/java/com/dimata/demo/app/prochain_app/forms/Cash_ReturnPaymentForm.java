package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.Cash_ReturnPayment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cash_ReturnPaymentForm implements RecordAdapter<Cash_ReturnPayment> {
    
    private long id;
        private long currencyId;
        private long cashBillMainId;
        private double rate;
        private String paymentStatus;
        private double amount;
        @Override
        public Cash_ReturnPayment convertNewRecord() {
            
            return Cash_ReturnPayment.Builder.createNewRecord(rate,amount)
                    .currencyId(currencyId)
                    .cashBillMainId(cashBillMainId)
                    .paymentStatus(paymentStatus)
                    .id(id)
                    .build();
        }
        @Override
        public Cash_ReturnPayment convertToRecord() {
           
            return Cash_ReturnPayment.Builder.emptyBuilder()
            .currencyId(currencyId)
            .cashBillMainId(cashBillMainId)
            .rate(rate)
            .paymentStatus(paymentStatus)
            .amount(amount)
            .id(id)
            .build();
        }

}
