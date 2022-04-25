package com.dimata.demo.app.prochain_app.enums;

import lombok.Getter;

public enum ProccesStatusEnum {
    ACTIVE(0),
    NONACTIVE(1),
    TRANSFERED(2),
    EXPIRED(3);

    @Getter
    private final int code;

    public static ProccesStatusEnum getProcces(Integer code){
        switch (code) {
            case 0:
                return ACTIVE;
            case 1:
                return NONACTIVE;
            case 2:
                return TRANSFERED;
            default:
                return EXPIRED;
        }
    }

    ProccesStatusEnum(int code) {
        this.code = code;
    }

    public String parseProcces(ProccesStatusEnum Procces){
        if (Procces == ProccesStatusEnum.ACTIVE) {
            return "ACTIVE";
        }
        return "NONACTIVE";
    }

    public String parseMaterila(int code) {
        if (getProcces(code) == ProccesStatusEnum.ACTIVE) {
            return "ACTIVE";
        } else if (getProcces(code) == ProccesStatusEnum.NONACTIVE) {
            return "NONACTIVE";
        } else if (getProcces(code) == ProccesStatusEnum.TRANSFERED){
            return "TRANSFERED";
        }
        return "EXPIRED";
    }

    public Object getProcces() {
        return null;
    }
}
