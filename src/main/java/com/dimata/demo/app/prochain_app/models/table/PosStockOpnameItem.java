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
public class PosStockOpnameItem implements UpdateAvailable<PosStockOpnameItem>, Persistable<Long>{
    public static final String TABLE_NAME = "pos_stock_opname_item ";
    public static final String ID_COL = "STOCK_OPNAME_ITEM_ID";
    public static final String STOC_OPNAME_ID_COL = "STOCK_OPNAME_ID";
    public static final String MATERIAL_ID_COL = "MATERIAL_ID";
    public static final String UNIT_ID_COL ="UNIT_ID";
    public static final String QTY_OPNAME_COL = "QTY_OPNAME";
    public static final String QTY_SOLD_COL = "QTY_SOLD";
    public static final String QTY_SYSTEM_COL = "QTY_SYSTEM";
    public static final String COST_COL = "COST";
    public static final String PRICE_COL ="PRICE";
    public static final String STOCK_OPNAME_COUNTER_COL = "STOCK_OPNAME_COUNTER";
    
    

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long  id;
        private Long stockOpnameId;
        private Long materialId;
        private Long unitId;
        private double qtyOpname;
        private double  qtySold;
        private double qtySystem;
        private double cost;
        private double price;
        private String stockOpnameCounter;



        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord() {
            return new Builder().newRecord(true);
                
                
                
        }

        public static Builder updateBuilder(PosStockOpnameItem oldRecord, PosStockOpnameItem newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .materialId(changeItOrNot(newRecord.getMaterialId(), oldRecord.getMaterialId()))
                .stockOpnameId(changeItOrNot(newRecord.getStockOpnameId(), oldRecord.getStockOpnameId()))
                .price(changeItOrNot(newRecord.getPrice(), oldRecord.getPrice()))
                .qtyOpname(changeItOrNot(newRecord.getQtyOpname(), oldRecord.getQtyOpname()))
                .qtySold(changeItOrNot(newRecord.getQtySold(), oldRecord.getQtySold()))
                .qtySystem(changeItOrNot(newRecord.getQtySystem(), oldRecord.getQtySystem()))
                .cost(changeItOrNot(newRecord.getCost(), oldRecord.getCost()))
                .stockOpnameCounter(changeItOrNot(newRecord.getStockOpnameCounter(), oldRecord.getStockOpnameCounter()))
                .unitId(changeItOrNot(newRecord.getUnitId(), oldRecord.getUnitId()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public PosStockOpnameItem  build() {
            PosStockOpnameItem  result = new PosStockOpnameItem ();
            result.setId(id);
            result.setStockOpnameId(stockOpnameId);
            result.setMaterialId(materialId);
            result.setUnitId(unitId);
            result.setQtyOpname(qtyOpname);
            result.setQtySold(qtySold);
            result.setQtySystem(qtySystem);
            result.setCost(cost);
            result.setPrice(price);
            result.setStockOpnameCounter(stockOpnameCounter);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long  id;
    private Long stockOpnameId;
    private Long materialId;
    private Long unitId;
    private double qtyOpname;
    private double  qtySold;
    private double qtySystem;
    private double cost;
    private double price;
    private String stockOpnameCounter;
    @Transient
    @JsonIgnore
    private Long insertId;

    

    public static PosStockOpnameItem  fromRow(Row row) {
        var result = new PosStockOpnameItem ();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setStockOpnameId(ManipulateUtil.parseRow(row, STOC_OPNAME_ID_COL, Long.class));
        result.setPrice(ManipulateUtil.parseRow(row, PRICE_COL, Double.class));
        result.setUnitId(ManipulateUtil.parseRow(row, UNIT_ID_COL, Long.class));
        result.setMaterialId(ManipulateUtil.parseRow(row, MATERIAL_ID_COL, Long.class));
        result.setQtyOpname(ManipulateUtil.parseRow(row,QTY_OPNAME_COL , Double.class));
        result.setPrice(ManipulateUtil.parseRow(row, PRICE_COL, Double.class));
        result.setQtySold(ManipulateUtil.parseRow(row, QTY_SOLD_COL, Double.class));
        result.setQtySystem(ManipulateUtil.parseRow(row, QTY_SYSTEM_COL, Double.class));
        result.setStockOpnameCounter(ManipulateUtil.parseRow(row, STOCK_OPNAME_COUNTER_COL, String.class));
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
    public PosStockOpnameItem update(PosStockOpnameItem  newData) {
        return Builder.updateBuilder(this, newData).build();
    }
}
