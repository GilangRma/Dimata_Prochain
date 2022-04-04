package com.dimata.demo.app.prochain_app.forms;

import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.api.RecordAdapter;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeDeserialize;
import com.dimata.demo.app.prochain_app.enums.PaymentTypeEnum;
import com.dimata.demo.app.prochain_app.models.table.ContactList;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactListForm implements RecordAdapter<ContactList> {

        private Long id;
        private String contactCode;
        private int contactType;
        private String cin;
        private int cinCounter;
        private LocalDateTime regDate1;
        private String salutation;
        private LocalDateTime dateOfBirth;
        private String personName;
        private String personLastname;
        private String motherName;
        private String nationality;
        private String occupation;
        private int ignoreBirthDate;
        private String drivingLicenceNo;
        private String passportNo;
        private String ktpNo;
        private String homeAddress;
        private String homeCity;
        private String homeState;
        private String homePoBox;
        private String homeCountry;
        private String homePhNumber;
        private String homeMobilePhone;
        private String homeEmail;
        private String homeFax;
        private String homeWebsite;
        private String homeprovince;
        private String homeZipCode;
        private String compName;
        private String compAddress;
        private String compCity;
        private String compState;
        private String compZipCode;
        private String compPoBox;
        private String compCountry;
        private String compProvince;
        private String compRegency;
        private String compPhNumber1;
        private String compPhNumber2;
        private String compFax;
        private String compEmail;
        private String compWebsite;
        private String reference;
        private String message;
        private String notes;
        private String bankAcc;
        private String bankAcc2;
        private Long employeeId;
        private Long parentId;
        private Long countryId;
        private int processStatus;
        private String homeAddr;
        private String homeTelp;
        private String homeTown;
        private String memberBarcode;
        private LocalDateTime memberBirthDate;
        private int memberCounter;
        private Long memberGroupId;
        private String memberIdCardNumber;
        private LocalDateTime memberLastUpadate;
        private double consigmentLimit;
        private double creditLimit;
        private String memberUserId;
        private String memberPasswordId;
        private Long currencyTypeIdConsigmentLimit;
        private Long currencyTypeIdCreditLimit;
        private PaymentTypeEnum termOfPayment;
        private Integer daysTermOfpayment;
        private Long sistemOfPayment;
        private String weekDayOfPayment;
        private String weekDayOfSalesVisit;
        private Integer termOfdelivery;
        private String email1;
        private LocalDateTime lastUpdate;
        private Long memberReligionId;
        private int memberSex;
        private int memberStatus;
        private String bussAddress;
        private LocalDateTime regdate;
        private String town;
        private String province;
        private String country;
        private String telpNr;
        private String telpMobile;
        private String fax;
        private String directions;
        private String email;
        private String companyBankAcc;
        private String positionPerson;
        private String postalCodeCompany;
        private String direction;
        private String fullName;
        private String websiteCompany;
        private String emailCompany;
        private String postalCodeHome;
        private Long locationId;
        private String memberPassword;
        @JsonDeserialize(converter = TimeDeserialize.class)
        @Override
        public ContactList convertNewRecord() {
    
            return ContactList.Builder.createNewRecord(contactCode, salutation, personName, personLastname, motherName, compName)

                    .contactType(contactType)
                    .cin(cin)
                    .cinCounter(cinCounter)
                    .regDate1(regDate1)
                    .salutation(salutation)
                    .dateOfBirth(dateOfBirth)
                    .personName(personName)
                    .personLastname(personLastname)
                    .motherName(motherName)
                    .nationality(nationality)
                    .occupation(occupation)
                    .ignoreBirthDate(ignoreBirthDate)
                    .drivingLicenceNo(drivingLicenceNo)
                    .passportNo(passportNo)
                    .ktpNo(ktpNo)
                    .homeAddress(homeAddress)
                    .homeCity(homeCity)
                    .homeState(homeState)
                    .homePoBox(homePoBox)
                    .homeCountry(homeCountry)
                    .homePhNumber(homePhNumber)
                    .homeMobilePhone(homeMobilePhone)
                    .homeEmail(homeEmail)
                    .homeFax(homeFax)
                    .homeWebsite(homeWebsite)
                    .homeprovince(homeprovince)
                    .homeZipCode(homeZipCode)
                    .compName(compName)
                    .compAddress(compAddress)
                    .compCity(compCity)
                    .compState(compState)
                    .compZipCode(compZipCode)
                    .compPoBox(compPoBox)
                    .compCountry(compCountry)
                    .compProvince(compProvince)
                    .compRegency(compRegency)
                    .compPhNumber1(compPhNumber1)
                    .compPhNumber2(compPhNumber2)
                    .compFax(compFax)
                    .compEmail(compEmail)
                    .compWebsite(compWebsite)
                    .reference(reference)
                    .message(message)
                    .notes(notes)
                    .bankAcc(bankAcc)
                    .bankAcc2(bankAcc2)
                    .employeeId(employeeId)
                    .parentId(parentId)
                    .countryId(countryId)
                    .processStatus(processStatus)
                    .homeAddr(homeAddr)
                    .homeTelp(homeTelp)
                    .homeTown(homeTown)
                    .memberBarcode(memberBarcode)
                    .memberBirthDate(memberBirthDate)
                    .memberCounter(memberCounter)
                    .memberGroupId(memberGroupId)
                    .memberIdCardNumber(memberIdCardNumber)
                    .memberLastUpadate(memberLastUpadate)
                    .consigmentLimit(consigmentLimit)
                    .creditLimit(creditLimit)
                    .memberUserId(memberUserId)
                    .memberPasswordId(memberPasswordId)
                    .currencyTypeIdConsigmentLimit(currencyTypeIdConsigmentLimit)
                    .currencyTypeIdCreditLimit(currencyTypeIdCreditLimit)
                    .termOfPayment(termOfPayment)
                    .daysTermOfpayment(daysTermOfpayment)
                    .sistemOfPayment(sistemOfPayment)
                    .weekDayOfPayment(weekDayOfPayment)
                    .email1(email1)
                    .lastUpdate(lastUpdate)
                    .memberReligionId(memberReligionId)
                    .memberSex(memberSex)
                    .memberStatus(memberStatus)
                    .bussAddress(bussAddress)
                    .weekDayOfSalesVisit(weekDayOfSalesVisit)
                    .termOfdelivery(termOfdelivery)
                    .regdate(regdate)
                    .town(town)
                    .province(province)
                    .country(country)
                    .telpNr(telpNr)
                    .telpMobile(telpMobile)
                    .fax(fax)
                    .directions(directions)
                    .email(email)
                    .companyBankAcc(companyBankAcc)
                    .positionPerson(positionPerson)
                    .postalCodeCompany(postalCodeCompany)
                    .direction(direction)
                    .fullName(fullName)
                    .websiteCompany(websiteCompany)
                    .emailCompany(emailCompany)
                    .postalCodeHome(postalCodeHome)
                    .locationId(locationId)
                    .memberPassword(memberPassword)
                    .id(id)
                    .build();
        }
        @Override
        public ContactList convertToRecord() {
        
            return ContactList.Builder.emptyBuilder()
                    .contactCode(contactCode)
                    .contactType(contactType)
                    .cin(cin)
                    .cinCounter(cinCounter)
                    .regDate1(regDate1)
                    .salutation(salutation)
                    .dateOfBirth(dateOfBirth)
                    .personName(personName)
                    .personLastname(personLastname)
                    .motherName(motherName)
                    .nationality(nationality)
                    .occupation(occupation)
                    .ignoreBirthDate(ignoreBirthDate)
                    .drivingLicenceNo(drivingLicenceNo)
                    .passportNo(passportNo)
                    .ktpNo(ktpNo)
                    .homeAddress(homeAddress)
                    .homeCity(homeCity)
                    .homeState(homeState)
                    .homePoBox(homePoBox)
                    .homeCountry(homeCountry)
                    .homePhNumber(homePhNumber)
                    .homeMobilePhone(homeMobilePhone)
                    .homeEmail(homeEmail)
                    .homeFax(homeFax)
                    .homeWebsite(homeWebsite)
                    .homeprovince(homeprovince)
                    .homeZipCode(homeZipCode)
                    .compName(compName)
                    .compAddress(compAddress)
                    .compCity(compCity)
                    .compState(compState)
                    .compZipCode(compZipCode)
                    .compPoBox(compPoBox)
                    .compCountry(compCountry)
                    .compProvince(compProvince)
                    .compRegency(compRegency)
                    .compPhNumber1(compPhNumber1)
                    .compPhNumber2(compPhNumber2)
                    .compFax(compFax)
                    .compEmail(compEmail)
                    .compWebsite(compWebsite)
                    .reference(reference)
                    .message(message)
                    .notes(notes)
                    .bankAcc(bankAcc)
                    .bankAcc2(bankAcc2)
                    .employeeId(employeeId)
                    .parentId(parentId)
                    .countryId(countryId)
                    .processStatus(processStatus)
                    .homeAddr(homeAddr)
                    .homeTelp(homeTelp)
                    .homeTown(homeTown)
                    .memberBarcode(memberBarcode)
                    .memberBirthDate(memberBirthDate)
                    .memberCounter(memberCounter)
                    .memberGroupId(memberGroupId)
                    .memberIdCardNumber(memberIdCardNumber)
                    .memberLastUpadate(memberLastUpadate)
                    .consigmentLimit(consigmentLimit)
                    .creditLimit(creditLimit)
                    .memberUserId(memberUserId)
                    .memberPasswordId(memberPasswordId)
                    .currencyTypeIdConsigmentLimit(currencyTypeIdConsigmentLimit)
                    .currencyTypeIdCreditLimit(currencyTypeIdCreditLimit)
                    .termOfPayment(termOfPayment)
                    .daysTermOfpayment(daysTermOfpayment)
                    .sistemOfPayment(sistemOfPayment)
                    .weekDayOfPayment(weekDayOfPayment)
                    .email1(email1)
                    .lastUpdate(lastUpdate)
                    .memberReligionId(memberReligionId)
                    .memberSex(memberSex)
                    .memberStatus(memberStatus)
                    .bussAddress(bussAddress)
                    .weekDayOfSalesVisit(weekDayOfSalesVisit)
                    .termOfdelivery(termOfdelivery)
                    .regdate(regdate)
                    .town(town)
                    .province(province)
                    .country(country)
                    .telpNr(telpNr)
                    .telpMobile(telpMobile)
                    .fax(fax)
                    .directions(directions)
                    .email(email)
                    .companyBankAcc(companyBankAcc)
                    .positionPerson(positionPerson)
                    .postalCodeCompany(postalCodeCompany)
                    .direction(direction)
                    .fullName(fullName)
                    .websiteCompany(websiteCompany)
                    .emailCompany(emailCompany)
                    .postalCodeHome(postalCodeHome)
                    .locationId(locationId)
                    .memberPassword(memberPassword)
                    .id(id)
                    .build();
                }

        
}
