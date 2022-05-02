package com.dimata.demo.app.prochain_app.forms;

import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.PosMaterialStockCode;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosMaterialStockCodeForm implements RecordAdapter<PosMaterialStockCode> {
    private Long id;
    private Long materialId;
    private Long locationId;
    private Long stockCode;
    private Integer stockStatus;
    private Double value;
    private LocalDateTime stockDate;

    @Override
    public PosMaterialStockCode convertNewRecord() {
        return PosMaterialStockCode.Builder.createNewRecord(materialId, locationId, stockCode, stockStatus)
        .value(value)
        .stockDate(stockDate)
        .id(id)
        .build();
    }
    @Override
    public PosMaterialStockCode convertToRecord() {
        return PosMaterialStockCode.Builder.emptyBuilder()
        .id(id)
        .materialId(materialId)
        .locationId(locationId)
        .stockCode(stockCode)
        .value(value)
        .stockStatus(stockStatus)
        .stockDate(stockDate)
        .build();
    }
}
