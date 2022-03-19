package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.CashMaster;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CashMasterForm implements RecordAdapter<CashMaster> {

    private Long id;
    private int cashierNumber;
    private Long locationId;
    private double tax;
    private double service;
    private String priceType;
    private String cabang;
    
    @Override
    public CashMaster convertToRecord() {
        return CashMaster.Builder.emptyBuilder()
                .cashierNumber(cashierNumber)
                .locationId(locationId)
                .tax(tax)
                .service(service)
                .priceType(priceType)
                .cabang(cabang)
                .build();
    }

    @Override
    public CashMaster convertNewRecord() {
        return CashMaster.Builder.createNewRecord(cashierNumber, tax, service, priceType, cabang, locationId)
                .build();
    }
    
    
}
