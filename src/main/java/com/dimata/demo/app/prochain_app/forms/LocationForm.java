package com.dimata.demo.app.prochain_app.forms;

import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.enums.LocationUsedEnum;
import com.dimata.demo.app.prochain_app.models.table.Location;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationForm implements RecordAdapter<Location>{

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
        private double usedValue;
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
        private long priceTypeId;
        private long standartRateId;
        private LocationUsedEnum locationUsedTable;
        private String sistemAddressHistoryOutlet;
        private String accountingEmail;
        private String locationIp;
        private String colorLocation;
        private long subRegencyId;
        private LocalDateTime lastUpdate;
        private String npwpd;
        private int status;
        
        @Override
        public Location convertNewRecord() {
           
            return Location.Builder.createNewRecord(name, description, address, person, priceTypeId, standartRateId, sistemAddressHistoryOutlet, accountingEmail, locationIp, colorLocation, status)
                .parentId(parentId)
                .code(code)
                .telephone(telephone)
                .fax(fax)
                .contactId(contactId)
                .email(email)
                .website(website)
                .vendorId(vendorId)
                .type(type)
                .servicePercentage(servicePercentage)
                .taxPercentage(taxPercentage)
                .departmentId(departmentId)
                .usedValue(usedValue)
                .serviceValue(serviceValue)
                .taxValue(taxValue)
                .serviceValueUSD(serviceValueUSD)
                .taxValueUSD(taxValueUSD)
                .locIndex(locIndex)
                .typeBased(typeBased)
                .reportGroup(reportGroup)
                .taxSvcDefault(taxSvcDefault)
                .persentaseDistributionPurchaseOrder(persentaseDistributionPurchaseOrder)
                .companyId(companyId)
                .locationUsedTable(locationUsedTable)
                .subRegencyId(subRegencyId)
                .lastUpdate(lastUpdate)
                .npwpd(npwpd)
                .id(id)
                .build();
        }
        @Override
        public Location convertToRecord() {
           
            return Location.Builder.emptyBuilder()
                .name(name)
                .parentId(parentId)
                .code(code)
                .description(description)
                .telephone(telephone)
                .fax(fax)
                .person(person)
                .contactId(contactId)
                .email(email)
                .website(website)
                .vendorId(vendorId)
                .type(type)
                .servicePercentage(servicePercentage)
                .taxPercentage(taxPercentage)
                .departmentId(departmentId)
                .usedValue(usedValue)
                .serviceValue(serviceValue)
                .taxValue(taxValue)
                .serviceValueUSD(serviceValueUSD)
                .taxValueUSD(taxValueUSD)
                .locIndex(locIndex)
                .typeBased(typeBased)
                .reportGroup(reportGroup)
                .taxSvcDefault(taxSvcDefault)
                .persentaseDistributionPurchaseOrder(persentaseDistributionPurchaseOrder)
                .companyId(companyId)
                .priceTypeId(priceTypeId)
                .standartRateId(standartRateId)
                .locationUsedTable(locationUsedTable)
                .sistemAddressHistoryOutlet(sistemAddressHistoryOutlet)
                .accountingEmail(accountingEmail)
                .locationIp(locationIp)
                .colorLocation(colorLocation)
                .subRegencyId(subRegencyId)
                .lastUpdate(lastUpdate)
                .npwpd(npwpd)
                .status(status)
                .id(id)
                .build();
        }
   
}
