package com.dimata.demo.app.prochain_app.enums;

import lombok.Getter;

public enum IncludePpnEnum {

    NO(0),
    YES(1),
    NOTUSE(-1);

    @Getter
    private final int code;

    public static IncludePpnEnum getInclude(Integer code){
        switch (code) {
            case 0:
                return NO;
            case 1:
                return YES;
            default:
                return NOTUSE;
                
        }
    }

    IncludePpnEnum(int code) {
        this.code = code;
    }

    public String parseInclude(IncludePpnEnum Ppn){
        if (Ppn == IncludePpnEnum.NO) {
            return "NO";
        }
        return "YES";
    }

    public String parseInclude(int code) {
        if (getInclude(code) == IncludePpnEnum.NO) {
            return "NO";
        } else if (getInclude(code) == IncludePpnEnum.YES) {
            return "YES";
        }
        return "NOTUSE";
    }

    public Object getInclude() {
        return null;
    }
    
}
