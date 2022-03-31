package com.dimata.demo.app.prochain_app.forms;


import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeSerialize;
import com.dimata.demo.app.prochain_app.models.table.PosDiscountQtyMapping;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PosDiscountQtyMappingForm implements RecordAdapter<PosDiscountQtyMapping>{
        private Long  id;
        private Long currencyTypeId;
        private Long locationId;
        private Long materialId;
        private Double startQty;
        private Double toQty;
        private Double discountValue;
        private String discountType;
        @JsonDeserialize(converter = TimeSerialize.class)
        private LocalDateTime updateDate;

        @Override
        public PosDiscountQtyMapping convertNewRecord() {
            return PosDiscountQtyMapping.Builder.createNewRecord(discountValue, discountType)
                .currencyTypeId(currencyTypeId)
                .locationId(locationId)
                .materialId(materialId)
                .startQty(startQty)
                .toQty(toQty)
                .updateDate(updateDate)
                .id(id)
                .build();
        }
        @Override
        public PosDiscountQtyMapping convertToRecord() {
            return PosDiscountQtyMapping.Builder.emptyBuilder()
                .currencyTypeId(currencyTypeId)
                .locationId(locationId)
                .materialId(materialId)
                .startQty(startQty)
                .toQty(toQty)
                .discountValue(discountValue)
                .discountType(discountType)
                .updateDate(updateDate)
                .id(id)
                .build();
        }
}
