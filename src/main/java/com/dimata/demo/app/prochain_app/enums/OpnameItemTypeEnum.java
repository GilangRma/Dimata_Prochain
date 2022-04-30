package com.dimata.demo.app.prochain_app.enums;

import lombok.Getter;

public enum OpnameItemTypeEnum {

    EMAS(0),
    BERLIAN(1),
    EMAS_LANTAKAN(-1);

    @Getter
    private final int code;

    public static OpnameItemTypeEnum getOpnameType(Integer code){
        switch (code) {
            case 0:
                return EMAS;
            case 1:
                return BERLIAN;
            default:
                return EMAS_LANTAKAN;
        }
    }

    OpnameItemTypeEnum(int code) {
        this.code = code;
    }

    public String parseOpname(OpnameItemTypeEnum opnameType){
        if (opnameType == OpnameItemTypeEnum.EMAS) {
            return "EMAS";
        }
        return "BERLIAN";
    }

    public String parseStatus(int code) {
        if (getOpnameType(code) == OpnameItemTypeEnum.EMAS) {
            return "EMAS";
        } else if (getOpnameType(code) == OpnameItemTypeEnum.BERLIAN) {
            return "BERLIAN";
        }
        return "EMAS_LANTAKAN";
    }
    
}
