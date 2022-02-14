package com.dimata.demo.app.prochain_app.core.search;

public interface GroupBySpec {
    GroupBySpec merge(GroupBySpec groupBy);
    GroupBySpec append(String group);
    String getQuery();
}
