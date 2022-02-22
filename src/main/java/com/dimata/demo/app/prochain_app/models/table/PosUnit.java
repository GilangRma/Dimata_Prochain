package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;


import com.dimata.demo.app.prochain_app.core.api.UpdateAvailable;
import com.dimata.demo.app.prochain_app.core.util.GenerateUtil;
import com.dimata.demo.app.prochain_app.core.util.ManipulateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class PosUnit implements UpdateAvailable<PosUnit>, Persistable<Long>{
    public static final String TABLE_NAME = "pos_unit ";
    public static final String ID_COL = "UNIT_ID";
    public static final String CODE_COL = "CODE";
    public static final String NAME_COL = "NAME";
    public static final String BASE_UNIT_ID_COL ="BASE_UNIT_ID";
    public static final String QTY_PER_BASE_UNIT_COL = "QTY_PER_BASE_UNIT";
    

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long  id;
        private String code;
        private String name;
        private Long baseUnitId;
        private double qtyPerBaseUnit; 


        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord() {
            return new Builder().newRecord(true);
                
        }

        public static Builder updateBuilder(PosUnit oldRecord, PosUnit  newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .name(changeItOrNot(newRecord.getName(), oldRecord.getName()))
                .code(changeItOrNot(newRecord.getCode(), oldRecord.getCode()))
                .baseUnitId(changeItOrNot(newRecord.getBaseUnitId(), oldRecord.getBaseUnitId()))
                .qtyPerBaseUnit(changeItOrNot(newRecord.getQtyPerBaseUnit(), oldRecord.getQtyPerBaseUnit()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public PosUnit  build() {
            PosUnit  result = new PosUnit ();
            result.setId(id);
            result.setName(name);
            result.setCode(code);
            result.setBaseUnitId(baseUnitId);
            result.setQtyPerBaseUnit(qtyPerBaseUnit);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long  id;
        private String code;
        private String name;
        private Long baseUnitId;
        private double qtyPerBaseUnit;
    @Transient
    @JsonIgnore
    private Long insertId;

    

    public static PosUnit  fromRow(Row row) {
        var result = new PosUnit ();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setName(ManipulateUtil.parseRow(row, NAME_COL, String.class));
        result.setCode(ManipulateUtil.parseRow(row, CODE_COL, String.class));
        result.setQtyPerBaseUnit(ManipulateUtil.parseRow(row, QTY_PER_BASE_UNIT_COL, Double.class));
        result.setBaseUnitId(ManipulateUtil.parseRow(row, BASE_UNIT_ID_COL, Long.class));
        
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
    public PosUnit  update(PosUnit  newData) {
        return Builder.updateBuilder(this, newData).build();
    }
}
