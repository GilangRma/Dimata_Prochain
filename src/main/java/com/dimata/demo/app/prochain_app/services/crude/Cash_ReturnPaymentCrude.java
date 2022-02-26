package com.dimata.demo.app.prochain_app.services.crude;

import com.dimata.demo.app.prochain_app.models.table.Cash_ReturnPayment;
import com.dimata.demo.app.prochain_app.services.dbHandler.Cash_ReturnPaymentDbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
<<<<<<< Updated upstream:src/main/java/com/dimata/demo/app/prochain_app/services/crude/Cash_ReturnPaymentCrude.java
public class Cash_ReturnPaymentCrude{

=======
public class PosStockOpnameItemCrude {
>>>>>>> Stashed changes:src/main/java/com/dimata/demo/app/prochain_app/services/crude/PosStockOpnameItemCrude.java
    @Autowired
    private Cash_ReturnPaymentDbHandler cash_ReturnPaymentDbHandler;

    public static Option initOption(Cash_ReturnPayment record) {
        return new Option(record);
    }

    public Mono<Cash_ReturnPayment> create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<Cash_ReturnPayment> createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<Cash_ReturnPayment> savedRecord = cash_ReturnPaymentDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<Cash_ReturnPayment> updateRecord(Option option) {
        return cash_ReturnPaymentDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final Cash_ReturnPayment record;
        
        public Option(Cash_ReturnPayment record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }

}