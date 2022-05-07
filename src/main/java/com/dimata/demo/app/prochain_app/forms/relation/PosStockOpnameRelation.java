package com.dimata.demo.app.prochain_app.forms.relation;

import java.util.List;

import com.dimata.demo.app.prochain_app.models.table.PosStockOpname;
import com.dimata.demo.app.prochain_app.models.table.PosStockOpnameItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosStockOpnameRelation {
    private PosStockOpname opname;
    private List<PosStockOpnameItem> items;
}
