package com.dimata.demo.app.prochain_app.enums;

import lombok.Getter;

public enum OpnameItemTypeEnum {

    Final(0),
    Posted(1),
    Draft(2),
    Closed(-1);

    @Getter
    private final int code;

    public static OpnameItemTypeEnum getOpnameType(Integer code){
        switch (code) {
            case 0:
                return Final;
            case 1:
                return Posted;
            case 2:
                return Draft;
            default:
                return Closed;
        }
    }

    OpnameItemTypeEnum(int code) {
        this.code = code;
    }

    public String parseOpname(OpnameItemTypeEnum opnameType){
        if (opnameType == OpnameItemTypeEnum.Final) {
            return "Final";
        }
        return "Posted";
    }

    public String parseStatus(int code) {
        if (getOpnameType(code) == OpnameItemTypeEnum.Final) {
            return "Final";
        } else if (getOpnameType(code) == OpnameItemTypeEnum.Posted) {
            return "Posted";
        } else if (getOpnameType(code)== OpnameItemTypeEnum.Draft) {
            return "Draft";
        }
        return "Closed";
    }
    
}
