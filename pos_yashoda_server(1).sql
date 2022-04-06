-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 06 Apr 2022 pada 03.31
-- Versi server: 10.4.21-MariaDB
-- Versi PHP: 7.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pos_yashoda_server`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `cash_cashier`
--

CREATE TABLE `cash_cashier` (
  `CASH_CASHIER_ID` bigint(20) NOT NULL DEFAULT 0,
  `CASH_MASTER_ID` bigint(20) NOT NULL DEFAULT 0,
  `APP_USER_ID` bigint(20) NOT NULL DEFAULT 0,
  `OPEN_DATE` datetime DEFAULT NULL,
  `SPV_OPEN_ID` bigint(20) DEFAULT NULL,
  `SPV_OPEN_NAME` char(50) DEFAULT NULL,
  `SPV_CLOSE_ID` bigint(20) DEFAULT NULL,
  `SPV_CLOSE_NAME` char(50) DEFAULT NULL,
  `SHIFT_ID` bigint(20) NOT NULL DEFAULT 0,
  `CLOSE_DATE` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `cash_closing`
--

CREATE TABLE `cash_closing` (
  `CASH_CLOSING_ID` bigint(20) NOT NULL DEFAULT 0,
  `CASH_CASHIER_ID` bigint(20) NOT NULL DEFAULT 0,
  `PAYMENT_TYPE` int(11) DEFAULT NULL,
  `AMOUNT_RP` decimal(10,2) DEFAULT NULL,
  `AMOUNT_USD` decimal(10,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `cash_master`
--

CREATE TABLE `cash_master` (
  `CASH_MASTER_ID` bigint(20) NOT NULL DEFAULT 0,
  `CASHIER_NUMBER` tinyint(4) DEFAULT NULL,
  `LOCATION_ID` bigint(20) NOT NULL DEFAULT 0,
  `TAX` double DEFAULT NULL,
  `SERVICE` double DEFAULT NULL,
  `PRICE_TYPE` varchar(20) DEFAULT NULL,
  `CABANG` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `cash_return_payment`
--

CREATE TABLE `cash_return_payment` (
  `RETURN_ID` bigint(20) NOT NULL DEFAULT 0,
  `CURRENCY_ID` bigint(20) NOT NULL DEFAULT 0,
  `CASH_BILL_MAIN_ID` bigint(20) NOT NULL DEFAULT 0,
  `RATE` double DEFAULT NULL,
  `PAYMENT_STATUS` int(11) NOT NULL DEFAULT 0,
  `AMOUNT` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `contact_list`
--

CREATE TABLE `contact_list` (
  `CONTACT_ID` bigint(20) NOT NULL DEFAULT 0,
  `CONTACT_CODE` varchar(20) NOT NULL DEFAULT '*',
  `CONTACT_TYPE` int(11) DEFAULT 0,
  `CIN` varchar(8) DEFAULT '',
  `CIN_COUNTER` int(11) DEFAULT 0,
  `REG_DATE1` datetime DEFAULT '2010-11-11 00:00:00',
  `SALUTATION` varchar(5) DEFAULT '',
  `DATE_OF_BIRTH` datetime DEFAULT '2010-11-11 00:00:00',
  `PERSON_NAME` varchar(64) DEFAULT '',
  `PERSON_LASTNAME` varchar(64) DEFAULT '',
  `MOTHER_NAME` varchar(64) DEFAULT '',
  `NATIONALITY` varchar(64) DEFAULT '',
  `OCCUPATION` varchar(64) DEFAULT '',
  `IGNORE_BIRTH_DATE` int(4) DEFAULT 0,
  `DRIVING_LICENCE_NO` varchar(30) DEFAULT '',
  `PASSPORT_NO` varchar(64) DEFAULT '',
  `KTP_NO` varchar(30) DEFAULT '',
  `HOME_ADDRESS` text DEFAULT NULL,
  `HOME_CITY` varchar(64) DEFAULT '',
  `HOME_STATE` varchar(64) DEFAULT '',
  `HOME_PO_BOX` varchar(20) DEFAULT '',
  `HOME_COUNTRY` varchar(64) DEFAULT '',
  `HOME_PH_NUMBER` varchar(20) DEFAULT '',
  `HOME_MOBILE_PHONE` varchar(20) DEFAULT '',
  `HOME_EMAIL` varchar(64) DEFAULT '',
  `HOME_FAX` varchar(100) DEFAULT '',
  `HOME_WEBSITE` varchar(64) DEFAULT '',
  `HOME_PROVINCE` varchar(20) DEFAULT '',
  `HOME_ZIP_CODE` varchar(20) DEFAULT '',
  `COMP_NAME` varchar(64) DEFAULT '',
  `COMP_ADDRESS` text DEFAULT NULL,
  `COMP_CITY` varchar(64) DEFAULT '',
  `COMP_STATE` varchar(64) DEFAULT '',
  `COMP_ZIP_CODE` varchar(20) DEFAULT '',
  `COMP_PO_BOX` varchar(20) DEFAULT '',
  `COMP_COUNTRY` varchar(64) DEFAULT '',
  `COMP_PROVINCE` varchar(100) DEFAULT NULL,
  `COMP_REGENCY` varchar(100) DEFAULT NULL,
  `COMP_PH_NUMBER1` varchar(180) DEFAULT NULL,
  `COMP_PH_NUMBER2` varchar(20) DEFAULT '',
  `COMP_FAX` varchar(20) DEFAULT '',
  `COMP_EMAIL` varchar(64) DEFAULT '',
  `COMP_WEBSITE` varchar(64) DEFAULT '',
  `REFERENCE` varchar(64) DEFAULT '',
  `MESSAGE` text DEFAULT NULL,
  `NOTES` text DEFAULT NULL,
  `BANK_ACC` varchar(20) DEFAULT '',
  `BANK_ACC2` varchar(20) DEFAULT '',
  `EMPLOYEE_ID` bigint(20) DEFAULT 0,
  `PARENT_ID` bigint(20) DEFAULT 0,
  `COUNTRY_ID` bigint(20) DEFAULT 0,
  `PROCESS_STATUS` int(11) DEFAULT 0,
  `HOME_ADDR` text DEFAULT NULL,
  `HOME_TELP` text DEFAULT NULL,
  `HOME_TOWN` text DEFAULT NULL,
  `MEMBER_BARCODE` varchar(100) DEFAULT '',
  `MEMBER_BIRTH_DATE` datetime DEFAULT '2010-11-11 00:00:00',
  `MEMBER_COUNTER` int(11) DEFAULT 0,
  `MEMBER_GROUP_ID` bigint(20) DEFAULT 0,
  `MEMBER_ID_CARD_NUMBER` varchar(100) DEFAULT '',
  `MEMBER_LAST_UPDATE` datetime DEFAULT '2010-11-11 00:00:00',
  `CONSIGMENT_LIMIT` double DEFAULT NULL,
  `CREDIT_LIMIT` double DEFAULT NULL,
  `MEMBER_USER_ID` varchar(20) DEFAULT NULL,
  `MEMBER_PASSWORD_ID` varchar(20) DEFAULT NULL,
  `CURRENCY_TYPE_ID_CONSIGMENT_LIMIT` bigint(20) DEFAULT NULL,
  `CURRENCY_TYPE_ID_CREDIT_LIMIT` bigint(20) DEFAULT NULL,
  `TERM_OF_PAYMENT` int(2) DEFAULT 0 COMMENT 'apakah cash atau credit',
  `DAYS_TERM_OF_PAYMENT` int(11) DEFAULT NULL COMMENT 'berapa lama bisa kredit',
  `SISTEM_OF_PAYMENT` bigint(20) DEFAULT NULL COMMENT 'cash, credit card',
  `WEEK_DAY_OF_PAYMENT` varchar(20) DEFAULT NULL COMMENT 'hari pembayaran',
  `WEEK_DAY_OF_SALES_VISIT` varchar(20) DEFAULT NULL COMMENT 'hari sales visit ke toko',
  `TERM_OF_DELIVERY` int(3) DEFAULT NULL COMMENT 'berapa hari kirim barang',
  `EMAIL1` varchar(120) DEFAULT NULL,
  `LAST_UPDATE` timestamp NULL DEFAULT current_timestamp(),
  `MEMBER_RELIGION_ID` bigint(20) DEFAULT 0,
  `MEMBER_SEX` int(11) DEFAULT 0,
  `MEMBER_STATUS` int(11) DEFAULT 0,
  `BUSS_ADDRESS` text DEFAULT NULL,
  `REGDATE` datetime DEFAULT '2010-11-11 00:00:00',
  `TOWN` varchar(100) DEFAULT '',
  `PROVINCE` varchar(100) DEFAULT '',
  `COUNTRY` varchar(100) DEFAULT '',
  `TELP_NR` varchar(100) DEFAULT '',
  `TELP_MOBILE` varchar(100) DEFAULT '',
  `FAX` varchar(100) DEFAULT '',
  `DIRECTIONS` varchar(100) DEFAULT '',
  `EMAIL` varchar(100) DEFAULT '',
  `COMPANY_BANK_ACC` varchar(100) DEFAULT NULL,
  `POSITION_PERSON` varchar(100) DEFAULT NULL,
  `POSTAL_CODE_COMPANY` varchar(100) DEFAULT NULL,
  `WEBSITE_COMPANY` varchar(100) DEFAULT NULL,
  `EMAIL_COMPANY` varchar(100) DEFAULT NULL,
  `POSTAL_CODE_HOME` varchar(100) DEFAULT NULL,
  `DIRECTION` text DEFAULT NULL,
  `FULL_NAME` varchar(128) NOT NULL DEFAULT '*',
  `LOCATION_ID` bigint(20) DEFAULT NULL,
  `MEMBER_PASSWORD` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `contact_list`
--

INSERT INTO `contact_list` (`CONTACT_ID`, `CONTACT_CODE`, `CONTACT_TYPE`, `CIN`, `CIN_COUNTER`, `REG_DATE1`, `SALUTATION`, `DATE_OF_BIRTH`, `PERSON_NAME`, `PERSON_LASTNAME`, `MOTHER_NAME`, `NATIONALITY`, `OCCUPATION`, `IGNORE_BIRTH_DATE`, `DRIVING_LICENCE_NO`, `PASSPORT_NO`, `KTP_NO`, `HOME_ADDRESS`, `HOME_CITY`, `HOME_STATE`, `HOME_PO_BOX`, `HOME_COUNTRY`, `HOME_PH_NUMBER`, `HOME_MOBILE_PHONE`, `HOME_EMAIL`, `HOME_FAX`, `HOME_WEBSITE`, `HOME_PROVINCE`, `HOME_ZIP_CODE`, `COMP_NAME`, `COMP_ADDRESS`, `COMP_CITY`, `COMP_STATE`, `COMP_ZIP_CODE`, `COMP_PO_BOX`, `COMP_COUNTRY`, `COMP_PROVINCE`, `COMP_REGENCY`, `COMP_PH_NUMBER1`, `COMP_PH_NUMBER2`, `COMP_FAX`, `COMP_EMAIL`, `COMP_WEBSITE`, `REFERENCE`, `MESSAGE`, `NOTES`, `BANK_ACC`, `BANK_ACC2`, `EMPLOYEE_ID`, `PARENT_ID`, `COUNTRY_ID`, `PROCESS_STATUS`, `HOME_ADDR`, `HOME_TELP`, `HOME_TOWN`, `MEMBER_BARCODE`, `MEMBER_BIRTH_DATE`, `MEMBER_COUNTER`, `MEMBER_GROUP_ID`, `MEMBER_ID_CARD_NUMBER`, `MEMBER_LAST_UPDATE`, `CONSIGMENT_LIMIT`, `CREDIT_LIMIT`, `MEMBER_USER_ID`, `MEMBER_PASSWORD_ID`, `CURRENCY_TYPE_ID_CONSIGMENT_LIMIT`, `CURRENCY_TYPE_ID_CREDIT_LIMIT`, `TERM_OF_PAYMENT`, `DAYS_TERM_OF_PAYMENT`, `SISTEM_OF_PAYMENT`, `WEEK_DAY_OF_PAYMENT`, `WEEK_DAY_OF_SALES_VISIT`, `TERM_OF_DELIVERY`, `EMAIL1`, `LAST_UPDATE`, `MEMBER_RELIGION_ID`, `MEMBER_SEX`, `MEMBER_STATUS`, `BUSS_ADDRESS`, `REGDATE`, `TOWN`, `PROVINCE`, `COUNTRY`, `TELP_NR`, `TELP_MOBILE`, `FAX`, `DIRECTIONS`, `EMAIL`, `COMPANY_BANK_ACC`, `POSITION_PERSON`, `POSTAL_CODE_COMPANY`, `WEBSITE_COMPANY`, `EMAIL_COMPANY`, `POSTAL_CODE_HOME`, `DIRECTION`, `FULL_NAME`, `LOCATION_ID`, `MEMBER_PASSWORD`) VALUES
(846074062789579, '7844878', 3434343, '345343', 45666, '2022-04-02 10:04:03', 'mohon', '2004-04-02 10:15:08', 'bagas12', 'bagas aja', 'bu putri', 'Indonesia', 'mahasiswa', 3453534, 'ada', '3545646', '4553534', '234.234.666.444', 'Tabanan', 'Indonesia', '45645455', 'Indonesia', '0932848448', '0847737733', 'rumahaja09@gmail.com', '36575444', 'www.home.com', 'Bali', '0882', 'dimata', '098.737.384.826', 'Denpasar', 'Indonesia', '545454', '546444', 'Indonesia', 'Bali', 'Denpasar', '08818939938', '08824892284', '5675655', 'dimataIT@gmail.com', 'www.dimataIT.co.id', 'google', 'notes', 'try hard', '453564545', '346556756', 43533336, 456454545, 567786766, 400, '565.656.56.77', '0876775675', 'Denpasar', '4564764454', '2004-04-02 10:15:08', 4354353, 567575656, '4565477564', '2010-11-11 00:00:00', 45.9, 4544.88, '365647654', '45667564545', 45343463433, 4345575656, 1, 56565, 5654544, 'senin', 'sabtu', 1, 'BagasAja@gmail.com', '2022-04-05 04:24:17', 674335744, 57967221, 78654487, '400', '2022-04-04 10:15:08', 'Denpasar', 'Bali', 'Indonesia', '087654675444', '0821356476', '4565675756', '564575646', 'BagasAja@gmail.com', '5643356', '565635356', '54365335', 'www.dimataIT.com', 'dimataIT@gmail.com', '4645445454', '563535', 'I Kadek Bagas arya', 49848584984, 'bagus');

-- --------------------------------------------------------

--
-- Struktur dari tabel `discount_type`
--

CREATE TABLE `discount_type` (
  `DISCOUNT_TYPE_ID` bigint(20) NOT NULL DEFAULT 0,
  `CODE` varchar(20) DEFAULT NULL,
  `DINDEX` int(11) NOT NULL DEFAULT 0,
  `NAME` varchar(64) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `discount_type`
--

INSERT INTO `discount_type` (`DISCOUNT_TYPE_ID`, `CODE`, `DINDEX`, `NAME`) VALUES
(846074060737865, '45665', 12345, 'Bagas'),
(846074060654590, '45665', 1234567892, 'Bagas');

-- --------------------------------------------------------

--
-- Struktur dari tabel `location`
--

CREATE TABLE `location` (
  `LOCATION_ID` bigint(20) NOT NULL DEFAULT 0,
  `NAME` varchar(64) DEFAULT NULL,
  `DESCRIPTION` text DEFAULT NULL,
  `PARENT_ID` bigint(20) DEFAULT 0,
  `CODE` varchar(100) DEFAULT '',
  `ADDRESS` text DEFAULT NULL,
  `TELEPHONE` varchar(100) DEFAULT '',
  `FAX` varchar(100) DEFAULT '',
  `PERSON` varchar(50) DEFAULT NULL,
  `CONTACT_ID` bigint(20) DEFAULT 0,
  `EMAIL` varchar(100) DEFAULT '',
  `WEBSITE` varchar(100) DEFAULT '',
  `VENDOR_ID` bigint(20) DEFAULT 0,
  `TYPE` int(11) DEFAULT 0,
  `SERVICE_PERCENTAGE` decimal(10,2) DEFAULT 0.00,
  `TAX_PERCENTAGE` decimal(10,2) DEFAULT 0.00,
  `DEPARTMENT_ID` bigint(20) DEFAULT 0,
  `USED_VALUE` double DEFAULT 0,
  `SERVICE_VALUE` decimal(10,2) DEFAULT 0.00,
  `TAX_VALUE` decimal(10,2) DEFAULT 0.00,
  `SERVICE_VALUE_USD` decimal(10,2) DEFAULT 0.00,
  `TAX_VALUE_USD` decimal(10,2) DEFAULT 0.00,
  `LOC_INDEX` int(11) DEFAULT 0,
  `TYPE_BASED` int(11) DEFAULT 0,
  `REPORT_GROUP` int(11) DEFAULT 0,
  `TAX_SVC_DEFAULT` int(10) NOT NULL DEFAULT 0,
  `PERSENTASE_DISTRIBUTION_PURCHASE_ORDER` double DEFAULT 0,
  `COMPANY_ID` bigint(20) DEFAULT 0,
  `PRICE_TYPE_ID` bigint(20) DEFAULT NULL COMMENT 'penentuan price type yang dipakai, untuk aplikasi outlet',
  `STANDART_RATE_ID` bigint(20) DEFAULT NULL,
  `LOCATION_USED_TABLE` int(1) DEFAULT 0 COMMENT '0=not use, 1 = use table',
  `SISTEM_ADDRESS_HISTORY_OUTLET` varchar(200) DEFAULT NULL,
  `ACCOUNTING_EMAIL` varchar(100) DEFAULT NULL,
  `LOCATION_IP` varchar(20) DEFAULT NULL,
  `COLOR_LOCATION` varchar(30) DEFAULT NULL,
  `SUB_REGENCY_ID` bigint(20) DEFAULT 0,
  `LAST_UPDATE` timestamp NULL DEFAULT current_timestamp(),
  `NPWPD` varchar(100) DEFAULT '',
  `STATUS` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pos_category`
