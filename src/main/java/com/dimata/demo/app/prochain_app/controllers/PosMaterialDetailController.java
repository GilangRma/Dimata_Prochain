package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.PosMaterialDetailForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosMaterialDetailRelation;
import com.dimata.demo.app.prochain_app.models.table.PosMaterial;
import com.dimata.demo.app.prochain_app.models.table.PosMaterialDetail;
import com.dimata.demo.app.prochain_app.services.api.PosMaterialDetailApi;

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
public class PosMaterialDetailController {
    @Autowired
    private PosMaterialDetailApi posMaterialDetailApi;
    

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/pos_material_detail", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PosMaterialDetail> maintainerAddPosMaterialDetail(@RequestBody PosMaterialDetailForm form) {
        return posMaterialDetailApi.createPosMaterialDetail(form);
    }

    @GetMapping(path = BASE_URL + "/pos_material_detail")
    public Flux<PosMaterialDetail> maintainerGetAllPosMaterialDetail(CommonParam param) {
        return posMaterialDetailApi.getAllPosMaterialDetail(param);
    }

    @GetMapping(path = BASE_URL + "/pos_material_detail/{MATERIAL_DETAIL_ID}")
    public Mono<PosMaterialDetail> maintainerGetPosMaterialDetail(@PathVariable("MATERIAL_DETAIL_ID") Long MATERIAL_DETAIL_ID) {
        return posMaterialDetailApi.getPosMaterialDetail(MATERIAL_DETAIL_ID);
    }

    @PutMapping(path = BASE_URL + "/pos_material_detail/{MATERIAL_DETAIL_ID}")
    public Mono<PosMaterialDetail> maintainerUpdatePosMaterialDetail(@PathVariable("MATERIAL_DETAIL_ID") long MATERIAL_DETAIL_ID, @RequestBody PosMaterialDetailForm form) {
        return posMaterialDetailApi.updatePosMaterialDetail(MATERIAL_DETAIL_ID, form);
    }
    @PostMapping(path = BASE_URL +"/pos_material_detail/material")
    public Mono<PosMaterial> maintainerMaterialId(@RequestBody PosMaterialDetailRelation form) {
        return posMaterialDetailApi.checkAvailableData(form)
            .flatMap(f -> posMaterialDetailApi.getDataByMaterial(f.getId()));
    }
}
