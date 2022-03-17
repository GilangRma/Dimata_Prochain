package com.dimata.demo.app.prochain_app.enums;

import lombok.Getter;

public enum PaymentType {
    CASH(0),
    CREDIT(1),
    UNDEFINED(-1);

    @Getter
    private final int code;

    public static PaymentType getPayment(Integer code){
        switch (code) {
            case 0:
                return CASH;
            case 1:
                return CREDIT;
            default:
                return UNDEFINED;
        }
    }

    PaymentType(int code) {
        this.code = code;
    }

    public String parsePayment(PaymentType payment){
        if (payment == PaymentType.CASH) {
            return "CASH";
        }
        return "CASH";
    }

    public String parseStatus(int code) {
        if (getPayment(code) == PaymentType.CASH) {
            return "CASH";
        } else if (getPayment(code) == PaymentType.CREDIT) {
            return "CREDIT";
        }
        return "Undefined";
    }
}
