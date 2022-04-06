package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.Location;
import com.dimata.demo.app.prochain_app.services.dbHandler.LocationDbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class LocationCrude {

    @Autowired
    private LocationDbHandler locationDbHandler;

    public static Option initOption(Location record) {
        return new Option(record);
    }

    public Mono<Location> create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<Location> createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<Location> savedRecord = locationDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<Location> updateRecord(Option option) {
        return locationDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final Location record;
        
        public Option(Location record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
    
}
