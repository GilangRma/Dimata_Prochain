package com.dimata.demo.app.prochain_app.forms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialAndstockForm {
    private PosMaterialForm material;
    private PosStockOpnameForm opname;
}
