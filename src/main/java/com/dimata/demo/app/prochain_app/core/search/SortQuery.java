package com.dimata.demo.app.prochain_app.core.search;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SortQuery {
    private String sortBy;
    private boolean asc;
}
