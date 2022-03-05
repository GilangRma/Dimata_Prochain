package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;
import java.time.LocalDateTime;
import java.util.Objects;

import com.dimata.demo.app.prochain_app.core.api.UpdateAvailable;
import com.dimata.demo.app.prochain_app.core.util.GenerateUtil;
import com.dimata.demo.app.prochain_app.core.util.ManipulateUtil;
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
public class Location implements UpdateAvailable<Location>, Persistable <Long>{
    
    public static final String TABLE_NAME = "location";
    public static final String ID_COL = "LOCATION_ID";
    public static final String NAME_COL = "NAME";
    public static final String DESCRIPTION_COL = "DESCRIPTION";
    public static final String PARENT_ID_COL ="PARENT_ID";
    public static final String CODE_COL = "CODE";
    public static final String ADDRESS_COL = "ADDRESS";
    public static final String TELEPHONE_COL = "TELEPHONE";
    public static final String FAX_COL = "FAX";
    public static final String PERSON_COL = "PERSON";
    public static final String CONTACT_ID_COL = "CONTACT_ID";
    public static final String EMAIL_COL = "EMAIL";
    public static final String WEBSITE_COL = "WEBSITE";
    public static final String VENDOR_ID_COL = "VENDOR_ID";
    public static final String TYPE_COL = "TYPE";
    public static final String SERVICE_PERCENTAGE_COL = "SERVICE_PERCENTAGE";
    public static final String TAX_PERCENTAGE_COL = "TAX_PERCENTAGE";
    public static final String DEPARTMENT_ID_COL = "DEPARTMENT_ID";
    public static final String USED_VALUE_COL = "USED_VALUE";
    public static final String SERVICE_VALUE_COL = "SERVICE_VALUE";
    public static final String TAX_VALUE_COL = "TAX_VALUE";
    public static final String SERVICE_VALUE_USD_COL = "SERVICE_VALUE_USD";
    public static final String TAX_VALUE_USD_COL = "TAX_VALUE_USD";
    public static final String LOC_INDEX_COL = "LOC_INDEX";
    public static final String TYPE_BASED_COL = "TYPE_BASED";
    public static final String REPORT_GROUP_COL = "REPORT_GROUP";
    public static final String TAX_SVC_DEFAULT_COL = "TAX_SVC_DEFAULT";
    public static final String PERSENTASE_DISTRIBUTION_PURCHASE_ORDER_COL = "PERSENTASE_DISTRIBUTION_PURCHASE_ORDER";
    public static final String COMPANY_ID_COL = "COMPANY_ID";
    public static final String PRICE_TYPE_ID_COL = "PRICE_TYPE_ID";
    public static final String STANDART_RATE_ID_COL = "STANDART_RATE_ID";
    public static final String  LOCATION_USED_TABLE_COL = "LOCATION_USED_TABLE";
    public static final String  SISTEM_ADDRESS_HISTORY_OUTLET_COL = " SISTEM_ADDRESS_HISTORY_OUTLET";
    public static final String ACCOUNTING_EMAIL_COL = "ACCOUNTING_EMAIL";
    public static final String LOCATION_IP_COL = "LOCATION_IP";
    public static final String COLOR_LOCATION_COL = "COLOR_LOCATION";
    public static final String SUB_REGENCY_ID_COL = "SUB_REGENCY_ID";
    public static final String LAST_UPDATE_COL = "LAST_UPDATE";
    public static final String NPWPD_COL = "NPWPD";
    public static final String STATUS_COL = "STATUS";


    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long  id;
        private String name;
        private String description;
        private Long parentId;
        private String code;
        private String address;
        private String telephone;
        private String fax;
        private String person;
        private Long contactId;
        private String email;
        private String website;
        private Long vendorId;
        private int type;
        private String servicePercentage;
        private String taxPercentage;
        private long departmentId;
        private double userdValue;
        private String serviceValue;
        private String taxValue;
        private String serviceValueUSD;
        private String taxValueUSD;
        private int locIndex;
        private int typeBased;
        private int reportGroup;
        private int taxSvcDefault;
        private double persentaseDistributionPurchaseOrder;
        private long companyId;
        private long princeTypeId;
        private long standartRateId;
        private int locationUsedTable;
        private String sistemAddressHistoryOutlet;
        private String accountingEmail;
        private String locationIp;
        private String colorLocation;
        private long subRegencyId;
        private LocalDateTime lastUpdate;
        private String npwpd;
        private int status;

        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord( String name,  String description,  String address, String person,  long princeTypeId,  long standartRateId, String sistemAddressHistoryOutlet, String accountingEmail, String locationIp, String colorLocation, int status) {
            return new Builder().newRecord(true)
                .name(Objects.requireNonNull(name, "name diperlukan"))
                .description(Objects.requireNonNull(description, "description diperlukan"))
                .address(Objects.requireNonNull(address, "address diperlukan"))
                .person(Objects.requireNonNull(person, "person diperlukan"))
                .princeTypeId(Objects.requireNonNull(princeTypeId, "princeTypeId diperlukan"))
                .standartRateId(Objects.requireNonNull(standartRateId, "standartRateId diperlukan"))
                .sistemAddressHistoryOutlet(Objects.requireNonNull(sistemAddressHistoryOutlet, "sistemAddressHistoryOutlet diperlukan"))
                .accountingEmail(Objects.requireNonNull(accountingEmail, "accountingEmail diperlukan"))
                .locationIp(Objects.requireNonNull(locationIp, "locationIp diperlukan"))
                .colorLocation(Objects.requireNonNull(colorLocation, "colorLocation diperlukan"))
                .status(Objects.requireNonNull(status, "status diperlukan"));
        }

