package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;


import java.util.Objects;

import com.dimata.demo.app.prochain_app.core.api.UpdateAvailable;
import com.dimata.demo.app.prochain_app.core.util.GenerateUtil;
import com.dimata.demo.app.prochain_app.core.util.ManipulateUtil;
import com.dimata.demo.app.prochain_app.enums.CatagoryEnum;
import com.dimata.demo.app.prochain_app.enums.StatusPosCatagory;
import com.dimata.demo.app.prochain_app.enums.TypeCatagoryEnum;
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

public class PosCategory implements UpdateAvailable<PosCategory>, Persistable <Long>{
    public static final String TABLE_NAME = "pos_category ";
    public static final String ID_COL = "CATEGORY_ID";
    public static final String NAME_COL = "NAME";
    public static final String CODE_COL = "CODE";
    public static final String POINT_PRICE_COL ="POINT_PRICE";
    public static final String CATEGORY_COL = "CATEGORY";
    public static final String TYPE_CATEGORY_COL = "TYPE_CATEGORY";
    public static final String DESCRIPTION_COL = "DESCRIPTION";
    public static final String LOCATION_ID_COL = "LOCATION_ID";
    public static final String CAT_PARENT_ID_COL = "CAT_PARENT_ID";
    public static final String STAUS_COL = "STATUS";
    

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long  id;
        private String name;
        private String code;
        private double pointPrice;
        private CatagoryEnum category; 
        private TypeCatagoryEnum typeCategory;
        private String description;

        private Long locationId;
        private Long catParentId;
        private StatusPosCatagory status;


        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord(String name, CatagoryEnum category) {
            return new Builder().newRecord(true)
                .name(Objects.requireNonNull(name, "nama diperlukan"))
                .category(Objects.requireNonNull(category, "category diperlukan"));
                
                
                
        }

        public static Builder updateBuilder(PosCategory oldRecord, PosCategory  newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .name(changeItOrNot(newRecord.getName(), oldRecord.getName()))
                .code(changeItOrNot(newRecord.getCode(), oldRecord.getCode()))
                .pointPrice(changeItOrNot(newRecord.getPointPrice(), oldRecord.getPointPrice()))
                .category(changeItOrNot(newRecord.getCatagory(), oldRecord.getCatagory()))
                .typeCategory(changeItOrNot(newRecord.getType(), oldRecord.getType()))
                .status(changeItOrNot(oldRecord.getStatus(), oldRecord.getStatus()))
                .description(changeItOrNot(newRecord.getDescription(), oldRecord.getDescription()))
                .locationId(changeItOrNot(newRecord.getLocationId(), oldRecord.getLocationId()))
                .catParentId(changeItOrNot(newRecord.getCatParentId(), oldRecord.getCatParentId()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public PosCategory  build() {
            PosCategory  result = new PosCategory ();
            result.setId(id);
            result.setName(name);
            result.setCode(code);
            result.setPointPrice(pointPrice);
            result.setCatagory(category);
            result.setType(typeCategory);
            result.setDescription(description);
            result.setLocationId(locationId);
            result.setCatParentId(catParentId);
            result.setStatus(status);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long  id;
    private String name;
    private String code;
    private double pointPrice;
    private Integer category; 
    private Integer typeCategory;
    private String description;
    private Long locationId;
    private Long catParentId;
    private Integer status;
    @Transient
    @JsonIgnore
    private Long insertId;
//status
public void setStatus(StatusPosCatagory status) {
    if (status != null) {
        this.status = status.getCode();
    }
}
public StatusPosCatagory getStatus() {
    if (status != null) {
        return StatusPosCatagory.getStatus(this.status);
    }
    return null;
}


//typeCategory

public void setType(TypeCatagoryEnum typeCategory) {
    if (typeCategory != null) {
        this.typeCategory = typeCategory.getCode();
    }
}
public TypeCatagoryEnum getType() {
    if (typeCategory != null) {
        return TypeCatagoryEnum.getType(this.typeCategory);
    }
    return null;
}
//catagory
public void setCatagory(CatagoryEnum category) {
    if(category != null){
        this.category = category.getCode();
    }
}
public CatagoryEnum getCatagory() {
    if(category != null){
        return CatagoryEnum.getCatagory(this.category);
    }
    return null;
}

    public static PosCategory  fromRow(Row row) {
        var result = new PosCategory ();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setName(ManipulateUtil.parseRow(row, NAME_COL, String.class));
        result.setCode(ManipulateUtil.parseRow(row, CODE_COL, String.class));
        result.setPointPrice(ManipulateUtil.parseRow(row, POINT_PRICE_COL, Double.class));
        result.setCatagory(CatagoryEnum.getCatagory(ManipulateUtil.parseRow(row, CATEGORY_COL, Integer.class)));
        result.setType(TypeCatagoryEnum.getType(ManipulateUtil.parseRow(row, TYPE_CATEGORY_COL,Integer.class)));
        result.setDescription(ManipulateUtil.parseRow(row, DESCRIPTION_COL, String.class));
        result.setLocationId(ManipulateUtil.parseRow(row, LOCATION_ID_COL, Long.class));
        result.setCatParentId(ManipulateUtil.parseRow(row, CAT_PARENT_ID_COL, Long.class));
        result.setStatus(StatusPosCatagory.getStatus(ManipulateUtil.parseRow(row, STAUS_COL, Integer.class)));
        
        
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
    public PosCategory  update(PosCategory  newData) {
        return Builder.updateBuilder(this, newData).build();
    }


}
