package com.dimata.demo.app.prochain_app.models.table;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;
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
public class PosMaterialStockCode implements UpdateAvailable <PosMaterialStockCode>, Persistable<Long> {
    public static final String TABLE_NAME = "pos_material_stock_code";
    public static final String ID_COL = "MATERIAL_STOCK_CODE_ID";
    public static final String MATERIAL_ID = "MATERIAL_ID";
    public static final String LOCATION_ID = "LOCATION_ID";
    public static final String STOCK_CODE = "STOCK_CODE";
    public static final String STOCK_STATUS = "STOCK_STATUS";
    public static final String VALUE = "VALUE";
    public static final String STOCK_DATE = "STOCK_DATE";

    @Accessors(fluent = true)
    @Setter
    public static class Builder {
        private Long id;
        private Long materialId;
        private Long locationId;
        private Long stockCode;
        private Integer stockStatus;
        private Double value;
        private LocalDateTime stockDate;
        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord(Long materialId, Long locationId, Long stockCode, Integer stockStatus) {
            return new Builder().newRecord(true)
                .materialId(Objects.requireNonNull(materialId, "materialId diperlukan"))
                .locationId(Objects.requireNonNull(locationId, "materialId diperlukan"))
                .stockCode(Objects.requireNonNull(stockCode, "materialId diperlukan"))
                .stockStatus(Objects.requireNonNull(stockStatus, "materialId diperlukan"));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public static Builder updateBuilder(PosMaterialStockCode oldRecord, PosMaterialStockCode newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .materialId(changeItOrNot(newRecord.getMaterialId(), oldRecord.getMaterialId()))
                .locationId(changeItOrNot(newRecord.getLocationId(), oldRecord.getLocationId()))
                .stockCode(changeItOrNot(newRecord.getStockCode(), oldRecord.getStockCode()))
                .stockStatus(changeItOrNot(newRecord.getStockStatus(), oldRecord.getStockStatus()))
                .value(changeItOrNot(newRecord.getValue(), oldRecord.getValue()))
                .stockDate(changeItOrNot(newRecord.getStockDate(), oldRecord.getStockDate()));
        }

        public PosMaterialStockCode build() {
            PosMaterialStockCode result = new PosMaterialStockCode();
            result.setId(id);
            result.setMaterialId(materialId);
            result.setLocationId(locationId);
            result.setStockCode(stockCode);
            result.setStockStatus(stockStatus);
            result.setValue(value);
            result.setStockDate(stockDate);
            
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long id;
    private Long materialId;
    private Long locationId;
    private Long stockCode;
    private Integer stockStatus;
    private Double value;
    @JsonSerialize(converter = TimeSerialize.class)
    private LocalDateTime stockDate;
    @Transient
    @JsonIgnore
    private Long insertId;

    public static PosMaterialStockCode fromRow(Row row) {
        var result = new PosMaterialStockCode();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setMaterialId(ManipulateUtil.parseRow(row, MATERIAL_ID, Long.class));
        result.setLocationId(ManipulateUtil.parseRow(row, LOCATION_ID ,Long.class));
        result.setStockCode(ManipulateUtil.parseRow(row, STOCK_CODE, Long.class));
        result.setStockStatus(ManipulateUtil.parseRow(row, STOCK_STATUS, Integer.class));
        result.setValue(ManipulateUtil.parseRow(row, VALUE, Double.class));
        result.setStockDate(ManipulateUtil.parseRow(row, STOCK_DATE, LocalDateTime.class));
        
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
    public PosMaterialStockCode update(PosMaterialStockCode newData) {
        return Builder.updateBuilder(this, newData).build();
    }

}
