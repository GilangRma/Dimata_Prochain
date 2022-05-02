package com.dimata.demo.app.prochain_app.enums;

import lombok.Getter;

public enum ContactTypeEnum {

    OWNER(0),
    SEMPLOY(1),
    SUPPLIER(2),
    NOTUSE(-1);

    @Getter
    private final int code;

    public static ContactTypeEnum getContactType(Integer code){
        switch (code) {
            case 0:
                return OWNER;
            case 1:
                return SEMPLOY;
            case 2:
                return SUPPLIER;
            default:
                return NOTUSE;
        }
    }

    ContactTypeEnum(int code) {
        this.code = code;
    }

    public String parseContact(ContactTypeEnum contact){
        if (contact == ContactTypeEnum.OWNER) {
            return "OWNER";
        }
        return "SEMPLOY";
    }

    public String parseContact(int code) {
        if (getContactType(code) == ContactTypeEnum.OWNER) {
            return "OWNER";
        } else if (getContactType(code) == ContactTypeEnum.SEMPLOY) {
            return "SEMPLOY";
        } else if (getContactType(code) == ContactTypeEnum.SUPPLIER){
            return "SUPPLIER";
        }
        return "NOTUSE";
    }
    
}
