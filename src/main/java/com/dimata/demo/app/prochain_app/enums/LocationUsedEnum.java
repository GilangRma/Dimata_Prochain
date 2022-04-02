package com.dimata.demo.app.prochain_app.enums;

import lombok.Getter;

public enum LocationUsedEnum {
    NOTUSE(0),
    USETABLE(1),
    UNDEFINED(-1);

    @Getter
    private final int code;

    public static LocationUsedEnum getLocation(Integer code){
        switch (code) {
            case 0:
                return NOTUSE;
            case 1:
                return USETABLE;
            default:
                return UNDEFINED;
        }
    }

    LocationUsedEnum(int code) {
        this.code = code;
    }

    public String parsePayment(LocationUsedEnum location){
        if (location == LocationUsedEnum.NOTUSE) {
            return "NOTUSE";
        }
        return "USETABLE";
    }

    public String parseStatus(int code) {
        if (getLocation(code) == LocationUsedEnum.NOTUSE) {
            return "NOTUSE";
        } else if (getLocation(code) == LocationUsedEnum.USETABLE) {
            return "USETABLE";
        }
        return "Undefined";
    }
    
}
