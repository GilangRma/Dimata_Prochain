package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.DiscountType;
import com.dimata.demo.app.prochain_app.services.dbHandler.DiscountTypeDbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DiscountTypeCrude {

    @Autowired
    private DiscountTypeDbHandler discountTypeDbHandler;

    public static Option initOption(DiscountType record) {
        return new Option(record);
    }

    public Mono<DiscountType> create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<DiscountType> createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<DiscountType> savedRecord = discountTypeDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<DiscountType> updateRecord(Option option) {
        return discountTypeDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    
    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final DiscountType record;
        
        public Option(DiscountType record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }

    
}
