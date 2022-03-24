package com.dimata.demo.app.prochain_app.forms;


import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeDeserialize;
import com.dimata.demo.app.prochain_app.models.table.PosDiscountMapping;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosDiscountMappingForm implements RecordAdapter<PosDiscountMapping>{
    
    private Long  id;
    private Long materialId;
    private Long currencyTypeId;
    private Double discountPct;
    private Double discountValue;
    @JsonDeserialize(converter = TimeDeserialize.class)
    private LocalDateTime updateDate;
    
    @Override
    public PosDiscountMapping convertNewRecord() {
        return PosDiscountMapping.Builder.createNewRecord(discountPct, discountValue)
        .materialId(materialId)
        .currencyTypeId(currencyTypeId)
        .updateDate(updateDate)
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
        .updateDate(updateDate)
        .id(id)
        .build();
    }
    
}
