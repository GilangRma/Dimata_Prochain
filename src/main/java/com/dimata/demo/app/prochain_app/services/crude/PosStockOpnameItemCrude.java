package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.PosStockOpnameItem;
import com.dimata.demo.app.prochain_app.services.dbHandler.PosStockOpnameItemDbhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PosStockOpnameItemCrude {
    @Autowired
    private PosStockOpnameItemDbhandler posStockOpnameItemDbhandler;

    public static Option initOption(PosStockOpnameItem record) {
        return new Option(record);
    }

    public Mono<PosStockOpnameItem > create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<PosStockOpnameItem > createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<PosStockOpnameItem > savedRecord = posStockOpnameItemDbhandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<PosStockOpnameItem > updateRecord(Option option) {
        return posStockOpnameItemDbhandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final PosStockOpnameItem record;
        
        public Option(PosStockOpnameItem record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
}
