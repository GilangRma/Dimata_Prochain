package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;

import java.time.LocalDateTime;
import java.util.Objects;

import com.dimata.demo.app.prochain_app.core.api.UpdateAvailable;
import com.dimata.demo.app.prochain_app.core.util.GenerateUtil;
import com.dimata.demo.app.prochain_app.core.util.ManipulateUtil;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
@JsonInclude(JsonInclude.Include.NON_NULL)

public class PosMaterialStock implements UpdateAvailable<PosMaterialStock>, Persistable <Long> {

    public static final String TABLE_NAME = "pos_material_stock";
    public static final String ID_COL = "MATERIAL_STOCK_ID";
    public static final String PERIODE_ID_COL = "PERIODE_ID";
    public static final String MATERIAL_UNIT_ID_COL = "MATERIAL_UNIT_ID";
    public static final String LOCATION_ID_COL = "LOCATION_ID";
    public static final String QTY_COL = "QTY";
    public static final String QTY_MIN_COL = "QTY_MIN";
    public static final String QTY_MAX_COL = "QTY_MAX";
    public static final String OPENING_QTY_COL = "OPENING_QTY";
    public static final String CLOSING_QTY_COL = "CLOSING_QTY";
    public static final String QTY_IN_COL = "QTY_IN";
    public static final String QTY_OUT_COL = "QTY_OUT";
    public static final String QTY_OPTIMUM_COL = "QTY_OPTIMUM";
    public static final String BERAT_COL = "BERAT";
    public static final String BERAT_IN_COL = "BERAT_IN";
    public static final String BERAT_OUT_COL = "BERAT_OUT";
    public static final String OPENING_BERAT_COL = "OPENING_BERAT";
    public static final String CLOSING_BERAT_COL = "CLOSING_BERAT";
    public static final String UPDATE_DATE_COL = "UPDATE_DATE";
    
    @Accessors(fluent = true)
    @Setter
    public static class Builder {
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
        private LocalDateTime updateDate;

        @Setter(AccessLevel.PRIVATE)
            private boolean newRecord = false;
             
            public static Builder createNewRecord(Long periodeId, Long materialUnitId,Long locationId,LocalDateTime updateDate){
                return new Builder().newRecord(true)
                .periodeId(Objects.requireNonNull(periodeId, "periodeId diperlukan"))
                .materialUnitId(Objects.requireNonNull(materialUnitId, "materialUnitId diperlukan"))
                .locationId(Objects.requireNonNull(locationId, "locationId diperlukan"))
                .updateDate(Objects.requireNonNull(updateDate, "updateDate diperlukan"));
            }

            public static Builder updateBuilder(PosMaterialStock oldRecord, PosMaterialStock newRecord) {
                return new Builder()
                    .id(oldRecord.getId())
                    .periodeId(changeItOrNot(newRecord.getPeriodeId(), oldRecord.getPeriodeId()))
                    .materialUnitId(changeItOrNot(newRecord.getMaterialUnitId(), oldRecord.getMaterialUnitId()))
                    .locationId(changeItOrNot(newRecord.getLocationId(), oldRecord.getLocationId()))
                    .qty(changeItOrNot(newRecord.getQty(), oldRecord.getQty()))
                    .qtyMin(changeItOrNot(newRecord.getQtyMin(), oldRecord.getQtyMin()))
                    .qtyMax(changeItOrNot(newRecord.getQtyMax(), oldRecord.getQtyMax()))
                    .openingQty(changeItOrNot(newRecord.getOpeningQty(), oldRecord.getOpeningQty()))
                    .closingQty(changeItOrNot(newRecord.getClosingQty(), oldRecord.getClosingQty()))
                    .qtyIn(changeItOrNot(newRecord.getQtyIn(), oldRecord.getQtyIn()))
                    .qtyOut(changeItOrNot(newRecord.getQtyOut(), oldRecord.getQtyOut()))
                    .qtyOptimum(changeItOrNot(newRecord.getQtyOptimum(), oldRecord.getQtyOptimum()))
                    .berat(changeItOrNot(newRecord.getBerat(), oldRecord.getBerat()))
                    .beratIn(changeItOrNot(newRecord.getBeratIn(), oldRecord.getBeratIn()))
                    .beratOut(changeItOrNot(newRecord.getBeratOut(), oldRecord.getBeratOut()))
                    .openingBerat(changeItOrNot(newRecord.getOpeningBerat(), oldRecord.getOpeningBerat()))
                    .closingBerat(changeItOrNot(newRecord.getClosingBerat(), oldRecord.getClosingBerat()))
                    .updateDate(changeItOrNot(newRecord.getUpdateDate(), oldRecord.getUpdateDate()));
            }

