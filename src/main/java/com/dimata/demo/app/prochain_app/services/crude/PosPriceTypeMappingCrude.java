package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.PosPriceTypeMapping;
import com.dimata.demo.app.prochain_app.services.dbHandler.PosPriceTypeMappingDbHandler;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PosPriceTypeMappingCrude {
    @Autowired
    private PosPriceTypeMappingDbHandler posPriceTypeMappingDbHandler;

    public static Option initOption(PosPriceTypeMapping record) {
        return new Option(record);
    }

    public Mono<PosPriceTypeMapping > create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<PosPriceTypeMapping > createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<PosPriceTypeMapping > savedRecord = posPriceTypeMappingDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<PosPriceTypeMapping > updateRecord(Option option) {
        return posPriceTypeMappingDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final PosPriceTypeMapping record;
        
        public Option(PosPriceTypeMapping record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
}
