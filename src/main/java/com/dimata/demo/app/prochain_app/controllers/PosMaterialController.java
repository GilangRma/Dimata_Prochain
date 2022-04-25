package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.PosMaterialForm;
import com.dimata.demo.app.prochain_app.forms.relation.PosMaterialRelation;
import com.dimata.demo.app.prochain_app.models.table.Location;
import com.dimata.demo.app.prochain_app.models.table.PosMaterial;
import com.dimata.demo.app.prochain_app.services.api.PosMaterialApi;

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
public class PosMaterialController {
    @Autowired
    private PosMaterialApi posMaterialApi;
    

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/pos_material", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PosMaterial> maintainerAddPosMaterial(@RequestBody PosMaterialForm form) {
        return posMaterialApi.createPosMaterial(form);
    }

    @GetMapping(path = BASE_URL + "/pos_material")
    public Flux<PosMaterial> maintainerGetAllPosMaterial(CommonParam param) {
        return posMaterialApi.getAllPosMaterial(param);
    }

    @GetMapping(path = BASE_URL + "/pos_material/{MATERIAL_ID}")
    public Mono<PosMaterial> maintainerGetPosMaterial(@PathVariable("MATERIAL_ID") Long MATERIAL_ID) {
        return posMaterialApi.getPosMaterial(MATERIAL_ID);
    }

    @PutMapping(path = BASE_URL + "/pos_material/{MATERIAL_ID}")
    public Mono<PosMaterial> maintainerUpdatePosMaterial(@PathVariable("MATERIAL_ID") long MATERIAL_ID, @RequestBody PosMaterialForm form) {
        return posMaterialApi.updatePosMaterial(MATERIAL_ID, form);
    }
    @PostMapping(path = BASE_URL +"/pos_material/location")
    public Mono<Location> maintainerLocationId(@RequestBody PosMaterialRelation form) {
        return posMaterialApi.checkAvailableData(form)
            .flatMap(f -> posMaterialApi.getDataLocation(f.getId()));
    }
}
