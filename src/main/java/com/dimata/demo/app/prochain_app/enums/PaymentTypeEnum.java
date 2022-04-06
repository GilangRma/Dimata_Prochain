package com.dimata.demo.app.prochain_app.enums;

import lombok.Getter;

public enum PaymentTypeEnum {
    CASH(0),
    CREDIT(1),
    UNDEFINED(-1);

    @Getter
    private final int code;

    public static PaymentTypeEnum getPayment(Integer code){
        switch (code) {
            case 0:
                return CASH;
            case 1:
                return CREDIT;
            default:
                return UNDEFINED;
        }
    }

    PaymentTypeEnum(int code) {
        this.code = code;
    }

    public String parsePayment(PaymentTypeEnum payment){
        if (payment == PaymentTypeEnum.CASH) {
            return "CASH";
        }
        return "CASH";
    }

    public String parseStatus(int code) {
        if (getPayment(code) == PaymentTypeEnum.CASH) {
            return "CASH";
        } else if (getPayment(code) == PaymentTypeEnum.CREDIT) {
            return "CREDIT";
        }
        return "Undefined";
    }
}
