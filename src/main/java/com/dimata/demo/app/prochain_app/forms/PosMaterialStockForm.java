package com.dimata.demo.app.prochain_app.forms;

import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeDeserialize;
import com.dimata.demo.app.prochain_app.models.table.PosMaterialStock;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosMaterialStockForm implements RecordAdapter<PosMaterialStock>{

    private Long id;
    private Long periodeId;
    private Long materialUnitId;
    private Long locationId;
    private double qty;
    private double qtyMin;
    private double qtyMax;
    private double openingQty;
    private double closingQty;
    private double qtyIn;
    private double qtyOut;
    private double qtyOptimum;
    private double berat;
    private double beratIn;
    private double beratOut;
    private double openingBerat;
    private double closingBerat;
    @JsonDeserialize(converter = TimeDeserialize.class)
    private LocalDateTime updateDate;
    
    @Override
    public PosMaterialStock convertNewRecord() {
        return PosMaterialStock.Builder.createNewRecord(periodeId, materialUnitId,locationId, updateDate)
        .qty(qty)
        .qtyMin(qtyMin)
        .qtyMax(qtyMax)
        .openingQty(openingQty)
        .closingQty(closingQty)
        .qtyIn(qtyIn)
        .qtyOut(qtyOut)
        .qtyOptimum(qtyOptimum)
        .beratIn(beratIn)
        .berat(berat)
        .beratOut(beratOut)
        .openingBerat(openingBerat)
        .closingBerat(closingBerat)
        .id(id)
        .build();

    }
    @Override
    public PosMaterialStock convertToRecord() {
        return PosMaterialStock.Builder.empetyBuilder()
        .periodeId(periodeId)
        .materialUnitId(materialUnitId)
        .locationId(locationId)
        .qty(qty)
        .qtyMin(qtyMin)
        .qtyMax(qtyMax)
        .openingQty(openingQty)
        .closingQty(closingQty)
        .qtyIn(qtyIn)
        .qtyOut(qtyOut)
        .qtyOptimum(qtyOptimum)
        .beratIn(beratIn)
        .berat(berat)
        .beratOut(beratOut)
        .openingBerat(openingBerat)
        .closingBerat(closingBerat)
        .updateDate(updateDate)
        .id(id)
        .build();

   
    }
    
}
