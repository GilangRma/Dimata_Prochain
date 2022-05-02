package com.dimata.demo.app.prochain_app.forms;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosMaterialAndStockForm {
    private PosMaterialForm material;
    private PosMaterialStockForm stock;
    private Long locationId;
    private Integer stockStatus;
    private Double value;
}
