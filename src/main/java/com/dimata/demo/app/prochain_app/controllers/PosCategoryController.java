package com.dimata.demo.app.prochain_app.controllers;

import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.PosCategoryForm;
import com.dimata.demo.app.prochain_app.models.table.PosCategory;
import com.dimata.demo.app.prochain_app.services.api.PosCategoryApi;

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
public class PosCategoryController {
    @Autowired
    private PosCategoryApi posCategoryApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/pos_category", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PosCategory> maintainerAddPosCatagory(@RequestBody PosCategoryForm form) {
        return posCategoryApi.createPosCategory(form);
    }

    @GetMapping(path = BASE_URL + "/pos_category")
    public Flux<PosCategory> maintainerGetAllPosCatagory(CommonParam param) {
        return posCategoryApi.getAllPosCatagory(param);
    }

    @GetMapping(path = BASE_URL + "/pos_category/{CATEGORY_ID}")
    public Mono<PosCategory> maintainerGetPosCatagory(@PathVariable("CATEGORY_ID") Long CATEGORY_ID) {
        return posCategoryApi.getPosCatagory(CATEGORY_ID);
    }

    @PutMapping(path = BASE_URL + "/pos_category/{CATEGORY_ID}")
    public Mono<PosCategory> maintainerUpdatePosCatagory(@PathVariable("CATEGORY_ID") long CATEGORY_ID, @RequestBody PosCategoryForm form) {
        return posCategoryApi.updatePosCatagory(CATEGORY_ID, form);
    }

    
}
