package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.PosMaterialDetail;
import com.dimata.demo.app.prochain_app.services.dbHandler.PosMaterialDetailDbhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PosMaterialDetailCrude {
    @Autowired
    private PosMaterialDetailDbhandler posMaterialDetailDbhandler;

    public static Option initOption(PosMaterialDetail record) {
        return new Option(record);
    }

    public Mono<PosMaterialDetail > create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<PosMaterialDetail > createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<PosMaterialDetail > savedRecord = posMaterialDetailDbhandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<PosMaterialDetail > updateRecord(Option option) {
        return posMaterialDetailDbhandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final PosMaterialDetail record;
        
        public Option(PosMaterialDetail record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
}