package com.dimata.demo.app.prochain_app.forms.relation;

import com.dimata.demo.app.prochain_app.models.table.Location;
import com.dimata.demo.app.prochain_app.models.table.PosMaterial;
import com.dimata.demo.app.prochain_app.models.table.PosMaterialStock;
import com.dimata.demo.app.prochain_app.models.table.PosMaterialStockCode;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosMaterialAndStockRelation {
     private PosMaterial material;
     private PosMaterialStock stock;
     private PosMaterialStockCode relation;
}
