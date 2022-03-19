package com.dimata.demo.app.prochain_app.models.table;

import static com.dimata.demo.app.prochain_app.core.util.ManipulateUtil.changeItOrNot;

import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.api.UpdateAvailable;
import com.dimata.demo.app.prochain_app.core.util.GenerateUtil;
import com.dimata.demo.app.prochain_app.core.util.ManipulateUtil;
import com.dimata.demo.app.prochain_app.enums.PaymentType;
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
@Table(ContactList.TABLE_NAME)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactList implements UpdateAvailable<ContactList>, Persistable <Long>{

    public static final String TABLE_NAME = "contact_list";
    public static final String ID_COL = "CONTACT_ID";
    public static final String CONTACT_CODE_COL = "CONTACT_CODE";
    public static final String CONTACT_TYPE_COL = "CONTACT_TYPE";
    public static final String CIN_COL ="CIN";
    public static final String CIN_COUNTER_COL = "CIN_COUNTER";
    public static final String REG_DATE_COL = "REG_DATE";
    public static final String SALUTATION_COL = "SALUTATION";
    public static final String DATE_OF_BIRTH_COL = "DATE_OF_BIRTH";
    public static final String PERSON_NAME_COL = "PERSON_NAME";
    public static final String PERSON_LASTNAME_COL = "PERSON_LASTNAME";
    public static final String MOTHER_NAME_COL = "MOTHER_NAME";
    public static final String NATIONALITY_COL = "NATIONALITY";
    public static final String OCCUPATION_COL = "OCCUPATION";
    public static final String IGNORE_BIRTH_DATE_COL = "IGNORE_BIRTH_DATE";
    public static final String DRIVING_LICENCE_NO_COL = "DRIVING_LICENCE_NO";
    public static final String PASSPORT_NO_COL = "PASSPORT_NO";
    public static final String KTP_NO_COL = "KTP_NO";
    public static final String HOME_ADDRESS_COL = "HOME_ADDRESS";
    public static final String HOME_CITY_COL = "HOME_CITY";
    public static final String HOME_STATE_COL = "HOME_STATE";
    public static final String HOME_PO_BOX_COL = "HOME_PO_BOX";
    public static final String HOME_COUNTRY_COL = "HOME_COUNTRY";
    public static final String HOME_PH_NUMBER_COL = "HOME_PH_NUMBER";
    public static final String HOME_MOBILE_PHONE_COL = "HOME_MOBILE_PHONE";
    public static final String HOME_EMAIL_COL = "HOME_EMAIL";
    public static final String HOME_FAX_COL = "HOME_FAX";
    public static final String HOME_WEBSITE_COL = "HOME_WEBSITE";
    public static final String HOME_PROVINCE_COL = "HOME_PROVINCE";
    public static final String HOME_ZIP_CODE_COL = "HOME_ZIP_CODE";
    public static final String COMP_NAME_COL = "COMP_NAME";
    public static final String COMP_ADDRESS_COL = "COMP_ADDRESS";
    public static final String COMP_CITY_COL = "COMP_CITY";
    public static final String COMP_STATE_COL = "COMP_STATE";
    public static final String COMP_ZIP_CODE_COL = "COMP_ZIP_CODE";
    public static final String COMP_PO_BOX_COL = "COMP_PO_BOX";
    public static final String COMP_COUNTRY_COL = "COMP_COUNTRY";
    public static final String COMP_PROVINCE_COL = "COMP_PROVINCE";
    public static final String COMP_REGENCY_COL = "COMP_REGENCY";
    public static final String COMP_PH_NUMBER1_COL = "COMP_PH_NUMBER1";
    public static final String COMP_PH_NUMBER2_COL = "COMP_PH_NUMBER2";
    public static final String COMP_FAX_COL = "COMP_FAX";
    public static final String COMP_EMAIL_COL = "COMP_EMAIL";
    public static final String COMP_WEBSITE_COL = "COMP_WEBSITE";
    public static final String REFERENCE_COL = "REFERENCE";
    public static final String MESSAGE_COL = "MESSAGE";
    public static final String NOTES_COL = "NOTES";
    public static final String BANK_ACC_COL = "BANK_ACC";
    public static final String BANK_ACC2_COL = "BANK_ACC2";
    public static final String EMPLOYEE_ID_COL = "EMPLOYEE_ID";
    public static final String PARENT_ID_COL = "PARENT_ID";
    public static final String COUNTRY_ID_COL = "COUNTRY_ID";
    public static final String PROCESS_STATUS_COL = "PROCESS_STATUS";
    public static final String HOME_ADDR_COL = "HOME_ADDR";
    public static final String HOME_TELP_COL = "HOME_TELP";
    public static final String HOME_TOWN_COL = "HOME_TOWN";
    public static final String MEMBER_BARCODE_COL = "MEMBER_BARCODE";
    public static final String MEMBER_BIRTH_DATE_COL = "MEMBER_BIRTH_DATE";
    public static final String MEMBER_COUNTER_COL = "MEMBER_COUNTER";
    public static final String MEMBER_GROUP_ID_COL = "MEMBER_GROUP_ID";
    public static final String MEMBER_ID_CARD_NUMBER_COL = "MEMBER_ID_CARD_NUMBER";
    public static final String MEMBER_LAST_UPDATE_COL = "MEMBER_LAST_UPDATE";
    public static final String CONSIGMENT_LIMIT_COL = "CONSIGMENT_LIMIT";
    public static final String CREDIT_LIMIT_COL = "CREDIT_LIMIT";
    public static final String MEMBER_USER_ID_COL = "MEMBER_USER_ID";
    public static final String MEMBER_PASSWORD_ID_COL = "MEMBER_PASSWORD_ID";
    public static final String CURRENCY_TYPE_ID_CONSIGMENT_LIMIT_COL = "CURRENCY_TYPE_ID_CONSIGMENT_LIMIT";
    public static final String CURRENCY_TYPE_ID_CREDIT_LIMIT_COL = "CURRENCY_TYPE_ID_CREDIT_LIMIT";
    public static final String TERM_OF_PAYMENT_COL = "TERM_OF_PAYMENT";
    public static final String DAYS_TERM_OF_PAYMENT_COL = "DAYS_TERM_OF_PAYMENT";
    public static final String SISTEM_OF_PAYMENT_COL = "SISTEM_OF_PAYMENT";
    public static final String WEEK_DAY_OF_PAYMENT_COL = "WEEK_DAY_OF_PAYMENT";
    public static final String WEEK_DAY_OF_SALES_VISIT_COL = "WEEK_DAY_OF_SALES_VISIT";
    public static final String TERM_OF_DELIVERY_COL = "TERM_OF_DELIVERY";
    public static final String EMAIL1_COL = "EMAIL1";
    public static final String LAST_UPDATE_COL = "LAST_UPDATE";
    public static final String MEMBER_RELIGION_ID_COL = "MEMBER_RELIGION_ID";
    public static final String MEMBER_SEX_COL = "MEMBER_SEX";
    public static final String MEMBER_STATUS_COL = "MEMBER_STATUS";
    public static final String BUSS_ADDRESS_COL = "BUSS_ADDRESS";
    public static final String REGDATE_COL = "REGDATE";
    public static final String TOWN_COL = "TOWN";
    public static final String PROVINCE_COL = "PROVINCE";
    public static final String COUNTRY_COL = "COUNTRY";
    public static final String TELP_NR_COL = "TELP_NR";
    public static final String TELP_MOBILE_COL = "TELP_MOBILE";
    public static final String FAX_COL = "FAX";
    public static final String DIRECTIONS_COL = "DIRECTIONS";
    public static final String EMAIL_COL = "EMAIL";
    public static final String COMPANY_BANK_ACC_COL = "COMPANY_BANK_ACC";
    public static final String POSITION_PERSON_COL = "POSITION_PERSON";
    public static final String POSTAL_CODE_COMPANY_COL = "POSTAL_CODE_COMPANY";
    public static final String WEBSITE_COMPANY_COL = "WEBSITE_COMPANY";
    public static final String EMAIL_COMPANY_COL = "EMAIL_COMPANY";
    public static final String POSTAL_CODE_HOME_COL = "POSTAL_CODE_HOME";
    public static final String DIRECTION_COL = "DIRECTION";
    public static final String FULL_NAME_COL = "FULL_NAME";
    public static final String LOCATION_ID_COL = "LOCATION_ID";
    public static final String MEMBER_PASSWORD_COL = "MEMBER_PASSWORD";

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

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
        private PaymentType termOfPayment;
        private int daysTermOfpayment;
        private Long sistemOfPayment;
        private String weekDayOfPayment;
        private String weekDayOfSalesVisit;
        private int termOfdelivery;
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

        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord() {
            return new Builder().newRecord(true);
                 
        }

        public static Builder updateBuilder(ContactList oldRecord, ContactList  newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .contactCode(changeItOrNot(newRecord.getContactCode(), oldRecord.getContactCode()))
                .contactType(changeItOrNot(newRecord.getContactType(), oldRecord.getContactType()))
                .cin(changeItOrNot(newRecord.getCin(), oldRecord.getCin()))
                .cinCounter(changeItOrNot(newRecord.getCinCounter(), oldRecord.getCinCounter()))
                .regDate1(changeItOrNot(newRecord.getRegDate1(), oldRecord.getRegDate1()))
                .salutation(changeItOrNot(newRecord.getSalutation(), oldRecord.getSalutation()))
                .dateOfBirth(changeItOrNot(newRecord.getDateOfBirth(), oldRecord.getDateOfBirth()))
                .personName(changeItOrNot(newRecord.getPersonName(), oldRecord.getPersonName()))
                .personLastname(changeItOrNot(newRecord.getPersonLastname(), oldRecord.getPersonLastname()))
                .motherName(changeItOrNot(newRecord.getMotherName(), oldRecord.getMotherName()))
                .nationality(changeItOrNot(newRecord.getNationality(), oldRecord.getNationality()))
                .occupation(changeItOrNot(newRecord.getOccupation(), oldRecord.getOccupation()))
                .ignoreBirthDate(changeItOrNot(newRecord.getIgnoreBirthDate(), oldRecord.getIgnoreBirthDate()))
                .drivingLicenceNo(changeItOrNot(newRecord.getDrivingLicenceNo(), oldRecord.getDrivingLicenceNo()))
                .passportNo(changeItOrNot(newRecord.getPassportNo(), oldRecord.getPassportNo()))
                .ktpNo(changeItOrNot(newRecord.getKtpNo(), oldRecord.getKtpNo()))
                .homeAddress(changeItOrNot(newRecord.getHomeAddress(), oldRecord.getHomeAddress()))
                .homeCity(changeItOrNot(newRecord.getHomeCity(), oldRecord.getHomeCity()))
                .homeState(changeItOrNot(newRecord.getHomeState(), oldRecord.getHomeState()))
                .homePoBox(changeItOrNot(newRecord.getHomePoBox(), oldRecord.getHomePoBox()))
                .homeCountry(changeItOrNot(newRecord.getHomeCountry(), oldRecord.getHomeCountry()))
                .homePhNumber(changeItOrNot(newRecord.getHomePhNumber(), oldRecord.getHomePhNumber()))
                .homeMobilePhone(changeItOrNot(newRecord.getHomeMobilePhone(), oldRecord.getHomeMobilePhone()))
                .homeEmail(changeItOrNot(newRecord.getHomeEmail(), oldRecord.getHomeEmail()))
                .homeFax(changeItOrNot(newRecord.getHomeFax(), oldRecord.getHomeFax()))
                .homeWebsite(changeItOrNot(newRecord.getHomeWebsite(), oldRecord.getHomeWebsite()))
                .homeprovince(changeItOrNot(newRecord.getHomeprovince(), oldRecord.getHomeprovince()))
                .homeZipCode(changeItOrNot(newRecord.getHomeZipCode(), oldRecord.getHomeZipCode()))
                .compName(changeItOrNot(newRecord.getCompName(), oldRecord.getCompName()))
                .compAddress(changeItOrNot(newRecord.getCompAddress(), oldRecord.getCompAddress()))
                .compCity(changeItOrNot(newRecord.getCompCity(), oldRecord.getCompCity()))
                .compState(changeItOrNot(newRecord.getCompState(), oldRecord.getCompState()))
                .compZipCode(changeItOrNot(newRecord.getCompZipCode(), oldRecord.getCompZipCode()))
                .compPoBox(changeItOrNot(newRecord.getCompPoBox(), oldRecord.getCompPoBox()))
                .compCountry(changeItOrNot(newRecord.getCompCountry(), oldRecord.getCompCountry()))
                .compProvince(changeItOrNot(newRecord.getCompProvince(), oldRecord.getCompProvince()))
                .compRegency(changeItOrNot(newRecord.getCompRegency(), oldRecord.getCompRegency()))
                .compPhNumber1(changeItOrNot(newRecord.getCompPhNumber1(), oldRecord.getCompPhNumber1()))
                .compPhNumber2(changeItOrNot(newRecord.getCompPhNumber2(), oldRecord.getCompPhNumber2()))
                .compFax(changeItOrNot(newRecord.getCompFax(), oldRecord.getCompFax()))
                .compEmail(changeItOrNot(newRecord.getCompEmail(), oldRecord.getCompEmail()))
                .compWebsite(changeItOrNot(newRecord.getCompWebsite(), oldRecord.getCompWebsite()))
                .reference(changeItOrNot(newRecord.getReference(), oldRecord.getReference()))
                .message(changeItOrNot(newRecord.getMessage(), oldRecord.getMessage()))
                .notes(changeItOrNot(newRecord.getNotes(), oldRecord.getNotes()))
                .bankAcc(changeItOrNot(newRecord.getBankAcc(), oldRecord.getBankAcc()))
                .bankAcc2(changeItOrNot(newRecord.getBankAcc2(), oldRecord.getBankAcc2()))
                .employeeId(changeItOrNot(newRecord.getEmployeeId(), oldRecord.getEmployeeId()))
                .parentId(changeItOrNot(newRecord.getParentId(), oldRecord.getParentId()))
                .countryId(changeItOrNot(newRecord.getCountryId(), oldRecord.getCountryId()))
                .processStatus(changeItOrNot(newRecord.getProcessStatus(), oldRecord.getProcessStatus()))
                .homeAddr(changeItOrNot(newRecord.getHomeAddr(), oldRecord.getHomeAddr()))
                .homeTelp(changeItOrNot(newRecord.getHomeTelp(), oldRecord.getHomeTelp()))
                .homeTown(changeItOrNot(newRecord.getHomeTown(), oldRecord.getHomeTown()))
                .memberBarcode(changeItOrNot(newRecord.getMemberBarcode(), oldRecord.getMemberBarcode()))
                .memberBirthDate(changeItOrNot(newRecord.getMemberBirthDate(), oldRecord.getMemberBirthDate()))
                .memberCounter(changeItOrNot(newRecord.getMemberCounter(), oldRecord.getMemberCounter()))
                .memberGroupId(changeItOrNot(newRecord.getMemberGroupId(), oldRecord.getMemberGroupId()))
                .memberIdCardNumber(changeItOrNot(newRecord.getMemberIdCardNumber(), oldRecord.getMemberIdCardNumber()))
                .memberLastUpadate(changeItOrNot(newRecord.getMemberLastUpadate(), oldRecord.getMemberLastUpadate()))
                .consigmentLimit(changeItOrNot(newRecord.getConsigmentLimit(), oldRecord.getConsigmentLimit()))
                .creditLimit(changeItOrNot(newRecord.getCreditLimit(), oldRecord.getCreditLimit()))
                .memberUserId(changeItOrNot(newRecord.getMemberUserId(), oldRecord.getMemberUserId()))
                .memberPasswordId(changeItOrNot(newRecord.getMemberPasswordId(), oldRecord.getMemberPasswordId()))
                .currencyTypeIdConsigmentLimit(changeItOrNot(newRecord.getCurrencyTypeIdConsigmentLimit(), oldRecord.getCurrencyTypeIdConsigmentLimit()))
                .currencyTypeIdCreditLimit(changeItOrNot(newRecord.getCurrencyTypeIdCreditLimit(), oldRecord.getCurrencyTypeIdCreditLimit()))
                .termOfPayment(changeItOrNot(newRecord.getPayment(), oldRecord.getPayment()))
                .daysTermOfpayment(changeItOrNot(newRecord.getDaysTermOfpayment(), oldRecord.getDaysTermOfpayment()))
                .sistemOfPayment(changeItOrNot(newRecord.getSistemOfPayment(), oldRecord.getSistemOfPayment()))
                .weekDayOfPayment(changeItOrNot(newRecord.getWeekDayOfPayment(), oldRecord.getWeekDayOfPayment()))
                .weekDayOfSalesVisit(changeItOrNot(newRecord.getWeekDayOfSalesVisit(), oldRecord.getWeekDayOfSalesVisit()))
                .termOfdelivery(changeItOrNot(newRecord.getTermOfdelivery(), oldRecord.getTermOfdelivery()))
                .email1(changeItOrNot(newRecord.getEmail1(), oldRecord.getEmail1()))
                .lastUpdate(changeItOrNot(newRecord.getLastUpdate(), oldRecord.getLastUpdate()))
                .memberReligionId(changeItOrNot(newRecord.getMemberReligionId(), oldRecord.getMemberReligionId()))
                .memberSex(changeItOrNot(newRecord.getMemberSex(), oldRecord.getMemberSex()))
                .memberStatus(changeItOrNot(newRecord.getMemberStatus(), oldRecord.getMemberStatus()))
                .bussAddress(changeItOrNot(newRecord.getBussAddress(), oldRecord.getBussAddress()))
                .regdate(changeItOrNot(newRecord.getRegdate(), oldRecord.getRegdate()))
                .town(changeItOrNot(newRecord.getTown(), oldRecord.getTown()))
                .province(changeItOrNot(newRecord.getProvince(), oldRecord.getProvince()))
                .country(changeItOrNot(newRecord.getCountry(), oldRecord.getCountry()))
                .telpNr(changeItOrNot(newRecord.getTelpNr(), oldRecord.getTelpNr()))
                .telpMobile(changeItOrNot(newRecord.getTelpMobile(), oldRecord.getTelpMobile()))
                .fax(changeItOrNot(newRecord.getFax(), oldRecord.getFax()))
                .directions(changeItOrNot(newRecord.getDirections(), oldRecord.getDirections()))
                .email(changeItOrNot(newRecord.getEmail(), oldRecord.getEmail()))
                .companyBankAcc(changeItOrNot(newRecord.getCompanyBankAcc(), oldRecord.getCompanyBankAcc()))
                .positionPerson(changeItOrNot(newRecord.getPositionPerson(), oldRecord.getPositionPerson()))
                .postalCodeCompany(changeItOrNot(newRecord.getPostalCodeCompany(), oldRecord.getPostalCodeCompany()))
                .direction(changeItOrNot(newRecord.getDirection(), oldRecord.getDirection()))
                .fullName(changeItOrNot(newRecord.getFullName(), oldRecord.getFullName()))
                .websiteCompany(changeItOrNot(newRecord.getWebsiteCompany(), oldRecord.getWebsiteCompany()))
                .emailCompany(changeItOrNot(newRecord.getEmailCompany(), oldRecord.getEmailCompany()))
                .postalCodeHome(changeItOrNot(newRecord.getPostalCodeHome(), oldRecord.getPostalCodeHome()))
                .locationId(changeItOrNot(newRecord.getLocationId(), oldRecord.getLocationId()))
                .memberPassword(changeItOrNot(newRecord.getMemberPassword(), oldRecord.getMemberPassword()));
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public ContactList  build() {
            ContactList result = new ContactList();
            result.setId(id);
            result.setContactCode(contactCode);
            result.setContactType(contactType);
            result.setCin(cin);
            result.setCinCounter(cinCounter);
            result.setRegDate1(regDate1);
            result.setSalutation(salutation);
            result.setDateOfBirth(dateOfBirth);
            result.setPersonName(personName);
            result.setPersonLastname(personLastname);
            result.setMotherName(motherName);
            result.setNationality(nationality);
            result.setOccupation(occupation);
            result.setIgnoreBirthDate(ignoreBirthDate);
            result.setDrivingLicenceNo(drivingLicenceNo);
            result.setPassportNo(passportNo);
            result.setKtpNo(ktpNo);
            result.setHomeAddress(homeAddress);
            result.setHomeCity(homeCity);
            result.setHomeState(homeState);
            result.setHomePoBox(homePoBox);
            result.setHomeCountry(homeCountry);
            result.setHomePhNumber(homePhNumber);
            result.setHomeMobilePhone(homeMobilePhone);
            result.setHomeEmail(homeEmail);
            result.setHomeFax(homeFax);
            result.setHomeWebsite(homeWebsite);
            result.setHomeprovince(homeprovince);
            result.setHomeZipCode(homeZipCode);
            result.setCompName(compName);
            result.setCompAddress(compAddress);
            result.setCompCity(compCity);
            result.setCompState(compState);
            result.setCompZipCode(compZipCode);
            result.setCompPoBox(compPoBox);
            result.setCompCountry(compCountry);
            result.setCompProvince(compProvince);
            result.setCompRegency(compRegency);
            result.setCompPhNumber1(compPhNumber1);
            result.setCompPhNumber2(compPhNumber2);
            result.setCompFax(compFax);
            result.setCompEmail(compEmail);
            result.setCompWebsite(compWebsite);
            result.setReference(reference);
            result.setMessage(message);
            result.setNotes(notes);
            result.setBankAcc(bankAcc);
            result.setBankAcc2(bankAcc2);
            result.setEmployeeId(employeeId);
            result.setParentId(parentId);
            result.setCountryId(countryId);
            result.setProcessStatus(processStatus);
            result.setHomeAddr(homeAddr);
            result.setHomeTelp(homeTelp);
            result.setHomeTown(homeTown);
            result.setMemberBarcode(memberBarcode);
            result.setMemberBirthDate(memberBirthDate);
            result.setMemberCounter(memberCounter);
            result.setMemberGroupId(memberGroupId);
            result.setMemberIdCardNumber(memberIdCardNumber);
            result.setMemberLastUpadate(memberLastUpadate);
            result.setConsigmentLimit(consigmentLimit);
            result.setCreditLimit(creditLimit);
            result.setMemberUserId(memberUserId);
            result.setMemberPasswordId(memberPasswordId);
            result.setCurrencyTypeIdConsigmentLimit(currencyTypeIdConsigmentLimit);
            result.setCurrencyTypeIdCreditLimit(currencyTypeIdCreditLimit);
            result.setPayment(termOfPayment);
            result.setDaysTermOfpayment(daysTermOfpayment);
            result.setSistemOfPayment(sistemOfPayment);
            result.setWeekDayOfPayment(weekDayOfPayment);
            result.setWeekDayOfSalesVisit(weekDayOfSalesVisit);
            result.setTermOfdelivery(termOfdelivery);
            result.setEmail1(email1);
            result.setLastUpdate(lastUpdate);
            result.setMemberReligionId(memberReligionId);
            result.setMemberSex(memberSex);
            result.setMemberStatus(memberStatus);
            result.setBussAddress(bussAddress);
            result.setRegdate(regdate);
            result.setTown(town);
            result.setProvince(province);
            result.setCountry(country);
            result.setTelpNr(telpNr);
            result.setTelpMobile(telpMobile);
            result.setFax(fax);
            result.setDirections(directions);
            result.setEmail(email);
            result.setCompanyBankAcc(companyBankAcc);
            result.setPositionPerson(positionPerson);
            result.setPostalCodeCompany(postalCodeCompany);
            result.setDirection(direction);
            result.setFullName(fullName);
            result.setWebsiteCompany(websiteCompany);
            result.setEmailCompany(emailCompany);
            result.setPostalCodeHome(postalCodeHome);
            result.setLocationId(locationId);
            result.setMemberPassword(memberPassword);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
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
        private Integer termOfPayment;
        private int daysTermOfpayment;
        private Long sistemOfPayment;
        private String weekDayOfPayment;
        private String weekDayOfSalesVisit;
        private int termOfdelivery;
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

        @Transient
        @JsonIgnore
        private Long insertId;

        public void setPayment(PaymentType termOfPayment) {
            if (termOfPayment != null) {
                this.termOfPayment = termOfPayment.getCode();
            }
        }
    
        public PaymentType getPayment() {
            if (termOfPayment != null) {
                return PaymentType.getPayment(this.termOfPayment);
            }
            return null;
        }
    

        public static ContactList  fromRow(Row row) {
            var result = new ContactList();
            result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
            result.setContactCode(ManipulateUtil.parseRow(row, CONTACT_CODE_COL, String.class));
            result.setContactType(ManipulateUtil.parseRow(row, CONTACT_TYPE_COL, Integer.class));
            result.setCin(ManipulateUtil.parseRow(row, CIN_COL, String.class));
            result.setCinCounter(ManipulateUtil.parseRow(row, CIN_COUNTER_COL,Integer.class));
            result.setRegDate1(ManipulateUtil.parseRow(row, REG_DATE_COL, LocalDateTime.class));
            result.setSalutation(ManipulateUtil.parseRow(row, SALUTATION_COL, String.class));
            result.setDateOfBirth(ManipulateUtil.parseRow(row, DATE_OF_BIRTH_COL, LocalDateTime.class));
            result.setPersonName(ManipulateUtil.parseRow(row, PERSON_NAME_COL, String.class));
            result.setPersonLastname(ManipulateUtil.parseRow(row, PERSON_LASTNAME_COL, String.class));
            result.setMotherName(ManipulateUtil.parseRow(row, MOTHER_NAME_COL, String.class));
            result.setNationality(ManipulateUtil.parseRow(row, NATIONALITY_COL, String.class));
            result.setOccupation(ManipulateUtil.parseRow(row, OCCUPATION_COL, String.class));
            result.setIgnoreBirthDate(ManipulateUtil.parseRow(row, IGNORE_BIRTH_DATE_COL, Integer.class));
            result.setDrivingLicenceNo(ManipulateUtil.parseRow(row, DRIVING_LICENCE_NO_COL, String.class));
            result.setPassportNo(ManipulateUtil.parseRow(row, PASSPORT_NO_COL, String.class));
            result.setKtpNo(ManipulateUtil.parseRow(row, KTP_NO_COL, String.class));
            result.setHomeAddress(ManipulateUtil.parseRow(row, HOME_ADDRESS_COL, String.class));
            result.setHomeCity(ManipulateUtil.parseRow(row, HOME_CITY_COL, String.class));
            result.setHomeState(ManipulateUtil.parseRow(row, HOME_STATE_COL, String.class));
            result.setHomePoBox(ManipulateUtil.parseRow(row, HOME_PO_BOX_COL, String.class));
            result.setHomeCountry(ManipulateUtil.parseRow(row, HOME_COUNTRY_COL, String.class));
            result.setHomePhNumber(ManipulateUtil.parseRow(row, HOME_PH_NUMBER_COL, String.class));
            result.setHomeMobilePhone(ManipulateUtil.parseRow(row, HOME_MOBILE_PHONE_COL, String.class));
            result.setHomeEmail(ManipulateUtil.parseRow(row, HOME_EMAIL_COL, String.class));
            result.setHomeFax(ManipulateUtil.parseRow(row, HOME_FAX_COL, String.class));
            result.setHomeWebsite(ManipulateUtil.parseRow(row, HOME_WEBSITE_COL, String.class));
            result.setHomeprovince(ManipulateUtil.parseRow(row, HOME_PROVINCE_COL, String.class));
            result.setHomeZipCode(ManipulateUtil.parseRow(row, HOME_ZIP_CODE_COL, String.class));
            result.setCompName(ManipulateUtil.parseRow(row, COMP_NAME_COL, String.class));
            result.setCompAddress(ManipulateUtil.parseRow(row, COMP_ADDRESS_COL, String.class));
            result.setCompCity(ManipulateUtil.parseRow(row, COMP_CITY_COL, String.class));
            result.setCompState(ManipulateUtil.parseRow(row, COMP_STATE_COL, String.class));
            result.setCompZipCode(ManipulateUtil.parseRow(row, COMP_ZIP_CODE_COL, String.class));
            result.setCompPoBox(ManipulateUtil.parseRow(row, COMP_PO_BOX_COL, String.class));
            result.setCompCountry(ManipulateUtil.parseRow(row, COMP_COUNTRY_COL, String.class));
            result.setCompProvince(ManipulateUtil.parseRow(row, COMP_PROVINCE_COL, String.class));
            result.setCompRegency(ManipulateUtil.parseRow(row, COMP_REGENCY_COL, String.class));
            result.setCompPhNumber1(ManipulateUtil.parseRow(row, COMP_PH_NUMBER1_COL, String.class));
            result.setCompPhNumber2(ManipulateUtil.parseRow(row, COMP_PH_NUMBER2_COL, String.class));
            result.setCompFax(ManipulateUtil.parseRow(row, COMP_FAX_COL, String.class));
            result.setCompEmail(ManipulateUtil.parseRow(row, COMP_EMAIL_COL, String.class));
            result.setCompWebsite(ManipulateUtil.parseRow(row, COMP_WEBSITE_COL, String.class));
            result.setReference(ManipulateUtil.parseRow(row, REFERENCE_COL, String.class));
            result.setMessage(ManipulateUtil.parseRow(row, MESSAGE_COL, String.class));
            result.setNotes(ManipulateUtil.parseRow(row, NOTES_COL, String.class));
            result.setBankAcc(ManipulateUtil.parseRow(row, BANK_ACC_COL, String.class));
            result.setBankAcc2(ManipulateUtil.parseRow(row, BANK_ACC2_COL, String.class));
            result.setEmployeeId(ManipulateUtil.parseRow(row, EMPLOYEE_ID_COL, Long.class));
            result.setParentId(ManipulateUtil.parseRow(row, PARENT_ID_COL, Long.class));
            result.setCountryId(ManipulateUtil.parseRow(row, COUNTRY_ID_COL, Long.class));
            result.setProcessStatus(ManipulateUtil.parseRow(row, PROCESS_STATUS_COL, Integer.class));
            result.setHomeAddr(ManipulateUtil.parseRow(row, HOME_ADDR_COL, String.class));
            result.setHomeTelp(ManipulateUtil.parseRow(row, HOME_TELP_COL, String.class));
            result.setHomeTown(ManipulateUtil.parseRow(row, HOME_TOWN_COL, String.class));
            result.setMemberBarcode(ManipulateUtil.parseRow(row, MEMBER_BARCODE_COL, String.class));
            result.setMemberBirthDate(ManipulateUtil.parseRow(row, MEMBER_BIRTH_DATE_COL, LocalDateTime.class));
            result.setMemberCounter(ManipulateUtil.parseRow(row, MEMBER_COUNTER_COL, Integer.class));
            result.setMemberGroupId(ManipulateUtil.parseRow(row, MEMBER_GROUP_ID_COL, Long.class));
            result.setMemberIdCardNumber(ManipulateUtil.parseRow(row, MEMBER_ID_CARD_NUMBER_COL, String.class));
            result.setMemberLastUpadate(ManipulateUtil.parseRow(row, MEMBER_LAST_UPDATE_COL, LocalDateTime.class));
            result.setConsigmentLimit(ManipulateUtil.parseRow(row, CONSIGMENT_LIMIT_COL, Long.class));
            result.setCreditLimit(ManipulateUtil.parseRow(row, CREDIT_LIMIT_COL, Long.class));
            result.setMemberUserId(ManipulateUtil.parseRow(row, MEMBER_USER_ID_COL, String.class));
            result.setMemberPasswordId(ManipulateUtil.parseRow(row, MEMBER_PASSWORD_ID_COL, String.class));
            result.setCurrencyTypeIdConsigmentLimit(ManipulateUtil.parseRow(row, CURRENCY_TYPE_ID_CONSIGMENT_LIMIT_COL, Long.class));
            result.setCurrencyTypeIdCreditLimit(ManipulateUtil.parseRow(row, CURRENCY_TYPE_ID_CREDIT_LIMIT_COL, Long.class));
            result.setPayment(PaymentType.getPayment(ManipulateUtil.parseRow(row, TERM_OF_PAYMENT_COL, Integer.class)));
            result.setDaysTermOfpayment(ManipulateUtil.parseRow(row, DAYS_TERM_OF_PAYMENT_COL, Integer.class));
            result.setSistemOfPayment(ManipulateUtil.parseRow(row, SISTEM_OF_PAYMENT_COL, Long.class));
            result.setWeekDayOfPayment(ManipulateUtil.parseRow(row, WEEK_DAY_OF_PAYMENT_COL, String.class));
            result.setWeekDayOfSalesVisit(ManipulateUtil.parseRow(row, WEEK_DAY_OF_SALES_VISIT_COL, String.class));
            result.setTermOfdelivery(ManipulateUtil.parseRow(row, TERM_OF_DELIVERY_COL, Integer.class));
            result.setEmail1(ManipulateUtil.parseRow(row, EMAIL1_COL, String.class));
            result.setLastUpdate(ManipulateUtil.parseRow(row, LAST_UPDATE_COL, LocalDateTime.class));
            result.setMemberReligionId(ManipulateUtil.parseRow(row, MEMBER_RELIGION_ID_COL, Long.class));
            result.setMemberSex(ManipulateUtil.parseRow(row, MEMBER_SEX_COL, Integer.class));
            result.setMemberStatus(ManipulateUtil.parseRow(row, MEMBER_STATUS_COL, Integer.class));
            result.setBussAddress(ManipulateUtil.parseRow(row, BUSS_ADDRESS_COL, String.class));
            result.setRegdate(ManipulateUtil.parseRow(row, REGDATE_COL, LocalDateTime.class));
            result.setTown(ManipulateUtil.parseRow(row, TOWN_COL, String.class));
            result.setProvince(ManipulateUtil.parseRow(row, PROVINCE_COL, String.class));
            result.setCountry(ManipulateUtil.parseRow(row, COUNTRY_COL, String.class));
            result.setTelpNr(ManipulateUtil.parseRow(row, TELP_NR_COL, String.class));
            result.setTelpMobile(ManipulateUtil.parseRow(row, TELP_MOBILE_COL, String.class));
            result.setFax(ManipulateUtil.parseRow(row, FAX_COL, String.class));
            result.setDirections(ManipulateUtil.parseRow(row, DIRECTIONS_COL, String.class));
            result.setEmail(ManipulateUtil.parseRow(row, EMAIL_COL, String.class));
            result.setCompanyBankAcc(ManipulateUtil.parseRow(row, COMPANY_BANK_ACC_COL, String.class));
            result.setPositionPerson(ManipulateUtil.parseRow(row, POSITION_PERSON_COL, String.class));
            result.setPostalCodeCompany(ManipulateUtil.parseRow(row, POSTAL_CODE_COMPANY_COL, String.class));
            result.setDirection(ManipulateUtil.parseRow(row, DIRECTION_COL, String.class));
            result.setFullName(ManipulateUtil.parseRow(row, FULL_NAME_COL, String.class));
            result.setWebsiteCompany(ManipulateUtil.parseRow(row, WEBSITE_COMPANY_COL, String.class));
            result.setEmailCompany(ManipulateUtil.parseRow(row, EMAIL_COMPANY_COL, String.class));
            result.setPostalCodeHome(ManipulateUtil.parseRow(row, POSTAL_CODE_HOME_COL, String.class));
            result.setLocationId(ManipulateUtil.parseRow(row, LOCATION_ID_COL, Long.class));
            result.setMemberPassword(ManipulateUtil.parseRow(row, MEMBER_PASSWORD_COL, String.class));
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
    public ContactList  update(ContactList  newData) {
        return Builder.updateBuilder(this, newData).build();
    }
        
}



