package com.dimata.demo.app.prochain_app.enums;

import lombok.Getter;

public enum CatagoryEnum {
    deactivated(0),
    Active(1),
    Undefined(-1);

    @Getter
    private final int code;

    public static CatagoryEnum getCatagory(Integer code){
        switch (code) {
            case 0:
                return deactivated;
            case 1:
                return Active;
            default:
                return Undefined;
        }
    }

    CatagoryEnum(int code) {
        this.code = code;
    }

    public String parseCatagory(CatagoryEnum Catagory){
        if (Catagory == CatagoryEnum.deactivated) {
            return "deactivated";
        }
        return "Active";
    }

    public String parseCatagory(int code) {
        if (getCatagory(code) == CatagoryEnum.deactivated) {
            return "deactivated";
        } else if (getCatagory(code) == CatagoryEnum.Active) {
            return "Active";
        }
        return "Undefined";
    }

    public Object getCatagory() {
        return null;
    }
}
