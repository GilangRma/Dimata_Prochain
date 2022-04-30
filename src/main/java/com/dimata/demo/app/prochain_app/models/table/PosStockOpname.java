package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;

import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.api.UpdateAvailable;
import com.dimata.demo.app.prochain_app.core.util.GenerateUtil;
import com.dimata.demo.app.prochain_app.core.util.ManipulateUtil;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeSerialize;
import com.dimata.demo.app.prochain_app.enums.OpnameItemTypeEnum;
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
public class PosStockOpname implements UpdateAvailable<PosStockOpname>, Persistable <Long>{ 

    public static final String TABLE_NAME = "pos_stock_opname";
    public static final String ID_COL = "STOCK_OPNAME_ID";
    public static final String STOCK_OPNAME_DATE_COL = "STOCK_OPNAME_DATE";
    public static final String STOCK_OPNAME_TIME_COL = "STOCK_OPNAME_TIME";
    public static final String STOCK_OPNAME_NUMBER_COL ="STOCK_OPNAME_NUMBER";
    public static final String LOCATION_ID_COL = "LOCATION_ID";
    public static final String SUPPLIER_ID_COL = "SUPPLIER_ID";
    public static final String CATEGORY_ID_COL = "CATEGORY_ID";
    public static final String SUB_CATEGORY_ID_COL = "SUB_CATEGORY_ID";
    public static final String STOCK_OPNAME_STATUS_COL = "STOCK_OPNAME_STATUS";
    public static final String REMARK_COL = "REMARK";
    public static final String ETALASE_ID_COL = "ETALASE_ID";
    public static final String OPNAME_ITEM_TYPE_COL = "OPNAME_ITEM_TYPE";
    public static final String CODE_COUNTER_COL = "CODE_COUNTER";
    public static final String MATERIAL_TYPE_ID_COL = "MATERIAL_TYPE_ID";

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long id;
        private LocalDateTime stockOpnameDate;
        private String stockOpnameTime;
        private String stockOpnameNumber;
        private Long locationId;
        private Long supplierId;
        private Long categoryId;
        private Long subCategoryId;
        private int stockOpnameStatus;
        private String remark;
        private Long etalaseId;
        private OpnameItemTypeEnum opnameItemType;
        private int codeCounter;
        private Long materialTypeId;

        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord(){
            return new Builder().newRecord(true);
        }

        public static Builder updateBuilder(PosStockOpname oldRecord, PosStockOpname  newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .stockOpnameDate(changeItOrNot(newRecord.getStockOpnameDate(), oldRecord.getStockOpnameDate()))
                .stockOpnameTime(changeItOrNot(newRecord.getStockOpnameTime(), oldRecord.getStockOpnameTime()))
                .stockOpnameNumber(changeItOrNot(newRecord.getStockOpnameNumber(), oldRecord.getStockOpnameNumber()))
                .locationId(changeItOrNot(newRecord.getLocationId(), oldRecord.getLocationId()))
                .supplierId(changeItOrNot(newRecord.getSupplierId(), oldRecord.getSupplierId()))
                .categoryId(changeItOrNot(newRecord.getCategoryId(), oldRecord.getCategoryId()))
                .subCategoryId(changeItOrNot(newRecord.getSubCategoryId(), oldRecord.getSubCategoryId()))
                .stockOpnameStatus(changeItOrNot(newRecord.getStockOpnameStatus(), oldRecord.getStockOpnameStatus()))
                .remark(changeItOrNot(newRecord.getRemark(), oldRecord.getRemark()))
                .etalaseId(changeItOrNot(newRecord.getEtalaseId(), oldRecord.getEtalaseId()))
                .opnameItemType(changeItOrNot(newRecord.getOpnameType(), oldRecord.getOpnameType()))
                .codeCounter(changeItOrNot(newRecord.getCodeCounter(), oldRecord.getCodeCounter()))
                .materialTypeId(changeItOrNot(newRecord.getMaterialTypeId(), oldRecord.getMaterialTypeId()));
                
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public PosStockOpname  build() {
            PosStockOpname  result = new PosStockOpname();
            result.setId(id);
            result.setStockOpnameDate(stockOpnameDate);
            result.setStockOpnameNumber(stockOpnameNumber);
            result.setLocationId(locationId);
            result.setStockOpnameTime(stockOpnameTime);
            result.setSupplierId(supplierId);
            result.setCategoryId(categoryId);
            result.setSubCategoryId(subCategoryId);
            result.setStockOpnameStatus(stockOpnameStatus);
            result.setRemark(remark);
            result.setEtalaseId(etalaseId);
            result.setOpnameType(opnameItemType);
            result.setCodeCounter(codeCounter);
            result.setMaterialTypeId(materialTypeId);
            return result;
        }
    } 

    @Id
    @Column(ID_COL)
    private Long id;
    @JsonSerialize(converter = TimeSerialize.class)
    private LocalDateTime stockOpnameDate;
    private String stockOpnameTime;
    private String stockOpnameNumber;
    private Long locationId;
    private Long supplierId;
    private Long categoryId;
    private Long subCategoryId;
    private int stockOpnameStatus;
    private String remark;
    private Long etalaseId;
    private Integer opnameItemType;
    private int codeCounter;
    private Long materialTypeId;

    @Transient
    @JsonIgnore
    private Long insertId;

    public void setOpnameType(OpnameItemTypeEnum opnameType) {
        if (opnameType != null) {
            this.opnameItemType = opnameType.getCode();
        }
    }

    public OpnameItemTypeEnum getOpnameType() {
        if (opnameItemType != null) {
            return OpnameItemTypeEnum.getOpnameType(this.opnameItemType);
        }
        return null;
    }
    
    public static PosStockOpname  fromRow(Row row) {
        var result = new PosStockOpname();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setStockOpnameDate(ManipulateUtil.parseRow(row, STOCK_OPNAME_DATE_COL, LocalDateTime.class));
        result.setStockOpnameNumber(ManipulateUtil.parseRow(row, STOCK_OPNAME_NUMBER_COL, String.class));
        result.setLocationId(ManipulateUtil.parseRow(row, LOCATION_ID_COL, Long.class));
        result.setStockOpnameTime(ManipulateUtil.parseRow(row, STOCK_OPNAME_TIME_COL, String.class));
        result.setSupplierId(ManipulateUtil.parseRow(row, SUPPLIER_ID_COL, Long.class));
        result.setCategoryId(ManipulateUtil.parseRow(row, CATEGORY_ID_COL, Long.class));
        result.setSubCategoryId(ManipulateUtil.parseRow(row, SUB_CATEGORY_ID_COL, Long.class));
        result.setStockOpnameStatus(ManipulateUtil.parseRow(row, STOCK_OPNAME_STATUS_COL, Integer.class));
        result.setRemark(ManipulateUtil.parseRow(row, REMARK_COL, String.class));
        result.setEtalaseId(ManipulateUtil.parseRow(row, ETALASE_ID_COL, Long.class));

        result.setOpnameType(OpnameItemTypeEnum.getOpnameType(ManipulateUtil.parseRow(row, OPNAME_ITEM_TYPE_COL, Integer.class)));
        result.setCodeCounter(ManipulateUtil.parseRow(row, CODE_COUNTER_COL, Integer.class));
        result.setMaterialTypeId(ManipulateUtil.parseRow(row, MATERIAL_TYPE_ID_COL, Long.class));
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
    public PosStockOpname  update(PosStockOpname  newData) {
        return Builder.updateBuilder(this, newData).build();
    }
    

    
}