--

CREATE TABLE `pos_category` (
  `CATEGORY_ID` bigint(20) NOT NULL DEFAULT 0,
  `NAME` varchar(50) DEFAULT NULL,
  `CODE` varchar(15) DEFAULT NULL,
  `POINT_PRICE` double NOT NULL DEFAULT 0,
  `CATEGORY` int(4) DEFAULT 0,
  `TYPE_CATEGORY` int(2) DEFAULT 0 COMMENT '0=item, 1=item sales',
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  `LOCATION_ID` bigint(20) DEFAULT NULL,
  `CAT_PARENT_ID` bigint(20) DEFAULT NULL,
  `STATUS` int(2) DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pos_discount_mapping`
--

CREATE TABLE `pos_discount_mapping` (
  `DISCOUNT_TYPE_ID` bigint(20) NOT NULL DEFAULT 0,
  `MATERIAL_ID` bigint(20) NOT NULL DEFAULT 0,
  `CURRENCY_TYPE_ID` bigint(20) NOT NULL DEFAULT 0,
  `DISCOUNT_PCT` decimal(10,2) DEFAULT NULL,
  `DISCOUNT_VALUE` decimal(10,2) DEFAULT NULL,
  `UPDATE_DATE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pos_discount_qty_mapping`
--

CREATE TABLE `pos_discount_qty_mapping` (
  `DISCOUNT_TYPE_ID` bigint(20) NOT NULL DEFAULT 0,
  `CURRENCY_TYPE_ID` bigint(20) NOT NULL,
  `LOCATION_ID` bigint(20) UNSIGNED NOT NULL,
  `MATERIAL_ID` bigint(20) UNSIGNED NOT NULL,
  `START_QTY` decimal(10,2) NOT NULL DEFAULT 0.00,
  `TO_QTY` decimal(10,2) NOT NULL DEFAULT 0.00,
  `DISCOUNT_VALUE` decimal(10,2) DEFAULT NULL,
  `DISCOUNT_TYPE` int(10) UNSIGNED DEFAULT NULL,
  `UPDATE_DATE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pos_price_type_mapping`
--

CREATE TABLE `pos_price_type_mapping` (
  `PRICE_TYPE_ID` bigint(20) NOT NULL DEFAULT 0,
  `MATERIAL_ID` bigint(20) NOT NULL DEFAULT 0,
  `STANDART_RATE_ID` bigint(20) NOT NULL DEFAULT 0,
  `PRICE` decimal(10,2) DEFAULT NULL,
  `UPDATE_DATE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pos_stock_opname_item`
--

CREATE TABLE `pos_stock_opname_item` (
  `STOCK_OPNAME_ITEM_ID` bigint(20) NOT NULL DEFAULT 0,
  `STOCK_OPNAME_ID` bigint(20) DEFAULT NULL,
  `MATERIAL_ID` bigint(20) DEFAULT NULL,
  `UNIT_ID` bigint(20) DEFAULT NULL,
  `QTY_OPNAME` double DEFAULT NULL,
  `QTY_SOLD` double DEFAULT NULL,
  `QTY_SYSTEM` double DEFAULT NULL,
  `COST` double DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  `STOCK_OPNAME_COUNTER` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pos_unit`
--

CREATE TABLE `pos_unit` (
  `UNIT_ID` bigint(20) NOT NULL DEFAULT 0,
  `CODE` varchar(20) NOT NULL DEFAULT '',
  `NAME` varchar(20) NOT NULL DEFAULT '',
  `BASE_UNIT_ID` bigint(20) NOT NULL DEFAULT 0,
  `QTY_PER_BASE_UNIT` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `price_type`
--

CREATE TABLE `price_type` (
  `PRICE_TYPE_ID` bigint(20) NOT NULL DEFAULT 0,
  `CODE` varchar(20) DEFAULT NULL,
  `PINDEX` int(11) NOT NULL DEFAULT 0,
  `NAME` varchar(64) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `cash_cashier`
--
ALTER TABLE `cash_cashier`
  ADD PRIMARY KEY (`CASH_CASHIER_ID`),
  ADD KEY `Idx_new` (`CASH_MASTER_ID`,`SHIFT_ID`,`CASH_CASHIER_ID`),
  ADD KEY `idx_CASH_CASHIER_ID` (`CASH_CASHIER_ID`,`SHIFT_ID`);

--
-- Indeks untuk tabel `cash_closing`
--
ALTER TABLE `cash_closing`
  ADD PRIMARY KEY (`CASH_CLOSING_ID`);

--
-- Indeks untuk tabel `cash_master`
--
ALTER TABLE `cash_master`
  ADD PRIMARY KEY (`CASH_MASTER_ID`),
  ADD UNIQUE KEY `XPKCASH_MASTER` (`CASH_MASTER_ID`),
  ADD KEY `idx_new` (`CASHIER_NUMBER`,`LOCATION_ID`);

--
-- Indeks untuk tabel `cash_return_payment`
--
ALTER TABLE `cash_return_payment`
  ADD PRIMARY KEY (`RETURN_ID`),
  ADD KEY `UNIQUE_IDX_BILL` (`CASH_BILL_MAIN_ID`);

--
-- Indeks untuk tabel `contact_list`
--
ALTER TABLE `contact_list`
  ADD PRIMARY KEY (`CONTACT_ID`),
  ADD UNIQUE KEY `Index_unique_name` (`PERSON_NAME`,`PERSON_LASTNAME`,`MOTHER_NAME`,`COMP_NAME`,`CONTACT_CODE`),
  ADD KEY `Index_contact_name1` (`SALUTATION`,`PERSON_NAME`,`PERSON_LASTNAME`);

--
-- Indeks untuk tabel `discount_type`
--
ALTER TABLE `discount_type`
  ADD PRIMARY KEY (`DISCOUNT_TYPE_ID`),
  ADD UNIQUE KEY `XPKPOS_DISCOUNT_TYPE` (`DISCOUNT_TYPE_ID`);

--
-- Indeks untuk tabel `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`LOCATION_ID`),
  ADD UNIQUE KEY `XPKLOCATION` (`LOCATION_ID`),
  ADD UNIQUE KEY `CODE` (`CODE`);

--
-- Indeks untuk tabel `pos_category`
--
ALTER TABLE `pos_category`
  ADD PRIMARY KEY (`CATEGORY_ID`),
  ADD UNIQUE KEY `XPKCATEGORY` (`CATEGORY_ID`),
  ADD UNIQUE KEY `CODE` (`CODE`);

--
-- Indeks untuk tabel `pos_discount_mapping`
--
ALTER TABLE `pos_discount_mapping`
  ADD PRIMARY KEY (`DISCOUNT_TYPE_ID`,`MATERIAL_ID`,`CURRENCY_TYPE_ID`),
  ADD UNIQUE KEY `XPKPOS_DISCOUNT_MAPPING` (`DISCOUNT_TYPE_ID`,`MATERIAL_ID`,`CURRENCY_TYPE_ID`);

--
-- Indeks untuk tabel `pos_discount_qty_mapping`
--
ALTER TABLE `pos_discount_qty_mapping`
  ADD PRIMARY KEY (`DISCOUNT_TYPE_ID`,`CURRENCY_TYPE_ID`,`LOCATION_ID`,`MATERIAL_ID`,`START_QTY`,`TO_QTY`),
  ADD UNIQUE KEY `UNIQUE_DISC_QTY` (`CURRENCY_TYPE_ID`,`LOCATION_ID`,`MATERIAL_ID`,`START_QTY`,`TO_QTY`,`DISCOUNT_TYPE_ID`);

--
-- Indeks untuk tabel `pos_price_type_mapping`
--
ALTER TABLE `pos_price_type_mapping`
  ADD PRIMARY KEY (`PRICE_TYPE_ID`,`MATERIAL_ID`,`STANDART_RATE_ID`),
  ADD UNIQUE KEY `XPKPOS_PRICE_TYPE_MAPPING` (`PRICE_TYPE_ID`,`MATERIAL_ID`,`STANDART_RATE_ID`);

--
-- Indeks untuk tabel `pos_stock_opname_item`
--
ALTER TABLE `pos_stock_opname_item`
  ADD PRIMARY KEY (`STOCK_OPNAME_ITEM_ID`),
  ADD KEY `IDX_OPNITEM_MAT_ID` (`MATERIAL_ID`),
  ADD KEY `Index_unix_opname` (`STOCK_OPNAME_ID`,`MATERIAL_ID`);

--
-- Indeks untuk tabel `pos_unit`
--
ALTER TABLE `pos_unit`
  ADD PRIMARY KEY (`UNIT_ID`),
  ADD UNIQUE KEY `XPKUNIT` (`UNIT_ID`);

--
-- Indeks untuk tabel `price_type`
--
ALTER TABLE `price_type`
  ADD PRIMARY KEY (`PRICE_TYPE_ID`),
  ADD UNIQUE KEY `XPKPOS_PRICE_TYPE` (`PRICE_TYPE_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
