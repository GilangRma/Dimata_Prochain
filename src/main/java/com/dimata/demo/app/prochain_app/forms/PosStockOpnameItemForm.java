package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.PosStockOpnameItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosStockOpnameItemForm implements RecordAdapter<PosStockOpnameItem>{
        private Long  id;
        private Long stockOpnameId;
        private Long materialId;
        private Long unitId;
        private double qtyOpname;
        private double  qtySold;
        private double qtySystem;
        private double cost;
        private double price;
        private Integer stockOpnameCounter;

        @Override
    public PosStockOpnameItem convertNewRecord() {
        return PosStockOpnameItem.Builder.createNewRecord( stockOpnameId,  materialId,  unitId,  qtyOpname,  qtySold,  qtySystem,  cost ,  price,  stockOpnameCounter )
        .id(id)
        .build();

    }

    @Override
    public PosStockOpnameItem convertToRecord() {
        return PosStockOpnameItem.Builder.emptyBuilder()
        .stockOpnameId(stockOpnameId)
        .materialId(materialId)
        .unitId(unitId)
        .qtyOpname(qtyOpname)
        .qtySold(qtySold)
        .qtySystem(qtySystem)
        .cost(cost)
        .price(price)
        .stockOpnameCounter(stockOpnameCounter)
        .id(id)
        .build();
    }
}
