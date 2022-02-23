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
public class PriceType implements UpdateAvailable<PriceType>, Persistable<Long> {
    public static final String TABLE_NAME = "price_type ";
    public static final String ID_COL = "PRICE_TYPE_ID";
    public static final String CODE_COL = "CODE";
    public static final String PINDEX_COL = "PINDEX";
    public static final String NAME_COL ="NAME";
    

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long  id;
        private String code;
        private String name;
        private String pindex;


        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord() {
            return new Builder().newRecord(true);
                
        }

        public static Builder updateBuilder(PriceType oldRecord, PriceType  newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .name(changeItOrNot(newRecord.getName(), oldRecord.getName()))
                .code(changeItOrNot(newRecord.getCode(), oldRecord.getCode()))
                .pindex(changeItOrNot(newRecord.getPindex(), oldRecord.getPindex()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public PriceType  build() {
            PriceType  result = new PriceType ();
            result.setId(id);
            result.setName(name);
            result.setCode(code);
            result.setPindex(pindex);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long  id;
    private String code;
    private String name;
    private String pindex;
    @Transient
    @JsonIgnore
    private Long insertId;

    

    public static PriceType  fromRow(Row row) {
        var result = new PriceType ();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setName(ManipulateUtil.parseRow(row, NAME_COL, String.class));
        result.setCode(ManipulateUtil.parseRow(row, CODE_COL, String.class));
        result.setPindex(ManipulateUtil.parseRow(row, PINDEX_COL, String.class));
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
    public PriceType  update(PriceType  newData) {
        return Builder.updateBuilder(this, newData).build();
    }
}
