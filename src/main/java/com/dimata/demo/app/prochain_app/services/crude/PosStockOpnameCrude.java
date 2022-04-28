package com.dimata.demo.app.prochain_app.services.crude;


import com.dimata.demo.app.prochain_app.models.table.PosStockOpname;
import com.dimata.demo.app.prochain_app.services.dbHandler.PosStockOpnameDbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PosStockOpnameCrude {
    
    @Autowired
    private PosStockOpnameDbHandler posStockOpnameDbHandler;

    public static Option initOption(PosStockOpname record) {
        return new Option(record);
    }

    public Mono<PosStockOpname> create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<PosStockOpname> createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<PosStockOpname> savedRecord = posStockOpnameDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<PosStockOpname> updateRecord(Option option) {
        return posStockOpnameDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final PosStockOpname record;
        
        public Option(PosStockOpname record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
}
