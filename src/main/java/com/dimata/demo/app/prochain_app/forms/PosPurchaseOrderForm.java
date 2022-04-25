package com.dimata.demo.app.prochain_app.forms;

import java.time.LocalDate;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.core.util.jackson.DateDeserialize;
import com.dimata.demo.app.prochain_app.enums.IncludePpnEnum;
import com.dimata.demo.app.prochain_app.models.table.PosPurchaseOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosPurchaseOrderForm implements RecordAdapter <PosPurchaseOrder>{

        private Long id;
        private Long supplierId;
        @JsonDeserialize(converter = DateDeserialize.class)
        private LocalDate purchDate;
        private String remark;
        private int poCodeCounter;
        private Long locationId;
        private String poCode;
        private int poStatus;
        private int locationType;
        private double ppn;
        private int creditTime;
        private Long currencyId;
        private int termsOfPayment;
        private String revisiCode;
        private IncludePpnEnum includePpn;
        private double exchangeRate;
        private Long categoryId;
        @Override
        public PosPurchaseOrder convertNewRecord() {
            return PosPurchaseOrder.Builder.createNewRecord(purchDate, remark, locationId, revisiCode, categoryId)
                    .supplierId(supplierId)
                    .poCodeCounter(poCodeCounter)
                    .poCode(poCode)
                    .poStatus(poStatus)
                    .locationType(locationType)
                    .ppn(ppn)
                    .creditTime(creditTime)
                    .currencyId(currencyId)
                    .termsOfPayment(termsOfPayment)
                    .includePpn(includePpn)
                    .exchangeRate(exchangeRate)
                    .id(id)
                    .build();
        }
        @Override
        public PosPurchaseOrder convertToRecord() {
            return PosPurchaseOrder.Builder.emptyBuilder()
                    .supplierId(supplierId)
                    .purchDate(purchDate)
                    .remark(remark)
                    .poCodeCounter(poCodeCounter)
                    .locationId(locationId)
                    .poCode(poCode)
                    .poStatus(poStatus)
                    .locationType(locationType)
                    .ppn(ppn)
                    .creditTime(creditTime)
                    .currencyId(currencyId)
                    .termsOfPayment(termsOfPayment)
                    .revisiCode(revisiCode)
                    .includePpn(includePpn)
                    .exchangeRate(exchangeRate)
                    .categoryId(categoryId)
                    .id(id)
                    .build();
        }
    
}
