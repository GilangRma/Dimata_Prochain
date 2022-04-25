package com.dimata.demo.app.prochain_app.models.table;

import org.springframework.data.annotation.Transient;
import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;

import com.dimata.demo.app.prochain_app.core.api.UpdateAvailable;
import com.dimata.demo.app.prochain_app.core.util.GenerateUtil;
import com.dimata.demo.app.prochain_app.core.util.ManipulateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import io.r2dbc.spi.Row;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PosMaterialDetail implements UpdateAvailable<PosMaterialDetail>, Persistable<Long>{

    public static final String TABLE_NAME = "pos_material_detail";
    public static final String ID_COL = "MATERIAL_DETAIL_ID";
    public static final String MATERIAL_ID_COL = "MATERIAL_ID";
    public static final String QTY_COL = "QTY";
    public static final String BERAT_COL ="BERAT";
    public static final String HARGA_BELI_COL = "HARGA_BELI";
    public static final String HARGA_JUAL_COL = "HARGA_JUAL";
    public static final String RATE_COL = "RATE";
    public static final String FAKTOR_JUAL_COL = "FAKTOR_JUAL";
    public static final String UPHET_PERSENTASE_COL = "UPHET_PERSENTASE";
    public static final String UPHET_VALUE_COL = "UPHET_VALUE";
    public static final String UPHET_PERSENTASE_TOT_COL = "UPHET_PERSENTASE_TOT";
    public static final String UPHET_VALUE_TOTAL_COL = "UPHET_VALUE_TOTAL";
    public static final String ONGKOS_COL = "ONGKOS";
    
    

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

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
        



        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord() {
            return new Builder().newRecord(true);
                
                
        }

        public static Builder updateBuilder(PosMaterialDetail oldRecord, PosMaterialDetail newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .materialId(changeItOrNot(newRecord.getMaterialId(), oldRecord.getMaterialId()))
                .qty(changeItOrNot(newRecord.getQty(), oldRecord.getQty()))
                .berat(changeItOrNot(newRecord.getBerat(), oldRecord.getBerat()))
                .hargaBeli(changeItOrNot(newRecord.getHargaBeli(), oldRecord.getHargaBeli()))
                .hargaJual(changeItOrNot(newRecord.getHargaJual(), oldRecord.getHargaJual()))
                .rate(changeItOrNot(newRecord.getRate(), oldRecord.getRate()))
                .faktorJual(changeItOrNot(newRecord.getFaktorJual(), oldRecord.getFaktorJual()))
                .uphetPersentase(changeItOrNot(newRecord.getUphetPersentase(), oldRecord.getUphetPersentase()))
                .uphetValue(changeItOrNot(newRecord.getUphetValue(), oldRecord.getUphetValue()))
                .uphetPersentaseTot(changeItOrNot(newRecord.getUphetPersentaseTot(), oldRecord.getUphetPersentaseTot()))
                .uphetValueTotal(changeItOrNot(newRecord.getUphetValueTotal(), oldRecord.getUphetValueTotal()))
                .ongkos(changeItOrNot(newRecord.getOngkos(), oldRecord.getOngkos()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public PosMaterialDetail  build() {
            PosMaterialDetail  result = new PosMaterialDetail ();
            result.setId(id);
            result.setMaterialId(materialId);
            result.setQty(qty);
            result.setBerat(berat);
            result.setHargaBeli(hargaBeli);
            result.setHargaJual(hargaJual);
            result.setRate(rate);
            result.setFaktorJual(faktorJual);
            result.setUphetPersentase(uphetPersentase);
            result.setUphetValue(uphetValue);
            result.setUphetPersentaseTot(uphetPersentaseTot);
            result.setUphetValueTotal(uphetValueTotal);
            result.setOngkos(ongkos);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
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
    @Transient
    @JsonIgnore
    private Long insertId;

    

    public static PosMaterialDetail  fromRow(Row row) {
        var result = new PosMaterialDetail ();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setMaterialId(ManipulateUtil.parseRow(row, MATERIAL_ID_COL, Long.class));
        result.setQty(ManipulateUtil.parseRow(row, QTY_COL, Double.class));
        result.setBerat(ManipulateUtil.parseRow(row, BERAT_COL, Double.class));
        result.setHargaBeli(ManipulateUtil.parseRow(row, HARGA_BELI_COL, Double.class));
        result.setHargaJual(ManipulateUtil.parseRow(row, HARGA_JUAL_COL, Double.class));
        result.setRate(ManipulateUtil.parseRow(row, RATE_COL, Double.class));
        result.setFaktorJual(ManipulateUtil.parseRow(row, FAKTOR_JUAL_COL, Double.class));
        result.setUphetPersentase(ManipulateUtil.parseRow(row, UPHET_PERSENTASE_COL, Double.class));
        result.setUphetValue(ManipulateUtil.parseRow(row, UPHET_VALUE_COL, Double.class));
        result.setUphetPersentaseTot(ManipulateUtil.parseRow(row, UPHET_PERSENTASE_TOT_COL, Double.class));
        result.setUphetValueTotal(ManipulateUtil.parseRow(row, UPHET_VALUE_TOTAL_COL, Double.class));
        result.setOngkos(ManipulateUtil.parseRow(row, ONGKOS_COL, Double.class));

        
        
        return result;
    }

    @Override
    public boolean isNew() {
        if (id == null && insertId == null) {
            id = new GenerateUtil().generateOID();
            return true;
        } else if (id == null) {
            id = insertId;
            return true;
        }
        return false;
    }

    @Override
    public PosMaterialDetail update(PosMaterialDetail  newData) {
        return Builder.updateBuilder(this, newData).build();
    }
    
}
