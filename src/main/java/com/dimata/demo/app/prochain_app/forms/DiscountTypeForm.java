package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.DiscountType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiscountTypeForm implements RecordAdapter<DiscountType> {
    
    private Long id;
    private String code;
    private Integer dindex;
    private String name;

    @Override
    public DiscountType convertNewRecord() {
        return DiscountType.Builder.createNewRecord(name, code, dindex)
                .id(id)
                .build();
    }
    @Override
    public DiscountType convertToRecord() {
        return DiscountType.Builder.emptyBuilder()
        .code(code)
        .dindex(dindex)
        .name(name)
        .id(id)
        .build();
    }

}