        public static Builder updateBuilder(Location oldRecord, Location newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .name(changeItOrNot(newRecord.getName(), oldRecord.getName()))
                .description(changeItOrNot(newRecord.getDescription(), oldRecord.getDescription()))
                .parentId(changeItOrNot(newRecord.getParentId(), oldRecord.getParentId()))
                .code(changeItOrNot(newRecord.getCode(), oldRecord.getCode()))
                .address(changeItOrNot(newRecord.getAddress(), oldRecord.getAddress()))
                .telephone(changeItOrNot(newRecord.getTelephone(), oldRecord.getTelephone()))
                .fax(changeItOrNot(newRecord.getFax(), oldRecord.getFax()))
                .person(changeItOrNot(newRecord.getPerson(), oldRecord.getPerson()))
                .contactId(changeItOrNot(newRecord.getContactId(), oldRecord.getContactId()))
                .email(changeItOrNot(newRecord.getEmail(), oldRecord.getEmail()))
                .website(changeItOrNot(newRecord.getWebsite(), oldRecord.getWebsite()))
                .vendorId(changeItOrNot(newRecord.getVendorId(), oldRecord.getVendorId()))
                .type(changeItOrNot(newRecord.getType(), oldRecord.getType()))
                .servicePercentage(changeItOrNot(newRecord.getServicePercentage(), oldRecord.getServicePercentage()))
                .taxPercentage(changeItOrNot(newRecord.getTaxPercentage(), oldRecord.getTaxPercentage()))
                .departmentId(changeItOrNot(newRecord.getDepartmentId(), oldRecord.getDepartmentId()))
                .userdValue(changeItOrNot(newRecord.getUserdValue(), oldRecord.getUserdValue()))
                .serviceValue(changeItOrNot(newRecord.getServiceValue(), oldRecord.getServiceValue()))
                .taxValue(changeItOrNot(newRecord.getTaxValue(), oldRecord.getTaxValue()))
                .serviceValueUSD(changeItOrNot(newRecord.getServiceValueUSD(), oldRecord.getServiceValueUSD()))
                .taxValueUSD(changeItOrNot(newRecord.getTaxValueUSD(), oldRecord.getTaxValueUSD()))
                .locIndex(changeItOrNot(newRecord.getLocIndex(), oldRecord.getLocIndex()))
                .typeBased(changeItOrNot(newRecord.getTypeBased(), oldRecord.getTypeBased()))
                .reportGroup(changeItOrNot(newRecord.getReportGroup(), oldRecord.getReportGroup()))
                .taxSvcDefault(changeItOrNot(newRecord.getTaxSvcDefault(), oldRecord.getTaxSvcDefault()))
                .persentaseDistributionPurchaseOrder(changeItOrNot(newRecord.getPersentaseDistributionPurchaseOrder(), oldRecord.getPersentaseDistributionPurchaseOrder()))
                .companyId(changeItOrNot(newRecord.getCompanyId(), oldRecord.getCompanyId()))
                .princeTypeId(changeItOrNot(newRecord.getPrinceTypeId(), oldRecord.getPrinceTypeId()))
                .standartRateId(changeItOrNot(newRecord.getStandartRateId(), oldRecord.getStandartRateId()))
                .locationUsedTable(changeItOrNot(newRecord.getLocationUsedTable(), oldRecord.getLocationUsedTable()))
                .sistemAddressHistoryOutlet(changeItOrNot(newRecord.getSistemAddressHistoryOutlet(), oldRecord.getSistemAddressHistoryOutlet()))
                .accountingEmail(changeItOrNot(newRecord.getAccountingEmail(), oldRecord.getAccountingEmail()))
                .locationIp(changeItOrNot(newRecord.getLocationIp(), oldRecord.getLocationIp()))
                .colorLocation(changeItOrNot(newRecord.getColorLocation(), oldRecord.getColorLocation()))
                .subRegencyId(changeItOrNot(newRecord.getSubRegencyId(), oldRecord.getSubRegencyId()))
                .lastUpdate(changeItOrNot(newRecord.getLastUpdate(), oldRecord.getLastUpdate()))
                .npwpd(changeItOrNot(newRecord.getNpwpd(), oldRecord.getNpwpd()))
                .status(changeItOrNot(newRecord.getStatus(), oldRecord.getStatus()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public Location  build() {
            Location  result = new Location ();
            result.setId(id);
            result.setName(name);
            result.setDescription(description);
            result.setParentId(parentId);
            result.setCode(code);
            result.setAddress(address);
            result.setTelephone(telephone);
            result.setFax(fax);
            result.setPerson(person);
            result.setContactId(contactId);
            result.setEmail(email);
            result.setWebsite(website);
            result.setVendorId(vendorId);
            result.setType(type);
            result.setServicePercentage(servicePercentage);
            result.setTaxPercentage(taxPercentage);
            result.setDepartmentId(departmentId);
            result.setUserdValue(userdValue);
            result.setServiceValue(serviceValue);
            result.setTaxValue(taxValue);
            result.setServiceValueUSD(serviceValueUSD);
            result.setTaxValueUSD(taxValueUSD);
            result.setLocIndex(locIndex);
            result.setTypeBased(typeBased);
            result.setReportGroup(reportGroup);
            result.setTaxSvcDefault(taxSvcDefault);
            result.setPersentaseDistributionPurchaseOrder(persentaseDistributionPurchaseOrder);
            result.setCompanyId(companyId);
            result.setPrinceTypeId(princeTypeId);
            result.setStandartRateId(standartRateId);
            result.setLocationUsedTable(locationUsedTable);
            result.setSistemAddressHistoryOutlet(sistemAddressHistoryOutlet);
            result.setAccountingEmail(accountingEmail);
            result.setLocationIp(locationIp);
            result.setColorLocation(colorLocation);
            result.setSubRegencyId(subRegencyId);
            result.setLastUpdate(lastUpdate);
            result.setNpwpd(npwpd);
            result.setStatus(status);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
        private Long  id;
        private String name;
        private String description;
        private Long parentId;
        private String code;
        private String address;
        private String telephone;
        private String fax;
        private String person;
        private Long contactId;
        private String email;
        private String website;
        private Long vendorId;
        private int type;
        private String servicePercentage;
        private String taxPercentage;
        private long departmentId;
        private double userdValue;
        private String serviceValue;
        private String taxValue;
        private String serviceValueUSD;
        private String taxValueUSD;
        private int locIndex;
        private int typeBased;
        private int reportGroup;
        private int taxSvcDefault;
        private double persentaseDistributionPurchaseOrder;
        private long companyId;
        private long princeTypeId;
        private long standartRateId;
        private int locationUsedTable;
        private String sistemAddressHistoryOutlet;
        private String accountingEmail;
        private String locationIp;
        private String colorLocation;
        private long subRegencyId;
        private LocalDateTime lastUpdate;
        private String npwpd;
        private int status;
    @Transient
    @JsonIgnore
    private Long insertId;

    public static Location  fromRow(Row row) {
        var result = new Location ();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setName(ManipulateUtil.parseRow(row,NAME_COL, String  .class));
        result.setDescription(ManipulateUtil.parseRow(row,DESCRIPTION_COL, String  .class));
        result.setParentId(ManipulateUtil.parseRow(row,PARENT_ID_COL,  Long.class));
        result.setCode(ManipulateUtil.parseRow(row,CODE_COL, String.class));
        result.setAddress(ManipulateUtil.parseRow(row,ADDRESS_COL,  String.class));
        result.setTelephone(ManipulateUtil.parseRow(row,TELEPHONE_COL,  String.class));
        result.setFax(ManipulateUtil.parseRow(row,FAX_COL, String.class));
        result.setPerson(ManipulateUtil.parseRow(row,PERSON_COL,  String.class));
        result.setContactId(ManipulateUtil.parseRow(row,CONTACT_ID_COL, Long.class));
        result.setEmail(ManipulateUtil.parseRow(row,EMAIL_COL,  String.class));
        result.setWebsite(ManipulateUtil.parseRow(row,WEBSITE_COL,  String.class));
        result.setVendorId(ManipulateUtil.parseRow(row,VENDOR_ID_COL,  Long.class));
        result.setType(ManipulateUtil.parseRow(row,TYPE_COL,  Integer.class));
        result.setServicePercentage(ManipulateUtil.parseRow(row,SERVICE_PERCENTAGE_COL, String .class));
        result.setTaxPercentage(ManipulateUtil.parseRow(row,TAX_PERCENTAGE_COL,  String.class));
        result.setDepartmentId(ManipulateUtil.parseRow(row,DEPARTMENT_ID_COL,  Long.class));
        result.setUserdValue(ManipulateUtil.parseRow(row,USED_VALUE_COL, Double .class));
        result.setServiceValue(ManipulateUtil.parseRow(row,SERVICE_VALUE_COL,  String.class));
        result.setTaxValue(ManipulateUtil.parseRow(row,TAX_VALUE_COL,  String.class));
        result.setServiceValueUSD(ManipulateUtil.parseRow(row,SERVICE_VALUE_USD_COL,  String.class));
        result.setTaxValueUSD(ManipulateUtil.parseRow(row,TAX_VALUE_USD_COL,  String.class));
        result.setLocIndex(ManipulateUtil.parseRow(row,LOC_INDEX_COL,  Integer.class));
        result.setTypeBased(ManipulateUtil.parseRow(row,TYPE_BASED_COL, Integer.class));
        result.setReportGroup(ManipulateUtil.parseRow(row,REPORT_GROUP_COL, Integer.class));
        result.setTaxSvcDefault(ManipulateUtil.parseRow(row,TAX_SVC_DEFAULT_COL,  Integer.class));
        result.setPersentaseDistributionPurchaseOrder(ManipulateUtil.parseRow(row,PERSENTASE_DISTRIBUTION_PURCHASE_ORDER_COL, Double .class));
        result.setCompanyId(ManipulateUtil.parseRow(row,COMPANY_ID_COL, Long.class));
        result.setPrinceTypeId(ManipulateUtil.parseRow(row,PRICE_TYPE_ID_COL,  Long.class));
        result.setStandartRateId(ManipulateUtil.parseRow(row,STANDART_RATE_ID_COL,  Long.class));
        result.setLocationUsedTable(ManipulateUtil.parseRow(row,LOCATION_USED_TABLE_COL, Integer.class));
        result.setSistemAddressHistoryOutlet(ManipulateUtil.parseRow(row,SISTEM_ADDRESS_HISTORY_OUTLET_COL,  String.class));
        result.setAccountingEmail(ManipulateUtil.parseRow(row,ACCOUNTING_EMAIL_COL,  String.class));
        result.setLocationIp(ManipulateUtil.parseRow(row,LOCATION_IP_COL, String .class));
        result.setColorLocation(ManipulateUtil.parseRow(row,COLOR_LOCATION_COL,  String.class));
        result.setSubRegencyId(ManipulateUtil.parseRow(row,SUB_REGENCY_ID_COL,  Long.class));
        result.setLastUpdate(ManipulateUtil.parseRow(row,LAST_UPDATE_COL,  LocalDateTime.class));
        result.setNpwpd(ManipulateUtil.parseRow(row,NPWPD_COL,  String.class));
        result.setStatus(ManipulateUtil.parseRow(row,STATUS_COL,  Integer.class));
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
    public Location  update(Location  newData) {
        return Builder.updateBuilder(this, newData).build();
    }

    


}






    