package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.models.table.PosUnit;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosUnitForm implements RecordAdapter<PosUnit>{
    private Long  id;
        private String code;
        private String name;
        private Long baseUnitId;
        private double qtyPerBaseUnit; 

        @Override
        public PosUnit convertNewRecord() {
            return PosUnit.Builder.createNewRecord()
                .name(name)
                .code(code)
                .baseUnitId(baseUnitId)
                .qtyPerBaseUnit(qtyPerBaseUnit)
                .id(id)
                .build();
        }
        @Override
        public PosUnit convertToRecord() {
            return PosUnit.Builder.emptyBuilder()
                .name(name)
                .code(code)
                .baseUnitId(baseUnitId)
                .qtyPerBaseUnit(qtyPerBaseUnit)
                .id(id)
                .build();
        }
}
