package com.dimata.demo.app.prochain_app.forms;

import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeDeserialize;
import com.dimata.demo.app.prochain_app.models.table.CashCashier;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CashCashierForm implements RecordAdapter <CashCashier>{
    
    private Long  id;
    private Long cashMasterId;
    private Long appUserId;
    @JsonDeserialize(converter = TimeDeserialize.class)
    private LocalDateTime openDate;
    private Long spvOpenId; 
    private String spvOpenName;
    private Long spvCloseId;
    private String spvCloseName;
    private Long shiftId;
    private LocalDateTime closeDate;
    
    @Override
    public CashCashier convertNewRecord() {
        return CashCashier.Builder.createNewRecord(openDate, spvOpenId, spvOpenName, spvCloseId, spvCloseName, closeDate)
                .cashMasterId(cashMasterId)
                .appUserId(appUserId)
                .shiftId(shiftId)
                .id(id)
                .build();
    }
    @Override
    public CashCashier convertToRecord() {
        return CashCashier.Builder.emptyBuilder()
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
