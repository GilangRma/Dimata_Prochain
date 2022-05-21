package com.dimata.demo.app.prochain_app.core.util.jackson;

import java.time.LocalTime;

import com.dimata.demo.app.prochain_app.core.util.FormatUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

public class LocalTimeDeserialize extends StdConverter<String, LocalTime>{

    @Override
    public LocalTime convert(String value) {

        return FormatUtil.convertTimeToLocalTime(value, "HH:mm:ss");
    }

}
