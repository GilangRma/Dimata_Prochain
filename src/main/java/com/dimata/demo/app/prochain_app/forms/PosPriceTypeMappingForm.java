package com.dimata.demo.app.prochain_app.forms;


import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeDeserialize;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeSerialize;
import com.dimata.demo.app.prochain_app.models.table.PosPriceTypeMapping;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosPriceTypeMappingForm implements RecordAdapter<PosPriceTypeMapping>{
    private Long  id;
    private Long materialId;
    private Long standartRateId;
    private Double price;
    @JsonDeserialize(converter = TimeSerialize.class)
    private LocalDateTime updateDate;

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
