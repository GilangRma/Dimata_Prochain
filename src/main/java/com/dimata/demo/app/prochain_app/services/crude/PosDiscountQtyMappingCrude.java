package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.PosDiscountQtyMapping;
import com.dimata.demo.app.prochain_app.services.dbHandler.PosDIscountQtyMappingDbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PosDiscountQtyMappingCrude {
    @Autowired
    private PosDIscountQtyMappingDbHandler posDIscountQtyMappingDbHandler;

    public static Option initOption(PosDiscountQtyMapping record) {
        return new Option(record);
    }

    public Mono<PosDiscountQtyMapping > create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<PosDiscountQtyMapping > createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<PosDiscountQtyMapping > savedRecord = posDIscountQtyMappingDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<PosDiscountQtyMapping > updateRecord(Option option) {
        return posDIscountQtyMappingDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final PosDiscountQtyMapping record;
        
        public Option(PosDiscountQtyMapping record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
}
