package com.dimata.demo.app.prochain_app.core.util.jackson;

import java.time.LocalTime;

import com.dimata.demo.app.prochain_app.core.util.FormatUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

public class LocalTimeSerialize extends  StdConverter<LocalTime, String>{

    @Override
    public String convert(LocalTime time) {
        return FormatUtil.convertTimeToString(time);
    }

}