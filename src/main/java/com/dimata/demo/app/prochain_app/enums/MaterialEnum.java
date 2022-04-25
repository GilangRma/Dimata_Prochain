package com.dimata.demo.app.prochain_app.enums;

import lombok.Getter;

public enum MaterialEnum {
    GENERAL(0),
    EMAS(1),
    BERLIAN(2),
    EMASLANTAKAN(3);

    @Getter
    private final int code;

    public static MaterialEnum getMaterial(Integer code){
        switch (code) {
            case 0:
                return EMAS;
            case 1:
                return BERLIAN;
            case 2:
                return EMASLANTAKAN;
            default:
                return GENERAL;
        }
    }

    MaterialEnum(int code) {
        this.code = code;
    }

    public String parseMaterial(MaterialEnum material){
        if (material == MaterialEnum.EMAS) {
            return "EMAS";
        }
        return "BERLIAN";
    }

    public String parseMaterila(int code) {
        if (getMaterial(code) == MaterialEnum.EMAS) {
            return "EMAS";
        } else if (getMaterial(code) == MaterialEnum.BERLIAN) {
            return "BERLIAN";
        } else if (getMaterial(code) == MaterialEnum.EMASLANTAKAN){
            return "EMAS LANTAKAN";
        }
        return "GENERAL";
    }

    public Object getMaterial() {
        return null;
    }
}
