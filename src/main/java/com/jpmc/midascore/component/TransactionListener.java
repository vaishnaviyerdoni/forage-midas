package com.jpmc.midascore.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.jpmc.midascore.foundation.Transaction;

@Component
public class TransactionListener {

    @Value("${general.kafka-topic}")
    private String transaction;

    private TransactiontoDB tDb;

    public TransactionListener(TransactiontoDB tDb){
        this.tDb = tDb;
    }
    
    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas")
    public void myListener(Transaction trans) {
        //String message = 
        tDb.addTransaction(trans);
    }
}
