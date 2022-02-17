package com.dimata.demo.app.prochain_app.forms;

import java.sql.Timestamp;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.PosDiscountMapping;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosDiscountMappingForm implements RecordAdapter<PosDiscountMapping>{
    
    private Long  id;
    private Long materialId;
    private Long currencyTypeId;
    private String discountPct;
    private String discountValue;
    private Timestamp updateDate;
    
    @Override
    public PosDiscountMapping convertNewRecord() {
        return PosDiscountMapping.Builder.createNewRecord(discountPct, discountValue)
        .materialId(materialId)
        .currencyTypeId(currencyTypeId)
        .id(id)
        .build();

    }

    @Override
    public PosDiscountMapping convertToRecord() {
        return PosDiscountMapping.Builder.emptyBuilder()
        .materialId(materialId)
        .currencyTypeId(currencyTypeId)
        .discountPct(discountPct)
        .discountValue(discountValue)
        .id(id)
        .build();
    }
    
}
