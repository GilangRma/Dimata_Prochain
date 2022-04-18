package com.dimata.demo.app.prochain_app.enums;

import lombok.Getter;

public enum TypeCatagoryEnum {
    Barang(0),
    Jasa(1),
    NotUse(-1);

    @Getter
    private final int code;

    public static TypeCatagoryEnum getType(Integer code){
        switch (code) {
            case 0:
                return Barang;
            case 1:
                return Jasa;
            default:
                return NotUse;
        }
    }

    TypeCatagoryEnum(int code) {
        this.code = code;
    }

    public String parseType(TypeCatagoryEnum Catagory){
        if (Catagory == TypeCatagoryEnum.Barang) {
            return "Barang";
        }
        return "Jasa";
    }

    public String parseCatagory(int code) {
        if (getType(code) == TypeCatagoryEnum.Barang) {
            return "Barang";
        } else if (getType(code) == TypeCatagoryEnum.Jasa) {
            return "Jasa";
        }
        return "Not Use";
    }
}
