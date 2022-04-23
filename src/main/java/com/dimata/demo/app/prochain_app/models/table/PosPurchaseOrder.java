package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;

import java.time.LocalDate;

import com.dimata.demo.app.prochain_app.core.api.UpdateAvailable;
import com.dimata.demo.app.prochain_app.core.util.GenerateUtil;
import com.dimata.demo.app.prochain_app.core.util.ManipulateUtil;
import com.dimata.demo.app.prochain_app.core.util.jackson.DateSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import io.r2dbc.spi.Row;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Table(DiscountType.TABLE_NAME)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PosPurchaseOrder implements UpdateAvailable<PosPurchaseOrder>, Persistable<Long> {

    public static final String TABLE_NAME = "pos_purchase_order";
    public static final String ID_COL = "PURCHASE_ORDER_ID";
    public static final String SUPPLIER_ID_COL = "SUPPLIER_ID";
    public static final String PURCH_DATE_COL = "PURCH_DATE";
    public static final String REMARK_COL = "REMARK";
    public static final String PO_CODE_COUNTER_COL = "PO_CODE_COUNTER";
    public static final String LOCATION_ID_COL = "LOCATION_ID";
    public static final String PO_CODE_COL = "PO_CODE";
    public static final String PO_STATUS_COL = "PO_STATUS";
    public static final String LOCATION_TYPE_COL = "LOCATION_TYPE";
    public static final String PPN_COL = "PPN";
    public static final String CREDIT_TIME_COL = "CREDIT_TIME";
    public static final String CURRENCY_ID_COL = "CURRENCY_ID";
    public static final String TERMS_OF_PAYMENT_COL = "TERMS_OF_PAYMENT";
    public static final String REVISI_CODE_COL = "REVISI_CODE";
    public static final String INCLUDE_PPN_COL = "INCLUDE_PPN";
    public static final String EXCHANGE_RATE_COL = "EXCHANGE_RATE";
    public static final String CATEGORY_ID_COL = "CATEGORY_ID";

    @Accessors(fluent = true)
    @Setter
    public static class Builder {
        
        private Long id;
        private Long supplierId;
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
        private int includePpn;
        private double exchangeRate;
        private Long categoryId;

        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;
        
        public static Builder createNewRecord(){
            return new Builder().newRecord(true);
        }

        public static Builder updateBuilder(PosPurchaseOrder oldRecord, PosPurchaseOrder  newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .supplierId(changeItOrNot(newRecord.getSupplierId(), oldRecord.getSupplierId()))
                .purchDate(changeItOrNot(newRecord.getPurchDate(), oldRecord.getPurchDate()))
                .remark(changeItOrNot(newRecord.getRemark(), oldRecord.getRemark()))
                .poCodeCounter(changeItOrNot(newRecord.getPoCodeCounter(), oldRecord.getPoCodeCounter()))
                .locationId(changeItOrNot(newRecord.getLocationId(), oldRecord.getLocationId()))
                .poCode(changeItOrNot(newRecord.getPoCode(), oldRecord.getPoCode()))
                .poStatus(changeItOrNot(newRecord.getPoStatus(), oldRecord.getPoStatus()))
                .locationType(changeItOrNot(newRecord.getLocationType(), oldRecord.getLocationType()))
                .ppn(changeItOrNot(newRecord.getPpn(), oldRecord.getPpn()))
                .creditTime(changeItOrNot(newRecord.getCreditTime(), oldRecord.getCreditTime()))
                .currencyId(changeItOrNot(newRecord.getCurrencyId(), oldRecord.getCurrencyId()))
                .termsOfPayment(changeItOrNot(newRecord.getTermsOfPayment(), oldRecord.getTermsOfPayment()))
                .revisiCode(changeItOrNot(newRecord.getRevisiCode(), oldRecord.getRevisiCode()))
                .includePpn(changeItOrNot(newRecord.getIncludePpn(), oldRecord.getIncludePpn()))
                .exchangeRate(changeItOrNot(newRecord.getExchangeRate(), oldRecord.getExchangeRate()))
                .categoryId(changeItOrNot(newRecord.getCategoryId(), oldRecord.getCategoryId()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public PosPurchaseOrder  build() {
            PosPurchaseOrder  result = new PosPurchaseOrder();
            result.setId(id);
            result.setSupplierId(supplierId);
            result.setPurchDate(purchDate);
            result.setRemark(remark);
            result.setPoCodeCounter(poCodeCounter);
            result.setLocationId(locationId);
            result.setPoCode(poCode);
            result.setPoStatus(poStatus);
            result.setLocationType(locationType);
            result.setPpn(ppn);
            result.setCreditTime(creditTime);
            result.setCurrencyId(currencyId);
            result.setTermsOfPayment(termsOfPayment);
            result.setRevisiCode(revisiCode);
            result.setIncludePpn(includePpn);
            result.setExchangeRate(exchangeRate);
            result.setCategoryId(categoryId);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long id;
    private Long supplierId;
    @JsonSerialize(converter = DateSerialize.class)
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
    private int includePpn;
    private double exchangeRate;
    private Long categoryId;

    @Transient
    @JsonIgnore
    private Long insertId;

    public static PosPurchaseOrder  fromRow(Row row) {
        var result = new PosPurchaseOrder ();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setSupplierId(ManipulateUtil.parseRow(row, SUPPLIER_ID_COL, Long.class));
        result.setPurchDate(ManipulateUtil.parseRow(row, PURCH_DATE_COL, LocalDate.class));
        result.setRemark(ManipulateUtil.parseRow(row, REMARK_COL, String.class));
        result.setPoCodeCounter(ManipulateUtil.parseRow(row, PO_CODE_COUNTER_COL, Integer.class));
        result.setLocationId(ManipulateUtil.parseRow(row, LOCATION_ID_COL, Long.class));
        result.setPoCode(ManipulateUtil.parseRow(row, PO_CODE_COL, String.class));
        result.setPoStatus(ManipulateUtil.parseRow(row, PO_STATUS_COL, Integer.class));
        result.setLocationType(ManipulateUtil.parseRow(row, LOCATION_TYPE_COL, Integer.class));
        result.setPpn(ManipulateUtil.parseRow(row, PPN_COL, Double.class));
        result.setCreditTime(ManipulateUtil.parseRow(row, CREDIT_TIME_COL, Integer.class));
        result.setCurrencyId(ManipulateUtil.parseRow(row, CURRENCY_ID_COL, Long.class));
        result.setTermsOfPayment(ManipulateUtil.parseRow(row, TERMS_OF_PAYMENT_COL, Integer.class));
        result.setRevisiCode(ManipulateUtil.parseRow(row, REVISI_CODE_COL, String.class));
        result.setIncludePpn(ManipulateUtil.parseRow(row, INCLUDE_PPN_COL, Integer.class));
        result.setExchangeRate(ManipulateUtil.parseRow(row, EXCHANGE_RATE_COL, Double.class));
        result.setCategoryId(ManipulateUtil.parseRow(row, CATEGORY_ID_COL, Long.class));
        return result;
    }

    @Override
    public boolean isNew() {
        if (id == null && insertId == null) {
            id = new GenerateUtil().generateOID();
            return true;
        } else if (id == null) {
            id = insertId;
            return true;
        }
        return false;
    }

    @Override
    public PosPurchaseOrder  update(PosPurchaseOrder  newData) {
        return Builder.updateBuilder(this, newData).build();
    }
    

    
}
