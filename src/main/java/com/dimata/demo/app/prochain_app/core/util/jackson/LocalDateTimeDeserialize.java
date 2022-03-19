package com.dimata.demo.app.prochain_app.core.util.jackson;

import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.util.FormatUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

public class LocalDateTimeDeserialize extends StdConverter<String, LocalDateTime>{

    @Override
    public LocalDateTime convert(String value) {
    
        return FormatUtil.convertDateToLocalDateTime(value, "dd-MM-yyyy ");
    }
    
}
