package com.dimata.demo.app.prochain_app.forms;

import java.sql.Timestamp;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.PosPriceTypeMapping;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosPriceTypeMappingForm implements RecordAdapter<PosPriceTypeMapping>{
    private Long  id;
    private Long materialId;
    private Long standartRateId;
    private String price;
    private Timestamp updateDate;

    @Override
    public PosPriceTypeMapping convertNewRecord() {
        return PosPriceTypeMapping.Builder.createNewRecord(price)
        .materialId(materialId)
        .standartRateId(standartRateId)
        .updateDate(updateDate)
        .id(id)
        .build();

    }

    @Override
    public PosPriceTypeMapping convertToRecord() {
        return PosPriceTypeMapping.Builder.emptyBuilder()
        .materialId(materialId)
        .standartRateId(standartRateId)
        .price(price)
        .updateDate(updateDate)
        .id(id)
        .build();
    }
}
