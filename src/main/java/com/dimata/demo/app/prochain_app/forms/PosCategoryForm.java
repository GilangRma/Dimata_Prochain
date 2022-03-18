package com.dimata.demo.app.prochain_app.forms;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.enums.CatagoryEnum;
import com.dimata.demo.app.prochain_app.enums.StatusPosCatagory;
import com.dimata.demo.app.prochain_app.enums.TypeCatagoryEnum;
import com.dimata.demo.app.prochain_app.models.table.PosCategory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosCategoryForm implements RecordAdapter <PosCategory>{
        private Long  id;
        private String name;
        private String code;
        private double pointPrice;
        private CatagoryEnum category; 
        private TypeCatagoryEnum typeCategory;
        private String description;
        private Long locationId;
        private Long catParentId;
        private StatusPosCatagory status;

        @Override
        public PosCategory convertNewRecord() {
            return PosCategory.Builder.createNewRecord(name,  category)
                .pointPrice(pointPrice)
                .code(code)
                .category(category)
                .typeCategory(typeCategory)
                .description(description)
                .locationId(locationId)
                .catParentId(catParentId)
                .status(status)
                .id(id)
                .build();
        }
        @Override
        public PosCategory convertToRecord() {
            return PosCategory.Builder.emptyBuilder()
                .name(name)
                .code(code)
                .pointPrice(pointPrice)
                .category(category)
                .typeCategory(typeCategory)
                .description(description)
                .locationId(locationId)
                .catParentId(catParentId)
                .status(status)
                .id(id)
                .build();
        }
}
