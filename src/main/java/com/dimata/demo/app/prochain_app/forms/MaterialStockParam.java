package com.dimata.demo.app.prochain_app.forms;

import java.time.LocalDateTime;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.core.util.jackson.TimeSerialize;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class MaterialStockParam extends CommonParam{
    private Long locationId;
    @JsonSerialize(converter = TimeSerialize.class)
    private LocalDateTime from;
    @JsonSerialize(converter = TimeSerialize.class)
    private LocalDateTime end;
    private Double value;
    private Integer stockStatus;
}
