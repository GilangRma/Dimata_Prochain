package com.dimata.demo.app.prochain_app.forms;
import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.PriceType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PriceTypeForm implements RecordAdapter<PriceType>{
    private Long  id;
    private String code;
    private String name;
    private String pindex;

    @Override
        public PriceType convertNewRecord() {
            return PriceType.Builder.createNewRecord()
                .name(name)
                .code(code)
                .pindex(pindex)
                .id(id)
                .build();
        }
        @Override
        public PriceType convertToRecord() {
            return PriceType.Builder.emptyBuilder()
                .name(name)
                .code(code)
                .pindex(pindex)
                .id(id)
                .build();
        }
}
