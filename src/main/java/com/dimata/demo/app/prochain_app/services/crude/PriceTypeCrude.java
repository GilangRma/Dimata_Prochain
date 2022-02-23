package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.PriceType;
import com.dimata.demo.app.prochain_app.services.dbHandler.PriceTypeDbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PriceTypeCrude {
    @Autowired
    private PriceTypeDbHandler priceTypeDbHandler;

    public static Option initOption(PriceType record) {
        return new Option(record);
    }

    public Mono<PriceType > create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<PriceType > createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<PriceType > savedRecord = priceTypeDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<PriceType > updateRecord(Option option) {
        return priceTypeDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final PriceType record;
        
        public Option(PriceType record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
}
