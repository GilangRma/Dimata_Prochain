package com.dimata.demo.app.prochain_app.forms;

import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeDeserialize;
import com.dimata.demo.app.prochain_app.models.table.PosStockOpname;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosStockOpnameForm implements RecordAdapter<PosStockOpname>{

    private Long id;
    @JsonDeserialize(converter = TimeDeserialize.class)
    private LocalDateTime stockOpnameDate;
    private String stockOpnameTime;
    private String stockOpnameNumber;
    private Long locationId;
    private Long supplierId;
    private Long categoryId;
    private Long subCategoryId;
    private int stockOpnameStatus;
    private String remark;
    private Long etalaseId;
    private int opnameItemType;
    private int codeCounter;
    private Long materialTypeId;
    @Override
    public PosStockOpname convertNewRecord() {
        return PosStockOpname.Builder.createNewRecord()
                .stockOpnameDate(stockOpnameDate)
                .stockOpnameTime(stockOpnameTime)
                .stockOpnameNumber(stockOpnameNumber)
                .locationId(locationId)
                .supplierId(supplierId)
                .categoryId(categoryId)
                .subCategoryId(subCategoryId)
                .stockOpnameStatus(stockOpnameStatus)
                .remark(remark)
                .etalaseId(etalaseId)
                .opnameItemType(opnameItemType)
                .codeCounter(codeCounter)
                .materialTypeId(materialTypeId)
                .id(id)
                .build();
    }
    @Override
    public PosStockOpname convertToRecord() {
        return PosStockOpname.Builder.emptyBuilder()
                .stockOpnameDate(stockOpnameDate)
                .stockOpnameTime(stockOpnameTime)
                .stockOpnameNumber(stockOpnameNumber)
                .locationId(locationId)
                .supplierId(supplierId)
                .categoryId(categoryId)
                .subCategoryId(subCategoryId)
                .stockOpnameStatus(stockOpnameStatus)
                .remark(remark)
                .etalaseId(etalaseId)
                .opnameItemType(opnameItemType)
                .codeCounter(codeCounter)
                .materialTypeId(materialTypeId)
                .id(id)
                .build();
    }
    
}
