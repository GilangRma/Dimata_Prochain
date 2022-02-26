package com.dimata.demo.app.prochain_app.enums;

import lombok.Getter;

public enum Status {
    LAKI_LAKI(0),
    PEREMPUAN(1),
    UNDEFINED(-1);

    @Getter
    private final int code;

    public static Status getStatus(Integer code){
        switch (code) {
            case 0:
                return LAKI_LAKI;
            case 1:
                return PEREMPUAN;
            default:
                return UNDEFINED;
        }
    }

    Status(int code) {
        this.code = code;
    }

    public String parseStatus(Status status){
        if (status == Status.LAKI_LAKI) {
            return "Laki-Laki";
        }
        return "Perempuan";
    }

    public String parseStatus(int code) {
        if (getStatus(code) == Status.LAKI_LAKI) {
            return "Laki-Laki";
        } else if (getStatus(code) == Status.PEREMPUAN) {
            return "Perempuan";
        }
        return "Undefined";
    }
}