            public static Builder empetyBuilder() {
                return new Builder();
            }

            public PosMaterialStock  build() {
                PosMaterialStock  result = new PosMaterialStock();
                result.setId(id);
                result.setPeriodeId(periodeId);
                result.setMaterialUnitId(materialUnitId);
                result.setLocationId(locationId);
                result.setQty(qty);
                result.setQtyMin(qtyMin);
                result.setQtyMax(qtyMax);
                result.setOpeningQty(openingQty);
                result.setClosingQty(closingQty);
                result.setQtyIn(qtyIn);
                result.setQtyOut(qtyOut);
                result.setQtyOptimum(qtyOptimum);
                result.setBerat(berat);
                result.setBeratIn(beratIn);
                result.setBeratOut(beratOut);
                result.setOpeningBerat(openingBerat);
                result.setClosingBerat(closingBerat);
                result.setUpdateDate(updateDate);
                return result;
            } 
    }
    
    @Id
    @Column(ID_COL)
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
    @JsonSerialize(converter = TimeSerialize.class)
    private LocalDateTime updateDate;

    @Transient
    @JsonIgnore
    private Long insertId;

    public static PosMaterialStock  fromRow(Row row) {
        var result = new PosMaterialStock ();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setPeriodeId(ManipulateUtil.parseRow(row, PERIODE_ID_COL, Long.class));
        result.setMaterialUnitId(ManipulateUtil.parseRow(row, MATERIAL_UNIT_ID_COL, Long.class));
        result.setLocationId(ManipulateUtil.parseRow(row, LOCATION_ID_COL, Long.class));
        result.setQty(ManipulateUtil.parseRow(row, QTY_COL, Double.class));
        result.setQtyMin(ManipulateUtil.parseRow(row, QTY_MIN_COL, Double.class));
        result.setQtyMax(ManipulateUtil.parseRow(row, QTY_MAX_COL, Double.class));
        result.setOpeningQty(ManipulateUtil.parseRow(row, OPENING_QTY_COL, Double.class));
        result.setClosingQty(ManipulateUtil.parseRow(row, CLOSING_QTY_COL, Double.class));
        result.setQtyIn(ManipulateUtil.parseRow(row, QTY_IN_COL, Double.class));
        result.setQtyOut(ManipulateUtil.parseRow(row, QTY_OUT_COL, Double.class));
        result.setQtyOptimum(ManipulateUtil.parseRow(row, QTY_OPTIMUM_COL, Double.class));
        result.setBerat(ManipulateUtil.parseRow(row, BERAT_COL, Double.class));
        result.setBeratIn(ManipulateUtil.parseRow(row, BERAT_IN_COL, Double.class));
        result.setBeratOut(ManipulateUtil.parseRow(row, BERAT_OUT_COL, Double.class));
        result.setOpeningBerat(ManipulateUtil.parseRow(row, OPENING_BERAT_COL, Double.class));
        result.setClosingBerat(ManipulateUtil.parseRow(row, CLOSING_BERAT_COL, Double.class));
        result.setUpdateDate(ManipulateUtil.parseRow(row, UPDATE_DATE_COL, LocalDateTime.class));
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
    public PosMaterialStock  update(PosMaterialStock  newData) {
        return Builder.updateBuilder(this, newData).build();
    }
}
