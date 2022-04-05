package com.dimata.demo.app.prochain_app.forms.relation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class CashMasterRelation {

    private Long id;
    private int cashierNumber;
    private Long locationId;
    
}
