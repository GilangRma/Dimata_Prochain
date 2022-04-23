package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.PosPurchaseOrder;
import com.dimata.demo.app.prochain_app.services.dbHandler.PosPurchaseOrderDbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PosPurchaseOrderCrude {

    @Autowired
    private PosPurchaseOrderDbHandler posPurchaseOrderDbHandler;

    public static Option initOption(PosPurchaseOrder record) {
        return new Option(record);
    }

    public Mono<PosPurchaseOrder> create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<PosPurchaseOrder> createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<PosPurchaseOrder> savedRecord = posPurchaseOrderDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<PosPurchaseOrder> updateRecord(Option option) {
        return posPurchaseOrderDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final PosPurchaseOrder record;

        public Option(PosPurchaseOrder record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
    
}
