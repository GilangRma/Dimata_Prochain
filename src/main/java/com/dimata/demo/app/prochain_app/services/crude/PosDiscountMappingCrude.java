package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.PosDiscountMapping;
import com.dimata.demo.app.prochain_app.services.dbHandler.PosDiscountMappingDbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PosDiscountMappingCrude {
    @Autowired
    private PosDiscountMappingDbHandler posDiscountMappingDbHandler;

    public static Option initOption(PosDiscountMapping record) {
        return new Option(record);
    }

    public Mono<PosDiscountMapping > create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<PosDiscountMapping > createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<PosDiscountMapping > savedRecord = posDiscountMappingDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<PosDiscountMapping > updateRecord(Option option) {
        return posDiscountMappingDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final PosDiscountMapping record;
        
        public Option(PosDiscountMapping record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
}
