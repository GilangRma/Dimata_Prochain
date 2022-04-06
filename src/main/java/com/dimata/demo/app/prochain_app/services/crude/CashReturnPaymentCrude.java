package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.CashReturnPayment;
import com.dimata.demo.app.prochain_app.services.dbHandler.CashReturnPaymentDbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CashReturnPaymentCrude{

    @Autowired
    private CashReturnPaymentDbHandler cashReturnPaymentDbHandler;

    public static Option initOption(CashReturnPayment record) {
        return new Option(record);
    }

    public Mono<CashReturnPayment> create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<CashReturnPayment> createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<CashReturnPayment> savedRecord = cashReturnPaymentDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<CashReturnPayment> updateRecord(Option option) {
        return cashReturnPaymentDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final CashReturnPayment record;
        
        public Option(CashReturnPayment record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }

}