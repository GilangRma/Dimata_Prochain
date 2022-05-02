package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.PosMaterialStockCode;
import com.dimata.demo.app.prochain_app.services.dbHandler.PosMaterialStockCodeDbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.AccessLevel;

@Component
public class PosMaterialStockCodeCrude {
    @Autowired
    private PosMaterialStockCodeDbHandler posMaterialStockCodeDbHandler;

    public static Option initOption(PosMaterialStockCode record) {
        return new Option(record);
    }

    public Mono<PosMaterialStockCode > create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<PosMaterialStockCode> createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<PosMaterialStockCode > savedRecord = posMaterialStockCodeDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<PosMaterialStockCode > updateRecord(Option option) {
        return posMaterialStockCodeDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final PosMaterialStockCode record;
        
        public Option(PosMaterialStockCode record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
}
