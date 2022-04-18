package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;

import java.util.Objects;

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
@Table(DiscountType.TABLE_NAME)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class DiscountType implements UpdateAvailable<DiscountType>, Persistable <Long>{

    public static final String TABLE_NAME = "discount_type";
    public static final String ID_COL = "DISCOUNT_TYPE_ID";
    public static final String NAME_COL = "NAME";
    public static final String DINDEX_COL = "DINDEX";
    public static final String CODE_COL ="CODE";

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

    private Long id;
    private String name;
    private int dindex;
    private String code;
    
    @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord(String code, String name, int dindex ) {
            return new Builder().newRecord(true)
            .name(Objects.requireNonNull(name, "name diperlukan"))
            .code(Objects.requireNonNull(code, "code diperlukan"))
            .dindex(Objects.requireNonNull(dindex, "dindex diperlukan")); 
        }

        public static Builder updateBuilder(DiscountType oldRecord, DiscountType  newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .name(changeItOrNot(newRecord.getName(), oldRecord.getName()))
                .code(changeItOrNot(newRecord.getCode(), oldRecord.getCode()))
                .dindex(changeItOrNot(newRecord.getDindex(), oldRecord.getDindex()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public DiscountType  build() {
            DiscountType  result = new DiscountType();
            result.setId(id);
            result.setName(name);
            result.setCode(code);
            result.setDindex(dindex);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long id;
    private String name;
    private Integer dindex;
    private String code;
    
    @Transient
    @JsonIgnore
    private Long insertId;

    public static DiscountType  fromRow(Row row) {
        var result = new DiscountType ();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setName(ManipulateUtil.parseRow(row, NAME_COL, String.class));
        result.setCode(ManipulateUtil.parseRow(row, CODE_COL, String.class));
        result.setDindex(ManipulateUtil.parseRow(row, DINDEX_COL, Integer.class));
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
    public DiscountType  update(DiscountType  newData) {
        return Builder.updateBuilder(this, newData).build();
    }
}