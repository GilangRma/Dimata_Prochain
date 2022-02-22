package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.PosUnitForm;
import com.dimata.demo.app.prochain_app.models.table.PosUnit;
import com.dimata.demo.app.prochain_app.services.api.PosUnitApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PosUnitController {
    @Autowired
    private PosUnitApi posUnitApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/pos_unit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PosUnit> maintainerAddPosUnit(@RequestBody PosUnitForm form) {
        return posUnitApi.createPosUnit(form);
    }

    @GetMapping(path = BASE_URL + "/pos_unit")
    public Flux<PosUnit> maintainerGetAllPosUnit(CommonParam param) {
        return posUnitApi.getAllPosUnit(param);
    }

    @GetMapping(path = BASE_URL + "/pos_unit/{UNIT_ID}")
    public Mono<PosUnit> maintainerGetPosUnit(@PathVariable("UNIT_ID") Long UNIT_ID) {
        return posUnitApi.getPosUnit(UNIT_ID);
    }

    @PutMapping(path = BASE_URL + "/pos_unit/{UNIT_ID}")
    public Mono<PosUnit> maintainerUpdatePosUnit(@PathVariable("UNIT_ID") long UNIT_ID, @RequestBody PosUnitForm form) {
        return posUnitApi.updatePosUnit(UNIT_ID, form);
    }
}
