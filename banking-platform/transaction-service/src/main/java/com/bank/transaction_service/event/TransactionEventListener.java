package com.bank.transaction.event;

import com.bank.common.event.AccountCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventListener {

    @KafkaListener(topics = "account-created", groupId = "transaction-group")
    public void onAccountCreated(AccountCreatedEvent event) {
        System.out.println("Received event from Kafka: " + event);
        // Optionally process new account for audit trail
    }
}
