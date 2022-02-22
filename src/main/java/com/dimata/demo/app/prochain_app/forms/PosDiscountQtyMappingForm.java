package com.dimata.demo.app.prochain_app.forms;

import java.sql.Timestamp;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeSerialize;
import com.dimata.demo.app.prochain_app.models.table.PosDiscountQtyMapping;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PosDiscountQtyMappingForm implements RecordAdapter<PosDiscountQtyMapping>{
        private Long  id;
        private Long currencyTypeId;
        private Long locationId;
        private Long materialId;
        private String startQty;
        private String toQty;
        private String discountValue;
        private String discountType;
        @JsonSerialize(converter = TimeSerialize.class)
        private Timestamp updateDate;

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
