package com.dimata.demo.app.prochain_app.forms.relation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  
public class CashCashierRelation {

    private Long  id;
    private Long cashMasterId;
    private Long shiftId; 
}
