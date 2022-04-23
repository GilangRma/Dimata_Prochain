package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.PosMaterial;
import com.dimata.demo.app.prochain_app.services.dbHandler.PosMaterialDbhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PosMaterialCrude {
    @Autowired
    private PosMaterialDbhandler posMaterialDbhandler;

    public static Option initOption(PosMaterial record) {
        return new Option(record);
    }

    public Mono<PosMaterial > create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<PosMaterial > createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<PosMaterial > savedRecord = posMaterialDbhandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<PosMaterial > updateRecord(Option option) {
        return posMaterialDbhandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final PosMaterial record;
        
        public Option(PosMaterial record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
}
