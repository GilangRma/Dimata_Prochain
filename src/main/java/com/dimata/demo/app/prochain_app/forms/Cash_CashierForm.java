package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeDeserialize;
import com.dimata.demo.app.prochain_app.models.table.Cash_Cashier;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cash_CashierForm implements RecordAdapter <Cash_Cashier>{
    
    private Long  id;
    private Long cashMasterId;
    private Long appUserId;
    private TimeDeserialize openDate;
    private Long spvOpenId; 
    private String spvOpenName;
    private Long spvCloseId;
    private String spvCloseName;
    private Long shiftId;
    private TimeDeserialize closeDate;

    public Cash_Cashier convertNewRecord() {
        return Cash_Cashier.Builder.createNewRecord(openDate, spvOpenId, spvOpenName, spvCloseId, spvCloseName, closeDate)
            .cashMasterId(cashMasterId)
            .appUserId(appUserId)
            .shiftId(shiftId)
            .id(id)
            .build();
    }

    public Cash_Cashier convertToRecord() {
        return Cash_Cashier.Builder.emptyBuilder()
            .cashMasterId(cashMasterId)
            .appUserId(appUserId)
            .openDate(openDate)
            .spvOpenId(spvOpenId)
            .spvOpenName(spvOpenName)
            .spvCloseId(spvCloseId)
            .spvCloseName(spvCloseName)
            .shiftId(shiftId)
            .closeDate(closeDate)
            .id(id)
            .build();
        }
}
