package com.jpmc.midascore.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.jpmc.midascore.foundation.Transaction;

@Component
public class TransactionListener {
    
    @Value("${general.kafka-topic}")
    private String transaction;
    
    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas")
    public void myListener(Transaction trans) {
        System.out.println("The received transaction are: " + trans);
    }
}
