package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.PosMaterialStock;
import com.dimata.demo.app.prochain_app.services.dbHandler.PosMaterialStockDbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PosMaterialStockCrude {

    @Autowired
    private PosMaterialStockDbHandler posMaterialStockDbHandler;

    public static Option initOption(PosMaterialStock record) {
        return new Option(record);
    }

    public Mono<PosMaterialStock> create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<PosMaterialStock> createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<PosMaterialStock> savedRecord = posMaterialStockDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<PosMaterialStock> updateRecord(Option option) {
        return posMaterialStockDbHandler.update(option.getRecord(), option.getRecord().getId());
    }


    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final PosMaterialStock record;
        
        public Option(PosMaterialStock record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
    
}
