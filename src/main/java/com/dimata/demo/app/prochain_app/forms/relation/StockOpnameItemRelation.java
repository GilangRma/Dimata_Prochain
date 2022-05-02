package com.dimata.demo.app.prochain_app.forms.relation;

import com.dimata.demo.app.prochain_app.models.table.PosMaterial;
import com.dimata.demo.app.prochain_app.models.table.PosStockOpname;
import com.dimata.demo.app.prochain_app.models.table.PosStockOpnameItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockOpnameItemRelation {
    private PosStockOpname stockOpname;
    private PosMaterial material;
    private PosStockOpnameItem relation;
}
