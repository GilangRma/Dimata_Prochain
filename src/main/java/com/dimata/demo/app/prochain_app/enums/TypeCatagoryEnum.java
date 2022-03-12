package com.dimata.demo.app.prochain_app.enums;

import lombok.Getter;

public enum TypeCatagoryEnum {
    Item(0),
    ItemSeles(1),
    Undefined(-1);

    @Getter
    private final int code;

    public static TypeCatagoryEnum getType(Integer code){
        switch (code) {
            case 0:
                return Item;
            case 1:
                return ItemSeles;
            default:
                return Undefined;
        }
    }

    TypeCatagoryEnum(int code) {
        this.code = code;
    }

    public String parseType(TypeCatagoryEnum Catagory){
        if (Catagory == TypeCatagoryEnum.Item) {
            return "Item";
        }
        return "ItemSeles";
    }

    public String parseCatagory(int code) {
        if (getType(code) == TypeCatagoryEnum.Item) {
            return "Item";
        } else if (getType(code) == TypeCatagoryEnum.ItemSeles) {
            return "ItemSeles";
        }
        return "Undefined";
    }
}
