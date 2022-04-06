package com.dimata.demo.app.prochain_app.controllers;


import com.dimata.demo.app.prochain_app.core.search.CommonParam;
import com.dimata.demo.app.prochain_app.forms.LocationForm;
import com.dimata.demo.app.prochain_app.models.table.Location;
import com.dimata.demo.app.prochain_app.services.api.LocationApi;

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
public class LocationController {

    @Autowired
    private LocationApi locationApi;

    private static final String BASE_URL = "/maintainer/v1";

    @PostMapping(path = BASE_URL + "/location", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Location> maintainerAddLocation(@RequestBody LocationForm form) {
       
        return locationApi.createLocation(form);
    }

    @GetMapping(path = BASE_URL + "/location")
    public Flux<Location> maintainerGetAllLocation(CommonParam param) {
       
        return locationApi.getAllLocation(param);
    }

    @GetMapping(path = BASE_URL + "/location/{LOCATION_ID}")
    public Mono<Location> maintainerGetLocation(@PathVariable("LOCATION_ID") Long LOCATION_ID) {
       
        return locationApi.getLocation(LOCATION_ID);
    }

    @PutMapping(path = BASE_URL + "/location/{LOCATION_ID}")
    public Mono<Location> maintainerUpdateLocation(@PathVariable("LOCATION_ID") long LOCATION_ID, @RequestBody LocationForm form) {
        return locationApi.updateLocation(LOCATION_ID, form);
    }


    
}
