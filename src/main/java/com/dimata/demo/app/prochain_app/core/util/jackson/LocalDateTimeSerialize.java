package com.dimata.demo.app.prochain_app.core.util.jackson;

import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.util.FormatUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

public class LocalDateTimeSerialize extends  StdConverter<LocalDateTime, String>{

    @Override
    public String convert(LocalDateTime dateTime) {
        return FormatUtil.convertLocalDateTimeToString(dateTime);
    }
    
}
