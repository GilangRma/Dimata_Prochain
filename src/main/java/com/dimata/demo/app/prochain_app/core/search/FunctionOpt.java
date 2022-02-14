package com.dimata.demo.app.prochain_app.core.search;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FunctionOpt {
    String function;

    @Override
    public String toString(){
        return function;
    }
}
