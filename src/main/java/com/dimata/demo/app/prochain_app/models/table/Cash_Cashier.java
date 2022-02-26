package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;
import java.util.Objects;

import com.dimata.demo.app.prochain_app.core.api.UpdateAvailable;
import com.dimata.demo.app.prochain_app.core.util.GenerateUtil;
import com.dimata.demo.app.prochain_app.core.util.ManipulateUtil;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeDeserialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

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
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cash_Cashier implements UpdateAvailable<Cash_Cashier>, Persistable <Long>{

    public static final String TABLE_NAME = "cash_cashier ";
    public static final String ID_COL = "CASH_CASHIER_ID";
    public static final String CASH_MASTER_ID_COL = "CASH_MASTER_ID";
    public static final String APP_USER_ID_COL = "APP_USER_ID";
    public static final String OPEN_DATE_COL ="OPEN_DATE";
    public static final String SPV_OPEN_ID_COL = "SPV_OPEN_ID";
    public static final String SPV_OPEN_NAME_COL = "SPV_OPEN_NAME";
    public static final String SPV_CLOSE_ID_COL = "SPV_CLOSE_ID";
    public static final String SPV_CLOSE_NAME_COL = "SPV_CLOSE_NAME";
    public static final String SHIFT_ID_COL = "SHIFT_ID";
    public static final String CLOSE_DATE_COL = "CLOSE_DATE";

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long  id;
        private Long cashMasterId;
        private Long appUserId;
        private TimeDeserialize openDate;
        private Long spvOpenId; 
        private String spvOpenName;
        private Long spvCloseId;
        private String spvCloseName;
        private Long shiftId;
        private TimeDeserialize closeDate;

        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord(TimeDeserialize openDate, Long spvOpenId, String spvOpenName, Long spvCloseId, String spvCloseName, TimeDeserialize closeDate) {
            return new Builder().newRecord(true)
                .openDate(Objects.requireNonNull(openDate, "openDate diperlukan"))
                .spvOpenId(Objects.requireNonNull(spvOpenId, "spvOpenId diperlukan"))
                .spvOpenName(Objects.requireNonNull(spvOpenName, "spvOpenName diperlukan"))
                .spvCloseId(Objects.requireNonNull(spvCloseId, "spvCloseId diperlukan"))
                .spvCloseName(Objects.requireNonNull(spvCloseName, "spvCloseName diperlukan"))
                .closeDate(Objects.requireNonNull(closeDate, "closeDate diperlukan"));      
        }

        public static Builder updateBuilder(Cash_Cashier oldRecord, Cash_Cashier  newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .cashMasterId(changeItOrNot(newRecord.getCashMasterId(), oldRecord.getCashMasterId()))
                .appUserId(changeItOrNot(newRecord.getAppUserId(), oldRecord.getAppUserId()))
                .openDate(changeItOrNot(newRecord.getOpenDate(), oldRecord.getOpenDate()))
                .spvOpenId(changeItOrNot(newRecord.getSpvOpenId(), oldRecord.getSpvOpenId()))
                .spvOpenName(changeItOrNot(newRecord.getSpvOpenName(), oldRecord.getSpvOpenName()))
                .spvCloseId(changeItOrNot(newRecord.getSpvCloseId(), oldRecord.getSpvCloseId()))
                .spvCloseName(changeItOrNot(newRecord.getSpvCloseName(), oldRecord.getSpvCloseName()))
                .shiftId(changeItOrNot(newRecord.getShiftId(), oldRecord.getShiftId()))
                .closeDate(changeItOrNot(newRecord.getCloseDate(), oldRecord.getCloseDate()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public Cash_Cashier  build() {
            Cash_Cashier  result = new Cash_Cashier ();
            result.setId(id);
            result.setCashMasterId(cashMasterId);
            result.setAppUserId(appUserId);
            result.setOpenDate(openDate);
            result.setSpvOpenId(spvOpenId);
            result.setSpvOpenName(spvOpenName);
            result.setSpvCloseId(spvCloseId);
            result.setSpvCloseName(spvCloseName);
            result.setShiftId(shiftId);
            result.setCloseDate(closeDate);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long  id;
    private Long cashMasterId;
    private Long appUserId;
    private TimeDeserialize openDate;
    private Long spvOpenId; 
    private String spvOpenName;
    private Long spvCloseId;
    private String spvCloseName;
    private Long shiftId;
    private TimeDeserialize closeDate;
    @Transient
    @JsonIgnore
    private Long insertId;

    public static Cash_Cashier  fromRow(Row row) {
        var result = new Cash_Cashier ();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setCashMasterId(ManipulateUtil.parseRow(row, CASH_MASTER_ID_COL, Long.class));
        result.setAppUserId(ManipulateUtil.parseRow(row, APP_USER_ID_COL, Long.class));
        result.setOpenDate(ManipulateUtil.parseRow(row, OPEN_DATE_COL, TimeDeserialize.class));
        result.setSpvOpenId(ManipulateUtil.parseRow(row, SPV_OPEN_ID_COL, Long.class));
        result.setSpvOpenName(ManipulateUtil.parseRow(row, SPV_OPEN_NAME_COL, String.class));
        result.setSpvCloseId(ManipulateUtil.parseRow(row, SPV_CLOSE_ID_COL, Long.class));
        result.setSpvCloseName(ManipulateUtil.parseRow(row, SPV_CLOSE_NAME_COL, String.class));
        result.setShiftId(ManipulateUtil.parseRow(row, SHIFT_ID_COL, Long.class));
        result.setCloseDate(ManipulateUtil.parseRow(row, CLOSE_DATE_COL, TimeDeserialize.class));
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
    public Cash_Cashier  update(Cash_Cashier  newData) {
        return Builder.updateBuilder(this, newData).build();
    }

}