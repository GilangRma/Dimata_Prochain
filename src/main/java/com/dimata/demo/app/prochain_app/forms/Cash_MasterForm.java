package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.Cash_Master;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cash_MasterForm implements RecordAdapter<Cash_ClosingForm> {

    private long id;
        private String CASHIER_NUMBER;
        private long LOCATION_ID;
        private double TAX;
        private double SERVICE;
        private String PRICE_TYPE;
        private String CABANG;
    @Override
    public Cash_Master convertToRecord() {
        return Cash_Master.Builder.emptyBuilder()
            .CASHIER_NUMBER(CASHIER_NUMBER)
            .LOCATION_ID(LOCATION_ID)
            .TAX(TAX)
            .SERVICE(SERVICE)
            .PRICE_TYPE(PRICE_TYPE)
            .CABANG(CABANG)
            .build();
    }

    @Override
    public Cash_Master convertNewRecord() {
        return Cash_Master.Builder.createNewRecord(CASHIER_NUMBER, LOCATION_ID, TAX, SERVICE, PRICE_TYPE, CABANG)
        .id(id)
        .build();
    }
    
}
