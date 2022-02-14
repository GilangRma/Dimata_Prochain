package com.dimata.demo.app.prochain_app.core.search;

public interface JoinOperationStep {
    JoinQueryStep on(WhereQueryStep operationStep);
}
