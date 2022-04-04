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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


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
public class PosPriceTypeMapping implements UpdateAvailable <PosPriceTypeMapping>, Persistable <Long>{
    public static final String TABLE_NAME = "pos_price_type_mapping ";
    public static final String ID_COL = "PRICE_TYPE_ID";
    public static final String MATERIAL_ID_COL = "MATERIAL_ID";
    public static final String STANDART_RATE_ID_COL = "STANDART_RATE_ID";
    public static final String PRICE_COL ="PRICE";
    public static final String UPDATE_DATE_COL = "UPDATE_DATE";
    
    

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long  id;
        private Long materialId;
        private Long standartRateId;
        private Double price;
        private LocalDateTime updateDate;



        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord(Double price) {
            return new Builder().newRecord(true)
                .price(Objects.requireNonNull(price, "price diperlukan"));
                
                
        }

        public static Builder updateBuilder(PosPriceTypeMapping oldRecord, PosPriceTypeMapping newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .materialId(changeItOrNot(newRecord.getMaterialId(), oldRecord.getMaterialId()))
                .standartRateId(changeItOrNot(newRecord.getStandartRateId(), oldRecord.getStandartRateId()))
                .price(changeItOrNot(newRecord.getPrice(), oldRecord.getPrice()))
                .updateDate(changeItOrNot(newRecord.getUpdateDate(), oldRecord.getUpdateDate()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public PosPriceTypeMapping  build() {
            PosPriceTypeMapping  result = new PosPriceTypeMapping ();
            result.setId(id);
            result.setMaterialId(materialId);
            result.setStandartRateId(standartRateId);
            result.setPrice(price);
            result.setUpdateDate(updateDate);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long  id;
    private Long materialId;
    private Long standartRateId;
    private Double price;
    @JsonDeserialize(converter = TimeSerialize.class)
    private LocalDateTime updateDate;
    @Transient
    @JsonIgnore
    private Long insertId;

    

    public static PosPriceTypeMapping  fromRow(Row row) {
        var result = new PosPriceTypeMapping ();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setStandartRateId(ManipulateUtil.parseRow(row, STANDART_RATE_ID_COL, Long.class));
        result.setPrice(ManipulateUtil.parseRow(row, PRICE_COL, Double.class));
        result.setUpdateDate(ManipulateUtil.parseRow(row, UPDATE_DATE_COL, LocalDateTime.class));
        result.setMaterialId(ManipulateUtil.parseRow(row, MATERIAL_ID_COL, Long.class));
        
        
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
    public PosPriceTypeMapping update(PosPriceTypeMapping  newData) {
        return Builder.updateBuilder(this, newData).build();
    }
}
