package com.dimata.demo.app.prochain_app.core.search;

public interface WhereQueryStep {
    String result();
    WhereQueryStep and(WhereQueryStep step);
    WhereQueryStep or(WhereQueryStep step);
    WhereQueryStep not(WhereQueryStep step);
}
