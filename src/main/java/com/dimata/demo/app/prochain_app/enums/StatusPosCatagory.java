package com.dimata.demo.app.prochain_app.enums;

import lombok.Getter;

public enum StatusPosCatagory {
    deactivated(0),
    Active(1),
    Undefined(-1);

    @Getter
    private final int code;

    public static StatusPosCatagory getStatus(Integer code){
        switch (code) {
            case 0:
                return deactivated;
            case 1:
                return Active;
            default:
                return Undefined;
        }
    }

    StatusPosCatagory(int code) {
        this.code = code;
    }

    public String parseStatus(StatusPosCatagory Status){
        if (Status == StatusPosCatagory.deactivated) {
            return "deactivated";
        }
        return "Active";
    }

    public String parseStatus(int code) {
        if (getStatus(code) == StatusPosCatagory.deactivated) {
            return "deactivated";
        } else if (getStatus(code) == StatusPosCatagory.Active) {
            return "Active";
        }
        return "Undefined";
    }
}
