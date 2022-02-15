package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.Cash_Closing;
import com.dimata.demo.app.prochain_app.services.dbHandler.Cash_ClosingDbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class Cash_ClosingCrud {
    @Autowired
    private Cash_ClosingDbHandler cash_ClosingDbHandler;

    public static Option initOption(Cash_Closing record) {
        return new Option(record);
    }

    public Mono<Cash_Closing> create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<Cash_Closing> createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<Cash_Closing> savedRecord = cash_ClosingDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<Cash_Closing> updateRecord(Option option) {
        return cash_ClosingDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final Cash_Closing record;
        
        public Option(Cash_Closing record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }

}
