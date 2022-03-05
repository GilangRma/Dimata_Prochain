package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.Cash_Master;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cash_MasterForm implements RecordAdapter<Cash_Master> {

    private Long id;
    private String cashierNumber;
    private Long locationId;
    private double tax;
    private double service;
    private String priceType;
    private String cabang;
    
    public Cash_Master convertToRecord() {
        return Cash_Master.Builder.emptyBuilder()
            .cashierNumber(cashierNumber)
            .locationId(locationId)
            .tax(tax)
            .service(service)
            .priceType(priceType)
            .cabang(cabang)
            .build();
    }

    
    public Cash_Master convertNewRecord() {
        return Cash_Master.Builder.createNewRecord(cashierNumber, tax, service, priceType, cabang)
        .locationId(locationId)
        .id(id)
        .build();
    }
    
}
