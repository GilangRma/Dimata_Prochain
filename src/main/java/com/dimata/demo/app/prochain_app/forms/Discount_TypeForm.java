package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.Discount_Type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Discount_TypeForm implements RecordAdapter<Discount_Type> {
    
    private long id;
    private String code;
    private String dindex;
    private String name;

    public Discount_Type convertToRecord() {
        return Discount_Type.Builder.emptyBuilder()
            .code(code)
            .dindex(dindex)
            .name(name)
            .build();
    }

    public Discount_Type convertNewRecord() {
        return Discount_Type.Builder.createNewRecord(name, code)
            .dindex(dindex)
            .id(id)
            .build();

    }

}
