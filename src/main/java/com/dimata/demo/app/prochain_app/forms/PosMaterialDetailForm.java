package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.PosMaterialDetail;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosMaterialDetailForm implements RecordAdapter<PosMaterialDetail> {
    private Long  id;
    private Long materialId;
    private Double qty;
    private Double berat;
    private Double hargaBeli;
    private Double hargaJual;
    private Double rate;
    private Double faktorJual;
    private Double uphetPersentase;
    private Double uphetValue;
    private Double uphetPersentaseTot;
    private Double uphetValueTotal;
    private Double ongkos;
    @Override
    public PosMaterialDetail convertNewRecord() {
        
        return PosMaterialDetail.Builder.createNewRecord()
        .materialId(materialId)
        .qty(qty)
        .berat(berat)
        .hargaBeli(hargaBeli)
        .hargaJual(hargaJual)
        .rate(rate)
        .faktorJual(faktorJual)
        .uphetPersentase(uphetPersentase)
        .uphetValue(uphetValue)
        .uphetPersentaseTot(uphetPersentaseTot)
        .uphetValueTotal(uphetValueTotal)
        .ongkos(ongkos)
        .id(id)
        .build();
    }
    @Override
    public PosMaterialDetail convertToRecord() {
        
        return PosMaterialDetail.Builder.emptyBuilder()
        .materialId(materialId)
        .qty(qty)
        .berat(berat)
        .hargaBeli(hargaBeli)
        .hargaJual(hargaJual)
        .rate(rate)
        .faktorJual(faktorJual)
        .uphetPersentase(uphetPersentase)
        .uphetValue(uphetValue)
        .uphetPersentaseTot(uphetPersentaseTot)
        .uphetValueTotal(uphetValueTotal)
        .ongkos(ongkos)
        .id(id)
        .build();
    }
}
