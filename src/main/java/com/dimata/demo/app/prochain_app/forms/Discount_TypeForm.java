package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.Discount_Type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Discount_TypeForm implements RecordAdapter<Discount_TypeForm> {
    
    private long id;
    private String CODE;
    private String DINDEX;
    private String NAME;

    @Override
    public Discount_Type convertToRecord() {
        return Discount_Type.Builder.emptyBuilder()
            .CODE(CODE)
            .DINDEX(DINDEX)
            .NAME(NAME)
            .build();
    }

    @Override
    public Discount_Type convertNewRecord() {
        return Discount_Type.Builder.createNewRecord()
            .id(id)
            .build();

    }

}
