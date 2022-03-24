package com.dimata.demo.app.prochain_app.enums;

import lombok.Getter;

public enum CatagoryEnum {
    ADMIN(0),
    CONTRACT(1),
    DIRECTOR(2),
    STAFF(-1);

    @Getter
    private final int code;

    public static CatagoryEnum getCatagory(Integer code){
        switch (code) {
            case 0:
                return ADMIN;
            case 1:
                return CONTRACT;
            case 2:
                return DIRECTOR;
            default:
                return STAFF;
        }
    }

    CatagoryEnum(int code) {
        this.code = code;
    }

    public String parseCatagory(CatagoryEnum Catagory){
        if (Catagory == CatagoryEnum.ADMIN) {
            return "ADMIN";
        }
        return "CONTRACT";
    }

    public String parseCatagory(int code) {
        if (getCatagory(code) == CatagoryEnum.ADMIN) {
            return "ADMIN";
        } else if (getCatagory(code) == CatagoryEnum.CONTRACT) {
            return "CONTRACT";
        } else if (getCatagory(code) == CatagoryEnum.DIRECTOR){
            return "DIRECTOR";
        }
        return "STAFF";
    }

    public Object getCatagory() {
        return null;
    }
}
