package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.Discount_Type;
import com.dimata.demo.app.prochain_app.services.dbHandler.Discount_TypeDbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class Discount_TypeCrude {

    @Autowired
    private Discount_TypeDbHandler discount_TypeDbHandler;

    public static Option initOption(Discount_Type record) {
        return new Option(record);
    }

    public Mono<Discount_Type> create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<Discount_Type> createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<Discount_Type> savedRecord = discount_TypeDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<Discount_Type> updateRecord(Option option) {
        return discount_TypeDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    
    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final Discount_Type record;
        
        public Option(Discount_Type record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }

    
}
